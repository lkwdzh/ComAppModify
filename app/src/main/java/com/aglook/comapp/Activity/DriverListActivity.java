package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.DriverListAdapter;
import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.url.DriverUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
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
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_driver_list = (PullToRefreshListView) findViewById(R.id.lv_driver_list);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tv_num_driver_list = (TextView) findViewById(R.id.tv_num_driver_list);
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
//        for (int i = 0; i < 10; i++) {
//            mList.get(i).setId(i);
//        }
        tv_confirm_driver_list = (TextView) findViewById(R.id.tv_confirm_driver_list);
    }

    //比较从网络获取的list与getIntent的list

    public void compareList() {
        if ((getList != null && getList.size() != 0) && (mList != null && mList.size() != 0)) {
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
    }

    public void click() {
        right_text.setOnClickListener(this);
        lv_driver_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //如果是从修改页面跳转的，点击则返回并带回数据，否则，跳转到详情
                if (isModify) {
                    intent.setClass(DriverListActivity.this, ModifyPickUpActivity.class);
                    intent.putExtra("driver", mList.get(position - 1));
                    AppUtils.toastText(DriverListActivity.this, position - 1 + "");
                    DriverListActivity.this.setResult(RESULT_OK, intent);
                    DriverListActivity.this.finish();
                } else {
                    intent.setClass(DriverListActivity.this, DriverInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
        tv_confirm_driver_list.setOnClickListener(this);
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
        }
    }

    //获取数据
    public void getData() {
//        List<DriverList> sonList = new ArrayList<>();
//        DriverList driver = null;
//        for (int i = 0; i < 30; i++) {
//            driver = new DriverList();
//            driver.setName("王大锤" + i + "号");
////            driver.setId(i);
//            sonList.add(driver);
//        }
//
//        mList.addAll(sonList);

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_driver", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<DriverList> sonListt = new ArrayList<DriverList>();
                if (status.equals("1")) {
                    sonListt = JsonUtils.parseList(obj, DriverList.class);
                    if (sonListt != null && sonListt.size() != 0) {
                        if (isAdd){
                            isAdd=false;
                            mList.clear();
                        }
                        mList.addAll(sonListt);
                    }
                } else {
                    AppUtils.toastText(DriverListActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.DRIVER_LIST, DriverUrl.postDriverListUrl(DefineUtil.TOKEN, DefineUtil.USERID), DriverListActivity.this);

        compareList();
        adapter.notifyDataSetChanged();
    }

}
