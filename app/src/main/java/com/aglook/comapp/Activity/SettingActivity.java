package com.aglook.comapp.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.util.AppUtils;

public class SettingActivity extends BaseActivity {


    private TextView tv_versionName_setting;
    private TextView tv_help_setting;
    private TextView tv_modify_pwd;
    private TextView tv_sell_setting;
    private RelativeLayout tv_update_setting;
    private Button btn_tuichu;

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        setTitleBar("设置");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();

    }

    public void init(){
        tv_versionName_setting = (TextView) findViewById(R.id.tv_versionName_setting);
        tv_versionName_setting.setText(getAppInfo());

        tv_help_setting = (TextView) findViewById(R.id.tv_help_setting);
        tv_sell_setting = (TextView) findViewById(R.id.tv_sell_setting);
        tv_modify_pwd = (TextView) findViewById(R.id.tv_modify_pwd);
        tv_update_setting = (RelativeLayout) findViewById(R.id.tv_update_setting);
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
    }

    public void click(){
        tv_help_setting.setOnClickListener(this);
        tv_sell_setting.setOnClickListener(this);
        tv_modify_pwd.setOnClickListener(this);
        tv_update_setting.setOnClickListener(this);
        btn_tuichu.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_help_setting:
                intent.setClass(SettingActivity.this,HelpCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sell_setting:
                intent.setClass(SettingActivity.this,ZiXunListActivity.class);
                intent.putExtra("className", "售后服务");
                startActivity(intent);
                break;
            case R.id.tv_modify_pwd:
                intent.setClass(SettingActivity.this,ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_update_setting:
                //版本更新
                AppUtils.toastText(SettingActivity.this,"更新");
                break;
            case R.id.btn_tuichu:
                AppUtils.toastText(SettingActivity.this,"退出");
                break;
        }

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
