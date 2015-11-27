package com.aglook.comapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PickInfoActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.view.Timestamp;

import java.util.List;

/**
 * Created by aglook on 2015/11/9.
 */
public class MyCangDanAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity context;
    private List<CangDanList> list;

    public MyCangDanAdapter(Activity context, List<CangDanList> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_my_cang_dan, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            holder.tv_trans_all_order_lv.setTag(position);
            holder.tv_tihuo_all_order_lv.setTag(position);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_trans_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        holder.ll_1.setVisibility(View.INVISIBLE);
        holder.ll_3.setVisibility(View.INVISIBLE);
        CangDanList cangDanList = list.get(position);
        holder.tv_house_num_my_cangdan.setText(cangDanList.getListId());
        holder.tv_in_time_my_cangdan.setText(Timestamp.getDateTo(cangDanList.getInnerTime()));
        XBitmap.displayImage(holder.iv_lv_lv, cangDanList.getGetlistPic(), context);
        holder.tv_name_lv_lv.setText(cangDanList.getPshCategory().getCategoryName());
        holder.tv_type_lv_lv.setText(cangDanList.getGoodsType());
        holder.tv_grade_lv_lv.setText(cangDanList.getDepotQuality());
        holder.tv_address_lv_lv.setText(cangDanList.getGoodsPlace());
        holder.tv_weight_lv_lv.setText(cangDanList.getInnerWeight() + "吨");

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_trans_all_order_lv:
                intent.setClass(context, GuaDanAddActivity.class);
                int index = (int) v.getTag();
                intent.putExtra("guadan", list.get(index));
                context.startActivityForResult(intent, 1);
                break;
            case R.id.tv_tihuo_all_order_lv:
                intent.setClass(context, PickInfoActivity.class);
                int index1 = (int) v.getTag();
                intent.putExtra("tihuo", list.get(index1));
                context.startActivity(intent);
                break;
        }
    }

    class ViewHolder {
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_type_lv_lv;
        TextView tv_grade_lv_lv;
        TextView tv_address_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        LinearLayout ll_1;
        LinearLayout ll_3;

        ViewHolder(View view) {
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_type_lv_lv = (TextView) view.findViewById(R.id.tv_type_lv_lv);
            tv_grade_lv_lv = (TextView) view.findViewById(R.id.tv_grade_lv_lv);
            tv_address_lv_lv = (TextView) view.findViewById(R.id.tv_address_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
            ll_1 = (LinearLayout) view.findViewById(R.id.ll_1);
            ll_3 = (LinearLayout) view.findViewById(R.id.ll_3);
        }
    }
}
