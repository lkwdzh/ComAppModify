package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
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
        ExitApplication.getInstance().addActivity(this);
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
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ToPayActivity.this, OrderDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void widgetClick(View view) {

    }


}
