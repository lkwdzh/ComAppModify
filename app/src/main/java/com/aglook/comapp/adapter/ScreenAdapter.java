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
 * Created by aglook on 2015/11/6.
 */
public class ScreenAdapter extends BaseAdapter {
    private Context context;

    public ScreenAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_screen_gridview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{

        ImageView iv_screen_gridview;
        TextView tv_address_screen_gridview;
        TextView tv_grade_screen_gridview;
        TextView tv_weight_screen_gridview;
        TextView tv_price_screen_gridview;

        ViewHolder(View view) {
            iv_screen_gridview=(ImageView)view.findViewById(R.id.iv_screen_gridview);
            tv_address_screen_gridview=(TextView)view.findViewById(R.id.tv_address_screen_gridview);
            tv_grade_screen_gridview=(TextView)view.findViewById(R.id.tv_grade_screen_gridview);
            tv_weight_screen_gridview=(TextView)view.findViewById(R.id.tv_weight_screen_gridview);
            tv_price_screen_gridview=(TextView)view.findViewById(R.id.tv_price_screen_gridview);
        }
    }
}
