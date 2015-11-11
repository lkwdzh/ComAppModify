package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.DriverListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class DriverListActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_driver_list;
    private DriverListAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_driver_list);
        setTitleBar("司机列表");
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_driver_list = (PullToRefreshListView) findViewById(R.id.lv_driver_list);
        adapter = new DriverListAdapter(DriverListActivity.this);
        lv_driver_list.setAdapter(adapter);
    }

    public void click() {
        right_text.setOnClickListener(this);
        lv_driver_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DriverListActivity.this, DriverInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(DriverListActivity.this, DriverAddActivity.class);
                startActivity(intent);
                break;
        }
    }


}
