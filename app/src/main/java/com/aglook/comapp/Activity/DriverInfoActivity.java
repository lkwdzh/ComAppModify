package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class DriverInfoActivity extends BaseActivity {


    private TextView tv_name_driver_info;
    private TextView tv_phone_driver_info;
    private TextView tv_email_driver_info;
    private TextView tv_num_driver_info;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_info);
        setTitleBar("司机资料");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        tv_name_driver_info = (TextView) findViewById(R.id.tv_name_driver_info);
        tv_phone_driver_info = (TextView) findViewById(R.id.tv_phone_driver_info);
        tv_email_driver_info = (TextView) findViewById(R.id.tv_email_driver_info);
        tv_num_driver_info = (TextView) findViewById(R.id.tv_num_driver_info);

    }

    public void click(){

    }
    @Override
    public void widgetClick(View view) {

    }


}
