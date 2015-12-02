package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PickUpAdapter;
import com.aglook.comapp.bean.PickUp;
import com.aglook.comapp.bean.PickUpList;
import com.aglook.comapp.url.PickUpUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class PickUpActivity extends BaseActivity {


    private PullToRefreshListView lv_pick_up;
    private PickUpAdapter adapter;
    private String code = "3001";
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;
    private CustomProgress customProgress;
    private List<PickUpList> mList = new ArrayList<>();
    private PickUp pickUp;
    private boolean isModify;
    private View emptyView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pick_up);
        setTitleBar("提货单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "加载中···", true);
        lv_pick_up = (PullToRefreshListView) findViewById(R.id.lv_pick_up);
        adapter = new PickUpAdapter(PickUpActivity.this, mList);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_pick_up.setAdapter(adapter);
    }

    public void click() {
//        lv_pick_up.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(PickUpActivity.this, PickUpDtailActivity.class);
//                startActivity(intent);
//            }
//        });
        lv_pick_up.setMode(PullToRefreshBase.Mode.BOTH);
        lv_pick_up.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                getData();
            }
        });
    }

    @Override
    public void widgetClick(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            isModify = true;
            getData();
        }
    }

    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_pickupList", arg0.result);
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    pickUp = JsonUtils.parse(obj, PickUp.class);
                    if (pickUp.getList() != null && pickUp.getList().size() != 0) {
                        if (pageNum == 1) {
                            mList.clear();
                        }
                        if (isModify) {
                            isModify = false;
                            mList.clear();
                        }
                        mList.addAll(pickUp.getList());
                    }
                } else {
                    AppUtils.toastText(PickUpActivity.this, message);
                }

                adapter.notifyDataSetChanged();
                lv_pick_up.onRefreshComplete();
                lv_pick_up.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, PickUpUrl.postPickUpListUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort), PickUpActivity.this);
    }


}
