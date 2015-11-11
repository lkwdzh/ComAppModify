package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Activity.OrderDetailActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.view.MyListView;

/**
 * Created by aglook on 2015/11/6.
 */
public class ToPayAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private TextView tv_delete_order;

    public ToPayAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.layout_all_order_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.lv_all_order_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, OrderDetailActivity.class);
                activity.startActivity(intent);
            }
        });
        holder.tv_click_all_order_lv.setText("确认付款");
        holder.tv_click_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_delete_all_order_lv.setVisibility(View.GONE);
        holder.tv_click_all_order_lv.setOnClickListener(this);
        holder.lv_all_order_lv.setAdapter(holder.adapter);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click_all_order_lv:
                showDialog();
                break;
            case R.id.btn_cancel_pay_popup:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_pay_popup:
                dialog.dismiss();
                break;
        }
    }

    class ViewHolder {
        TextView tv_order_num_all_order_lv;
        TextView tv_success_all_order_lv;
        MyListView lv_all_order_lv;
        TextView tv_order_total_all_order_lv;
        TextView tv_money_all_order_lv;
        TextView tv_cost_all_order_lv;
        TextView tv_click_all_order_lv;
        AllOrderLVAdapter adapter;
        TextView tv_delete_all_order_lv;

        ViewHolder(View view) {
            tv_order_num_all_order_lv = (TextView) view.findViewById(R.id.tv_order_num_all_order_lv);
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            lv_all_order_lv = (MyListView) view.findViewById(R.id.lv_all_order_lv);
            tv_order_total_all_order_lv = (TextView) view.findViewById(R.id.tv_order_total_all_order_lv);
            tv_money_all_order_lv = (TextView) view.findViewById(R.id.tv_money_all_order_lv);
            tv_cost_all_order_lv = (TextView) view.findViewById(R.id.tv_cost_all_order_lv);
            tv_click_all_order_lv = (TextView) view.findViewById(R.id.tv_click_all_order_lv);
            tv_delete_all_order_lv=(TextView)view.findViewById(R.id.tv_delete_all_order_lv);
            adapter=new AllOrderLVAdapter(activity);
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
        LayoutInflater inflater = LayoutInflater.from(activity);
        View inView = inflater.inflate(R.layout.layout_pay_dialog, null);
        btn_cancel_pay_popup = (Button) inView.findViewById(R.id.btn_cancel_pay_popup);
        btn_confirm_pay_popup = (Button) inView.findViewById(R.id.btn_confirm_pay_popup);
        tv_money_pay_popup = (TextView) inView.findViewById(R.id.tv_money_pay_popup);
        tv_card_pay_popup = (TextView) inView.findViewById(R.id.tv_card_pay_popup);
        et_input_pay_popup = (EditText) inView.findViewById(R.id.et_input_pay_popup);
        tv_yanzheng_pay_popup = (TextView) inView.findViewById(R.id.tv_yanzheng_pay_popup);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_pay_popup.setOnClickListener(this);
        btn_confirm_pay_popup.setOnClickListener(this);
    }

}
