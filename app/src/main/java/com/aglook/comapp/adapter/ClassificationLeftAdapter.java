package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Classify;

import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class ClassificationLeftAdapter extends BaseAdapter {
    private List<Classify> list;
    private Context context;
    private int imageArray[] = {R.drawable.liangshi, R.drawable.siliao, R.drawable.tongxun,
            R.drawable.youlei, R.drawable.youliao};
    private int position;

    public ClassificationLeftAdapter(List<Classify> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
//        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_classify_listview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (i == position) {
            holder.ll_classify_listview.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.tv_classify_lisview.setTextColor(context.getResources().getColor(R.color.red_a50000));
            holder.view_left.setVisibility(View.VISIBLE);
        } else {
            holder.ll_classify_listview.setBackgroundColor(context.getResources().getColor(R.color.gray_eeeeee));
            holder.tv_classify_lisview.setTextColor(context.getResources().getColor(R.color.textcolor_333333));
            holder.view_left.setVisibility(View.INVISIBLE);
        }

        Classify classify = list.get(i);
        holder.tv_classify_lisview.setText(classify.getName());
        return view;
    }

    class ViewHolder {
        TextView tv_classify_lisview;
        RelativeLayout ll_classify_listview;
        View view_left;
        ViewHolder(View view) {
            tv_classify_lisview = (TextView) view.findViewById(R.id.tv_classify_lisview);
            ll_classify_listview = (RelativeLayout) view.findViewById(R.id.ll_classify_listview);
            view_left=(View)view.findViewById(R.id.view_left);
        }
    }

    public void setSelectItem(int position) {
        this.position = position;
    }
}
