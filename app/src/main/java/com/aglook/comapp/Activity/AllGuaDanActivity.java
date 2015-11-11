package com.aglook.comapp.Activity;

import android.view.LayoutInflater;
import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AllGuaDanAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class AllGuaDanActivity extends BaseActivity {
    private PullToRefreshListView lv_all_order;
    private AllGuaDanAdapter adapter;
    private View emptyView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_all_gua_dan);
        setTitleBar("全部挂单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }
    public void init(){
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new AllGuaDanAdapter(AllGuaDanActivity.this);
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
