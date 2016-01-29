package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.url.FindPasswordUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.PatternNum;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindPasswordActivity extends BaseActivity {


    @Bind(R.id.et_username_findPwd)
    EditText etUsernameFindPwd;
    @Bind(R.id.et_phone__findPwd)
    EditText etPhoneFindPwd;
    @Bind(R.id.et_code_findPwd)
    EditText etCodeFindPwd;
    @Bind(R.id.tv_code_register)
    TextView tvCodeRegister;
    @Bind(R.id.et_password__findPwd)
    EditText etPasswordFindPwd;
    @Bind(R.id.et_confirm__findPwd)
    EditText etConfirmFindPwd;
    @Bind(R.id.btn_findPwd)
    Button btnFindPwd;
    private String userName;
    private String phone;
    private String phoneCode;
    private String password;
    private String confirmPassword;

    private CustomProgress customProgress;


    private int time = 60;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time == 0) {
                    tvCodeRegister.setText("验证码");
                    handler.removeMessages(1);
                    time = 60;
                } else {
                    tvCodeRegister.setText(time + "");
                    time--;
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        }
    };

    @Override
    public void initView() {
//        ExitApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_find_password);
        setTitleBar("找回密码");
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        click();
    }

    public void click() {
        tvCodeRegister.setOnClickListener(this);
        btnFindPwd.setOnClickListener(this);
    }

    //获取输入值
    public void getInput() {
        userName = AppUtils.toStringTrim_ET(etUsernameFindPwd);
        phone = AppUtils.toStringTrim_ET(etPhoneFindPwd);
        phoneCode = AppUtils.toStringTrim_ET(etCodeFindPwd);
        password = AppUtils.toStringTrim_ET(etPasswordFindPwd);
        confirmPassword = AppUtils.toStringTrim_ET(etConfirmFindPwd);
        if (userName == null || "".equals(userName)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入用户名");
            return;
        }
        if (userName.length() < 6) {
            AppUtils.toastText(FindPasswordActivity.this, "用户名请输入6-15位字符");
            return;
        }

        if (phone == null || "".equals(phone)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入手机号");
            return;
        }
        if (phone.length() != 11) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入正确手机号");
            return;
        }
        if (!PatternNum.isMobileNO(phone)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入正确手机号");
            return;
        }
        if (phoneCode == null || "".equals(phoneCode)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入验证码");
            return;
        }

        if (password == null || "".equals(password)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入新密码");
            return;
        }
        if (password.length() < 6) {
            AppUtils.toastText(FindPasswordActivity.this, "新密码请输入6-20位字符");
            return;
        }
        if (confirmPassword == null || "".equals(confirmPassword)) {
            AppUtils.toastText(FindPasswordActivity.this, "请输入确认密码");
            return;
        }
        if (!confirmPassword.equals(password)) {
            AppUtils.toastText(FindPasswordActivity.this, "两次输入密码不一致");
            return;
        }

        getData();
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code_register:
                phone = AppUtils.toStringTrim_ET(etPhoneFindPwd);
                if (phone == null || "".equals(phone)) {
                    AppUtils.toastText(FindPasswordActivity.this, "手机号不能为空");
                    return;
                }
                if (phone.length() != 11) {
                    AppUtils.toastText(FindPasswordActivity.this, "请输入正确手机号");
                    return;
                }
                if (!PatternNum.isMobileNO(phone)) {
                    AppUtils.toastText(FindPasswordActivity.this, "请输入正确手机号");
                    return;
                }
                if (time == 60) {
                    getPhoneCode();
                }
                break;
            case R.id.btn_findPwd:
                getInput();
                break;
        }
    }

    //获取验证码
    public void getPhoneCode() {
        customProgress = CustomProgress.show(FindPasswordActivity.this, "", true);
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
                }
//                else {
//                    AppUtils.toastText(FindPasswordActivity.this, message);
//                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePostCheck(DefineUtil.FIND_PWD_CODE, FindPasswordUrl.postFindPwdCodeUrl(phone), FindPasswordActivity.this);
    }


    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(FindPasswordActivity.this, "", true);
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
                    Intent intent = new Intent(FindPasswordActivity.this, LoginActivity.class);
                    intent.putExtra("password",password);
                    FindPasswordActivity.this.setResult(RESULT_OK,intent);
                    FindPasswordActivity.this.finish();
                }
//                else {
//                    AppUtils.toastText(FindPasswordActivity.this, message);
//                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.FIND_PASSWORD, FindPasswordUrl.postFindPasswordUrl(phone, userName, password, phoneCode), FindPasswordActivity.this);
    }
}
