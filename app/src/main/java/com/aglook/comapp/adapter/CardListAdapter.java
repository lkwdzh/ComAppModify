package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.CardList;

import java.util.List;

/**
 * Created by aglook on 2015/11/9.
 */
public class CardListAdapter extends BaseAdapter {
    private Context context;
    private List<CardList>list;

    public CardListAdapter(Context context, List<CardList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        return  list!=null?list.size():0;
        return 13;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_card_list,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

//        CardList cardList = list.get(position);
//        holder.tv_bank_name_card_lv.setText(cardList.getBankAlis());
//        holder.tv_success_card_lv.setText(cardList.getDefaultType());
//        holder.tv_num_card_lv.setText(cardList.getCardNo());
//        holder.tv_type_card_lv.setText(cardList.getCardType());
        return convertView;
    }

    class ViewHolder{
        ImageView iv_card_lv;
        TextView tv_bank_name_card_lv;
        TextView tv_success_card_lv;
        TextView tv_type_card_lv;
        TextView tv_num_card_lv;
        TextView tv_is_moren_card_list;

        ViewHolder(View view) {
            iv_card_lv=(ImageView)view.findViewById(R.id.iv_card_lv);
            tv_bank_name_card_lv = (TextView) view.findViewById(R.id.tv_bank_name_card_lv);
            tv_success_card_lv=(TextView)view.findViewById(R.id.tv_success_card_lv);
            tv_type_card_lv=(TextView)view.findViewById(R.id.tv_type_card_lv);
            tv_num_card_lv=(TextView)view.findViewById(R.id.tv_num_card_lv);
            tv_is_moren_card_list=(TextView)view.findViewById(R.id.tv_is_moren_card_list);
        }
    }
}
