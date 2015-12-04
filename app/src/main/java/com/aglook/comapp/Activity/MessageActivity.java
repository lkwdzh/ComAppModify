package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.ArrayAdapter;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Message;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {
    private List<Message>mList=new ArrayList<>();
    private List<String >strList=new ArrayList<>();
    private Message message;
    private PullToRefreshListView lv_message;

    @Override
    public void initView() {
        setContentView(R.layout.activity_message);
        setTitleBar("消息");
        init();
        click();
    }

    public void init(){

        lv_message = (PullToRefreshListView) findViewById(R.id.lv_message);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
        lv_message.setAdapter(adapter);

        for (int i = 0; i < 10; i++) {
            message=new Message();
            message.setId(i);
            message.setTitle("title-__"+i);
            mList.add(message);
        }
        //创建数据库
        DbUtils.DaoConfig config=new DbUtils.DaoConfig(this);
//        //数据库名字
        config.setDbName("MESSAGE");
        DbUtils db = DbUtils.create(config);
        try {
            db.createTableIfNotExist(Message.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
//        DbUtils db=DbUtils.create(this);
//        try {
//            db.createTableIfNotExist(Message.class);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < mList.size(); i++) {
            try {
                db.save(mList.get(i));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        List<Message> all =null;
        try {
          all = db.findAll(Message.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < mList.size(); i++) {
//            try {
//                if (all!=null) {
//                    for (int j = 0; j < all.size(); j++) {
//                    if (mList.get(i).getTitle().equals(all.get(j).getTitle()))
//                    {
//
//                    }else {
//                        db.save(mList.get(i));
//                    }
//
//                    }
//                }else {
//                    db.save(mList.get(i));
//                }
//            } catch (DbException e) {
//                e.printStackTrace();
//            }
//        }
        if (all!=null) {
            for (int i = 0; i < all.size(); i++) {
                strList.add(all.get(i).getTitle());
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
