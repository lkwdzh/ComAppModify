package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/10.
 */
public class OrderDetailAdapter extends BaseAdapter {
    private Context context;

    public OrderDetailAdapter(Context context) {
        this.context = context;
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_all_order_lv_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    class ViewHolder{
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_type_lv_lv;
        TextView tv_grade_lv_lv;
        TextView tv_address_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;

        ViewHolder(View view) {
            iv_lv_lv=(ImageView)view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv=(TextView)view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv=(TextView)view.findViewById(R.id.tv_price_lv_lv);
            tv_type_lv_lv=(TextView)view.findViewById(R.id.tv_type_lv_lv);
            tv_grade_lv_lv=(TextView)view.findViewById(R.id.tv_grade_lv_lv);
            tv_address_lv_lv=(TextView)view.findViewById(R.id.tv_address_lv_lv);
            tv_weight_lv_lv=(TextView)view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv=(TextView)view.findViewById(R.id.tv_num_lv_lv);
        }
    }
}
