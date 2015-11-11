package com.aglook.comapp.Activity;

import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class SettingActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        setTitleBar("设置");
        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void widgetClick(View view) {

    }


}
