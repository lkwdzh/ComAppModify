package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Information;

import java.util.List;

/**
 * Created by aglook on 2016/1/11.
 */
public class HelpCenterAdapter extends BaseAdapter {
    private List<Information>list;
    private Context context;

    public HelpCenterAdapter(List<Information> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_help_center,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Information information = list.get(position);
        holder.tv_setting.setText(information.getClassName());
        return convertView;
    }

    class ViewHolder{
    TextView tv_setting;

        ViewHolder(View view) {
            tv_setting=(TextView)view.findViewById(R.id.tv_setting);
        }
    }
}
