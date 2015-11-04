package com.aglook.comapp.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.aglook.comapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

public class ClassifyActivity extends BaseActivity {
    private List<String>mList=new ArrayList<>();
    private PullToRefreshGridView gv_classify;
    private ArrayAdapter<String> adapter;


    @Override
    public void initView() {
        setContentView(R.layout.activity_classify);
        for (int i = 0; i < 30; i++) {
            mList.add("分类"+i);
        }
        init();
        click();
    }

    //初始化控件
    public void init(){
        gv_classify = (PullToRefreshGridView) findViewById(R.id.gv_classify);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        gv_classify.setAdapter(adapter);
        gv_classify.setMode(PullToRefreshBase.Mode.BOTH);
    }

    //点击控件
    public void click(){
        gv_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ClassifyActivity.this,GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }


}
