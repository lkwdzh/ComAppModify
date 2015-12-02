package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.SearchGVAdapter;
import com.aglook.comapp.adapter.SearchLVAdapter;
import com.aglook.comapp.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {


    private EditText et_search;
    private ImageView iv_search;
    private ListView lv_search;
    private GridView gv_search;

    private List<String> gvList = new ArrayList<>();
    private List<String> lvList = new ArrayList<>();
    private SearchGVAdapter gvAdapter;
    private SearchLVAdapter lvAdapter;
    private String productName;


    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        et_search = (EditText) findViewById(R.id.et_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_search = (ListView) findViewById(R.id.lv_search);
        gv_search = (GridView) findViewById(R.id.gv_search);

        for (int i = 0; i < 10; i++) {
            gvList.add("数据" + i);
            lvList.add("数据" + i);
        }
        gvAdapter = new SearchGVAdapter(gvList, SearchActivity.this);
        gv_search.setAdapter(gvAdapter);
        lvAdapter = new SearchLVAdapter(lvList, SearchActivity.this);
        lv_search.setAdapter(lvAdapter);
    }

    public void click() {
        iv_search.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                productName= AppUtils.toStringTrim_ET(et_search);
                Intent intent = new Intent(SearchActivity.this, ScreenActivity.class);
                intent.putExtra("productName",productName);
                intent.putExtra("isSearch",true);
                startActivity(intent);
                break;
        }
    }


}