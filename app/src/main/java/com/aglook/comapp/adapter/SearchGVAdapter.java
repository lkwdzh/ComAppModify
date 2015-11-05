package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;

import java.util.List;

/**
 * Created by aglook on 2015/11/5.
 */
public class SearchGVAdapter extends BaseAdapter {
    private List<String>list;
    private Context context;

    public SearchGVAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }else {
            if (list.size()>5){
                return 5;
            }else {
                return list.size();
            }
        }
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_search_gridview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_search_gridView.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tv_search_gridView;

        ViewHolder(View view) {
            tv_search_gridView=(TextView)view.findViewById(R.id.tv_search_gridView);
        }
    }
}
