package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.url.DriverUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.IDCard;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class DriverInfoActivity extends BaseActivity {


    private EditText tv_name_driver_info;
    private EditText tv_phone_driver_info;
    private EditText tv_email_driver_info;
    private EditText tv_num_driver_info;
    private ImageView left_icon;
    private TextView title;
    private CheckBox cb_driver_info;
    private DriverList driver;


    private String driverName;
    private String driverTel;
    private String driverPhone;
    private String carCode;
    private String cardNo;
    private String driverId;
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_info);
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        driver = (DriverList) getIntent().getSerializableExtra("driver");
        left_icon = (ImageView) findViewById(R.id.left_icon);
        title = (TextView) findViewById(R.id.title);
        title.setText("修改司机信息");
        cb_driver_info = (CheckBox) findViewById(R.id.cb_driver_info);
        tv_name_driver_info = (EditText) findViewById(R.id.tv_name_driver_info);
        tv_phone_driver_info = (EditText) findViewById(R.id.tv_phone_driver_info);
        tv_email_driver_info = (EditText) findViewById(R.id.tv_email_driver_info);
        tv_num_driver_info = (EditText) findViewById(R.id.tv_num_driver_info);
        fillData();
    }

    public void fillData() {
        //刚开始都不可输入，当点击修改之后就可以了
        tv_name_driver_info.setFocusable(false);
        tv_name_driver_info.setFocusableInTouchMode(false);
        tv_phone_driver_info.setFocusable(false);
        tv_phone_driver_info.setFocusableInTouchMode(false);
        tv_email_driver_info.setFocusable(false);
        tv_email_driver_info.setFocusableInTouchMode(false);
        tv_num_driver_info.setFocusable(false);
        tv_num_driver_info.setFocusableInTouchMode(false);
        if (driver != null && !"".equals(driver)) {
            tv_name_driver_info.setText(driver.getUserName());
            tv_phone_driver_info.setText(driver.getPhone());
            tv_num_driver_info.setText(driver.getCardNo());
            tv_email_driver_info.setText(driver.getCarCode());
        }
    }

    public void click() {
        left_icon.setOnClickListener(this);
        cb_driver_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_driver_info.setChecked(true);
                    cb_driver_info.setText("完成");
                    //可以输入
                    tv_name_driver_info.setFocusable(true);
                    tv_name_driver_info.setFocusableInTouchMode(true);
                    tv_name_driver_info.requestFocus();
                    tv_phone_driver_info.setFocusable(true);
                    tv_phone_driver_info.setFocusableInTouchMode(true);
                    tv_phone_driver_info.requestFocus();
                    tv_email_driver_info.setFocusable(true);
                    tv_email_driver_info.setFocusableInTouchMode(true);
                    tv_email_driver_info.requestFocus();
                    tv_num_driver_info.setFocusable(true);
                    tv_num_driver_info.setFocusableInTouchMode(true);
                    tv_num_driver_info.requestFocus();
                } else {
                   getInput();

                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                DriverInfoActivity.this.finish();
                break;
        }
    }

    //获取输入信息
    public void getInput(){
        driverId=driver.getId()+"";
        driverName= AppUtils.toStringTrim_ET(tv_name_driver_info);
        driverPhone=AppUtils.toStringTrim_ET(tv_phone_driver_info);
        carCode=AppUtils.toStringTrim_ET(tv_email_driver_info);
        cardNo=AppUtils.toStringTrim_ET(tv_num_driver_info);
        if (driverName==null||"".equals(driverName)){
            AppUtils.toastText(DriverInfoActivity.this,"司机姓名不能为空");
            return;
        }
        if (driverPhone==null||"".equals(driverPhone)){
            AppUtils.toastText(DriverInfoActivity.this,"司机手机号不能为空");
            return;
        }
        if (driverPhone.length()!=11){
            AppUtils.toastText(this,"请输入11位手机号");
            return;
        }
        if (cardNo==null||"".equals(cardNo)){
            AppUtils.toastText(this,"司机身份证号不能为空");
            return;
        }
        //判断身份证格式
        String ss= IDCard.IDCardValidate(cardNo);
        if (!"".equals(ss)){
            AppUtils.toastText(DriverInfoActivity.this,ss);
            return;
        }
        if (carCode==null||"".equals(carCode)){
            AppUtils.toastText(DriverInfoActivity.this,"司机车牌号不能为空");
            return;
        }
        upData();
    }

    //更新司机信息
    public void upData() {
        customProgress = CustomProgress.show(this, "提交中···", true);

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_update",driverId+"_____"+arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status.equals("1")){
                    cb_driver_info.setChecked(false);
                    cb_driver_info.setText("修改");
                    DriverInfoActivity.this.setResult(RESULT_OK);
                    DriverInfoActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.DRIVER_UPDATE, DriverUrl.postDriverUpdateUrl(DefineUtil.TOKEN,DefineUtil.USERID,driverId,driverName,driverTel,driverPhone,carCode,cardNo),DriverInfoActivity.this);
    }


}
