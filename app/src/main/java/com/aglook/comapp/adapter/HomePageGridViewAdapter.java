package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.HomePage;

import java.util.List;

/**
 * Created by aglook on 2015/10/30.
 */
public class HomePageGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<HomePage>list;

    public HomePageGridViewAdapter(Context context, List<HomePage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        return list!=null?list.size():list.size();
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_home_page_mygridview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }

//        HomePage homePage = list.get(position);
//        holder.tv_name_homepage.setText(homePage.getProductName());
//        holder.tv_address_mygridview.setText(homePage.getGoodPlace());
//        holder.tv_grade_mygridview.setText(homePage.getGoodType());
//        holder.tv_weight_mygridview.setText(homePage.getProductSellNum());
//        holder.tv_price_mygridview.setText(homePage.getProductMoney());
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
