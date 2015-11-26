package com.aglook.comapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.Fragment.MineFragment;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

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
    private LinearLayout ll_top;
    private int totalNum;

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
        mShare=getSharedPreferences("une_pwd", LoginActivity.this.MODE_PRIVATE);
        mEditor=mShare.edit();
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        spinner.setAdapter(adapter);
        btn_login = (Button) findViewById(R.id.btn_login);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    accountType = "1";
                } else {
                    accountType = "0";
                }
                mEditor.putString("accountType", accountType);
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
        ll_top.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    InputMethodManager imm = (InputMethodManager) LoginActivity.this
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                ll_top.getApplicationWindowToken(), 0);

                    }
                }
                return false;
            }
        });
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
                Log.d("result_login",arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String str = JsonUtils.getJsonParam(arg0.result, "obj");
                login = JsonUtils.parse(str, Login.class);
                if (status.equals("1")) {//登录成功,跳转页面
                    DefineUtil.TOKEN = login.getToken();
                    DefineUtil.USERID=login.getPshUser().getUserId();
                    comAppApplication.setLogin(login);
                    Intent intent = new Intent(LoginActivity.this, MineFragment.class);
                    LoginActivity.this.setResult(1);
                    LoginActivity.this.finish();
                    getCartListData();
                }
                AppUtils.toastText(LoginActivity.this, message);
            }
            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePost(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl(userName, password, accountType), LoginActivity.this);
    }
    //    获取购物车列表
    public void getCartListData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_getCartList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ShoppingCart> list = new ArrayList<ShoppingCart>();
                list = JsonUtils.parseList(obj, ShoppingCart.class);
                if (status.equals("1")) {
                    if (list!=null&&list.size()!=0){
                        for (int i = 0; i < list.size(); i++) {
                            DefineUtil.NUM+=list.get(i).getProductNum();
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePost(DefineUtil.CARTLIST, ShoppingCartUrl.postCartListUrl(DefineUtil.USERID, DefineUtil.TOKEN), LoginActivity.this);
    }

}
