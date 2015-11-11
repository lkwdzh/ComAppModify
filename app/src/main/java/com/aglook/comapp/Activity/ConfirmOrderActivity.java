package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ConfirmOrderAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ConfirmOrderActivity extends BaseActivity {

    private TextView tv_name_confirm_order;
    private TextView tv_phone_confirm_order;
    private PullToRefreshListView lv_confirm_order;
    private TextView tv_num_confirm_order;
    private TextView tv_money_confirm_order;
    private TextView tv_confirm_confirm_order;
    private ConfirmOrderAdapter adapter;



    @Override
    public void initView() {
        setContentView(R.layout.activity_confirm_order);
        setTitleBar("确认订单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        tv_name_confirm_order = (TextView) findViewById(R.id.tv_name_confirm_order);
        tv_phone_confirm_order = (TextView) findViewById(R.id.tv_phone_confirm_order);
        lv_confirm_order = (PullToRefreshListView) findViewById(R.id.lv_confirm_order);
        tv_num_confirm_order = (TextView) findViewById(R.id.tv_num_confirm_order);
        tv_money_confirm_order = (TextView) findViewById(R.id.tv_money_confirm_order);
        tv_confirm_confirm_order = (TextView) findViewById(R.id.tv_confirm_confirm_order);
        adapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this);
        lv_confirm_order.setAdapter(adapter);
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
