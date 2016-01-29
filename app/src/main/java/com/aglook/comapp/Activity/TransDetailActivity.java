package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.GuaDanStataLiL;
import com.aglook.comapp.url.GuaDanUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class TransDetailActivity extends BaseActivity {

    private String code = "4007";//交易信息详情

    private String productId;//商品id
    private String orderdataId;//平台仓单id
    private CustomProgress customProgress;
    private GuaDanStataLiL guaDanStataLiL;
    private TextView tv_house_num_my_cangdan;
    private TextView tv_success_all_order_lv;
    private TextView tv_in_time_my_cangdan;
    private TextView tv_pay_order_detail;
    private TextView tv_over_order_detail;
    private ImageView iv_lv_lv;
    private TextView tv_price_lv_lv;
    private TextView tv_weight_lv_lv;
    //    private TextView tv_delete_order_detail;
    private TextView tv_money_lv_lv;
    private TextView tv_shouxu_lv_lv;
    private TextView tv_cangchu_lv_lv;
    //    private TextView tv_click_all_order_lv;
    private TextView tv_xia_order_detail;
    private TextView tv_username_order_detail;
    private TextView tv_name_order_detail;
    private TextView tv_name_lv_lv;
    private LinearLayout ll_top;
    //    private TextView tv_address_order_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_trans_detail);
//        ExitApplication.getInstance().addActivity(this);
        setTitleBar("交易详情");
        init();
        click();
        getData();
    }

    public void init() {

        productId = getIntent().getStringExtra("productId");
        orderdataId = getIntent().getStringExtra("orderdataId");
        tv_house_num_my_cangdan = (TextView) findViewById(R.id.tv_house_num_my_cangdan);
        tv_success_all_order_lv = (TextView) findViewById(R.id.tv_success_all_order_lv);
        tv_in_time_my_cangdan = (TextView) findViewById(R.id.tv_in_time_my_cangdan);
        tv_pay_order_detail = (TextView) findViewById(R.id.tv_pay_order_detail);
        tv_over_order_detail = (TextView) findViewById(R.id.tv_over_order_detail);
        iv_lv_lv = (ImageView) findViewById(R.id.iv_lv_lv);
        tv_price_lv_lv = (TextView) findViewById(R.id.tv_price_lv_lv);
        tv_weight_lv_lv = (TextView) findViewById(R.id.tv_weight_lv_lv);
//        tv_delete_order_detail = (TextView) findViewById(R.id.tv_delete_order_detail);
        tv_money_lv_lv = (TextView) findViewById(R.id.tv_money_lv_lv);
        tv_shouxu_lv_lv = (TextView) findViewById(R.id.tv_shouxu_lv_lv);
        tv_cangchu_lv_lv = (TextView) findViewById(R.id.tv_cangchu_lv_lv);
//        tv_click_all_order_lv = (TextView) findViewById(R.id.tv_click_all_order_lv);
        tv_xia_order_detail = (TextView) findViewById(R.id.tv_xia_order_detail);
        tv_username_order_detail = (TextView) findViewById(R.id.tv_username_order_detail);
        tv_name_order_detail = (TextView) findViewById(R.id.tv_name_order_detail);
//        tv_address_order_detail = (TextView) findViewById(R.id.tv_address_order_detail);
        tv_name_lv_lv = (TextView) findViewById(R.id.tv_name_lv_lv);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);
    }

    public void fillData() {
        tv_house_num_my_cangdan.setText(guaDanStataLiL.getOrderId());
        tv_name_lv_lv.setText(guaDanStataLiL.getProductName());
        if (guaDanStataLiL.getOrderState().equals("close")) {
            tv_success_all_order_lv.setText("已关闭");
        } else if (guaDanStataLiL.getOrderState().equals("success")) {
            tv_success_all_order_lv.setText("交易成功");

        } else if (guaDanStataLiL.getOrderState().equals("notpay")) {
            tv_success_all_order_lv.setText("交易中");
        }

        if (!"".equals(guaDanStataLiL.getOrderAtime()) ) {
            tv_in_time_my_cangdan.setText(Timestamp.getDateTo(guaDanStataLiL.getOrderAtime()));
            tv_xia_order_detail.setText(Timestamp.getDateTo(guaDanStataLiL.getOrderAtime()));
        }
        if (!"0".equals(guaDanStataLiL.getOrderPtime())) {
            tv_pay_order_detail.setText(Timestamp.getDateTo(guaDanStataLiL.getOrderPtime()));
        } else {
            tv_pay_order_detail.setText("未付款");
        }

        if (!"0".equals(guaDanStataLiL.getOrderFtime() )) {
            tv_over_order_detail.setText(Timestamp.getDateTo(guaDanStataLiL.getOrderFtime()));
        } else {
            tv_over_order_detail.setText("未付款");
        }
        if (guaDanStataLiL.getProductLogo() != null) {
            XBitmap.displayImage(iv_lv_lv, guaDanStataLiL.getProductLogo(), TransDetailActivity.this);
        }
        tv_price_lv_lv.setText(guaDanStataLiL.getProductMoney());
        tv_weight_lv_lv.setText(guaDanStataLiL.getProductNum()+"吨");
        if (guaDanStataLiL.getProductNum()!=null&&guaDanStataLiL.getProductMoney()!=null&&guaDanStataLiL.getCounter()!=null) {
            tv_money_lv_lv.setText(Double.parseDouble(guaDanStataLiL.getProductNum()) * Double.parseDouble(guaDanStataLiL.getProductMoney()) + Double.parseDouble(guaDanStataLiL.getCounter()) + "");
        }
        tv_shouxu_lv_lv.setText(guaDanStataLiL.getCounter());
        tv_cangchu_lv_lv.setText(guaDanStataLiL.getStorage());
        tv_username_order_detail.setText(guaDanStataLiL.getBuyUserSeat());
        tv_name_order_detail.setText(guaDanStataLiL.getBuyUsername());
    }

    public void click() {
        ll_top.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_top:
                intent.setClass(TransDetailActivity.this, GoodsDetailActivity.class);
                intent.putExtra("isSelf", true);
                intent.putExtra("productId", guaDanStataLiL.getProductId());
                startActivity(intent);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==33&&resultCode==1){
            getData();
        }
    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_trading", DefineUtil.TOKEN + "____" + DefineUtil.USERID + "___" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    guaDanStataLiL = JsonUtils.parse(obj, GuaDanStataLiL.class);
                    if (guaDanStataLiL != null) {
                        fillData();
                    }
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CANG_DAN, GuaDanUrl.postGuaDanDetailUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, orderdataId, productId), TransDetailActivity.this);
    }


}
