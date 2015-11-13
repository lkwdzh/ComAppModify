package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/12.
 */
public class BuyerListAdapter extends BaseAdapter {
    private Context context;
    private boolean isBuyer;

    public BuyerListAdapter(Context context, boolean isBuyer) {
        this.context = context;
        this.isBuyer = isBuyer;
    }

    @Override
    public int getCount() {
        return 11;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_driver_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if (isBuyer){
            holder.cb_driver_lv.setBackgroundResource(R.drawable.ischecked);
        }else {
            holder.cb_driver_lv.setBackgroundResource(R.drawable.star_checked);
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv_driver_lv;
        CheckBox cb_driver_lv;

        ViewHolder(View view) {
            tv_driver_lv=(TextView)view.findViewById(R.id.tv_driver_lv);
            cb_driver_lv=(CheckBox)view.findViewById(R.id.cb_driver_lv);
        }
    }
}
