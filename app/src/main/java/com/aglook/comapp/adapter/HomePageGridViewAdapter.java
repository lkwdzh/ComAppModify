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
 * Created by aglook on 2015/10/30.
 */
public class HomePageGridViewAdapter extends BaseAdapter {
    private Context context;

    public HomePageGridViewAdapter(Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_home_page_mygridview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        ImageView iv_myGridView_homepage;
        TextView tv_name_homepage, tv_address_mygridview, tv_grade_mygridview, tv_weight_mygridview, tv_price_mygridview;

        ViewHolder(View view) {
            iv_myGridView_homepage = (ImageView) view.findViewById(R.id.iv_myGridView_homepage);
            tv_name_homepage = (TextView) view.findViewById(R.id.tv_name_homepage);
            tv_address_mygridview = (TextView) view.findViewById(R.id.tv_address_mygridview);
            tv_grade_mygridview = (TextView) view.findViewById(R.id.tv_grade_mygridview);
            tv_weight_mygridview = (TextView) view.findViewById(R.id.tv_weight_mygridview);
            tv_price_mygridview = (TextView) view.findViewById(R.id.tv_price_mygridview);

        }

    }
}
