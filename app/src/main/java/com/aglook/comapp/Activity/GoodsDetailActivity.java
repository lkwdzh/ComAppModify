package com.aglook.comapp.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class GoodsDetailActivity extends BaseActivity {


    private TextView right_text;
    private ViewPager vp_goods_detail;
    private TextView tv_detail_goods_detail;
    private TextView tv_price_goods_detail;
    private TextView tv_buy_goods_detail;
    private TextView tv_cangdanhao_goods_detail;
    private TextView tv_huowuzhonglei_goods_detail;
    private TextView tv_weight_goods_detail;
    private TextView tv_in_time_goods_detail;
    private TextView tv_producing_area_goods_detail;
    private TextView tv_xuetou_goods_detail;
    private TextView tv_userful_life_goods_detail;
    private TextView tv_name_goods_detail;
    private TextView tv_phone_goods_detail;
    private TextView tv_emailgoods_detail;
    private TextView tv_address_goods_detail;
    private TextView tv_goods_detail_goods_detail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_goods_detail);
        setTitleBar("商品详情");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("更多");

        vp_goods_detail = (ViewPager) findViewById(R.id.vp_goods_detail);
        tv_detail_goods_detail = (TextView) findViewById(R.id.tv_detail_goods_detail);
        tv_price_goods_detail = (TextView) findViewById(R.id.tv_price_goods_detail);
        tv_buy_goods_detail = (TextView) findViewById(R.id.tv_buy_goods_detail);
        tv_cangdanhao_goods_detail = (TextView) findViewById(R.id.tv_cangdanhao_goods_detail);
        tv_huowuzhonglei_goods_detail = (TextView) findViewById(R.id.tv_huowuzhonglei_goods_detail);
        tv_weight_goods_detail = (TextView) findViewById(R.id.tv_weight_goods_detail);
        tv_in_time_goods_detail = (TextView) findViewById(R.id.tv_in_time_goods_detail);
        tv_producing_area_goods_detail = (TextView) findViewById(R.id.tv_producing_area_goods_detail);
        tv_xuetou_goods_detail = (TextView) findViewById(R.id.tv_xuetou_goods_detail);
        tv_userful_life_goods_detail = (TextView) findViewById(R.id.tv_userful_life_goods_detail);
        tv_name_goods_detail = (TextView) findViewById(R.id.tv_name_goods_detail);
        tv_phone_goods_detail = (TextView) findViewById(R.id.tv_phone_goods_detail);
        tv_emailgoods_detail = (TextView) findViewById(R.id.tv_emailgoods_detail);
        tv_address_goods_detail = (TextView) findViewById(R.id.tv_address_goods_detail);
        tv_goods_detail_goods_detail = (TextView) findViewById(R.id.tv_goods_detail_goods_detail);
    }

    public void click(){
        tv_buy_goods_detail.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.tv_buy_goods_detail:
                Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }


}
