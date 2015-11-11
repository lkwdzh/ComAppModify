package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.CardListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class CardListActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_card_list;
    private CardListAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_card_list);
        setTitleBar("绑定银行卡");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_card_list = (PullToRefreshListView) findViewById(R.id.lv_card_list);
        lv_card_list.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new CardListAdapter(CardListActivity.this);
        lv_card_list.setAdapter(adapter);
    }

    public void click(){
        //添加银行卡
        right_text.setOnClickListener(this);
    }
    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.right_text:
                intent.setClass(CardListActivity.this,BandCardActivity.class);
                startActivity(intent);
                break;
        }
    }


}
