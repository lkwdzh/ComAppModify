package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.url.DriverUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.IDCard;
import com.aglook.comapp.view.PatternNum;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class DriverAddActivity extends BaseActivity {


    private TextView right_text;
    private EditText et_name_driver_add;
    private EditText et_phone_driver_add;
    private EditText et_email_driver_add;
    private EditText et_num_driver_add;

    private String driverName;
    private String driverTel;
    private String driverPhone;
    private String carCode;
    private String cardNo;
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_add);
        setTitleBar("添加司机");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);

        et_name_driver_add = (EditText) findViewById(R.id.et_name_driver_add);
        et_phone_driver_add = (EditText) findViewById(R.id.et_phone_driver_add);
        et_email_driver_add = (EditText) findViewById(R.id.et_email_driver_add);
        et_num_driver_add = (EditText) findViewById(R.id.et_num_driver_add);
    }

    public void click() {
        right_text.setOnClickListener(this);
    }

    public void getInput() {
        driverName = AppUtils.toStringTrim_ET(et_name_driver_add);
        driverPhone = AppUtils.toStringTrim_ET(et_phone_driver_add);
        carCode = AppUtils.toStringTrim_ET(et_email_driver_add);
        cardNo = AppUtils.toStringTrim_ET(et_num_driver_add);
        if (driverName == null || "".equals(driverName)) {
            AppUtils.toastText(this, "司机姓名不能为空");
            return;
        }
        if (driverPhone == null || "".equals(driverPhone)) {
            AppUtils.toastText(this, "司机手机号不能为空");
            return;
        }
        if (driverPhone.length() != 11) {
            AppUtils.toastText(this, "请输入11位手机号");
            return;
        }
        if (!PatternNum.isMobileNO(driverPhone)) {
            AppUtils.toastText(this, "请输入正确手机号");
            return;
        }
        if (cardNo == null || "".equals(cardNo)) {
            AppUtils.toastText(this, "司机身份证号不能为空");
            return;
        }
        //判断身份证格式
        String ss = IDCard.IDCardValidate(cardNo);
        if (!"".equals(ss)) {
            AppUtils.toastText(DriverAddActivity.this, ss);
            return;
        }
        if (carCode == null || "".equals(carCode)) {
            AppUtils.toastText(this, "司机车牌号不能为空");
            return;
        }
        if (!PatternNum.isCarnumberNO(carCode)) {
            AppUtils.toastText(this, "请输入正确车牌号");
            return;
        }
        addDriver();
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                //调用增加接口，若成功，则返回
                getInput();

                break;
        }
    }

    //添加司机
    public void addDriver() {
        customProgress = CustomProgress.show(this, "", true);

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_add",arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    //成功
                    DriverAddActivity.this.setResult(1);
                    DriverAddActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePostCheck(DefineUtil.DRIVER_ADD, DriverUrl.postDriverAddUrl(DefineUtil.TOKEN, DefineUtil.USERID, driverName, driverTel, driverPhone, carCode, cardNo), DriverAddActivity.this);
    }


}
