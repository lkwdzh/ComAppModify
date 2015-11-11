package com.aglook.comapp.Activity;

import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.MyCangDanAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MyCangDanActivity extends BaseActivity {


    private PullToRefreshListView lv_my_cang_dan;
    private MyCangDanAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_my_cang_dan);
        setTitleBar("仓单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        lv_my_cang_dan = (PullToRefreshListView) findViewById(R.id.lv_my_cang_dan);
        lv_my_cang_dan.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MyCangDanAdapter(MyCangDanActivity.this);
        lv_my_cang_dan.setAdapter(adapter);
    }
    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
