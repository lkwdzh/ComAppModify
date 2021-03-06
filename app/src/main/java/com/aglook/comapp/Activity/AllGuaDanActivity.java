package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AllGuaDanAdapter;
import com.aglook.comapp.bean.GuaDan;
import com.aglook.comapp.bean.GuaDanList;
import com.aglook.comapp.url.AllGuaDanUrl;
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

public class AllGuaDanActivity extends BaseActivity {
    private PullToRefreshListView lv_all_order;
    private AllGuaDanAdapter adapter;
    private View emptyView;
    private List<GuaDanList> mList = new ArrayList<>();
    private GuaDan guaDan;
    private String code = "4001";

    private int pageNum = 1;
    private int pageSize = 10;
    private String _sort;
    private boolean isModify;
    private CustomProgress customProgress;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_all_gua_dan);
        setTitleBar("全部挂单");
        comAppApplication= (ComAppApplication) getApplication();
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {

        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new AllGuaDanAdapter(AllGuaDanActivity.this, mList);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);

        lv_all_order.setMode(PullToRefreshBase.Mode.BOTH);
    }

    public void click() {
        final Intent intent = new Intent();
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(AllGuaDanActivity.this, GoodsDetailActivity.class);
                intent.putExtra("isSelf", true);
                intent.putExtra("productId", mList.get(position - 1).getProductId());
                startActivity(intent);
            }
        });
        lv_all_order.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==12&&resultCode==1){
            isModify=true;
            getData();
        }else if (requestCode==33&&resultCode==1){
            mList.clear();
            getData();
        }
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
                    customProgress.cancle();
                }
//                Log.d("result_All_guadan", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    if (obj != null && !"".equals(obj)) {
                        guaDan = JsonUtils.parse(obj, GuaDan.class);
                            if (pageNum == 1) {
                                mList.clear();
                            }
                            if (isModify){
                                isModify=false;
                                mList.clear();
                            }
                        if (guaDan.getList() != null && guaDan.getList().size() != 0) {

                            mList.addAll(guaDan.getList());
                        }
                    }
                }
//


                adapter.notifyDataSetChanged();
                lv_all_order.onRefreshComplete();
                lv_all_order.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, AllGuaDanUrl.postGuaDanListUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort), AllGuaDanActivity.this);
    }

}
