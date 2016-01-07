package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Information;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
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
    private CustomProgress customProgress;
    private int pageSize = 10;
    private int pageNumber = 1;
    private List<Information> mList = new ArrayList<>();
    private TextView tv_sell_setting;
//    private TextView tv_lc_help;


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
        tv_sell_setting = (TextView) findViewById(R.id.tv_sell_setting);
//        tv_lc_help = (TextView) findViewById(R.id.tv_lc_help);
    }

    public void click() {
        tv_xh_help.setOnClickListener(this);
        tv_ht_help.setOnClickListener(this);
        tv_mm_help.setOnClickListener(this);
        tv_gy_help.setOnClickListener(this);
        tv_sell_setting.setOnClickListener(this);
//        tv_lc_help.setOnClickListener(this);
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
                className = "网站说明";
                break;
            case R.id.tv_sell_setting:
                className="售后服务";
                break;
//            case R.id.tv_lc_help:
//                className="买卖流程";
//                break;
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
//                Log.d("result_classId", classId);
                return;
            } else {
                classId=null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==33&&resultCode==1){
            mList.clear();
            getData();
        }
    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_help", arg0.result);

                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<Information> sonList = new ArrayList<Information>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(obj, Information.class);
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.SETTING_HELP, SettingUrl.postHelpUrl(String.valueOf(pageSize), String.valueOf(pageNumber)), HelpCenterActivity.this);
    }

}
