package com.aglook.comapp.Activity;

import android.view.LayoutInflater;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AllOrderAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class AllOrderActivity extends BaseActivity {


    private PullToRefreshListView lv_all_order;
    private AllOrderAdapter adapter;
    private View emptyView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_all_order);
        setTitleBar("全部订单");
        init();
        click();
    }

    public void init(){
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new AllOrderAdapter(AllOrderActivity.this);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_all_order.setEmptyView(emptyView);
    }
    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }



}
