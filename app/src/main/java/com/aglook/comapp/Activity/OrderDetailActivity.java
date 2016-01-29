package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Fragment.IncreaseBillFragment;
import com.aglook.comapp.Fragment.OrdinaryBillFragment;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.OrderDetailAdapter;
import com.aglook.comapp.bean.AllOrderDataList;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.OrderDetail;
import com.aglook.comapp.bean.OrderDetailOrder;
import com.aglook.comapp.url.AllOrderUrl;
import com.aglook.comapp.url.OrderDetailUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyListView;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends FragmentActivity implements View.OnClickListener {


    private MyListView lv_all_order_lv;
    private OrderDetailAdapter adapter;
    private TextView tv_order_num_order_detail;
    private TextView tv_success_order_detail;
    private TextView tv_order_total_order_detail;
    private TextView tv_money_order_detail;
    private TextView tv_cost_order_detail;
    private TextView tv_delete_order_detail;
    private TextView tv_xia_order_detail;
    private TextView tv_pay_order_detail;
    private TextView tv_phone_order_detail;
    private TextView tv_username_order_detail;
    private TextView tv_name_order_detail;
    //    private TextView tv_address_order_detail;
    private TextView tv_over_order_detail;
    private OrderDetail orderDetail;
    private List<AllOrderDataList> mList = new ArrayList<>();
    private Login login;
    private ComAppApplication comAppApplication;
    private String orderId;
    private boolean isSuccess;
    private TextView tv_click_all_order_lv;
    private CustomProgress customProgress;

    private String money;

    private final int DETAIL_PAY = 1;
    private boolean isPay;

    private boolean isDelete;


    private String orderStatus;
    private FrameLayout fragment_content;
    private ImageView left_icon;
    private TextView title;
    //    private String  pageSize=10;
//    private String  pageNum=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        title = (TextView) findViewById(R.id.title);
        title.setText("订单详情");
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }


    public void init() {
        lv_all_order_lv = (MyListView) findViewById(R.id.lv_all_order_lv);
//        allOrder= (AllOrder) getIntent().getSerializableExtra("AllOrder");
        orderId = getIntent().getStringExtra("orderId");

        getData();

//        mList.addAll(allOrder.getOrderDateList());
        adapter = new OrderDetailAdapter(OrderDetailActivity.this, mList);
        lv_all_order_lv.setAdapter(adapter);
        left_icon = (ImageView) findViewById(R.id.left_icon);
        tv_order_num_order_detail = (TextView) findViewById(R.id.tv_order_num_order_detail);
        tv_success_order_detail = (TextView) findViewById(R.id.tv_success_order_detail);
        tv_order_total_order_detail = (TextView) findViewById(R.id.tv_order_total_order_detail);
        tv_money_order_detail = (TextView) findViewById(R.id.tv_money_order_detail);
        tv_cost_order_detail = (TextView) findViewById(R.id.tv_cost_order_detail);
        tv_delete_order_detail = (TextView) findViewById(R.id.tv_delete_order_detail);
        tv_click_all_order_lv = (TextView) findViewById(R.id.tv_click_all_order_lv);
        tv_xia_order_detail = (TextView) findViewById(R.id.tv_xia_order_detail);
        tv_pay_order_detail = (TextView) findViewById(R.id.tv_pay_order_detail);
        tv_phone_order_detail = (TextView) findViewById(R.id.tv_phone_order_detail);
        tv_username_order_detail = (TextView) findViewById(R.id.tv_username_order_detail);
        tv_name_order_detail = (TextView) findViewById(R.id.tv_name_order_detail);
//        tv_address_order_detail = (TextView) findViewById(R.id.tv_address_order_detail);
        tv_over_order_detail = (TextView) findViewById(R.id.tv_over_order_detail);
        fragment_content = (FrameLayout) findViewById(R.id.fragment_content);
    }

    //填充数据
    public void fillData() {
        OrderDetailOrder order = orderDetail.getOrder();
        tv_order_num_order_detail.setText(String.valueOf(order.getOrderId()));
        if (orderDetail.getOrderStatus().equals("notpay")) {
            tv_success_order_detail.setText("待支付");
            tv_success_order_detail.setTextColor((getResources().getColor(R.color.green_356600)));
            isSuccess = false;
            tv_delete_order_detail.setVisibility(View.VISIBLE);
            tv_click_all_order_lv.setVisibility(View.VISIBLE);
            tv_delete_order_detail.setText("取消");
            tv_click_all_order_lv.setText("去支付");
        } else if (orderDetail.getOrderStatus().equals("success")) {
            tv_success_order_detail.setText("交易成功");
            tv_success_order_detail.setTextColor((getResources().getColor(R.color.red_c91014)));
            isSuccess = true;
            tv_delete_order_detail.setVisibility(View.GONE);
            tv_click_all_order_lv.setVisibility(View.GONE);
            tv_delete_order_detail.setText("交易");
            tv_click_all_order_lv.setText("提货");
        } else if (orderDetail.getOrderStatus().equals("close")) {
            tv_success_order_detail.setText("交易关闭");
            tv_success_order_detail.setTextColor((getResources().getColor(R.color.red_c91014)));
            isSuccess = false;
            tv_delete_order_detail.setVisibility(View.GONE);
            tv_click_all_order_lv.setVisibility(View.GONE);
        }
        adapter.isSuccess(isSuccess);
        tv_order_total_order_detail.setText(String.valueOf(order.getOrderMoney()));
        if (order.getOrderAtime() != 0) {
            tv_xia_order_detail.setText(Timestamp.getDateTo(String.valueOf(order.getOrderAtime())));
        }
        if (order.getOrderPtime() != 0) {
            tv_pay_order_detail.setText(Timestamp.getDateTo(String.valueOf(order.getOrderPtime())));
        } else {
            tv_pay_order_detail.setText("未付款");
        }

        if (order.getOrderFtime() != 0) {
            tv_over_order_detail.setText(Timestamp.getDateTo(String.valueOf(order.getOrderFtime())));
        } else {
            tv_over_order_detail.setText("未确认");
        }
        tv_money_order_detail.setText(String.valueOf(order.getOrderMoney()));
        tv_cost_order_detail.setText(String.valueOf(order.getCounterFee()));
        tv_phone_order_detail.setText(order.getUserPhone());
        tv_username_order_detail.setText(orderDetail.getUserSeat());
        tv_name_order_detail.setText(order.getUserTname());
        Bundle bundle = new Bundle();
        bundle.putSerializable("orderDetail", orderDetail);
        if (order.getFType().equals("0")) {//普通
            OrdinaryBillFragment ordinaryBillFragment = new OrdinaryBillFragment();
            ordinaryBillFragment.setArguments(bundle);
            showFragment(ordinaryBillFragment);
        }else {
            IncreaseBillFragment increaseBillFragment = new IncreaseBillFragment();
            increaseBillFragment.setArguments(bundle);
            showFragment(increaseBillFragment);
        }
        adapter.notifyDataSetChanged();
    }

    //显示fragment
    public void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_content,fragment);
        transaction.commit();
    }

    public void click() {
        tv_click_all_order_lv.setOnClickListener(this);
        tv_delete_order_detail.setOnClickListener(this);
        left_icon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_delete_order_detail:
                showDailog();
                break;
            case R.id.tv_click_all_order_lv:
                if (DefineUtil.BANKBAND) {
                    //已绑定银行卡
                    money = String.valueOf(orderDetail.getOrder().getOrderMoney());
                    orderId = String.valueOf(orderDetail.getOrder().getOrderId());
                    intent.setClass(OrderDetailActivity.this, PayActivity.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("money", money);
//                    Log.d("result_OrderId", orderId);
                    startActivityForResult(intent, DETAIL_PAY);
                } else {
                    AppUtils.toastText(OrderDetailActivity.this, "尚未绑定银行卡");
                    intent.setClass(OrderDetailActivity.this, CardListActivity.class);
                    OrderDetailActivity.this.startActivity(intent);
                }

                break;

            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                cancelOrder();
                dialog.dismiss();
                break;
            case R.id.left_icon:
                OrderDetailActivity.this.setResult(2);
                OrderDetailActivity.this.finish();
                break;
        }
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            OrderDetailActivity.this.setResult(2);
            OrderDetailActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_PAY && resultCode == 2) {
            isPay = true;
            getData();
        } else if (requestCode == 33 && resultCode == 1) {
            mList.clear();
            getData();
        } else if (requestCode == 13 && resultCode == RESULT_OK) {
            mList.clear();
            getData();
        } else if (requestCode == 13 && resultCode == 1) {
            mList.clear();
            getData();
        }
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
                Log.d("result_detail", orderId + "_____" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                    orderDetail = JsonUtils.parse(obj, OrderDetail.class);
                    if (isPay) {
                        mList.clear();
                        isPay = false;
                    }

                    if (isDelete) {
                        isDelete = false;
                        mList.clear();
                    }
                    if (orderDetail != null && !"".equals(orderDetail)) {
                        if (orderDetail.getOrderDetailList() != null && orderDetail.getOrderDetailList().size() != 0) {
                            mList.addAll(orderDetail.getOrderDetailList());
                        }
                        fillData();
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.ORDER_DETAIL, OrderDetailUrl.postOrdetDetailUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderId), OrderDetailActivity.this);
    }

    private Dialog dialog;
    private TextView tv_delete_order;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) OrderDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认删除此订单?");
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }

    private Button btn_cancel_delete;
    private Button btn_confirm_delete;

    public void cancelOrder() {
        customProgress = CustomProgress.show(OrderDetailActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_cancel", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    //若成功，则刷新列表
                    isDelete = true;
                    getData();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.CANCEL_ORDER, AllOrderUrl.postCancelOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderId), OrderDetailActivity.this);
    }


}
