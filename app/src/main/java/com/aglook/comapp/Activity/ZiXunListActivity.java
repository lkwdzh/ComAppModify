package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ZiXunListAdapter;
import com.aglook.comapp.bean.ZiXunList;
import com.aglook.comapp.url.ZiXunUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class ZiXunListActivity extends BaseActivity {


    private PullToRefreshListView lv_hang_qing_list;
    private ZiXunListAdapter adapter;
    private String classId;
    private List<ZiXunList>mList=new ArrayList<>();
    private String className;
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_hang_qing_list);
        className=getIntent().getStringExtra("className");
        setTitleBar(className);
        customProgress = CustomProgress.show(ZiXunListActivity.this, "加载中···", true);
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        classId=getIntent().getStringExtra("classId");
        lv_hang_qing_list = (PullToRefreshListView) findViewById(R.id.lv_hang_qing_list);
        adapter = new ZiXunListAdapter(ZiXunListActivity.this,mList);
        lv_hang_qing_list.setAdapter(adapter);
    }

    public void click() {
        lv_hang_qing_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ZiXunListActivity.this, HangDetailActivity.class);
                intent.putExtra("url",mList.get(position-1).getUrl());
//                AppUtils.toastText(ZiXunListActivity.this,position-1+"");
                intent.putExtra("className",className);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_zixunList",classId+"___"+arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                List<ZiXunList>sonList=new ArrayList<ZiXunList>();
                if (status.equals("1")){
                    sonList=JsonUtils.parseList(obj,ZiXunList.class);
                    if (sonList!=null&&sonList.size()!=0){
                        mList.addAll(sonList);
                    }
                }else {
                    AppUtils.toastText(ZiXunListActivity.this,message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.INFOR_LIST, ZiXunUrl.postZiXunListUrl(classId),ZiXunListActivity.this);
    }

}
