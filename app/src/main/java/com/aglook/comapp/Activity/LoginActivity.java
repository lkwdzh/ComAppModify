package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.aglook.comapp.R;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;

public class LoginActivity extends BaseActivity {


    private String str[] = {"账    号：", "席位号："};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Button btn_login;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        setTitleBar("登录");
        init();
        click();
    }

    public void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        spinner.setAdapter(adapter);
        btn_login = (Button) findViewById(R.id.btn_login);

    }

    public void click() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    private String url = "http://192.168.1.113:8080/payment/login-in";

    //登录账户
    public void login() {
        RequestParams params=new RequestParams();
        params.addBodyParameter("account","ffffff");
        params.addBodyParameter("password","111111");
        params.addBodyParameter("accountType","1");

        new XHttpuTools() {

            @Override
            public void initViews(ResponseInfo<String> arg0) {
                AppUtils.toastText(LoginActivity.this,arg0.result);
                Log.d("result",arg0.result);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                AppUtils.toastText(LoginActivity.this,"1111");
            }
        }.datePost(url, LoginUrl.postLonginUrl("ffffff","111111","1"), LoginActivity.this);


    }


}
