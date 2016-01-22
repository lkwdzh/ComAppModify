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
    private List<CardList> list;
//    private int imageArray[] = {R.drawable.beijing, R.drawable.gongshang, R.drawable.guangda, R.drawable.huaxia, R.drawable.jianshe
//            , R.drawable.jiaotong, R.drawable.minsheng, R.drawable.nongye, R.drawable.pingan, R.drawable.shanghai,
//            R.drawable.xingye, R.drawable.youzheng, R.drawable.zhaoshang, R.drawable.zhongguo, R.drawable.zhongxin};

    public CardListAdapter(Context context, List<CardList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_card_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CardList cardList = list.get(position);
        holder.tv_bank_name_card_lv.setText(cardList.getBankAlis());
        holder.tv_success_card_lv.setText(cardList.getDefaultType());
        String cardNo = cardList.getCardNo();
            String str = "";
        if (cardNo.length()>8) {
            String cardNoCenter = cardNo.substring(4, cardNo.length() - 4);
            for (int i = 0; i < cardNoCenter.length(); i++) {
//            cardNoCenter.replace(cardNoCenter.charAt(i),'*');
                str += "*";
            }
        }
//        holder.tv_num_card_lv.setText(cardList.getCardNo());
        holder.tv_num_card_lv.setText(cardNo.substring(0,4)+" "+str+" "+cardNo.substring(cardNo.length()-4,cardNo.length()));
        holder.tv_type_card_lv.setText(cardList.getCardType());
        if (cardList.getDefaultType().equals("1")) {
            holder.tv_is_moren_card_list.setVisibility(View.VISIBLE);
        }else {
            holder.tv_is_moren_card_list.setVisibility(View.GONE);
        }
        if (cardList.getCardType().equals("0")) {
            holder.tv_type_card_lv.setText("储蓄卡");
        } else {
            holder.tv_type_card_lv.setText("信用卡");
        }
//        holder.iv_card_lv.setImageResource(R.drawable.bejing);
        String bankCode = cardList.getBankCode();
        if (bankCode.equals("6")){
            holder.iv_card_lv.setImageResource(R.drawable.bejing);
        }else if (bankCode.equals("62")){
            holder.iv_card_lv.setImageResource(R.drawable.huaxia);
        }else if (bankCode.equals("76")){
            holder.iv_card_lv.setImageResource(R.drawable.jiaotong);
        }else if (bankCode.equals("109")){
            holder.iv_card_lv.setImageResource(R.drawable.pingan);
        }else if (bankCode.equals("123")){
            holder.iv_card_lv.setImageResource(R.drawable.shanghai);
        }else if (bankCode.equals("156")){
            holder.iv_card_lv.setImageResource(R.drawable.zhaoshang);
        }else if (bankCode.equals("166")){
            holder.iv_card_lv.setImageResource(R.drawable.gongshang);
        }else if (bankCode.equals("167")){
            holder.iv_card_lv.setImageResource(R.drawable.guangda);
        }else if (bankCode.equals("168")){
            holder.iv_card_lv.setImageResource(R.drawable.jianshe);
        }else if (bankCode.equals("169")){
            holder.iv_card_lv.setImageResource(R.drawable.minsheng);
        }else if (bankCode.equals("170")){
            holder.iv_card_lv.setImageResource(R.drawable.nongye);
        }else if (bankCode.equals("171")){
            holder.iv_card_lv.setImageResource(R.drawable.zhongguo);
        }else if (bankCode.equals("172")){
            holder.iv_card_lv.setImageResource(R.drawable.youzheng);
        }else if (bankCode.equals("173")){
            holder.iv_card_lv.setImageResource(R.drawable.zhongxin);
        }else if (bankCode.equals("177")){
            holder.iv_card_lv.setImageResource(R.drawable.xingye);
        }else {
            holder.iv_card_lv.setImageResource(R.drawable.moren);
        }


        return convertView;
    }

    class ViewHolder {
        ImageView iv_card_lv;
        TextView tv_bank_name_card_lv;
        TextView tv_success_card_lv;
        TextView tv_type_card_lv;
        TextView tv_num_card_lv;
        TextView tv_is_moren_card_list;

        ViewHolder(View view) {
            iv_card_lv = (ImageView) view.findViewById(R.id.iv_card_lv);
            tv_bank_name_card_lv = (TextView) view.findViewById(R.id.tv_bank_name_card_lv);
            tv_success_card_lv = (TextView) view.findViewById(R.id.tv_success_card_lv);
            tv_type_card_lv = (TextView) view.findViewById(R.id.tv_type_card_lv);
            tv_num_card_lv = (TextView) view.findViewById(R.id.tv_num_card_lv);
            tv_is_moren_card_list = (TextView) view.findViewById(R.id.tv_is_moren_card_list);
        }
    }
}
