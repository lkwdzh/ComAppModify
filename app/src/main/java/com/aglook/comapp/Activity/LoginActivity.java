package com.aglook.comapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class LoginActivity extends BaseActivity {


    private String str[] = {"账    号：", "席位号："};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Button btn_login;
    private String accountType = "0";
    private String userName;
    private String password;
    private EditText et_username_login;
    private EditText et_password_login;
    private Login login;
    private ComAppApplication comAppApplication;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        setTitleBar("登录");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        comAppApplication= (ComAppApplication) getApplication();
        mShare=getSharedPreferences("une_pwd",LoginActivity.this.MODE_PRIVATE);
        mEditor=mShare.edit();
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        spinner.setAdapter(adapter);
        btn_login = (Button) findViewById(R.id.btn_login);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    accountType = "0";
                } else {
                    accountType = "1";
                }
                fillData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        et_username_login = (EditText) findViewById(R.id.et_username_login);
        et_password_login = (EditText) findViewById(R.id.et_password_login);
    }

    public void fillData(){
        if (accountType.equals("0")) {
            et_username_login.setText(mShare.getString("userName", ""));
        }else {
            et_username_login.setText(mShare.getString("setName", ""));
        }
        et_password_login.setText(mShare.getString("password",""));

    }

    public void click() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_login:
                userName = AppUtils.toStringTrim_ET(et_username_login);
                password = AppUtils.toStringTrim_ET(et_password_login);
                if (accountType.equals("0")) {
                    mEditor.putString("userName", userName);
                }else {
                    mEditor.putString("setName", userName);
                }
                mEditor.putString("password",password);
                mEditor.commit();
                login();
                break;
        }
    }


    //登录账户
    public void login() {

        new XHttpuTools() {

            @Override
            public void initViews(ResponseInfo<String> arg0) {
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String str = JsonUtils.getJsonParam(arg0.result, "obj");
                login = JsonUtils.parse(str, Login.class);
                if (status.equals("1")) {//登录成功,跳转页面
                    DefineUtil.TOKEN = login.getToken();
                    comAppApplication.setLogin(login);
                }
                AppUtils.toastText(LoginActivity.this, message);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl("nnnnnn", "123456", "1"), LoginActivity.this);


    }


}
