package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.MyCangDanAdapter;
import com.aglook.comapp.bean.CangDan;
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.url.CangDanUrl;
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

public class MyCangDanActivity extends BaseActivity {


    private PullToRefreshListView lv_my_cang_dan;
    private MyCangDanAdapter adapter;
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;
    private List<CangDanList> mList = new ArrayList<>();
    private CangDan cangDan = new CangDan();
    private String code="1001";
    private CustomProgress customProgress;
    private View emptyView;
    @Override
    public void initView() {
        setContentView(R.layout.activity_my_cang_dan);
        setTitleBar("原始仓单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        lv_my_cang_dan = (PullToRefreshListView) findViewById(R.id.lv_my_cang_dan);
        lv_my_cang_dan.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MyCangDanAdapter(MyCangDanActivity.this,mList);
        lv_my_cang_dan.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==RESULT_OK){
            mList.clear();
            getData();
        }else if (requestCode==33&&resultCode==1){
            mList.clear();
            getData();
        }else if (requestCode==1&&resultCode==1){
            Log.d("result_pu_can_1","_________");
            mList.clear();
            getData();
        }else if (requestCode==1&&resultCode==RESULT_OK){
            Log.d("result_pu_can_2","_________");
            mList.clear();
            getData();
        }
    }

    public void click() {
        lv_my_cang_dan.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_myCangdan", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                cangDan = JsonUtils.parse(obj, CangDan.class);
                if (status.equals("1")) {
                    if (cangDan != null) {
                        if (cangDan.getList() != null && cangDan.getList().size() != 0) {
                            if (pageNum == 1) {
                                mList.clear();
                            }
                            mList.addAll(cangDan.getList());
                        }
                    }
                }

                adapter.notifyDataSetChanged();
                lv_my_cang_dan.onRefreshComplete();
                lv_my_cang_dan.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postCangDanUrl(code,DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort), this);

    }

}
