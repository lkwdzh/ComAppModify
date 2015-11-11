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
public class HangQingListAdapter extends BaseAdapter {
    private Context context;

    public HangQingListAdapter(Context context) {
        this.context = context;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_hang_qing_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{
        ImageView iv_hang_qing_list;
        TextView tv_hang_qing_list;

        ViewHolder(View view) {
            tv_hang_qing_list=(TextView)view.findViewById(R.id.tv_hang_qing_list);
            iv_hang_qing_list=(ImageView)view.findViewById(R.id.iv_hang_qing_list);
        }
    }
}
