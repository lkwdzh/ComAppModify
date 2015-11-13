package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.BuyerListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class BuyerListActivity extends BaseActivity {

    private TextView right_text;
    private PullToRefreshListView lv_buyer_list;
    private TextView tv_num_buyer_list;
    private TextView tv_confirm_buyer_list;
    private boolean isBuyer;
    private BuyerListAdapter adapter;
    private RelativeLayout rl_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_list);
        setTitleBar("联系人列表");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_buyer_list = (PullToRefreshListView) findViewById(R.id.lv_buyer_list);
        tv_num_buyer_list = (TextView) findViewById(R.id.tv_num_buyer_list);
        tv_confirm_buyer_list = (TextView) findViewById(R.id.tv_confirm_buyer_list);
        isBuyer=getIntent().getBooleanExtra("buyOrLink", false);
        adapter = new BuyerListAdapter(BuyerListActivity.this, isBuyer);
        lv_buyer_list.setAdapter(adapter);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        if (isBuyer){
            rl_bottom.setVisibility(View.VISIBLE);
        }else {
            rl_bottom.setVisibility(View.GONE);
        }
    }

    public void click(){
        right_text.setOnClickListener(this);
        lv_buyer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuyerListActivity.this, FriendsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.right_text:
                intent.setClass(BuyerListActivity.this,BuyerAddActivity.class);
                startActivity(intent);
                break;
        }
    }


}
