package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.FriendsDetailAdapter;
import com.aglook.comapp.view.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class FriendsDetailActivity extends BaseActivity {


    private TextView tv_phone_friend_detail;
    private TextView tv_email_friend_detail;
    private TextView tv_num_friend_detail;
    private MyListView lv_friend_detail;
    private FriendsDetailAdapter adapter;
    private PullToRefreshScrollView sv_friends_detail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_friends_detail);
        setTitleBar("联系人的挂单");
        init();
        click();
    }

    public void init() {
        tv_phone_friend_detail = (TextView) findViewById(R.id.tv_phone_friend_detail);
        tv_email_friend_detail = (TextView) findViewById(R.id.tv_email_friend_detail);
        tv_num_friend_detail = (TextView) findViewById(R.id.tv_num_friend_detail);
        lv_friend_detail = (MyListView) findViewById(R.id.lv_friend_detail);
        adapter = new FriendsDetailAdapter(FriendsDetailActivity.this);
        lv_friend_detail.setAdapter(adapter);
        sv_friends_detail = (PullToRefreshScrollView) findViewById(R.id.sv_friends_detail);
        //        令scrollview显示顶部
        sv_friends_detail.getRefreshableView().smoothScrollBy(0, 0);
        sv_friends_detail.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    public void click() {

    }

    @Override
    public void widgetClick(View view) {

    }


}
