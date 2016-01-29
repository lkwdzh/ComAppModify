package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.url.ModifyPasswordUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class ModifyPasswordActivity extends BaseActivity {


    private TextView right_text;
    private LinearLayout ll_modify;
    private ImageView iv_modify_pwd;
    private Button btn_tijiao;
    private EditText et_name_modify_password;
    private EditText et_phone_modify_password;
    private String pwd;
    private String newPwd;
    private String secPwd;
    private EditText et_email_modify_password;

    @Override
    public void initView() {
        setContentView(R.layout.activity_modify_password);
        setTitleBar("修改密码");
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {

        ll_modify = (LinearLayout) findViewById(R.id.ll_modify);
        iv_modify_pwd = (ImageView) findViewById(R.id.iv_modify_pwd);
        btn_tijiao = (Button) findViewById(R.id.btn_tijiao);
        et_name_modify_password = (EditText) findViewById(R.id.et_name_modify_password);
        et_phone_modify_password = (EditText) findViewById(R.id.et_phone_modify_password);
        et_email_modify_password = (EditText) findViewById(R.id.et_email_modify_password);
    }

    public void click() {
        btn_tijiao.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tijiao:
                pwd = AppUtils.toStringTrim_ET(et_name_modify_password);
                newPwd = AppUtils.toStringTrim_ET(et_phone_modify_password);
                secPwd=AppUtils.toStringTrim_ET(et_email_modify_password);
                if (pwd==null||"".equals(pwd)){
                    AppUtils.toastText(ModifyPasswordActivity.this,"请输入原始密码");
                    return;
                }
                if (pwd.length()<6){
                    AppUtils.toastText(ModifyPasswordActivity.this,"原密码长度不正确");
                    return;
                }
                if (newPwd==null||"".equals(newPwd)){
                    AppUtils.toastText(ModifyPasswordActivity.this,"请输入新密码");
                    return;
                }
                if (newPwd.length()<6){
                    AppUtils.toastText(ModifyPasswordActivity.this,"新密码长度不正确");
                    return;
                }
                if (!newPwd.equals(secPwd)){
                    AppUtils.toastText(ModifyPasswordActivity.this,"两次输入密码不一致");
                    return;
                }
                modifyPwd();
                break;
        }
    }


    //修改密码
    public void modifyPwd() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_modypwd", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                    ll_modify.setVisibility(View.GONE);
                    iv_modify_pwd.setVisibility(View.VISIBLE);
                if (status.equals("1")){
                    iv_modify_pwd.setBackgroundResource(R.drawable.mimaxiugaichenggong);
                }else {
                    iv_modify_pwd.setBackgroundResource(R.drawable.mimaxiugaishibai);
                    AppUtils.toastText(ModifyPasswordActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostCheck(DefineUtil.CHANGE_PWD, ModifyPasswordUrl.postModifypwdUrl(DefineUtil.USERID, DefineUtil.TOKEN, pwd, newPwd), ModifyPasswordActivity.this);
    }

}
