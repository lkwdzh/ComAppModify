package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HangQingListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class HangQingListActivity extends BaseActivity {


    private PullToRefreshListView lv_hang_qing_list;
    private HangQingListAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_hang_qing_list);
        setTitleBar("行情资讯");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        lv_hang_qing_list = (PullToRefreshListView) findViewById(R.id.lv_hang_qing_list);
        adapter = new HangQingListAdapter(HangQingListActivity.this);
        lv_hang_qing_list.setAdapter(adapter);
    }

    public void click() {
        lv_hang_qing_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HangQingListActivity.this, HangDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }


}
