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
 * Created by aglook on 2015/12/2.
 */
public class HomePageZiAdapter extends BaseAdapter{
    private Context context;
    private int[] imageArray={R.drawable.kandian,R.drawable.hangqin,R.drawable.zoushi,R.drawable.gonggao,R.drawable.wangzhan,R.drawable.zhishi,R.drawable.buy,R.drawable.mai};
    private String[] str={"每日看点","行情报告","品种走势","品种公告","网站公告","知识学堂","帮助中心","我要卖"};
    public HomePageZiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
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
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_hp_zi,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.iv_ho_zi.setImageResource(imageArray[position]);
        holder.tv_ho_zi.setText(str[position]);
        return convertView;
    }

    class ViewHolder{
        TextView tv_ho_zi;
        ImageView iv_ho_zi;

        ViewHolder(View view) {
            tv_ho_zi=(TextView)view.findViewById(R.id.tv_ho_zi);
            iv_ho_zi=(ImageView)view.findViewById(R.id.iv_ho_zi);
        }
    }
}
