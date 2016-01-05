package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AllOrderAdapter;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.url.AllOrderUrl;
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

public class AllOrderActivity extends BaseActivity {


    private PullToRefreshListView lv_all_order;
    private AllOrderAdapter adapter;
    private View emptyView;
    private List<AllOrder> mList = new ArrayList<>();
    private boolean isBrower;
    private CustomProgress customProgress;
    private int pageNum = 1;
    private int pageSize = 10;
    private String orderState;
    private String code = "2004";
    private String _sort;
    private String orderId;
    private String orderStatus;

    private final int LIST_DETAIL = 2;
    private boolean isDetail;

    private int status;
    private ImageView left_icon;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_all_order);
        comAppApplication = (ComAppApplication) getApplication();
        status = getIntent().getIntExtra("status", 0);
        if (status == 1) {
            setTitleBar("全部订单");
        } else if (status == 2) {
            setTitleBar("待付款");
            orderStatus = "1";
        } else if (status == 3) {
            setTitleBar("成功订单");
            orderStatus = "0";
        }
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {

        left_icon = (ImageView) findViewById(R.id.left_icon);
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new AllOrderAdapter(AllOrderActivity.this, mList);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);

        lv_all_order.setMode(PullToRefreshBase.Mode.BOTH);
    }

    public void click() {
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllOrderActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", mList.get(position - 1).getOrderId());
                startActivityForResult(intent, LIST_DETAIL);
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
        left_icon.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                AllOrderActivity.this.setResult(22);
                AllOrderActivity.this.finish();
                break;
        }
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AllOrderActivity.this.setResult(22);
            AllOrderActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {//支付
            isBrower = true;
            getData();
        } else if (requestCode == LIST_DETAIL && resultCode == 2) {
            isDetail = true;
            pageNum = 1;
            getData();
        } else if (requestCode == 33 && resultCode == 1) {
            isLogin = true;
            getData();
        }else if (requestCode==13&&resultCode==RESULT_OK){
            mList.clear();
            getData();
        }else if (requestCode==13&&resultCode==1){
            mList.clear();
            getData();
        }
    }


    private boolean isLogin;


    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "加载中···", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_all_order", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<AllOrder> sonList = new ArrayList<AllOrder>();
                sonList = JsonUtils.parseList(obj, AllOrder.class);
                if (status.equals("1")) {
                    if (isBrower) {
                        mList.clear();
                        isBrower = false;
                    }
                    if (isDetail) {
                        isDetail = false;
                        mList.clear();
                    }
                    if (pageNum == 1) {
                        mList.clear();
                    }
                    if (isLogin) {
                        isLogin = false;
                        mList.clear();
                    }
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                    if (orderStatus != null && orderStatus.equals("1")) {
                        if (mList.size() != 0) {
                            DefineUtil.NOTPAY_NUM = mList.size();
                        } else {
                            DefineUtil.NOTPAY_NUM = 0;
                        }
                    }
                }
//
                adapter.notifyDataSetChanged();
                lv_all_order.setEmptyView(emptyView);
                lv_all_order.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderStatus, String.valueOf(pageSize), String.valueOf(pageNum), orderId), AllOrderActivity.this);
    }
}
