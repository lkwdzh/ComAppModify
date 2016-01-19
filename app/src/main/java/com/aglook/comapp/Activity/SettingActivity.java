package com.aglook.comapp.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class SettingActivity extends BaseActivity {


    private TextView tv_versionName_setting;
    private TextView tv_modify_pwd;
    private RelativeLayout tv_update_setting;
    private Button btn_tuichu;
    private ComAppApplication comAppApplication;
    private CustomProgress customProgress;
    private TextView tv_intro;
    private TextView tv_share;

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
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        if (comAppApplication.getLogin()==null){
            btn_tuichu.setVisibility(View.INVISIBLE);
        }else {
            btn_tuichu.setVisibility(View.VISIBLE);
        }
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_share = (TextView) findViewById(R.id.tv_share);
    }

    public void click(){
        tv_modify_pwd.setOnClickListener(this);
        tv_update_setting.setOnClickListener(this);
        btn_tuichu.setOnClickListener(this);
        tv_intro.setOnClickListener(this);
        tv_share.setOnClickListener(this);
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
            case R.id.btn_tuichu:
                loginOut();
                break;
            case R.id.tv_intro:
                intent.setClass(SettingActivity.this,IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_share:
                //分享
//                ShareUtil.Share(SettingActivity.this);
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

    //退出
    public void loginOut(){
        customProgress=CustomProgress.show(this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
//                Log.d("result_login_out",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    //成功退出
                    comAppApplication.setLogin(null);
                    SettingActivity.this.finish();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
            }
        }.datePostUp(DefineUtil.LOGIN_OUT, SettingUrl.postLogin_out_url(DefineUtil.USERID),SettingActivity.this);
    }

}
