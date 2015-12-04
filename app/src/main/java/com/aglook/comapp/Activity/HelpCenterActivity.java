package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;

public class HelpCenterActivity extends BaseActivity {


    private TextView tv_xh_help;
    private TextView tv_ht_help;
    private TextView tv_mm_help;
    private TextView tv_gy_help;

    private String className;
    private String classId;

    @Override
    public void initView() {
        setContentView(R.layout.activity_help_center);
        setTitleBar("帮助中心");
        init();
        click();
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

        intent.setClass(HelpCenterActivity.this, ZiXunListActivity.class);
        intent.putExtra("className", className);
        startActivity(intent);
    }


}
