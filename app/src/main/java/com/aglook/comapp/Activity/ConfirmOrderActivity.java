package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ConfirmOrderAdapter;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderActivity extends BaseActivity {

    private TextView tv_name_confirm_order;
    private TextView tv_phone_confirm_order;
    private PullToRefreshListView lv_confirm_order;
    private TextView tv_money_confirm_order;
    private TextView tv_confirm_confirm_order;
    private ConfirmOrderAdapter adapter;
    private ComAppApplication comAppApplication;
    private Login login;
    private List<ShoppingCart> mList = new ArrayList<>();
    private double goodsMoney;
    private double costMoney;
    private double allMoney;
    private TextView tv_zonge_confirm_order;
    private TextView tv_shouxufei_confirm_order;


    @Override
    public void initView() {
        setContentView(R.layout.activity_confirm_order);
        setTitleBar("确认订单");
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        click();
    }

    public void init() {
        login = comAppApplication.getLogin();
        mList = (List<ShoppingCart>) getIntent().getSerializableExtra("CharList");
        tv_name_confirm_order = (TextView) findViewById(R.id.tv_name_confirm_order);
        tv_phone_confirm_order = (TextView) findViewById(R.id.tv_phone_confirm_order);
        lv_confirm_order = (PullToRefreshListView) findViewById(R.id.lv_confirm_order);
        tv_money_confirm_order = (TextView) findViewById(R.id.tv_money_confirm_order);
        tv_confirm_confirm_order = (TextView) findViewById(R.id.tv_confirm_confirm_order);
        View view = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.footview_confirm_order, null);
        lv_confirm_order.getRefreshableView().addFooterView(view);
        tv_zonge_confirm_order = (TextView) view.findViewById(R.id.tv_zonge_confirm_order);
        tv_shouxufei_confirm_order = (TextView) view.findViewById(R.id.tv_shouxufei_confirm_order);
        addCostMoney();
    }

    //填充数据
    public void fillData() {
        if (login != null) {
            tv_name_confirm_order.setText(login.getPshUser().getUsername());
            tv_phone_confirm_order.setText(login.getPshUser().getUserPhone());
            tv_zonge_confirm_order.setText(goodsMoney+"");
            tv_shouxufei_confirm_order.setText(costMoney+"");
            tv_money_confirm_order.setText(allMoney+"");
        }
    }

    //计算手续费并且添加到实体类中
    public void addCostMoney() {
        for (int i = 0; i < mList.size(); i++) {
            ShoppingCart cart = mList.get(i);
            cart.setCostMoney(cart.getProductMoney() * cart.getProductNum() / 1000);
        }
        adapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this, mList);
        lv_confirm_order.setAdapter(adapter);

        //计算各种总额
        for (int i = 0; i < mList.size(); i++) {
            ShoppingCart cart = mList.get(i);
            goodsMoney += cart.getProductMoney() * cart.getProductNum();
            costMoney += cart.getCostMoney();

        }
        allMoney = goodsMoney + costMoney;

        fillData();
    }

    public void click() {
        tv_confirm_confirm_order.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_pay_popup:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_pay_popup:
                dialog.dismiss();
                break;
            case R.id.tv_confirm_confirm_order:
                showDialog();
                break;
        }
    }

    private Dialog dialog;
    private Button btn_cancel_pay_popup;
    private Button btn_confirm_pay_popup;
    private TextView tv_money_pay_popup;
    private TextView tv_card_pay_popup;
    private EditText et_input_pay_popup;
    private TextView tv_yanzheng_pay_popup;

    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(ConfirmOrderActivity.this);
        View inView = inflater.inflate(R.layout.layout_pay_dialog, null);
        btn_cancel_pay_popup = (Button) inView.findViewById(R.id.btn_cancel_pay_popup);
        btn_confirm_pay_popup = (Button) inView.findViewById(R.id.btn_confirm_pay_popup);
        tv_money_pay_popup = (TextView) inView.findViewById(R.id.tv_money_pay_popup);
        tv_card_pay_popup = (TextView) inView.findViewById(R.id.tv_card_pay_popup);
        et_input_pay_popup = (EditText) inView.findViewById(R.id.et_input_pay_popup);
        tv_yanzheng_pay_popup = (TextView) inView.findViewById(R.id.tv_yanzheng_pay_popup);
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmOrderActivity.this);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_pay_popup.setOnClickListener(this);
        btn_confirm_pay_popup.setOnClickListener(this);
    }


}
