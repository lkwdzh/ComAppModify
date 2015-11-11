package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class BasicInformationActivity extends BaseActivity {

    private TextView right_text;


    @Override
    public void initView() {
        setContentView(R.layout.activity_basic_information);
        setTitleBar("基本信息");
        ExitApplication.getInstance().addActivity(this);
        init();
    }

    //初始化控件
    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("完成");
    }

    @Override
    public void widgetClick(View view) {

    }


}
