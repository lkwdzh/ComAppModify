package com.aglook.comapp.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class SettingActivity extends BaseActivity {


    private TextView tv_versionName_setting;
    private TextView tv_help_setting;
    private TextView tv_modify_pwd;
//    private TextView tv_sell_setting;
    private RelativeLayout tv_update_setting;
    private Button btn_tuichu;
    private ComAppApplication comAppApplication;

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
        tv_versionName_setting.setText(getAppInfo());

        tv_help_setting = (TextView) findViewById(R.id.tv_help_setting);
//        tv_sell_setting = (TextView) findViewById(R.id.tv_sell_setting);
        tv_modify_pwd = (TextView) findViewById(R.id.tv_modify_pwd);
        tv_update_setting = (RelativeLayout) findViewById(R.id.tv_update_setting);
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
    }

    public void click(){
        tv_help_setting.setOnClickListener(this);
//        tv_sell_setting.setOnClickListener(this);
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
//            case R.id.tv_sell_setting:
//                intent.setClass(SettingActivity.this,ZiXunListActivity.class);
//
//                intent.putExtra("className", "售后服务");
//                startActivity(intent);
//                break;
            case R.id.tv_modify_pwd:
                intent.setClass(SettingActivity.this,ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_update_setting:
                //版本更新
                AppUtils.toastText(SettingActivity.this,"更新");
                break;
            case R.id.btn_tuichu:
//                AppUtils.toastText(SettingActivity.this,"退出");
                loginOut();
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

    //退出
    public void loginOut(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_login_out",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    //成功退出
                    AppUtils.toastText(SettingActivity.this,"退出成功");
                    comAppApplication.setLogin(null);
                    SettingActivity.this.finish();
                }else {
                    AppUtils.toastText(SettingActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.LOGIN_OUT, SettingUrl.postLogin_out_url(DefineUtil.USERID),SettingActivity.this);
    }

}
