package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.url.RegisterUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.PatternNum;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class RegisterActivity extends BaseActivity {


    private EditText et_username_register;
    private EditText et_password__register;
    private EditText et_phone__register;
    private EditText et_code_register;
    private TextView tv_code_register;
    private RadioGroup rg_type;
    private RadioButton rb_person;
    private RadioButton rb_company;

    private LinearLayout ll_company;
    private EditText et_company_register;
    private EditText et_company_address_register;

    private CustomProgress customProgress;

    private String userName;
    private String userPw;
    private String userPhone;
    private String authCode;
    private String userType = "2";
    private String userCompany;
    private String userAddres;
    private Button btn_register;
    private String userCode;

    private boolean isAgreeXie=true;
    private boolean isAgreeBan=true;

    private int time = 60;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time == 0) {
                    tv_code_register.setText("验证码");
                    handler.removeMessages(1);
                    time = 60;
                } else {
                    tv_code_register.setText(time + "");
                    time--;
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        }
    };
    private EditText et_tuijian__register;
    private CheckBox cb_xieYi;
    private TextView tv_xieYi;
    private CheckBox cb_banFa;
    private TextView tv_banFa;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
//        ExitApplication.getInstance().addActivity(this);
        setTitleBar("注册");
        init();
        click();
    }

    public void init() {
        et_username_register = (EditText) findViewById(R.id.et_username_register);
        et_password__register = (EditText) findViewById(R.id.et_password__register);
        et_phone__register = (EditText) findViewById(R.id.et_phone__register);
        et_code_register = (EditText) findViewById(R.id.et_code_register);
        tv_code_register = (TextView) findViewById(R.id.tv_code_register);
        rg_type = (RadioGroup) findViewById(R.id.rg_type);
        rb_person = (RadioButton) findViewById(R.id.rb_person);
        rb_company = (RadioButton) findViewById(R.id.rb_company);
        ll_company = (LinearLayout) findViewById(R.id.ll_company);
        et_company_register = (EditText) findViewById(R.id.et_company_register);
        et_company_address_register = (EditText) findViewById(R.id.et_company_address_register);
        btn_register = (Button) findViewById(R.id.btn_register);
        et_tuijian__register = (EditText) findViewById(R.id.et_tuijian__register);
        cb_xieYi = (CheckBox) findViewById(R.id.cb_xieYi);
        tv_xieYi = (TextView) findViewById(R.id.tv_xieYi);
        cb_banFa = (CheckBox) findViewById(R.id.cb_banFa);
        tv_banFa = (TextView) findViewById(R.id.tv_banFa);
    }

    public void click() {
        rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_person) {
                    rb_person.setTextColor(getResources().getColor(R.color.white));
                    rb_company.setTextColor(getResources().getColor(R.color.textcolor_333333));
                    userType = "2";
                    ll_company.setVisibility(View.GONE);
                } else {
                    rb_person.setTextColor(getResources().getColor(R.color.textcolor_333333));
                    rb_company.setTextColor(getResources().getColor(R.color.white));
                    userType = "1";
                    ll_company.setVisibility(View.VISIBLE);
                }
            }
        });
        tv_code_register.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        tv_xieYi.setOnClickListener(this);
        tv_banFa.setOnClickListener(this);
        cb_xieYi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isAgreeXie = true;
                } else {
                    isAgreeXie = false;
                }
            }
        });
        cb_banFa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isAgreeBan = true;
                } else {
                    isAgreeBan = false;
                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_code_register:
                userPhone = AppUtils.toStringTrim_ET(et_phone__register);
                if (userPhone == null || "".equals(userPhone)) {
                    AppUtils.toastText(RegisterActivity.this, "手机号不能为空");
                    return;
                }
                if (userPhone.length() != 11) {
                    AppUtils.toastText(RegisterActivity.this, "请输入正确手机号");
                    return;
                }
                if (!PatternNum.isMobileNO(userPhone)) {
                    AppUtils.toastText(RegisterActivity.this, "请输入正确手机号");
                    return;
                }
                if (time == 60) {
                    getPhoneCode();
                }
                break;
            case R.id.btn_register:
                getInput();
                break;
            case R.id.tv_xieYi:
                intent.setClass(RegisterActivity.this, RegisterAgreementActivity.class);
                intent.putExtra("className","DECX用户注册协议");
                intent.putExtra("url","http://www.decxagri.com/trade/information/detail?articleId=26&type=register");
                startActivity(intent);
                break;
            case R.id.tv_banFa:
                intent.setClass(RegisterActivity.this, RegisterAgreementActivity.class);
                intent.putExtra("className","DECX电子商务网管理办法");
                intent.putExtra("url","http://www.decxagri.com/trade/information/detail?articleId=56&type=register");
                startActivity(intent);
                break;
        }
    }

    //获取输入值
    public void getInput() {
        userName = AppUtils.toStringTrim_ET(et_username_register);
        userPw = AppUtils.toStringTrim_ET(et_password__register);
        userPhone = AppUtils.toStringTrim_ET(et_phone__register);
        authCode = AppUtils.toStringTrim_ET(et_code_register);
        userCompany = AppUtils.toStringTrim_ET(et_company_register);
        userAddres = AppUtils.toStringTrim_ET(et_company_address_register);
        userCode = AppUtils.toStringTrim_ET(et_tuijian__register);
        if (userName == null || "".equals(userName)) {
            AppUtils.toastText(RegisterActivity.this, "请输入用户名");
            return;
        }
        if (userName.length() < 6) {
            AppUtils.toastText(RegisterActivity.this, "用户名请输入6-15位字符");
            return;
        }
        if (userPw == null || "".equals(userPw)) {
            AppUtils.toastText(RegisterActivity.this, "请输入密码");
            return;
        }
        if (userPw.length() < 6) {
            AppUtils.toastText(RegisterActivity.this, "密码请输入6-20位字符");
            return;
        }

        if (userPhone == null || "".equals(userPhone)) {
            AppUtils.toastText(RegisterActivity.this, "请输入手机号");
            return;
        }
        if (userPhone.length() != 11) {
            AppUtils.toastText(RegisterActivity.this, "请输入正确手机号");
            return;
        }
        if (!PatternNum.isMobileNO(userPhone)) {
            AppUtils.toastText(RegisterActivity.this, "请输入正确手机号");
            return;
        }
        if (authCode == null || "".equals(authCode)) {
            AppUtils.toastText(RegisterActivity.this, "请输入验证码");
            return;
        }

        if (userType.equals("1")) {
            //公司
            if (userCompany == null || "".equals(userCompany)) {
                AppUtils.toastText(RegisterActivity.this, "请输入公司名称");
                return;
            }
            if (!userCompany.endsWith("公司")){
                AppUtils.toastText(RegisterActivity.this, "请输入正确的公司名称");
                return;
            }
            if (userAddres == null || "".equals(userAddres)) {
                AppUtils.toastText(RegisterActivity.this, "请输入公司地址");
                return;
            }
        } else if (userType.equals("2")) {
            userCompany = null;
            userAddres = null;
        }

        if (!isAgreeXie) {
            AppUtils.toastText(RegisterActivity.this, "是否同意 \"DECX用户注册协议\"");
            return;
        }
        if (!isAgreeBan) {
            AppUtils.toastText(RegisterActivity.this, "是否同意 \"DECX电子商务网管理办法\"");
            return;
        }
        register();
    }


    //获取验证码
    public void getPhoneCode() {
        customProgress = CustomProgress.show(RegisterActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_getPhoneCode", arg0.result);
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                if (status.equals("1")) {
                    handler.sendEmptyMessage(1);
                } else {
                    AppUtils.toastText(RegisterActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePostCheck(DefineUtil.GETPHONECODE, RegisterUrl.postGetCodeUrl(userPhone), RegisterActivity.this);
    }


    //注册
    public void register() {
        customProgress = CustomProgress.show(RegisterActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_getPhoneCode", arg0.result);
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                if (status.equals("1")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username", userName);
                    intent.putExtra("password", userPw);
                    RegisterActivity.this.setResult(RESULT_OK, intent);
                    RegisterActivity.this.finish();
                } else {
                    AppUtils.toastText(RegisterActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostCheck(DefineUtil.REGISTER, RegisterUrl.postRegisterUrl(userName, userPw, userPhone, authCode, userType, userCompany, userAddres, userCode), RegisterActivity.this);
    }

}
