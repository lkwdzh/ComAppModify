package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.BandCardList;

import java.util.List;

/**
 * Created by aglook on 2015/11/13.
 */
public class BandCardDialogAdapter extends BaseAdapter {
    private Context context;
    private List<BandCardList>list;

    public BandCardDialogAdapter(Context context, List<BandCardList> list) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_band_dialog,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        BandCardList bandCardList = list.get(position);
        holder.tv_name_band_dialog.setText(bandCardList.getBankCodeName());
        return convertView;
    }

    class ViewHolder{
    TextView tv_name_band_dialog;

        ViewHolder(View view) {
            tv_name_band_dialog=(TextView)view.findViewById(R.id.tv_name_band_dialog);
        }
    }
}
