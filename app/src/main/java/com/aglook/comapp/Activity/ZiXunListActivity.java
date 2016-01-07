package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ZiXunListAdapter;
import com.aglook.comapp.bean.ZiXunList;
import com.aglook.comapp.url.ZiXunUrl;
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

public class ZiXunListActivity extends BaseActivity {


    private PullToRefreshListView lv_hang_qing_list;
    private ZiXunListAdapter adapter;
    private String classId;
    private List<ZiXunList> mList = new ArrayList<>();
    private String className;
    private CustomProgress customProgress;
    private boolean isMessage;//判断是否是消息
    private View emptyView;
    private int pageSize = 15;
    private int pageNum = 1;
    private String url;
    private ImageView left_icon;
    private boolean isReceiver;


    @Override
    public void initView() {
        setContentView(R.layout.activity_hang_qing_list);
        className = getIntent().getStringExtra("className");
        isMessage = getIntent().getBooleanExtra("isMessage", false);
        isReceiver = getIntent().getBooleanExtra("isReceiver", false);
        setTitleBar(className);

        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        if (isMessage) {
            getMessage();
        } else {
            getData();
        }
    }

    public void init() {
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        classId = getIntent().getStringExtra("classId");
        left_icon = (ImageView) findViewById(R.id.left_icon);
        lv_hang_qing_list = (PullToRefreshListView) findViewById(R.id.lv_hang_qing_list);
        adapter = new ZiXunListAdapter(ZiXunListActivity.this, mList);
        lv_hang_qing_list.setAdapter(adapter);
        if (isMessage) {
            lv_hang_qing_list.setMode(PullToRefreshBase.Mode.BOTH);
        } else {
            lv_hang_qing_list.setMode(PullToRefreshBase.Mode.DISABLED);
        }
    }

    public void click() {
        left_icon.setOnClickListener(this);
        lv_hang_qing_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZiXunListActivity.this, HangDetailActivity.class);
                if (isMessage) {
                    url = DefineUtil.PUSH_ARTICLE + "?classId=" + mList.get(position - 1).getClassId() + "&articleId=" + mList.get(position - 1).getArticleId();
                    intent.putExtra("url", url);
                } else {
                    intent.putExtra("url", mList.get(position - 1).getUrl());
                }
//                AppUtils.toastText(ZiXunListActivity.this,position-1+"");
                intent.putExtra("className", mList.get(position-1).getArticleName());
                startActivity(intent);
            }
        });
        lv_hang_qing_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum = 1;
                getMessage();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                getMessage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 33 && resultCode == 1) {

        }
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.left_icon:
//                if (isReceiver&&!DefineUtil.IS_LAUNCH){
//                    intent.setClass(ZiXunListActivity.this,LaunchActivity.class);
//                    startActivity(intent);
//                    ZiXunListActivity.this.finish();
//                }else {
//                    ZiXunListActivity.this.finish();
//                }
//                if (isReceiver){
//                    intent.setClass(ZiXunListActivity.this,LaunchActivity.class);
//                    startActivity(intent);
//                    ZiXunListActivity.this.finish();
//                }else {
                ZiXunListActivity.this.finish();
//                }
                break;
        }

    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(ZiXunListActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_zixunList", classId + "___" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ZiXunList> sonList = new ArrayList<ZiXunList>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(obj, ZiXunList.class);
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                }

                adapter.notifyDataSetChanged();
                lv_hang_qing_list.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.INFOR_LIST, ZiXunUrl.postZiXunListUrl(classId), ZiXunListActivity.this);
    }

    //获取消息
    public void getMessage() {
        customProgress = CustomProgress.show(ZiXunListActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_zixunList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                String content = JsonUtils.getJsonParam(obj, "content");
                List<ZiXunList> sonList = new ArrayList<ZiXunList>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(content, ZiXunList.class);
                    if (pageNum == 1) {
                        mList.clear();
                    }
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                }

                adapter.notifyDataSetChanged();
                lv_hang_qing_list.onRefreshComplete();
                lv_hang_qing_list.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.PUSH_MESSAGE, ZiXunUrl.postMessageUrl(String.valueOf(pageSize), String.valueOf(pageNum)), ZiXunListActivity.this);
    }

}
