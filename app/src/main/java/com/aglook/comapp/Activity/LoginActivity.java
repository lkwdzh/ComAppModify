package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
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

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        setTitleBar("登录");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
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
                AppUtils.toastText(LoginActivity.this, accountType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_username_login = (EditText) findViewById(R.id.et_username_login);
        et_password_login = (EditText) findViewById(R.id.et_password_login);
    }

    public void click() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_login:
                userName=AppUtils.toStringTrim_ET(et_username_login);
                password=AppUtils.toStringTrim_ET(et_password_login);
                login();
                break;
        }
    }


    //登录账户
    public void login() {

        new XHttpuTools() {

            @Override
            public void initViews(ResponseInfo<String> arg0) {
                AppUtils.toastText(LoginActivity.this, arg0.result);
                Log.d("result", arg0.result);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                AppUtils.toastText(LoginActivity.this, "1111");
            }
        }.datePost(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl("nnnnnn", "123456", "1"), LoginActivity.this);


    }


}
