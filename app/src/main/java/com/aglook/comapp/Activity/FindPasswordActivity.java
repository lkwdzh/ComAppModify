package com.aglook.comapp.Activity;

import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class FindPasswordActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_find_password);
        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void widgetClick(View view) {

    }


}
