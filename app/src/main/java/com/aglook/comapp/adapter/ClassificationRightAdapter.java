package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.ClassifyGV;

import java.util.List;

/**
 * Created by aglook on 2015/11/2.
 */
public class ClassificationRightAdapter extends BaseAdapter {
    private Context context;
    private List<ClassifyGV> list;

    public ClassificationRightAdapter(Context context, List<ClassifyGV> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_classify_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ClassifyGV classifyGV = list.get(position);
        holder.tv_classify_gridvew.setText(classifyGV.getCategoryName());

        return convertView;
    }

    class ViewHolder {
        TextView tv_classify_gridvew;

        ViewHolder(View view) {
            tv_classify_gridvew = (TextView) view.findViewById(R.id.tv_classify_gridvew);
        }
    }
}
