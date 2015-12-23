package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.GuaDanAddActivity;
import com.aglook.comapp.Activity.PickInfoActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.util.AppUtils;
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
        holder.tv_mai_all_order_lv.setVisibility(View.VISIBLE);
        holder.tv_mai_all_order_lv.setOnClickListener(this);
        holder.tv_trans_all_order_lv.setOnClickListener(this);
        holder.tv_tihuo_all_order_lv.setOnClickListener(this);
        holder.ll_1.setVisibility(View.INVISIBLE);
        holder.ll_3.setVisibility(View.INVISIBLE);
        holder.tv_success_all_order_lv.setVisibility(View.INVISIBLE);
        CangDanList cangDanList = list.get(position);
        holder.tv_house_num_my_cangdan.setText(cangDanList.getListId());
        if (cangDanList.getInnerTime() != null && !"".equals(cangDanList.getInnerTime())) {
            holder.tv_in_time_my_cangdan.setText(Timestamp.getDateTo(cangDanList.getInnerTime()));
        }
        XBitmap.displayImage(holder.iv_lv_lv, cangDanList.getGetlistPic(), context);
        holder.tv_name_lv_lv.setText(cangDanList.getPshCategory().getCategoryName());
        holder.tv_weight_lv_lv.setText(cangDanList.getWeightUseable() + "吨");

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_trans_all_order_lv:
                intent.setClass(context, GuaDanAddActivity.class);
                int index = (int) v.getTag();
                intent.putExtra("originalId", String.valueOf(list.get(index).getId()));
                intent.putExtra("code", "1002");
                intent.putExtra("codeGua", "1003");
                intent.putExtra("isPlate", false);
                context.startActivityForResult(intent, 1);
                break;
            case R.id.tv_tihuo_all_order_lv:
                intent.setClass(context, PickInfoActivity.class);
                int index1 = (int) v.getTag();
                intent.putExtra("tihuo", list.get(index1));
                intent.putExtra("originalId", String.valueOf(list.get(index1).getId()));
                intent.putExtra("code", "1002");
                intent.putExtra("isPlate", false);
                context.startActivityForResult(intent, 1);
                break;
            case R.id.tv_mai_all_order_lv:
                showDailog();
                break;
            case R.id.tv_boda:
                //打电话，并取消dialog
//                Intent intent1 = new Intent(Intent.ACTION_CALL);
//                intent1.setData(Uri.parse("tel:" + AppUtils.toStringTrim_TV(tv_phone_call)));
//                context.startActivity(intent1);
                // 必须明确使用mailto前缀来修饰邮件地址,如果使用
// intent.putExtra(Intent.EXTRA_EMAIL, email)，结果将匹配不到任何应用
                Uri uri = Uri.parse("mailto:" + AppUtils.toStringTrim_TV(tv_phone_call));
                String[] email = {AppUtils.toStringTrim_TV(tv_phone_call)};
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri);
                intent1.putExtra(Intent.EXTRA_CC, email); // 抄送人
                intent1.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
                intent1.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
                context.startActivity(Intent.createChooser(intent1, "请选择邮件类应用"));
                builder.dismiss();
                break;
            case R.id.tv_quxiao:
                builder.dismiss();
                break;
        }
    }

    class ViewHolder {
        ImageView iv_lv_lv;
        TextView tv_name_lv_lv;
        TextView tv_price_lv_lv;
        TextView tv_weight_lv_lv;
        TextView tv_num_lv_lv;
        TextView tv_house_num_my_cangdan;
        TextView tv_in_time_my_cangdan;
        TextView tv_trans_all_order_lv;
        TextView tv_tihuo_all_order_lv;
        LinearLayout ll_1;
        LinearLayout ll_3;
        TextView tv_success_all_order_lv;
        TextView tv_mai_all_order_lv;

        ViewHolder(View view) {
            iv_lv_lv = (ImageView) view.findViewById(R.id.iv_lv_lv);
            tv_name_lv_lv = (TextView) view.findViewById(R.id.tv_name_lv_lv);
            tv_price_lv_lv = (TextView) view.findViewById(R.id.tv_price_lv_lv);
            tv_weight_lv_lv = (TextView) view.findViewById(R.id.tv_weight_lv_lv);
            tv_num_lv_lv = (TextView) view.findViewById(R.id.tv_num_lv_lv);
            tv_house_num_my_cangdan = (TextView) view.findViewById(R.id.tv_house_num_my_cangdan);
            tv_in_time_my_cangdan = (TextView) view.findViewById(R.id.tv_in_time_my_cangdan);
            tv_trans_all_order_lv = (TextView) view.findViewById(R.id.tv_trans_all_order_lv);
            tv_tihuo_all_order_lv = (TextView) view.findViewById(R.id.tv_tihuo_all_order_lv);
            ll_1 = (LinearLayout) view.findViewById(R.id.ll_1);
            ll_3 = (LinearLayout) view.findViewById(R.id.ll_3);
            tv_success_all_order_lv = (TextView) view.findViewById(R.id.tv_success_all_order_lv);
            tv_mai_all_order_lv = (TextView) view.findViewById(R.id.tv_mai_all_order_lv);
        }
    }


    private Dialog dialog;
    private TextView tv_phone_call;
    private AlertDialog builder;
    private TextView tv_quxiao;
    private TextView tv_boda;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_alpha_dialog, null);
        tv_phone_call = (TextView) view.findViewById(R.id.tv_phone_call);
        tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        tv_boda = (TextView) view.findViewById(R.id.tv_boda);
        builder = new AlertDialog.Builder(context).create();
//        builder.create();
        builder.setView(view);
        Window window = builder.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置透明度为0.75
//        lp.alpha = 0.25f;
//        window.setAttributes(lp);
        builder.show();
//        builder.setCancelable(false);
//        dialog = builder.show();
        tv_quxiao.setOnClickListener(this);
        tv_boda.setOnClickListener(this);
    }

}
