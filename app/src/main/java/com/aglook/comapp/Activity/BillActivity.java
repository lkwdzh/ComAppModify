package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Bill;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.LoginPshUser;
import com.aglook.comapp.url.BasicInformationUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

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
    @Bind(R.id.et_taiTou_bill)
    EditText etTaiTouBill;
    @Bind(R.id.ll_taiTou)
    LinearLayout llTaiTou;
    @Bind(R.id.et_content_bill)
    EditText etContentBill;
    @Bind(R.id.ll_content_bill)
    LinearLayout llContentBill;
    private String conpanyName;
    private String numBill;
    private String companyAddress;
    private String phone;
    private String bank;
    private String bankNum;
    private String taitou;
    private String content;

    private ComAppApplication comAppApplication;
    private Login login;
    private boolean isFromConfirm;//是否从确认订单界面过来
    private Bill bill = new Bill();
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        setTitleBar("发票信息");
        rightText.setText("保存");
        rightText.setVisibility(View.VISIBLE);
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
        isFromConfirm = getIntent().getBooleanExtra("isFromConfirm", false);
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
                if (isFromConfirm) {
                    llTaiTou.setVisibility(View.VISIBLE);
                    llContentBill.setVisibility(View.VISIBLE);
                    etTaiTouBill.setText(user.getUserCaty());
                } else {
                    llTaiTou.setVisibility(View.GONE);
                    llContentBill.setVisibility(View.GONE);
                }
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
        taitou = AppUtils.toStringTrim_ET(etTaiTouBill);
        conpanyName = AppUtils.toStringTrim_ET(etCmompanyNameBill);
        numBill = AppUtils.toStringTrim_ET(tvNumBill);
        companyAddress = AppUtils.toStringTrim_ET(tvCompanyAddresBill);
        phone = AppUtils.toStringTrim_ET(tvPhoneBill);
        bank = AppUtils.toStringTrim_ET(etBankBill);
        bankNum = AppUtils.toStringTrim_ET(etBankNumBill);
        content = AppUtils.toStringTrim_ET(etContentBill);
        if (isFromConfirm) {
            //判断输入是否为空
            if (taitou == null || "".equals(taitou)) {
                AppUtils.toastText(BillActivity.this, "发票抬头不能为空");
                return;
            }
        }
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

        if (!isFromConfirm) {
            //假如不是从确认订单过来，则调用接口提交
            //TODO 调用接口
            upData();
        } else {
            //将值返回上一个界面
            bill.setTaitou(taitou);
            bill.setConpanyName(conpanyName);
            bill.setCompanyAddress(companyAddress);
            bill.setNumBill(numBill);
            bill.setPhone(phone);
            bill.setBank(bank);
            bill.setBankNum(bankNum);
            bill.setContent(content);
            Intent intent = new Intent(BillActivity.this, ConfirmOrderActivity.class);
            intent.putExtra("Bill", bill);
            BillActivity.this.setResult(RESULT_OK, intent);
            BillActivity.this.finish();
        }
    }


    public void upData() {
        customProgress=CustomProgress.show(BillActivity.this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
                Log.d("result_bill",arg0.result);
                String status= JsonUtils.getJsonParam(arg0.result, "status");
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                if (status.equals("1")){
                    upDataUser();
                    BillActivity.this.finish();
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.PERSON_UPDATE, BasicInformationUrl.postUpDateBillInfoUrl(DefineUtil.USERID, DefineUtil.TOKEN, conpanyName, numBill, companyAddress,
                phone, bank, bankNum), BillActivity.this);
    }

    //更新本地信息
    public void upDataUser(){
        LoginPshUser user = login.getPshUser();
        user.setUserCaty(conpanyName);
        user.setUserNnumb(numBill);
        user.setUserZcdz(companyAddress);
        user.setUserTels(phone);
        user.setUserBanks(bank);
        user.setUserBnumb(bankNum);
    }

}
