package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;

public class PersonInformationActivity extends BaseActivity {


    private TextView tv_basic_infor_peson_info;
    private TextView tv_bang_card_person_info;
    private TextView tv_linkman_person_info;
    private TextView tv_driver_person_info;
    private TextView tv_friend_person_info;
    private Login login;
    private TextView tv_name_person_info;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person_information);
        setTitleBar("个人信息");
        comAppApplication= (ComAppApplication) getApplication();
        ExitApplication.getInstance().addActivity(this);
        init();
        fillData();
        click();
    }

    public void init(){
        login= comAppApplication.getLogin();
        tv_basic_infor_peson_info = (TextView) findViewById(R.id.tv_basic_infor_peson_info);
        tv_bang_card_person_info = (TextView) findViewById(R.id.tv_bang_card_person_info);
        tv_linkman_person_info = (TextView) findViewById(R.id.tv_linkman_person_info);
        tv_driver_person_info = (TextView) findViewById(R.id.tv_driver_person_info);
        tv_friend_person_info = (TextView) findViewById(R.id.tv_friend_person_info);
        tv_name_person_info = (TextView) findViewById(R.id.tv_name_person_info);
    }
    public void click(){
        tv_basic_infor_peson_info.setOnClickListener(this);
        tv_bang_card_person_info.setOnClickListener(this);
        tv_linkman_person_info.setOnClickListener(this);
        tv_driver_person_info.setOnClickListener(this);
        tv_friend_person_info.setOnClickListener(this);
    }

    public void fillData(){
        if (login!=null){
            tv_name_person_info.setText(login.getPshUser().getUsername());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        login=comAppApplication.getLogin();
        fillData();
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_basic_infor_peson_info:
                intent.setClass(PersonInformationActivity.this,BasicInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bang_card_person_info:
                intent.setClass(PersonInformationActivity.this,CardListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_driver_person_info:
                intent.setClass(PersonInformationActivity.this,DriverListActivity.class);
                intent.putExtra("canCheck",false);
                startActivity(intent);
                break;
            case R.id.tv_linkman_person_info:
                intent.setClass(PersonInformationActivity.this,FriendsListActivity.class);
                intent.putExtra("buyOrLink",false);
                startActivity(intent);
                break;
            case R.id.tv_friend_person_info:
                intent.setClass(PersonInformationActivity.this,GoodsCollectActivity.class);
                startActivity(intent);
        }
    }


}
