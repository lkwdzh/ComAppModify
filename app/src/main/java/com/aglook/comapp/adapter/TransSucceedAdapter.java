package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.view.MyListView;

/**
 * Created by aglook on 2015/11/6.
 */
public class TransSucceedAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private TextView tv_delete_order;

    public TransSucceedAdapter(Activity activity) {
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

        holder.tv_click_all_order_lv.setText("提货");
        holder.tv_click_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_delete_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_delete_all_order_lv.setText("转售");
        holder.tv_click_all_order_lv.setOnClickListener(this);
        holder.lv_all_order_lv.setAdapter(holder.adapter);
        holder.tv_delete_all_order_lv.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_click_all_order_lv:
                //跳转到提货页面
                break;
            case R.id.tv_delete_all_order_lv:

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


}
