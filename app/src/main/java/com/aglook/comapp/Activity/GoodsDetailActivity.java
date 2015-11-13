package com.aglook.comapp.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.GoodsDetail;
import com.aglook.comapp.url.GoodsDetailUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

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

    private String productId;
    private ComAppApplication comAppApplication;
    private GoodsDetail goodsDetail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_goods_detail);
        setTitleBar("商品详情");
        ExitApplication.getInstance().addActivity(this);
        comAppApplication= (ComAppApplication) getApplication();
        init();
        getData();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("更多");
        productId=getIntent().getStringExtra("productId");
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

    public void getData(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_GoodsDetail", arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result, "obj");
                goodsDetail=JsonUtils.parse(obj,GoodsDetail.class);
                if (status.equals("1")){
                    //成功
                    Log.d("result_GoodsDetail_goodsDetail",goodsDetail+"");
                    fillData();
                }else {
                    AppUtils.toastText(GoodsDetailActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.PRODUCT_DETAIL, GoodsDetailUrl.postGoodsDetailUrl(productId),GoodsDetailActivity.this);
    }

    public void fillData(){
        tv_detail_goods_detail.setText(goodsDetail.getProductName());
        tv_price_goods_detail.setText(goodsDetail.getProductMoney());
        tv_cangdanhao_goods_detail.setText(goodsDetail.getProductListId());
        tv_huowuzhonglei_goods_detail.setText(goodsDetail.getCategoryName());
        tv_weight_goods_detail.setText(goodsDetail.getProductSellNum());
        tv_in_time_goods_detail.setText(Timestamp.getDateToString(goodsDetail.getInnerTime()));
        tv_producing_area_goods_detail.setText(goodsDetail.getGoodsPlace());
        tv_xuetou_goods_detail.setText(goodsDetail.getMark());
        tv_userful_life_goods_detail.setText(Timestamp.getDateToString(goodsDetail.getProductAvailable()));
        tv_name_goods_detail.setText(goodsDetail.getDepotResponsible());
        tv_phone_goods_detail.setText(goodsDetail.getResponsibleMobile());
        tv_emailgoods_detail.setText(goodsDetail.getResponsibleEmail());
        tv_address_goods_detail.setText(goodsDetail.getDepotAddress());
        tv_goods_detail_goods_detail.setText(goodsDetail.getProductAppDesc());
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
