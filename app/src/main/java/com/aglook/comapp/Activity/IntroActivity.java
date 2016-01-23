package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class IntroActivity extends BaseActivity {


    private TextView tv_login;
    private TextView tv_buy;
    private TextView tv_guadan;
    private TextView tv_rongzi;
    private TextView tv_pickup;


    @Override
    public void initView() {
        setContentView(R.layout.activity_intro);
        ExitApplication.getInstance().addActivity(this);
        setTitleBar("软件操作手册");
        init();
        click();
    }
    public void init(){
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        tv_guadan = (TextView) findViewById(R.id.tv_guadan);
        tv_rongzi = (TextView) findViewById(R.id.tv_rongzi);
        tv_pickup = (TextView) findViewById(R.id.tv_pickup);
    }
    public void click(){
        tv_login.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_guadan.setOnClickListener(this);
        tv_rongzi.setOnClickListener(this);
        tv_pickup.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent(IntroActivity.this, ImageActivity.class);
        switch (view.getId()){
            case R.id.tv_login:
                intent.putExtra("num",1);
                break;
            case R.id.tv_buy:
                intent.putExtra("num",2);
                break;
            case R.id.tv_guadan:
                intent.putExtra("num",3);
                break;
            case R.id.tv_rongzi:
                intent.putExtra("num",4);
                break;
            case R.id.tv_pickup:
                intent.putExtra("num",5);
                break;
        }
        startActivity(intent);
    }


}
