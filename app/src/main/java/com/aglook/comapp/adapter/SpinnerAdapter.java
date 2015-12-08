package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/12/8.
 */
public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private String[] str;
    private int index;

    public SpinnerAdapter(Context context, String[] str) {
        this.context = context;
        this.str = str;
    }

    @Override
    public int getCount() {
        return str.length;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_my_spinner,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        if (index==position){
            holder.tv.setBackgroundResource(R.drawable.hong);
            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            holder.tv.setBackgroundResource(R.drawable.upupup);
            holder.tv.setTextColor(context.getResources().getColor(R.color.textcolor_333333));
        }
        holder.tv.setText(str[position]);
        return convertView;
    }

    class ViewHolder{
        TextView tv;
        LinearLayout ll;

        ViewHolder(View view) {
            tv=(TextView)view.findViewById(R.id.tv);
            ll=(LinearLayout)view.findViewById(R.id.ll);
        }
    }

    public void setSelectItem(int index){
        this.index=index;
    }

}
