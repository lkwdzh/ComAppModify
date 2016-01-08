package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AddressListAdapter;
import com.aglook.comapp.bean.Address;
import com.aglook.comapp.url.AddressUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends BaseActivity {

    private final int ADDADDRESS = 1;//添加地址
    private final int MODIFY_ADDRESS=2;//修改地址

    private ListView lv_add_list;

    private TextView right_text;

    private AddressListAdapter adapter;
    private String defaultFlag;//1默认 0 非默认（非必须）
    private View emptyView;
    private CustomProgress customProgress;
    private List<Address> mList = new ArrayList<>();
    private boolean isAdd;

    private int id;//被选中的地址
    private boolean isFromConfirm;//是否从确认页调过来

    private String addressId;//删除的id

    private boolean isOnly;//判断是否是第一个

    private boolean isDelete;//是否删除

    private boolean isModify;//是否是修改

    @Override
    public void initView() {
        setContentView(R.layout.activity_address_list);
        setTitleBar("我的发票地址");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        id = getIntent().getIntExtra("id", -1);
        isFromConfirm = getIntent().getBooleanExtra("isFromConfirm", false);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("新增");
        right_text.setVisibility(View.VISIBLE);
        lv_add_list = (ListView) findViewById(R.id.lv_add_list);
        adapter = new AddressListAdapter(this, mList);
        lv_add_list.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
    }


    public void click() {
        right_text.setOnClickListener(this);
        lv_add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isFromConfirm) {
                    Intent intent = new Intent(AddressListActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("selectAddress", mList.get(position));
                    AddressListActivity.this.setResult(RESULT_OK, intent);
                    AddressListActivity.this.finish();
                }
            }
        });

        lv_add_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                addressId = String.valueOf(mList.get(position).getId());
                if (mList.get(position).isCheck()){
                    AppUtils.toastText(AddressListActivity.this,"当前地址不可删除");
                }else {
                    showDailog();
                }
                return true;
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(AddressListActivity.this, AddAddressActivity.class);
                isAdd = true;
                intent.putExtra("isOnly", isOnly);
                startActivityForResult(intent, ADDADDRESS);
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                //删除
                deleteAddress();
                dialog.dismiss();
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 33 && resultCode == 1) {
            mList.clear();
            getData();
        } else if (requestCode == ADDADDRESS && resultCode == RESULT_OK) {
            getData();
        }else if (requestCode==MODIFY_ADDRESS&&resultCode==RESULT_OK){
            isModify=true;
            getData();
        }
    }

    //遍历list，获取相同id，加上对号
    public void compare() {
        if (id != -1) {
            for (int i = 0; i < mList.size(); i++) {
                if (id == mList.get(i).getId()) {
                    mList.get(i).setCheck(true);
                } else {
                    mList.get(i).setCheck(false);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_addresslist", arg0.result);
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<Address> sonList = new ArrayList<Address>();
                if (status.equals("1")) {
                    if (isAdd) {
                        isAdd = false;
                        mList.clear();
                    }
                    if (isDelete) {
                        isDelete = false;
                        mList.clear();
                    }

                    if (isModify){
                        isModify=false;
                        mList.clear();
                    }
                    sonList = JsonUtils.parseList(obj, Address.class);
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                        compare();
                        isOnly = false;
                    } else {
                        isOnly = true;
                    }

                }

                lv_add_list.setEmptyView(emptyView);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.ADDRESS_LIST, AddressUrl.postAddressListUrl(DefineUtil.USERID, DefineUtil.TOKEN, defaultFlag), AddressListActivity.this);
    }

    //删除
    public void deleteAddress() {
        customProgress = CustomProgress.show(this, "", true);
        isDelete = true;
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_addressdelete", arg0.result);
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    getData();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.ADDRESS_UPDATE, AddressUrl.postDeletaAddressUrl(DefineUtil.USERID, DefineUtil.TOKEN, addressId), AddressListActivity.this);
    }


    private Dialog dialog;
    private TextView tv_delete_order;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) AddressListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认删除?");
        AlertDialog.Builder builder = new AlertDialog.Builder(AddressListActivity.this);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }

    private Button btn_cancel_delete;
    private Button btn_confirm_delete;


}
