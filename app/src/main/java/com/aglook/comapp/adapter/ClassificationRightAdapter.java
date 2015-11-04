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
 * Created by aglook on 2015/11/2.
 */
public class ClassificationRightAdapter extends BaseAdapter {
    private Context context;

    public ClassificationRightAdapter(Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_classify_gridview,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }
    class ViewHolder{
        TextView tv_classify_gridvew;
        ImageView iv_classify_gridview;

        ViewHolder(View view) {
            iv_classify_gridview=(ImageView)view.findViewById(R.id.iv_classify_gridview);
            tv_classify_gridvew=(TextView)view.findViewById(R.id.tv_classify_gridvew);
        }
    }
}
