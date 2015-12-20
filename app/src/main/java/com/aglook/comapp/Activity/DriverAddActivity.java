package com.aglook.comapp.Activity;

import android.util.Log;
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

    public void getInput(){
        driverName=AppUtils.toStringTrim_ET(et_name_driver_add);
        driverPhone=AppUtils.toStringTrim_ET(et_phone_driver_add);
        carCode=AppUtils.toStringTrim_ET(et_email_driver_add);
        cardNo=AppUtils.toStringTrim_ET(et_num_driver_add);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                //调用增加接口，若成功，则返回
                addDriver();

                break;
        }
    }

    //添加司机
    public void addDriver(){
        customProgress = CustomProgress.show(this, "提交中···", true);
        getInput();
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_add",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
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
        }.datePostCheck(DefineUtil.DRIVER_ADD, DriverUrl.postDriverAddUrl(DefineUtil.TOKEN,DefineUtil.USERID,driverName,driverTel,driverPhone,carCode,cardNo),DriverAddActivity.this);
    }


}
