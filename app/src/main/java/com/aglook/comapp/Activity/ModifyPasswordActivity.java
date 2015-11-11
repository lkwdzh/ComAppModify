package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;

public class ModifyPasswordActivity extends BaseActivity {


    private TextView right_text;

    @Override
    public void initView() {
        setContentView(R.layout.activity_modify_password);
        setTitleBar("修改密码");
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
