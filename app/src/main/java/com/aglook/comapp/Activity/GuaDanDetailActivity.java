package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.view.MyListView;
import com.aglook.comapp.view.Timestamp;

public class GuaDanDetailActivity extends BaseActivity {
    private String title="挂单-精选小米";
    private TextView tv_goods_image_gua_dan;
    private TextView tv_huo_quan_image_gua_dan;
    private TextView tv_goods_name_gua_dan;
    private TextView tv_cangdanhao_gua_dan;
    private TextView tv_goods_kind_gua_dan;
    private TextView tv_stock_weight_gua_dan;
    private TextView tv_use_weight_gua_dan;
    private TextView tv_in_time_gua_dan;
    private TextView tv_goods_area_gua_dan;
    private TextView tv_xue_tou_gua_dan;
    private TextView tv_userful_time_gua_dan;
    private TextView tv_cang_name_gua_dan;
    private TextView tv_cang_phone_gua_dan;
    private TextView tv_cang_email_gua_dan;
    private TextView tv_cang_address_gua_dan;
    private TextView tv_buyer_gua_dan;
    private TextView tv_goods_detail_gua_dan;
    private MyListView lv_gua_dan;

    private CangDanList cangDan;
    private ImageView iv_huowu;
    private ImageView iv_huoquan;

    @Override
    public void initView() {
        setContentView(R.layout.activity_gua_dan_detail);
//        ExitApplication.getInstance().addActivity(this);
        setTitleBar(title);
        init();
        click();
    }

    public void init(){
        cangDan = (CangDanList) getIntent().getSerializableExtra("guadan");
        tv_goods_name_gua_dan = (TextView) findViewById(R.id.tv_goods_name_gua_dan);
        tv_cangdanhao_gua_dan = (TextView) findViewById(R.id.tv_cangdanhao_gua_dan);
        tv_goods_kind_gua_dan = (TextView) findViewById(R.id.tv_goods_kind_gua_dan);
        tv_stock_weight_gua_dan = (TextView) findViewById(R.id.tv_stock_weight_gua_dan);
        tv_use_weight_gua_dan = (TextView) findViewById(R.id.tv_use_weight_gua_dan);
        tv_in_time_gua_dan = (TextView) findViewById(R.id.tv_in_time_gua_dan);
        tv_goods_area_gua_dan = (TextView) findViewById(R.id.tv_goods_area_gua_dan);
        tv_xue_tou_gua_dan = (TextView) findViewById(R.id.tv_xue_tou_gua_dan);
        tv_userful_time_gua_dan = (TextView) findViewById(R.id.tv_userful_time_gua_dan);
        tv_cang_name_gua_dan = (TextView) findViewById(R.id.tv_cang_name_gua_dan);
        tv_cang_phone_gua_dan = (TextView) findViewById(R.id.tv_cang_phone_gua_dan);
        tv_cang_email_gua_dan = (TextView) findViewById(R.id.tv_cang_email_gua_dan);
        tv_cang_address_gua_dan = (TextView) findViewById(R.id.tv_cang_address_gua_dan);
        tv_buyer_gua_dan = (TextView) findViewById(R.id.tv_buyer_gua_dan);
        tv_goods_detail_gua_dan = (TextView) findViewById(R.id.tv_goods_detail_gua_dan);
        lv_gua_dan = (MyListView) findViewById(R.id.lv_gua_dan);
        iv_huowu = (ImageView) findViewById(R.id.iv_huowu);
        iv_huoquan = (ImageView) findViewById(R.id.iv_huoquan);
        fillData();
    }
    //填充数据
    public void fillData() {
        if (cangDan != null) {
            XBitmap.displayImage(iv_huowu, cangDan.getGetlistPic(), GuaDanDetailActivity.this);
            XBitmap.displayImage(iv_huoquan, cangDan.getGoodsOwnerProve(), GuaDanDetailActivity.this);
            tv_goods_name_gua_dan.setText(cangDan.getPshCategory().getCategoryName());
            tv_cangdanhao_gua_dan.setText(cangDan.getListId());
            tv_goods_kind_gua_dan.setText(cangDan.getGoodsType());
            tv_stock_weight_gua_dan.setText(cangDan.getInnerWeight());
            tv_in_time_gua_dan.setText(Timestamp.getDateToString(cangDan.getInnerTime()));
            tv_goods_area_gua_dan.setText(cangDan.getGoodsPlace());
            tv_xue_tou_gua_dan.setText(cangDan.getMark());
            tv_cang_name_gua_dan.setText(cangDan.getDepotResponsible());
            tv_cang_phone_gua_dan.setText(cangDan.getResponsiblePhone());
            tv_cang_email_gua_dan.setText(cangDan.getResponsibleEmail());
            tv_cang_address_gua_dan.setText(cangDan.getDepotAddr());
            tv_goods_detail_gua_dan.setText(cangDan.getPshCategory().getCategoryDesc());
        }
    }

    public void click(){

    }

    @Override
    public void widgetClick(View view) {

    }


}
