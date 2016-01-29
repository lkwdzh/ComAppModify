package com.aglook.comapp.Activity;

import android.view.View;

import com.aglook.comapp.R;

public class PlatformDetailActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_platform_detail);
        setTitleBar("平台仓单");
//        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void widgetClick(View view) {

    }


}
