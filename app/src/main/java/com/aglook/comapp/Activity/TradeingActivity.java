package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.TradeingAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class TradeingActivity extends BaseActivity {

    private PullToRefreshListView lv_tradeing;
    private TradeingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_tradeing);
        setTitleBar("交易中");
        init();
        click();
    }

    public void init(){
        lv_tradeing = (PullToRefreshListView) findViewById(R.id.lv_tradeing);
        adapter = new TradeingAdapter(TradeingActivity.this);
        lv_tradeing.setAdapter(adapter);
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
