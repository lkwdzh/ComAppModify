package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.CardListAdapter;
import com.aglook.comapp.bean.CardList;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.SelectPopupWindow;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_card_list;
    private CardListAdapter adapter;
    private List<CardList> mList = new ArrayList<>();
    private SelectPopupWindow popupWindow;

    @Override
    public void initView() {
        setContentView(R.layout.activity_card_list);
        setTitleBar("银行卡详情");
        ExitApplication.getInstance().addActivity(this);
        init();
        getData();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_card_list = (PullToRefreshListView) findViewById(R.id.lv_card_list);
        lv_card_list.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new CardListAdapter(CardListActivity.this,mList);
        lv_card_list.setAdapter(adapter);
        lv_card_list.setMode(PullToRefreshBase.Mode.BOTH);
    }

    public void click() {
        //添加银行卡
        right_text.setOnClickListener(this);
        lv_card_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
            }
        });

        lv_card_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow = new SelectPopupWindow(CardListActivity.this, itemsOnClick);
                // 显示窗口
                popupWindow.showAtLocation(CardListActivity.this.findViewById(R.id.waww),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(CardListActivity.this, BandCardActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                mList = JsonUtils.parseList(obj, CardList.class);
                if (status.equals("1")) {
                    //假如成功

                } else {
                    AppUtils.toastText(CardListActivity.this, message);
                }
                adapter.notifyDataSetChanged();
                lv_card_list.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        };
    }


    //为弹出框实现监听
    private View.OnClickListener itemsOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_moren_select_popup:
                    AppUtils.toastText(CardListActivity.this,"默认");
                    popupWindow.dismiss();
                    break;
                case R.id.tv_delete_select_popup:
                    AppUtils.toastText(CardListActivity.this,"删除");
                    popupWindow.dismiss();
                    break;
            }
        }
    };

}
