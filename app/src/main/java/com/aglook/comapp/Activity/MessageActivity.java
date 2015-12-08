package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Message;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {
    private List<Message> mList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();
    private Message message;
    private PullToRefreshListView lv_message;
    private int pageSize = 5;
    private int pageNum = 0;
    private static DbUtils db;
    private ArrayAdapter<String> adapter;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
        setTitleBar("消息");
        init();
        click();
    }

    public void init() {
        comAppApplication = (ComAppApplication) getApplication();
        lv_message = (PullToRefreshListView) findViewById(R.id.lv_message);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        lv_message.setAdapter(adapter);

//        for (int i = 0; i < 20; i++) {
//            message = new Message();
//            message.setId(i);
//            message.setTitle("title-__" + i);
//            mList.add(message);
//        }
        db =comAppApplication.getDb();
//        db = DbUtils.create(this, "MESSAGE");
//        comAppApplication.setDb(db);
//        for (int i = 0; i < mList.size(); i++) {
//            try {
//                db.save(mList.get(i));
//            } catch (DbException e) {
//                e.printStackTrace();
//            }
//        }
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

        List<Message> all = null;
        try {
//          all = db.findAll(Message.class);
            all = db.findAll(Selector.from(Message.class).orderBy("id", true).limit(pageSize).offset(pageSize * pageNum));
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (all != null) {
            if (pageNum == 0) {
                strList.clear();
            }
            for (int i = 0; i < all.size(); i++) {
                strList.add(all.get(i).getTitle());
            }
        }
        lv_message.postDelayed(new Runnable() {
            @Override
            public void run() {
                lv_message.onRefreshComplete();
        adapter.notifyDataSetChanged();

            }
        }, 1000);
    }

    @Override
    public void widgetClick(View view) {

    }


}
