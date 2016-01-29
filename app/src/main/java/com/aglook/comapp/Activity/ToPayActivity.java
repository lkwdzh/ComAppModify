package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ToPayAdapter;
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class ToPayActivity extends BaseActivity {

    private PullToRefreshListView lv_all_order;
    private ToPayAdapter adapter;
    private View emptyView;
    private List<AllOrder> mList=new ArrayList<>();
    private boolean isBrower;
    private boolean isSuccess;
    private CustomProgress customProgress;
    private int pageNum = 1;
    private int pageSize = 10;
    private String orderState;
    private String code = "2004";
    private String _sort;
    @Override
    public void initView() {
        setContentView(R.layout.activity_to_pay);
        isSuccess=getIntent().getBooleanExtra("isSuccess",false);
        if (isSuccess){
            setTitleBar("成功订单");
        }else {
        setTitleBar("待付款");
        }
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }
    public void init(){
        customProgress = CustomProgress.show(this, "", true);

        if (isSuccess){
            orderState="success";
        }else {
            orderState="notpay";
        }
        lv_all_order = (PullToRefreshListView) findViewById(R.id.lv_all_order);
        adapter = new ToPayAdapter(ToPayActivity.this,mList,isSuccess);
        lv_all_order.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);

    }
    public void click(){
        lv_all_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ToPayActivity.this, OrderDetailActivity.class);
                intent.putExtra("AllOrder",mList.get(position-1));
                startActivity(intent);
            }
        });
        lv_all_order.setMode(PullToRefreshBase.Mode.BOTH);
        lv_all_order.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum=1;
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
        if (requestCode==1&&resultCode==2){
           isBrower=true;
            getData();
        }
    }


    //获取数据
    public void getData() {
//        new XHttpuTools() {
//            @Override
//            public void initViews(ResponseInfo<String> arg0) {
//                if (customProgress != null && customProgress.isShowing()) {
//                    customProgress.cancle();
//                }
//                Log.d("result_trading", arg0.result);
//                String message= JsonUtils.getJsonParam(arg0.result, "message");
//                String status=JsonUtils.getJsonParam(arg0.result,"status");
//                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
//                List<AllOrder> sonList=new ArrayList<AllOrder>();
//                sonList=JsonUtils.parseList(obj,AllOrder.class);
//                if (status.equals("1")){
//                    if (sonList!=null&&sonList.size()!=0){
//                        if (isBrower){
//                            mList.clear();
//                            isBrower=false;
//                        }
//
//                        if (pageNum==1){
//                            mList.clear();
//                        }
//                        mList.addAll(sonList);
//                    }
//                }else {
//                    AppUtils.toastText(ToPayActivity.this, message);
//                }
//                adapter.notifyDataSetChanged();
//                lv_all_order.setEmptyView(emptyView);
//                lv_all_order.onRefreshComplete();
//            }
//
//            @Override
//            public void failureInitViews(HttpException arg0, String arg1) {
//                if (customProgress != null && customProgress.isShowing()) {
//                    customProgress.cancle();
//                }
//            }
//        }.datePost(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(code,DefineUtil.TOKEN, DefineUtil.USERID,  String.valueOf(pageSize), String.valueOf(pageNum),_sort, orderState), ToPayActivity.this);
    }




}
