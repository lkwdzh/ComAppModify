package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.FriendsAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class FriendsActivity extends BaseActivity {


    private PullToRefreshListView lv_friends;
    private FriendsAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_friends);
        setTitleBar("收藏好友");
        init();
        click();
    }

    public void init(){
        lv_friends = (PullToRefreshListView) findViewById(R.id.lv_friends);
        adapter = new FriendsAdapter(FriendsActivity.this);
        lv_friends.setAdapter(adapter);

    }

    public void click(){
lv_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(FriendsActivity.this, FriendsDetailActivity.class);
        startActivity(intent);
    }
});
    }

    @Override
    public void widgetClick(View view) {

    }


}
