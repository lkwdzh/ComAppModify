package com.aglook.comapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.view.MyGridView;

/**
 * Created by aglook on 2015/11/6.
 */
public class ToReceiveAdapter extends BaseAdapter {
    private Activity activity;
    private TextView tv_delete_order;

    public ToReceiveAdapter(Activity activity) {
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

        holder.tv_click_all_order_lv.setText("确定收货");
        holder.iv_delete_all_order_lv.setVisibility(View.GONE);
        holder.gv_all_order_lv.setAdapter(holder.adapter);
        return convertView;
    }


    class ViewHolder {
        TextView tv_name_all_order_lv;
        ImageView iv_delete_all_order_lv;
        MyGridView gv_all_order_lv;
        TextView tv_money_all_order_lv;
        TextView tv_click_all_order_lv;
        AllOrderGVAdapter adapter;

        ViewHolder(View view) {
            tv_name_all_order_lv = (TextView) view.findViewById(R.id.tv_name_all_order_lv);
            iv_delete_all_order_lv = (ImageView) view.findViewById(R.id.iv_delete_all_order_lv);
            gv_all_order_lv = (MyGridView) view.findViewById(R.id.gv_all_order_lv);
            tv_money_all_order_lv = (TextView) view.findViewById(R.id.tv_money_all_order_lv);
            tv_click_all_order_lv = (TextView) view.findViewById(R.id.tv_click_all_order_lv);
            adapter = new AllOrderGVAdapter(activity);
        }
    }


}
