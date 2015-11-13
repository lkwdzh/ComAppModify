package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.LoginPshUser;
import com.aglook.comapp.url.BasicInformationUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.SelectPopupWindow;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class BasicInformationActivity extends BaseActivity {

    private TextView right_text;
    private RelativeLayout rl_right;
    private EditText et_username_basic_info;
    private EditText et_truename_basic_info;
    private TextView tv_seat_basic_info;
    private EditText et_num_basic_info;
    private EditText et_email_basic_info;
    private EditText et_phone_basic_info;
    private EditText et_guding_basic_info;
    private TextView tv_change_basic_info;

    private ComAppApplication comAppApplication;
    private Login login;


    private SelectPopupWindow popupWindow;
    private EditText et_qq_basic_info;

    @Override
    public void initView() {
        setContentView(R.layout.activity_basic_information);
        setTitleBar("基本信息");
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        click();
    }


    private String userTName, userNumber, userPhone, userEmail, userTel, userQq, userMoney, userPoint,
            userAllPoint, username, userAddress, userSeat;

    //初始化控件
    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("完成");
        login = comAppApplication.getLogin();
        rl_right = (RelativeLayout) findViewById(R.id.rl_right);
        et_username_basic_info = (EditText) findViewById(R.id.et_username_basic_info);
        et_truename_basic_info = (EditText) findViewById(R.id.et_truename_basic_info);
        tv_seat_basic_info = (TextView) findViewById(R.id.tv_seat_basic_info);
        et_num_basic_info = (EditText) findViewById(R.id.et_num_basic_info);
        et_email_basic_info = (EditText) findViewById(R.id.et_email_basic_info);
        et_phone_basic_info = (EditText) findViewById(R.id.et_phone_basic_info);
        et_guding_basic_info = (EditText) findViewById(R.id.et_guding_basic_info);
        tv_change_basic_info = (TextView) findViewById(R.id.tv_change_basic_info);
        et_qq_basic_info = (EditText) findViewById(R.id.et_qq_basic_info);
        fillData();
    }


    //填充数据
    public void fillData() {
        if (login != null) {
            LoginPshUser user = login.getPshUser();
            if (user != null) {
                et_username_basic_info.setText(user.getUsername());
                et_truename_basic_info.setText(user.getUserTName());
                tv_seat_basic_info.setText(user.getUserSeat());
                et_num_basic_info.setText(user.getUserNumber());
                et_email_basic_info.setText(user.getUserEmail());
                et_qq_basic_info.setText(user.getUserQq());
                et_guding_basic_info.setText(user.getUserTel());
                et_phone_basic_info.setText(user.getUserPhone());
                userMoney=user.getUserMoney();
                userPoint=user.getUserPoint();
                userAllPoint=user.getUserAllPoint();
                userAddress=user.getUserAddress();
                userSeat=user.getUserSeat();
            }
        }

    }


    public void click() {
        right_text.setOnClickListener(this);
        tv_change_basic_info.setOnClickListener(this);
//        rl_right.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_change_basic_info:
                intent.setClass(BasicInformationActivity.this, ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.right_text:
                getInput();
                break;
        }
    }

    //修改信息
    public void updata() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_basinfo", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    finish();
                }
                AppUtils.toastText(BasicInformationActivity.this,message);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.PERSON_UPDATE, BasicInformationUrl.postBasicInfoUpdateUrl(DefineUtil.USERID, DefineUtil.TOKEN, userTName,userNumber,userPhone,userEmail,userTel,userQq,userMoney,userPoint,userAllPoint,username,userAddress,userSeat), BasicInformationActivity.this);
    }

    //获取输入内容
    public void getInput() {

        userQq = AppUtils.toStringTrim_ET(et_qq_basic_info);
        userTel = AppUtils.toStringTrim_ET(et_guding_basic_info);
        userEmail = AppUtils.toStringTrim_ET(et_email_basic_info);
        userNumber = AppUtils.toStringTrim_ET(et_num_basic_info);
        userPhone = AppUtils.toStringTrim_ET(et_phone_basic_info);
        username = AppUtils.toStringTrim_ET(et_username_basic_info);
        userTName = AppUtils.toStringTrim_ET(et_truename_basic_info);
        AppUtils.toastText(BasicInformationActivity.this,"11111");
        updata();
    }
}
