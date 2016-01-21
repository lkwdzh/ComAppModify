package com.aglook.comapp.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.util.SharedPreferencesUtils;

import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends BaseActivity {


    private TextView tv_versionName_setting;
    private TextView tv_modify_pwd;
    private RelativeLayout tv_update_setting;

    private ComAppApplication comAppApplication;
    private CheckBox cb_jpush;


    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        setTitleBar("设置");
        ExitApplication.getInstance().addActivity(this);
        comAppApplication= (ComAppApplication) getApplication();
        init();
        click();
    }

    public void init(){
        tv_versionName_setting = (TextView) findViewById(R.id.tv_versionName_setting);
        tv_versionName_setting.setText("V"+getAppInfo());

        tv_modify_pwd = (TextView) findViewById(R.id.tv_modify_pwd);
        tv_update_setting = (RelativeLayout) findViewById(R.id.tv_update_setting);
        cb_jpush = (CheckBox) findViewById(R.id.cb_jpush);
        cb_jpush.setChecked(SharedPreferencesUtils.getBoolean(SettingActivity.this,"CAN_JPUST",true));
    }

    public void click(){
        tv_modify_pwd.setOnClickListener(this);
        tv_update_setting.setOnClickListener(this);
        cb_jpush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    JPushInterface.stopPush(SettingActivity.this);
                    SharedPreferencesUtils.saveBoolean(SettingActivity.this,"CAN_JPUST",false);
                }else {
                    JPushInterface.resumePush(SettingActivity.this);
                    SharedPreferencesUtils.saveBoolean(SettingActivity.this,"CAN_JPUST",true);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {

        }
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_modify_pwd:
                if (comAppApplication.getLogin()==null||"".equals(comAppApplication.getLogin())){
                    intent.setClass(SettingActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 1);
                }else {
                    intent.setClass(SettingActivity.this, ModifyPasswordActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_update_setting:
                //版本更新
                intent.setClass(SettingActivity.this,VersionActivity.class);
                startActivity(intent);
                break;


        }

    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    //获取版本号与版本名
    public String getAppInfo(){
        String versionName=null;
        int versionCode=0;
        String packageName = this.getPackageName();
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(packageName, 0);
            versionName=packageInfo.versionName;
            versionCode=packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }



}
