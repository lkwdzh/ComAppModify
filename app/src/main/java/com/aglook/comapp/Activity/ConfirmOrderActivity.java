package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ConfirmOrderAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ConfirmOrderActivity extends BaseActivity {

    private TextView tv_name_confirm_order;
    private TextView tv_phone_confirm_order;
    private PullToRefreshListView lv_confirm_order;
    private TextView tv_num_confirm_order;
    private TextView tv_money_confirm_order;
    private TextView tv_confirm_confirm_order;
    private ConfirmOrderAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_confirm_order);
        setTitleBar("确认订单");
        init();
        click();
    }

    public void init(){
        tv_name_confirm_order = (TextView) findViewById(R.id.tv_name_confirm_order);
        tv_phone_confirm_order = (TextView) findViewById(R.id.tv_phone_confirm_order);
        lv_confirm_order = (PullToRefreshListView) findViewById(R.id.lv_confirm_order);
        tv_num_confirm_order = (TextView) findViewById(R.id.tv_num_confirm_order);
        tv_money_confirm_order = (TextView) findViewById(R.id.tv_money_confirm_order);
        tv_confirm_confirm_order = (TextView) findViewById(R.id.tv_confirm_confirm_order);
        adapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this);
        lv_confirm_order.setAdapter(adapter);
    }
    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
