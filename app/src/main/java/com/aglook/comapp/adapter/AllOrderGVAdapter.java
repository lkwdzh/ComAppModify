package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/11/6.
 */
public class AllOrderGVAdapter extends BaseAdapter {
    private Context context;

    public AllOrderGVAdapter(Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_all_order_gv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        ImageView iv_all_order_gv;

        ViewHolder(View view) {
            iv_all_order_gv=(ImageView)view.findViewById(R.id.iv_all_order_gv);
        }
    }
}
