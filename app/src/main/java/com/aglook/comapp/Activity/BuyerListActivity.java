package com.aglook.comapp.Activity;

import android.view.LayoutInflater;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.BuyerListAdapter;
import com.aglook.comapp.bean.Buyer;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class BuyerListActivity extends BaseActivity {

    private List<Buyer>mList=new ArrayList<>();
    private PullToRefreshListView lv_buyer_list;
    private BuyerListAdapter adapter;
    private View emptyView;
    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_list2);
        setTitleBar("指定买家");
        init();
        click();
    }

    public void init(){
        mList= (List<Buyer>) getIntent().getSerializableExtra("list");
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_buyer_list = (PullToRefreshListView) findViewById(R.id.lv_buyer_list);
        adapter = new BuyerListAdapter(mList,BuyerListActivity.this);
        lv_buyer_list.setAdapter(adapter);
        lv_buyer_list.setEmptyView(emptyView);
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
