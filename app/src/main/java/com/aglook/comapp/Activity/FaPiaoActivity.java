package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aglook.comapp.R;
import com.aglook.comapp.util.AppUtils;

public class FaPiaoActivity extends BaseActivity {


    private EditText et_taitou;
    private EditText et_content;
    private String taitou;
    private String content;
    private Button btn_baocun;

    @Override
    public void initView() {
        setContentView(R.layout.activity_fa_piao);
        setTitleBar("发票详情");
        init();
        click();
    }

    public void init(){
        et_taitou = (EditText) findViewById(R.id.et_taitou);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_baocun = (Button) findViewById(R.id.btn_baocun);
        taitou=getIntent().getStringExtra("taitou");
        content=getIntent().getStringExtra("content");
        et_taitou.setText(taitou);
        et_content.setText(content);
    }

    public void click(){
    btn_baocun.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.btn_baocun:
                taitou= AppUtils.toStringTrim_ET(et_taitou);
                content=AppUtils.toStringTrim_ET(et_content);
                if (taitou==null||"".equals(taitou)){
                    AppUtils.toastText(FaPiaoActivity.this,"请输入发票抬头");
                    return;
                }
                if (content==null||"".equals(content)){
                    AppUtils.toastText(FaPiaoActivity.this,"请输入发票内容");
                    return;
                }

                Intent intent = new Intent(FaPiaoActivity.this, ConfirmOrderActivity.class);
                intent.putExtra("taitou",taitou);
                intent.putExtra("content",content);
                FaPiaoActivity.this.setResult(RESULT_OK,intent);
                FaPiaoActivity.this.finish();
                break;
        }
    }

}
