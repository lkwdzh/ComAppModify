package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.OrderDetailAdapter;
import com.aglook.comapp.view.MyListView;

public class OrderDetailActivity extends BaseActivity {


    private MyListView lv_all_order_lv;
    private OrderDetailAdapter adapter;
    private TextView tv_order_num_order_detail;
    private TextView tv_success_order_detail;
    private TextView tv_order_total_order_detail;
    private TextView tv_money_order_detail;
    private TextView tv_cost_order_detail;
    private TextView tv_delete_order_detail;
    private TextView tv_xia_order_detail;
    private TextView tv_pay_order_detail;
    private TextView tv_phone_order_detail;
    private TextView tv_username_order_detail;
    private TextView tv_name_order_detail;
    private TextView tv_address_order_detail;
    private TextView tv_over_order_detail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_order_detail);
        setTitleBar("订单详情");
        init();
        click();
    }

    public void init(){
        lv_all_order_lv = (MyListView) findViewById(R.id.lv_all_order_lv);
        adapter = new OrderDetailAdapter(OrderDetailActivity.this);
        lv_all_order_lv.setAdapter(adapter);

        tv_order_num_order_detail = (TextView) findViewById(R.id.tv_order_num_order_detail);
        tv_success_order_detail = (TextView) findViewById(R.id.tv_success_order_detail);
        tv_order_total_order_detail = (TextView) findViewById(R.id.tv_order_total_order_detail);
        tv_money_order_detail = (TextView) findViewById(R.id.tv_money_order_detail);
        tv_cost_order_detail = (TextView) findViewById(R.id.tv_cost_order_detail);
        tv_delete_order_detail = (TextView) findViewById(R.id.tv_delete_order_detail);
        tv_xia_order_detail = (TextView) findViewById(R.id.tv_xia_order_detail);
        tv_pay_order_detail = (TextView) findViewById(R.id.tv_pay_order_detail);
        tv_phone_order_detail = (TextView) findViewById(R.id.tv_phone_order_detail);
        tv_username_order_detail = (TextView) findViewById(R.id.tv_username_order_detail);
        tv_name_order_detail = (TextView) findViewById(R.id.tv_name_order_detail);
        tv_address_order_detail = (TextView) findViewById(R.id.tv_address_order_detail);
        tv_over_order_detail = (TextView) findViewById(R.id.tv_over_order_detail);
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
