package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;

public class GoodsDetailActivity extends BaseActivity {


    private TextView right_text;

    @Override
    public void initView() {
        setContentView(R.layout.activity_goods_detail);
        setTitleBar("商品详情");
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("更多");
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
