package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.DriverListAdapter;
import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.url.DriverUrl;
import com.aglook.comapp.url.PickUpUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DriverListActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_driver_list;
    private DriverListAdapter adapter;
    private boolean canCheck;
    private RelativeLayout rl_bottom;
    private TextView tv_confirm_driver_list;
    private List<DriverList> mList = new ArrayList<>();
    private List<DriverList> getList = new ArrayList<>();
    private TextView tv_num_driver_list;
    private int total = 0;
    private boolean isModify;
    private boolean isAdd;
    private boolean isUpDate;
    private CustomProgress customProgress;
    private View emptyView;
    private String code="3005";
    private String getListDriverId;
    private String getId;
    private String driverId;
    private String name;
    private CheckBox cb_driver_list;
    private LinearLayout ll_cb;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_list);
        setTitleBar("司机列表");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "加载中···", true);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        getListDriverId=getIntent().getStringExtra("getListDriverId");
        getId=getIntent().getStringExtra("getId");
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_driver_list = (PullToRefreshListView) findViewById(R.id.lv_driver_list);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tv_num_driver_list = (TextView) findViewById(R.id.tv_num_driver_list);
        ll_cb = (LinearLayout) findViewById(R.id.ll_cb);
        canCheck = getIntent().getBooleanExtra("canCheck", false);
        isModify = getIntent().getBooleanExtra("isModify", false);
        adapter = new DriverListAdapter(DriverListActivity.this, mList, canCheck, new DriverListAdapter.CallBackData() {
            @Override
            public void callBack(int num) {
                tv_num_driver_list.setText(num + "");
            }
        });
        lv_driver_list.setAdapter(adapter);
        if (canCheck) {
            rl_bottom.setVisibility(View.VISIBLE);
        } else {
            rl_bottom.setVisibility(View.GONE);
        }
        getList = (List<DriverList>) getIntent().getSerializableExtra("ToSelect");
        adapter.notifyDataSetChanged();
        tv_confirm_driver_list = (TextView) findViewById(R.id.tv_confirm_driver_list);
        cb_driver_list = (CheckBox) findViewById(R.id.cb_driver_list);
    }

    //比较从网络获取的list与getIntent的list

    public void compareList() {
        if ((getList != null && getList.size() != 0) && (mList != null && mList.size() != 0)) {
            Log.d("result_driver_getList", getList+"_____"+mList);
            for (int i = 0; i < getList.size(); i++) {
                for (int j = 0; j < mList.size(); j++) {
                    if (getList.get(i).getId() == mList.get(j).getId()) {
                        mList.get(j).setChecked(getList.get(i).isChecked());
                        total++;
                    }
                }
            }
        }
        tv_num_driver_list.setText(total + "");
        adapter.notifyDataSetChanged();
    }

    public void click() {
        right_text.setOnClickListener(this);
        lv_driver_list.setMode(PullToRefreshBase.Mode.DISABLED);
        lv_driver_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //如果是从修改页面跳转的，点击则返回并带回数据，否则，跳转到详情
                if (isModify) {
                    driverId = mList.get(position - 1).getId() + "";
                    name = mList.get(position - 1).getUserName();
                    modifyDriver();

                } else {

                    intent.setClass(DriverListActivity.this, DriverInfoActivity.class);
                    intent.putExtra("driver", mList.get(position - 1));
                    startActivityForResult(intent, 2);
                }
            }
        });
        tv_confirm_driver_list.setOnClickListener(this);
        ll_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cb_driver_list.isChecked()) {
                    cb_driver_list.setChecked(true);
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                    }
                    tv_num_driver_list.setText(mList.size() + "");
                } else {
                    cb_driver_list.setChecked(false);
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                    tv_num_driver_list.setText("0");
                }
                adapter.notifyDataSetChanged();
            }
        });
    }



    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(DriverListActivity.this, DriverAddActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_confirm_driver_list:
                intent.setClass(DriverListActivity.this, PickInfoActivity.class);
                //取出所有选中的项
                List<DriverList> setList = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChecked()) {
                        setList.add(mList.get(i));
                    }
                }
                Log.d("result_isCheck3","_______"+setList);
                intent.putExtra("Selected", (Serializable) setList);
                DriverListActivity.this.setResult(1, intent);
                DriverListActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            isAdd=true;
            getData();
        }else if (requestCode==2&&resultCode==RESULT_OK){
            isUpDate=true;
            getData();
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
                Log.d("result_driver", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<DriverList> sonListt = new ArrayList<DriverList>();
                if (status.equals("1")) {
                    sonListt = JsonUtils.parseList(obj, DriverList.class);
                    if (isAdd){
                        isAdd=false;
                        mList.clear();
                    }

                    if (isUpDate){
                        isUpDate=false;
                        mList.clear();
                    }
                    if (sonListt != null && sonListt.size() != 0) {

                        mList.addAll(sonListt);
                        compareList();
                    }
                } else {
                    AppUtils.toastText(DriverListActivity.this, message);
                }
                adapter.notifyDataSetChanged();
                lv_driver_list.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.DRIVER_LIST, DriverUrl.postDriverListUrl(DefineUtil.TOKEN, DefineUtil.USERID), DriverListActivity.this);



    }


    //修改司机信息
    public void modifyDriver(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_modify——getId,driverId,getListDriverId",getId+"_____"+driverId+"_____"+getListDriverId+"_____"+arg0.result);
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    Intent intent = new Intent();
                    intent.setClass(DriverListActivity.this, ModifyPickUpActivity.class);
                    intent.putExtra("name", name);
                    DriverListActivity.this.setResult(RESULT_OK, intent);
                    DriverListActivity.this.finish();
                    AppUtils.toastText(DriverListActivity.this,"修改成功");
                }else {
                    AppUtils.toastText(DriverListActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, PickUpUrl.postModifyDriverUrl(code,DefineUtil.TOKEN,DefineUtil.USERID,getId,driverId,getListDriverId),DriverListActivity.this);
    }

}
