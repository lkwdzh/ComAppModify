package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.View;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.TradeingAdapter;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.url.AllOrderUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class TradeingActivity extends BaseActivity {

    private PullToRefreshListView lv_tradeing;
    private TradeingAdapter adapter;
    private List<AllOrder>mList=new ArrayList<>();
    private String orderStatus="1";


    @Override
    public void initView() {
        setContentView(R.layout.activity_tradeing);
        setTitleBar("交易中");
        init();
        click();

    }

    public void init(){
        lv_tradeing = (PullToRefreshListView) findViewById(R.id.lv_tradeing);
        adapter = new TradeingAdapter(TradeingActivity.this,mList);
        lv_tradeing.setAdapter(adapter);
        getData();
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
        AppUtils.toastText(this,"11111111");
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                AppUtils.toastText(TradeingActivity.this,"2222222");
                Log.d("result_trading", arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result, "message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                List<AllOrder> sonList=new ArrayList<AllOrder>();
                sonList=JsonUtils.parseList(obj,AllOrder.class);
                if (status.equals("1")){
                    if (sonList!=null&&sonList.size()!=0){
                        mList.addAll(sonList);
                    }
                }else {
                    AppUtils.toastText(TradeingActivity.this, message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderStatus), TradeingActivity.this);
    }

}
