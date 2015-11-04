package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.view.View;

import com.aglook.comapp.R;

public class BasicInformationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_basic_information);
        setTitleBar("基本信息");
    }

    @Override
    public void widgetClick(View view) {

    }


}
