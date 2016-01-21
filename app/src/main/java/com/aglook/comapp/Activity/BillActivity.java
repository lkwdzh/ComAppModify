package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.LoginPshUser;
import com.aglook.comapp.util.AppUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillActivity extends BaseActivity {

    @Bind(R.id.right_text)
    TextView rightText;
    @Bind(R.id.et_cmompanyName_bill)
    EditText etCmompanyNameBill;
    @Bind(R.id.tv_num_bill)
    EditText tvNumBill;
    @Bind(R.id.tv_companyAddres_bill)
    EditText tvCompanyAddresBill;
    @Bind(R.id.tv_phone_bill)
    EditText tvPhoneBill;
    @Bind(R.id.et_bank_bill)
    EditText etBankBill;
    @Bind(R.id.et_bankNum_bill)
    EditText etBankNumBill;
    private String conpanyName;
    private String numBill;
    private String companyAddress;
    private String phone;
    private String bank;
    private String bankNum;

    private ComAppApplication comAppApplication;
    private Login login;

    @Override
    public void initView() {
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        setTitleBar("发票信息");
        rightText.setText("保存");
        rightText.setVisibility(View.VISIBLE);
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
        fillData();
        click();
    }

    public void fillData() {
        if (login != null && !"".equals(login)) {
            LoginPshUser user = login.getPshUser();
            if (user != null && !"".equals(user)) {
                etCmompanyNameBill.setText(user.getUserCaty());
                tvNumBill.setText(user.getUserNnumb());
                tvCompanyAddresBill.setText(user.getUserZcdz());
                tvPhoneBill.setText(user.getUserTels());
                etBankBill.setText(user.getUserBanks());
                etBankNumBill.setText(user.getUserBnumb());
            }
        }
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

    //获取输入的值
    public void getInput() {
        conpanyName = AppUtils.toStringTrim_ET(etCmompanyNameBill);
        numBill = AppUtils.toStringTrim_ET(tvNumBill);
        companyAddress = AppUtils.toStringTrim_ET(tvCompanyAddresBill);
        phone = AppUtils.toStringTrim_ET(tvPhoneBill);
        bank = AppUtils.toStringTrim_ET(etBankBill);
        bankNum = AppUtils.toStringTrim_ET(etBankNumBill);

        //判断输入是否为空
        if (conpanyName == null || "".equals(conpanyName)) {
            AppUtils.toastText(BillActivity.this, "单位名称不能为空");
            return;
        }
        if (numBill == null || "".equals(numBill)) {
            AppUtils.toastText(BillActivity.this, "纳税人识别号不能为空");
            return;
        }
        if (numBill.length() < 15) {
            AppUtils.toastText(BillActivity.this, "请输入正确的纳税人识别号");
            return;
        }
        if (companyAddress == null || "".equals(companyAddress)) {
            AppUtils.toastText(BillActivity.this, "注册地址不能为空");
            return;
        }
        if (phone == null || "".equals(phone)) {
            AppUtils.toastText(BillActivity.this, "单位电话不能为空");
            return;
        }
        if (bank == null || "".equals(bank)) {
            AppUtils.toastText(BillActivity.this, "开户银行不能为空");
            return;
        }
        if (bankNum == null || "".equals(bankNum)) {
            AppUtils.toastText(BillActivity.this, "银行账号不能为空");
            return;
        }
        //TODO 调用接口
    }


}
