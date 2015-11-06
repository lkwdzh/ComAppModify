package com.aglook.comapp.Activity;

import android.view.LayoutInflater;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ToPayAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ToPayActivity extends BaseActivity {

    private PullToRefreshListView lv_all_order;
    private ToPayAdapter adapter;
    private View emptyView;
    @Override
    public void initView() {
        setContentView(R.layout.activity_to_pay);
        setTitleBar("代付款");
        init();
        click();
    }
    public void init(){
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new ToPayAdapter(ToPayActivity.this);
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
