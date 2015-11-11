package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/6.
 */
public class ConfirmOrderAdapter extends BaseAdapter{
    private Context context;

    public ConfirmOrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
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
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_confirm_order_listview, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    class ViewHolder{
        ImageView iv_confirm_order_lv;
        TextView tv_name_confirm_order_lv;
        TextView tv_price_confirm_order_lv;
        TextView tv_num_confirm_order_lv;
        TextView tv_cost_confirm_order_lv;
        EditText et_input_confirm_order_lv;

        ViewHolder(View view) {
            iv_confirm_order_lv=(ImageView)view.findViewById(R.id.iv_confirm_order_lv);
            tv_name_confirm_order_lv=(TextView)view.findViewById(R.id.tv_name_confirm_order_lv);
            tv_price_confirm_order_lv=(TextView)view.findViewById(R.id.tv_price_confirm_order_lv);
            tv_num_confirm_order_lv=(TextView)view.findViewById(R.id.tv_num_confirm_order_lv);
            tv_cost_confirm_order_lv=(TextView)view.findViewById(R.id.tv_cost_confirm_order_lv);
            et_input_confirm_order_lv=(EditText)view.findViewById(R.id.et_input_confirm_order_lv);
        }
    }


}
