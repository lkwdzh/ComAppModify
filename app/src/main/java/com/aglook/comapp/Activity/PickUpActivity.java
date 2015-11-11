package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PickUpAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PickUpActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_pick_up;
    private PickUpAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pick_up);
        setTitleBar("提货单");
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_pick_up = (PullToRefreshListView) findViewById(R.id.lv_pick_up);
        adapter = new PickUpAdapter(PickUpActivity.this);
        lv_pick_up.setAdapter(adapter);
    }

    public void click(){
        right_text.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.right_text:
                Intent intent = new Intent(PickUpActivity.this, PickInfoActivity.class);
                startActivity(intent);
                break;
        }
    }


}
