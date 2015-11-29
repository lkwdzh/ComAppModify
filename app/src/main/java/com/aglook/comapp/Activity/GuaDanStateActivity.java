package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.GuaDanStataAdapter;
import com.aglook.comapp.bean.GuaDanStata;
import com.aglook.comapp.bean.GuaDanStataLiL;
import com.aglook.comapp.url.AllGuaDanUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class GuaDanStateActivity extends BaseActivity {

    private String code = "4002";
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;
    private String productId;
    private String orderState;
    private GuaDanStataAdapter adapter;
    private PullToRefreshListView lv_order_state;
    private GuaDanStata guaDanStata;
    private List<GuaDanStataLiL> mList = new ArrayList<>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_order_state);
        setTitleBar("交易状态");
        init();
        click();
        getData();
    }

    public void init() {
        productId = getIntent().getStringExtra("productId");
        adapter = new GuaDanStataAdapter(GuaDanStateActivity.this,mList);
        lv_order_state = (PullToRefreshListView) findViewById(R.id.lv_order_state);
        lv_order_state.setAdapter(adapter);
    }

    public void click() {

    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_guadan_state", productId + "_____" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    guaDanStata = JsonUtils.parse(obj, GuaDanStata.class);
                    if (guaDanStata.getList().getList()!=null&&guaDanStata.getList().getList().size()!=0){
                        mList.addAll(guaDanStata.getList().getList());
                    }
                } else {
                    AppUtils.toastText(GuaDanStateActivity.this, message);
                }
                adapter.notifyDataSetChanged();
                lv_order_state.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CANG_DAN, AllGuaDanUrl.postLogUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort, productId, orderState), GuaDanStateActivity.this);
    }

}
