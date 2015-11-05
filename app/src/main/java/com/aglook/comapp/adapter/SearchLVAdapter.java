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
public class SearchLVAdapter extends BaseAdapter {
    private List<String >list;
    private Context context;

    public SearchLVAdapter(List<String> list, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_search_listview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_search_listview.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView tv_search_listview;

        ViewHolder(View view) {
            tv_search_listview=(TextView)view.findViewById(R.id.tv_search_listview);
        }
    }

}
