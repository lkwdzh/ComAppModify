package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AllOrderAdapter;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.url.AllOrderUrl;
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

public class AllOrderActivity extends BaseActivity {


    private PullToRefreshListView lv_all_order;
    private AllOrderAdapter adapter;
    private View emptyView;
    private String orderStatus;
    private List<AllOrder> mList = new ArrayList<>();
    private boolean isBrower;
    private CustomProgress customProgress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_all_order);
        setTitleBar("全部订单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "加载中···", true);
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new AllOrderAdapter(AllOrderActivity.this,mList);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);

        lv_all_order.setMode(PullToRefreshBase.Mode.BOTH);
    }

    public void click() {
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllOrderActivity.this, OrderDetailActivity.class);
               intent.putExtra("AllOrder",  mList.get(position-1));
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==2){
            isBrower=true;
            getData();
        }
    }
    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_all_order",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                List<AllOrder> sonList=new ArrayList<AllOrder>();
                sonList=JsonUtils.parseList(obj,AllOrder.class);
                if (status.equals("1")){
                    if (sonList!=null&&sonList.size()!=0){
                        if (isBrower){
                            mList.clear();
                            isBrower=false;
                        }
                        mList.addAll(sonList);
                    }
                }else {
                    AppUtils.toastText(AllOrderActivity.this,message);
                }
                adapter.notifyDataSetChanged();
                lv_all_order.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderStatus), AllOrderActivity.this);
    }


}
