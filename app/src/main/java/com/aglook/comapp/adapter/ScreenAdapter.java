package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Screen;
import com.aglook.comapp.util.XBitmap;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class ScreenAdapter extends BaseAdapter {
    private Context context;
    private List<Screen> list;

    public ScreenAdapter(Context context, List<Screen> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_screen_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Screen screen = list.get(position);
        XBitmap.displayImage(holder.iv_screen_gridview, screen.getProductLogo(), context);
        holder.tv_address_screen_gridview.setText(screen.getGoodPlace());
        holder.tv_grade_screen_gridview.setText(screen.getGoodType());
        holder.tv_weight_screen_gridview.setText(screen.getProductSellNum());
        holder.tv_price_screen_gridview.setText(screen.getProductMoney());
        holder.tv_name_screen_gridview.setText(screen.getProductName());
        if (screen.getIsAppoint()!=null&&!"".equals(screen.getIsAppoint())){

        if (screen.getIsAppoint().equals("1")){
            holder.iv_dx.setVisibility(View.VISIBLE);
        }else {
            holder.iv_dx.setVisibility(View.INVISIBLE);
        }
        }
        return convertView;
    }

    class ViewHolder {

        ImageView iv_screen_gridview;
        TextView tv_address_screen_gridview;
        TextView tv_grade_screen_gridview;
        TextView tv_weight_screen_gridview;
        TextView tv_price_screen_gridview;
        ImageView iv_dx;
        TextView tv_name_screen_gridview;

        ViewHolder(View view) {
            iv_dx=(ImageView)view.findViewById(R.id.iv_dx);
            iv_screen_gridview = (ImageView) view.findViewById(R.id.iv_screen_gridview);
            tv_address_screen_gridview = (TextView) view.findViewById(R.id.tv_address_screen_gridview);
            tv_grade_screen_gridview = (TextView) view.findViewById(R.id.tv_grade_screen_gridview);
            tv_weight_screen_gridview = (TextView) view.findViewById(R.id.tv_weight_screen_gridview);
            tv_price_screen_gridview = (TextView) view.findViewById(R.id.tv_price_screen_gridview);
            tv_name_screen_gridview = (TextView) view.findViewById(R.id.tv_name_screen_gridview);
        }

    }
}
