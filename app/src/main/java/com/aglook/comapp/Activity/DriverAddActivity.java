package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class DriverAddActivity extends BaseActivity {


    private TextView right_text;
    private EditText et_name_driver_add;
    private EditText et_phone_driver_add;
    private EditText et_email_driver_add;
    private EditText et_num_driver_add;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_add);
        setTitleBar("添加司机");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);

        et_name_driver_add = (EditText) findViewById(R.id.et_name_driver_add);
        et_phone_driver_add = (EditText) findViewById(R.id.et_phone_driver_add);
        et_email_driver_add = (EditText) findViewById(R.id.et_email_driver_add);
        et_num_driver_add = (EditText) findViewById(R.id.et_num_driver_add);
    }

    public void click() {

    }


    @Override
    public void widgetClick(View view) {

    }


}
