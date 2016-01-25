package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.IDCard;
import com.aglook.comapp.view.PatternNum;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CompanyInfoActivity extends BaseActivity {

    @Bind(R.id.right_text)
    TextView rightText;
    @Bind(R.id.et_username_basic_info)
    TextView etUsernameBasicInfo;
    @Bind(R.id.tv_seat_basic_info)
    TextView tvSeatBasicInfo;
    @Bind(R.id.tv_company_basic_info)
    TextView tvCompanyBasicInfo;
    @Bind(R.id.view_company)
    View viewCompany;
    @Bind(R.id.tv_company_address_basic_info)
    EditText tvCompanyAddressBasicInfo;
    @Bind(R.id.tv_trueName)
    TextView tvTrueName;
    @Bind(R.id.et_truename_basic_info)
    EditText etTruenameBasicInfo;
    @Bind(R.id.et_num_basic_info)
    EditText etNumBasicInfo;
    @Bind(R.id.tv_company_ordinaryNum)
    EditText tvCompanyOrdinaryNum;
    @Bind(R.id.tv_company_increNum)
    EditText tvCompanyIncreNum;
    @Bind(R.id.et_managerName)
    EditText etManagerName;
    @Bind(R.id.et_phone_basic_info)
    EditText etPhoneBasicInfo;
    @Bind(R.id.et_guding_basic_info)
    EditText etGudingBasicInfo;
    @Bind(R.id.et_email_basic_info)
    EditText etEmailBasicInfo;
    @Bind(R.id.left_icon)
    ImageView leftIcon;

    private ComAppApplication comAppApplication;
    private Login login;
    private String trueName;
    private String idCard;
    private String ordinaryNum;
    private String increNum;
    private String managerName;
    private String phone;
    private String telephone;
    private String email;
    private CustomProgress customProgress;
    private LoginPshUser user;
    private String companyAddress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_company_info);
        setTitleBar("公司信息");
        ButterKnife.bind(this);
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
        user = login.getPshUser();
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("完成");
        fillData();
        inputType();
        click();
    }


    public void click() {
        rightText.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                getInput();
                break;
        }
    }


    //获取输入值并判断
    public void getInput() {
        trueName = AppUtils.toStringTrim_ET(etTruenameBasicInfo);
        idCard = AppUtils.toStringTrim_ET(etNumBasicInfo);
        ordinaryNum = AppUtils.toStringTrim_ET(tvCompanyOrdinaryNum);
        increNum = AppUtils.toStringTrim_ET(tvCompanyIncreNum);
        managerName = AppUtils.toStringTrim_ET(etManagerName);
        phone = AppUtils.toStringTrim_ET(etPhoneBasicInfo);
        telephone = AppUtils.toStringTrim_ET(etGudingBasicInfo);
        email = AppUtils.toStringTrim_ET(etEmailBasicInfo);
        companyAddress = AppUtils.toStringTrim_ET(tvCompanyAddressBasicInfo);
        if (companyAddress==null||"".equals(companyAddress)){
            AppUtils.toastText(CompanyInfoActivity.this, "公司地址不能为空");
            return;
        }
        if (trueName == null || "".equals(trueName)) {
            AppUtils.toastText(CompanyInfoActivity.this, "法人姓名不能为空");
            return;
        }

        if (idCard == null || "".equals(idCard)) {
            AppUtils.toastText(CompanyInfoActivity.this, "法人身份证号不能为空");
            return;
        }
        //判断身份证格式
        String ss = IDCard.IDCardValidate(idCard);
        if (!"".equals(ss)) {
            AppUtils.toastText(CompanyInfoActivity.this, ss);
            return;
        }

        if (ordinaryNum == null || "".equals(ordinaryNum)) {
            AppUtils.toastText(CompanyInfoActivity.this, "普通票税号不能为空");
            return;
        }

        if (increNum == null || "".equals(increNum)) {
            AppUtils.toastText(CompanyInfoActivity.this, "增值票税号不能为空");
            return;
        }

        if (managerName == null || "".equals(managerName)) {
            AppUtils.toastText(CompanyInfoActivity.this, "经理姓名不能为空");
            return;
        }

        if (phone == null || "".equals(phone)) {
            AppUtils.toastText(CompanyInfoActivity.this, "经理手机号不能为空");
            return;
        }
        if (!PatternNum.isMobileNO(phone)) {
            AppUtils.toastText(CompanyInfoActivity.this, "请输入正确手机号");
            return;
        }

        if (email == null || "".equals(email)) {
            AppUtils.toastText(CompanyInfoActivity.this, "常用邮箱不能为空");
            return;
        }
        //判断邮箱格式
        if (!PatternNum.isEmail(email)) {
            AppUtils.toastText(CompanyInfoActivity.this, "邮箱格式不正确");
            return;
        }
        upData();
    }

    //填充数据
    public void fillData() {
        if (login != null && login.getPshUser() != null) {
            LoginPshUser user = login.getPshUser();
            etUsernameBasicInfo.setText(user.getUsername());
            tvSeatBasicInfo.setText(user.getUserSeat());
            tvCompanyBasicInfo.setText(user.getUserCompany());
            tvCompanyAddressBasicInfo.setText(user.getUserAddres());
            etTruenameBasicInfo.setText(user.getUserTName());
            etNumBasicInfo.setText(user.getUserNumber());
            tvCompanyOrdinaryNum.setText(user.getUserInvoice());
            tvCompanyIncreNum.setText(user.getUserInvoices());
            etManagerName.setText(user.getUserJname());
            etPhoneBasicInfo.setText(user.getUserPhone());
            etGudingBasicInfo.setText(user.getUserTel());
            etEmailBasicInfo.setText(user.getUserEmail());
        }
    }

    //根据数据判断是否可以输入
    public void inputType() {
        if (login != null && !"".equals(login)) {

            if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
//                leftIcon.setVisibility(View.INVISIBLE);
                tvCompanyIncreNum.setFocusable(true);
                tvCompanyIncreNum.setFocusableInTouchMode(true);
                tvCompanyIncreNum.requestFocus();

                tvCompanyOrdinaryNum.setFocusable(true);
                tvCompanyOrdinaryNum.setFocusableInTouchMode(true);
                tvCompanyOrdinaryNum.requestFocus();

                etNumBasicInfo.setFocusable(true);
                etNumBasicInfo.setFocusableInTouchMode(true);
                etNumBasicInfo.requestFocus();

                etTruenameBasicInfo.setFocusable(true);
                etTruenameBasicInfo.setFocusableInTouchMode(true);
                etTruenameBasicInfo.requestFocus();

            } else {

//                leftIcon.setVisibility(View.VISIBLE);
                etTruenameBasicInfo.setFocusable(false);
                etTruenameBasicInfo.setFocusableInTouchMode(false);

                etNumBasicInfo.setFocusable(false);
                etNumBasicInfo.setFocusableInTouchMode(false);

                tvCompanyOrdinaryNum.setFocusable(false);
                tvCompanyOrdinaryNum.setFocusableInTouchMode(false);

                tvCompanyIncreNum.setFocusable(false);
                tvCompanyIncreNum.setFocusableInTouchMode(false);
            }
        }
    }

//    //监听返回键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
//                return true;
//            } else {
//                CompanyInfoActivity.this.finish();
//                return false;
//            }
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }


    //更新数据
    public void upData() {
        customProgress=CustomProgress.show(CompanyInfoActivity.this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
                Log.d("result_company",arg0.result);
                String status= JsonUtils.getJsonParam(arg0.result,"status");
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                if (status.equals("1")){
                    CompanyInfoActivity.this.finish();
                    user.setUserTName(trueName);
                    user.setUserNumber(idCard);
                    user.setUserInvoice(ordinaryNum);
                    user.setUserInvoices(increNum);
                    user.setUserJname(managerName);
                    user.setUserPhone(phone);
                    user.setUserTel(telephone);
                    user.setUserEmail(email);
                    user.setUserAddres(companyAddress);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.PERSON_UPDATE, BasicInformationUrl.postCompanyInfoUpdateUrl(DefineUtil.USERID, DefineUtil.TOKEN,
                trueName, idCard, phone, email, telephone, managerName, ordinaryNum, increNum,companyAddress), CompanyInfoActivity.this);
    }

}
