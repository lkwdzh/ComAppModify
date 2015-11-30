package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.ZiXunList;

import java.util.List;

/**
 * Created by aglook on 2015/11/10.
 */
public class ZiXunListAdapter extends BaseAdapter {
    private Context context;
    private List<ZiXunList>list;

    public ZiXunListAdapter(Context context, List<ZiXunList> list) {
        this.context = context;
        this.list = list;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_hang_qing_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ZiXunList zixun = list.get(position);
        holder.tv_hang_qing_list.setText(zixun.getArticleName());
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
