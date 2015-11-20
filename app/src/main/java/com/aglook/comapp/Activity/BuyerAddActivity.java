package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class BuyerAddActivity extends BaseActivity {


    private TextView right_text;
    private EditText et_name_buyer_add;
    private EditText et_phone_buyer_add;
    private EditText et_email_buyer_add;
    private EditText et_num_buyer_add;

    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_add);
        setTitleBar("添加联系人");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);
        et_name_buyer_add = (EditText) findViewById(R.id.et_name_buyer_add);
        et_phone_buyer_add = (EditText) findViewById(R.id.et_phone_buyer_add);
        et_email_buyer_add = (EditText) findViewById(R.id.et_email_buyer_add);
        et_num_buyer_add = (EditText) findViewById(R.id.et_num_buyer_add);
    }

    public void click(){
        right_text.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.right_text:
                //假如成功，则返回
                BuyerAddActivity.this.setResult(1);
                BuyerAddActivity.this.finish();
                break;
        }
    }


}
