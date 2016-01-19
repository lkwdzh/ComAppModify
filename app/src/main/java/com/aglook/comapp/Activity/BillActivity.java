package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillActivity extends BaseActivity {

    @Bind(R.id.right_text)
    TextView rightText;
    @Bind(R.id.et_cmompanyName_bill)
    TextView etCmompanyNameBill;
    @Bind(R.id.tv_num_bill)
    EditText tvNumBill;
    @Bind(R.id.tv_companyAddres_bill)
    EditText tvCompanyAddresBill;
    @Bind(R.id.tv_phone_bill)
    EditText tvPhoneBill;
    @Bind(R.id.tv_trueName)
    TextView tvTrueName;
    @Bind(R.id.et_bank_bill)
    EditText etBankBill;
    @Bind(R.id.et_bankNum_bill)
    EditText etBankNumBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void widgetClick(View view) {

    }


}
