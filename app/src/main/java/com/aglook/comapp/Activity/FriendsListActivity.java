package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.FriendsListAdapter;
import com.aglook.comapp.bean.LinkMan;
import com.aglook.comapp.url.FriendsUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends BaseActivity {

    private TextView right_text;
    private PullToRefreshListView lv_buyer_list;
    private TextView tv_num_buyer_list;
    private TextView tv_confirm_buyer_list;
    private boolean isBuyer;
    private FriendsListAdapter adapter;
    private RelativeLayout rl_bottom;
    private List<LinkMan> mList = new ArrayList<>();
    private List<LinkMan> getList = new ArrayList<>();
    private int total = 0;
    private boolean isAdd;
    private String contactId;
    private boolean isDelete;
    private CustomProgress customProgress;
    private View emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_list);
        setTitleBar("联系人列表");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        customProgress = CustomProgress.show(this, "加载中···", true);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_buyer_list = (PullToRefreshListView) findViewById(R.id.lv_buyer_list);
        tv_num_buyer_list = (TextView) findViewById(R.id.tv_num_buyer_list);
        tv_confirm_buyer_list = (TextView) findViewById(R.id.tv_confirm_buyer_list);
        isBuyer = getIntent().getBooleanExtra("buyOrLink", false);
        adapter = new FriendsListAdapter(FriendsListActivity.this, isBuyer, mList, new FriendsListAdapter.CallBackData() {
            @Override
            public void callBack(int num) {
                tv_num_buyer_list.setText(num + "");
            }
        });
        lv_buyer_list.setAdapter(adapter);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        if (isBuyer) {
            rl_bottom.setVisibility(View.VISIBLE);
        } else {
            rl_bottom.setVisibility(View.GONE);
        }

        getList = (List<LinkMan>) getIntent().getSerializableExtra("ToSelect");
    }

    public void click() {
        right_text.setOnClickListener(this);
//        lv_buyer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(FriendsListActivity.this, FriendsDetailActivity.class);
//                startActivity(intent);
//            }
//        });

       lv_buyer_list.getRefreshableView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               contactId=String.valueOf(mList.get(position-1).getUserId());
               showDailog();
               return true;
           }
       });
        tv_confirm_buyer_list.setOnClickListener(this);
    }


    //比较从网络获取的list与接收的list
    public void compareList() {
        if ((getList != null && getList.size() != 0) && (mList != null && mList.size() != 0)) {
            for (int i = 0; i < getList.size(); i++) {
                for (int j = 0; j < mList.size(); j++) {
                    if (getList.get(i).getUserId() == mList.get(j).getUserId()) {
                        mList.get(j).setChecked(getList.get(i).isChecked());
                        total++;
                    }
                }
            }
        }
        tv_num_buyer_list.setText(total + "");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            customProgress = CustomProgress.show(this, "加载中···", true);
            getData();
        }
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(FriendsListActivity.this, FriendsAddActivity.class);
                isAdd=true;
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_confirm_buyer_list:
                //取出所有选中项
                List<LinkMan> setList = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChecked()) {
                        setList.add(mList.get(i));
                    }
                }

                intent.setClass(FriendsListActivity.this, GuaDanAddActivity.class);
                intent.putExtra("setSelected", (Serializable) setList);
                Log.d("11111", setList.size() + "");
                FriendsListActivity.this.setResult(1, intent);
                FriendsListActivity.this.finish();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                dialog.dismiss();
                deleteFriend();
                break;
        }
    }


    //获取数据
    public void getData() {


        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                List<LinkMan> llList = new ArrayList<>();
                Log.d("result_BuyerList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                    llList = JsonUtils.parseList(obj, LinkMan.class);
                    if (llList != null && llList.size() != 0) {
                        if (isAdd){
                            isAdd=false;
                            mList.clear();
                        }
                        if (isDelete){
                            isDelete=false;
                            mList.clear();
                        }
                        mList.addAll(llList);
                    }
                } else {
                    AppUtils.toastText(FriendsListActivity.this, message);
                }

                adapter.notifyDataSetChanged();
                compareList();
                lv_buyer_list.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CONTACT_USER, FriendsUrl.postFriendsListUrl(DefineUtil.USERID, DefineUtil.TOKEN), FriendsListActivity.this);
    }


    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) FriendsListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认删除此订单?");
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendsListActivity.this);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }
    private Dialog dialog;
    private TextView tv_delete_order;
    private Button btn_cancel_delete;
    private Button btn_confirm_delete;


    //删除联系人
    public void deleteFriend(){
        customProgress = CustomProgress.show(this, "删除中···", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
            Log.d("result_delete",arg0.result);
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    //成功
                    isDelete=true;
                    getData();
                }else {
                    AppUtils.toastText(FriendsListActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.DELETE_CONTACT,FriendsUrl.postDeleteUrl(DefineUtil.USERID,DefineUtil.TOKEN,contactId),FriendsListActivity.this);
    }

}
