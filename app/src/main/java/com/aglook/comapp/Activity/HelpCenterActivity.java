package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Information;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class HelpCenterActivity extends BaseActivity {


    private TextView tv_xh_help;
    private TextView tv_ht_help;
    private TextView tv_mm_help;
    private TextView tv_gy_help;

    private String className;
    private String classId;

    private int pageSize = 10;
    private int pageNumber = 1;
    private List<Information> mList = new ArrayList<>();


    @Override
    public void initView() {
        setContentView(R.layout.activity_help_center);
        setTitleBar("帮助中心");
        init();
        click();
        getData();
    }

    public void init() {
        tv_xh_help = (TextView) findViewById(R.id.tv_xh_help);
        tv_ht_help = (TextView) findViewById(R.id.tv_ht_help);
        tv_mm_help = (TextView) findViewById(R.id.tv_mm_help);
        tv_gy_help = (TextView) findViewById(R.id.tv_gy_help);
    }

    public void click() {
        tv_xh_help.setOnClickListener(this);
        tv_ht_help.setOnClickListener(this);
        tv_mm_help.setOnClickListener(this);
        tv_gy_help.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_xh_help:
                className = "仓库现货";
                break;
            case R.id.tv_ht_help:
                className = "远期合同";
                break;
            case R.id.tv_mm_help:
                className = "买卖规则";
                break;
            case R.id.tv_gy_help:
                className = "关于我们";
                break;
        }
        getId(className);
        intent.setClass(HelpCenterActivity.this, ZiXunListActivity.class);
        intent.putExtra("className", className);

        intent.putExtra("classId", classId);
        startActivity(intent);
    }

    //根据className获取classId;
    public void getId(String name) {
        Information information = null;
        for (int i = 0; i < mList.size(); i++) {
            information = mList.get(i);
            if (information.getClassName().equals(name)) {
                classId = information.getClassId();
                Log.d("result_classId", classId);
                return;
            } else {
                classId=null;
            }
        }
    }

    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_help", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<Information> sonList = new ArrayList<Information>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(obj, Information.class);
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                } else {
                    AppUtils.toastText(HelpCenterActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.SETTING_HELP, SettingUrl.postHelpUrl(String.valueOf(pageSize), String.valueOf(pageNumber)), HelpCenterActivity.this);
    }

}
