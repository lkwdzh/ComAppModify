package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.view.View;

import com.aglook.comapp.R;

public class PickUpDtailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_pick_up_dtail_activity);
        setTitleBar("提货单");
//        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void widgetClick(View view) {

    }


}
