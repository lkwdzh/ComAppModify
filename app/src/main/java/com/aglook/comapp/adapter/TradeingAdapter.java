package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.view.MyListView;

/**
 * Created by aglook on 2015/11/12.
 */
public class TradeingAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;


    public TradeingAdapter(Activity activity) {
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

        holder.tv_click_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_click_all_order_lv.setText("取消");
        holder.tv_delete_all_order_lv.setVisibility(View.GONE);
        holder.tv_click_all_order_lv.setOnClickListener(this);
        holder.lv_all_order_lv.setAdapter(holder.adapter);
        holder.lv_all_order_lv.setFocusable(false);
//        holder.lv_all_order_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(activity, OrderDetailActivity.class);
//                activity.startActivity(intent);
//            }
//        });
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click_all_order_lv:
                showDailog();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
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
    private TextView tv_delete_order;
    public void showDailog(){
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete= (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("确认取消此仓单?");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }
    private Button btn_cancel_delete;
    private Button btn_confirm_delete;
}
