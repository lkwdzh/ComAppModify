package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
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
import com.aglook.comapp.view.IDCard;
import com.aglook.comapp.view.PatternNum;
import com.aglook.comapp.view.SelectPopupWindow;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class BasicInformationActivity extends BaseActivity {

    private TextView right_text;
    //    private RelativeLayout rl_right;
    private TextView et_username_basic_info;
    private EditText et_truename_basic_info;
    private TextView tv_seat_basic_info;
    private EditText et_num_basic_info;
    private EditText et_email_basic_info;
    private EditText et_phone_basic_info;
    private EditText et_guding_basic_info;

    private ComAppApplication comAppApplication;
    private Login login;


    private SelectPopupWindow popupWindow;
    private EditText et_qq_basic_info;
    private TextView tv_trueName;


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

        et_username_basic_info = (TextView) findViewById(R.id.et_username_basic_info);
        et_truename_basic_info = (EditText) findViewById(R.id.et_truename_basic_info);
        tv_seat_basic_info = (TextView) findViewById(R.id.tv_seat_basic_info);
        et_num_basic_info = (EditText) findViewById(R.id.et_num_basic_info);
        et_email_basic_info = (EditText) findViewById(R.id.et_email_basic_info);
        et_phone_basic_info = (EditText) findViewById(R.id.et_phone_basic_info);
        et_guding_basic_info = (EditText) findViewById(R.id.et_guding_basic_info);
        et_qq_basic_info = (EditText) findViewById(R.id.et_qq_basic_info);
        tv_trueName = (TextView) findViewById(R.id.tv_trueName);
        if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
            //有一个为空


            et_num_basic_info.setFocusable(true);
            et_num_basic_info.setFocusableInTouchMode(true);
            et_num_basic_info.requestFocus();

            et_truename_basic_info.setFocusable(true);
            et_truename_basic_info.setFocusableInTouchMode(true);
            et_truename_basic_info.requestFocus();

        } else {
            et_truename_basic_info.setFocusable(false);
            et_truename_basic_info.setFocusableInTouchMode(false);
//            et_email_basic_info.setFocusable(false);
//            et_email_basic_info.setFocusableInTouchMode(false);
            et_num_basic_info.setFocusable(false);
            et_num_basic_info.setFocusableInTouchMode(false);

            et_truename_basic_info.setText(login.getPshUser().getUserTName());
            et_num_basic_info.setText(login.getPshUser().getUserId());
            et_email_basic_info.setText(login.getPshUser().getUserEmail());
        }

        fillData();
    }

//    //监听返回键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
//                return true;
//            } else {
//                BasicInformationActivity.this.finish();
//                return false;
//            }
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }

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
                userMoney = user.getUserMoney();
                userPoint = user.getUserPoint();
                userAllPoint = user.getUserAllPoint();
                userAddress = user.getUserAddress();
                userSeat = user.getUserSeat();

            }
        }

    }


    public void click() {
        right_text.setOnClickListener(this);
//        rl_right.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
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
//                Log.d("result_basinfo", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    finish();
                    LoginPshUser pshUser = login.getPshUser();
                    if (userEmail != null && !"".equals(userEmail)) {
                        pshUser.setUserEmail(userEmail);
                    }
                    if (userNumber != null && !"".equals(userNumber)) {
                        pshUser.setUserNumber(userNumber);
                    }
                    if (userPhone != null && !"".equals(userPhone)) {
                        pshUser.setUserPhone(userPhone);
                    }
                    if (username != null && !"".equals(username)) {
                        pshUser.setUsername(username);
                    }
                    if (userTName != null && !"".equals(userTName)) {
                        pshUser.setUserTName(userTName);
                    }


                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostUp(DefineUtil.PERSON_UPDATE, BasicInformationUrl.postBasicInfoUpdateUrl(DefineUtil.USERID, DefineUtil.TOKEN, userTName, userNumber, userPhone, userEmail), BasicInformationActivity.this);
    }

    //获取输入内容
    public void getInput() {

        userQq = AppUtils.toStringTrim_ET(et_qq_basic_info);
        userTel = AppUtils.toStringTrim_ET(et_guding_basic_info);
        userEmail = AppUtils.toStringTrim_ET(et_email_basic_info);
        userNumber = AppUtils.toStringTrim_ET(et_num_basic_info);
        userPhone = AppUtils.toStringTrim_ET(et_phone_basic_info);
        username = AppUtils.toStringTrim_TV(et_username_basic_info);
        userTName = AppUtils.toStringTrim_ET(et_truename_basic_info);
        if (userTName == null || "".equals(userTName)) {
            AppUtils.toastText(BasicInformationActivity.this, "真实姓名不能为空");
            return;
        }
        if (userNumber == null || "".equals(userNumber)) {
            AppUtils.toastText(BasicInformationActivity.this, "身份证号不能为空");
            return;
        }
        //判断身份证格式
        String ss = IDCard.IDCardValidate(userNumber);
        if (!"".equals(ss)) {
            AppUtils.toastText(BasicInformationActivity.this, ss);
            return;
        }
        if (userEmail == null || "".equals(userEmail)) {
            AppUtils.toastText(BasicInformationActivity.this, "邮箱不能为空");
            return;
        }
        //判断邮箱格式
        if (!PatternNum.isEmail(userEmail)) {
            AppUtils.toastText(BasicInformationActivity.this, "邮箱格式不正确");
            return;
        }
        if (userPhone == null || "".equals(userPhone)) {
            AppUtils.toastText(BasicInformationActivity.this, "手机号不能为空");
            return;
        }
        if (userPhone.length() != 11) {
            AppUtils.toastText(BasicInformationActivity.this, "请输入11位手机号");
            return;
        }
        updata();
    }


}
