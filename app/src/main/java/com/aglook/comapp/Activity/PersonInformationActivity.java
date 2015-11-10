package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;

public class PersonInformationActivity extends BaseActivity {


    private TextView tv_basic_infor_peson_info;
    private TextView tv_bang_card_person_info;

    @Override
    public void initView() {
        setContentView(R.layout.activity_person_information);
        setTitleBar("个人信息");
        init();
        click();
    }

    public void init(){
        tv_basic_infor_peson_info = (TextView) findViewById(R.id.tv_basic_infor_peson_info);
        tv_bang_card_person_info = (TextView) findViewById(R.id.tv_bang_card_person_info);
    }
    public void click(){
        tv_basic_infor_peson_info.setOnClickListener(this);
        tv_bang_card_person_info.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_basic_infor_peson_info:
                intent.setClass(PersonInformationActivity.this,BasicInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bang_card_person_info:
                intent.setClass(PersonInformationActivity.this,CardListActivity.class);
                startActivity(intent);
                break;
        }
    }


}
