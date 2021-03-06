package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Message;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {
    private List<Message> mList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();
    private Message message;
    private PullToRefreshListView lv_message;
    private int pageSize = 5;
    private int pageNum = 0;
//    private static DbUtils db;
    private ArrayAdapter<String> adapter;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
//        ExitApplication.getInstance().addActivity(this);
        setTitleBar("消息");
        init();
        click();
    }

    public void init() {
        comAppApplication = (ComAppApplication) getApplication();
        lv_message = (PullToRefreshListView) findViewById(R.id.lv_message);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        lv_message.setAdapter(adapter);

//        db =comAppApplication.getDb();
        getData();

        lv_message.setMode(PullToRefreshBase.Mode.BOTH);
        lv_message.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum = 0;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                getData();
            }
        });
    }

    public void click() {

    }

    public void getData() {

    }

    @Override
    public void widgetClick(View view) {

    }


}
