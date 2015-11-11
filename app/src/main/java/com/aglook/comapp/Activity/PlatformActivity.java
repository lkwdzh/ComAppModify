package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PlatformAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PlatformActivity extends BaseActivity {


    private PullToRefreshListView lv_my_platform;
    private PlatformAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_platform);
        setTitleBar("平台仓单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        lv_my_platform = (PullToRefreshListView) findViewById(R.id.lv_my_platform);
        adapter = new PlatformAdapter(PlatformActivity.this);
        lv_my_platform.setAdapter(adapter);
    }

    public void click(){
        lv_my_platform.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlatformActivity.this, PlatformDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }


}
