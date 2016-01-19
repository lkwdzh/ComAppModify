package com.aglook.comapp.Activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.LoginPshUser;

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
    TextView tvCompanyAddressBasicInfo;
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
    TextView etManagerName;
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

    @Override
    public void initView() {
        setContentView(R.layout.activity_company_info);
        ButterKnife.bind(this);
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("完成");
        fillData();
        inputType();
    }

    @Override
    public void widgetClick(View view) {

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
            etGudingBasicInfo.setText(user.getUserTels());
            etEmailBasicInfo.setText(user.getUserEmail());
        }
    }

    //根据数据判断是否可以输入
    public void inputType() {
        if (login != null && !"".equals(login)) {

            if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
                leftIcon.setVisibility(View.GONE);
                etTruenameBasicInfo.setFocusable(true);
                etTruenameBasicInfo.setFocusableInTouchMode(true);
                etTruenameBasicInfo.requestFocus();

                etNumBasicInfo.setFocusable(true);
                etNumBasicInfo.setFocusableInTouchMode(true);
                etNumBasicInfo.requestFocus();

                tvCompanyOrdinaryNum.setFocusable(true);
                tvCompanyOrdinaryNum.setFocusableInTouchMode(true);
                tvCompanyOrdinaryNum.requestFocus();

                tvCompanyIncreNum.setFocusable(true);
                tvCompanyIncreNum.setFocusableInTouchMode(true);
                tvCompanyIncreNum.requestFocus();
            } else {

                leftIcon.setVisibility(View.VISIBLE);
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

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if ((login.getPshUser().getUserNumber() == null || "".equals(login.getPshUser().getUserNumber()))) {
                return true;
            } else {
                CompanyInfoActivity.this.finish();
                return false;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
