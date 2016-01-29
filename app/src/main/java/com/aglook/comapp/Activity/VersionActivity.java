package com.aglook.comapp.Activity;

import android.view.View;

import com.aglook.comapp.R;

public class VersionActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_version);
//        ExitApplication.getInstance().addActivity(this);
        setTitleBar("版本信息");
    }

    @Override
    public void widgetClick(View view) {

    }


}
