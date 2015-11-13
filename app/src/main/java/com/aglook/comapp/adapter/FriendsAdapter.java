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
public class FriendsAdapter extends BaseAdapter {
    private Context context;

    public FriendsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
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
