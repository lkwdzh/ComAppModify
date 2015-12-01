package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.TransSucceedAdapter;
import com.aglook.comapp.bean.GuaDanStataLi;
import com.aglook.comapp.bean.GuaDanStataLiL;
import com.aglook.comapp.url.AllGuaDanUrl;
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

public class TransSucceedActivity extends BaseActivity {
    private PullToRefreshListView lv_all_order;
    private TransSucceedAdapter adapter;
    private View emptyView;
    private List<GuaDanStataLiL> mList = new ArrayList<>();
    private GuaDanStataLi guaDanStataLi;
    //    private String orderStatus="1";
    private String code = "4003";
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;
    private String orderState = "success";
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_to_receive);
        setTitleBar("交易成功");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "加载中···", true);
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new TransSucceedAdapter(TransSucceedActivity.this, mList);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_all_order.setEmptyView(emptyView);
        lv_all_order.setMode(PullToRefreshBase.Mode.BOTH);
    }

    public void click() {
        final Intent intent = new Intent();
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.setClass(TransSucceedActivity.this, GoodsDetailActivity.class);
                intent.putExtra("isSelf", true);
                intent.putExtra("productId",mList.get(position-1).getProductId());
                AppUtils.toastText(TransSucceedActivity.this,position-1+"");
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
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_success", DefineUtil.TOKEN + "____" + DefineUtil.USERID + "___" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                guaDanStataLi = JsonUtils.parse(obj, GuaDanStataLi.class);
                if (status.equals("1")) {
                    if (guaDanStataLi.getList() != null && guaDanStataLi.getList().size() != 0) {
                        if (pageNum == 1) {
                            mList.clear();
                        }
                        mList.addAll(guaDanStataLi.getList());
                        Log.d("1111", mList.toString());
                    }
                } else {
                    AppUtils.toastText(TransSucceedActivity.this, message);
                }
                adapter.notifyDataSetChanged();
                lv_all_order.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, AllGuaDanUrl.postTranUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort, orderState), TransSucceedActivity.this);
    }

}
