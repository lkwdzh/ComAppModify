package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Fragment.ShoppingCartFragment;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.GoodsDetail;
import com.aglook.comapp.url.GoodsDetailUrl;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.ShareUtil;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.text.DecimalFormat;

public class GoodsDetailActivity extends BaseActivity {


    private TextView right_text;
    private TextView tv_detail_goods_detail;
    private TextView tv_price_goods_detail;
    private TextView tv_buy_goods_detail;
    private TextView tv_cangdanhao_goods_detail;
    private TextView tv_huowuzhonglei_goods_detail;
    private TextView tv_weight_goods_detail;
    private TextView tv_in_time_goods_detail;
    private TextView tv_producing_area_goods_detail;
    private TextView tv_xuetou_goods_detail;
    private TextView tv_userful_life_goods_detail;
    private TextView tv_name_goods_detail;
    private TextView tv_phone_goods_detail;
    private TextView tv_emailgoods_detail;
    private TextView tv_address_goods_detail;
    private TextView tv_goods_detail_goods_detail;

    private String productId;
    private String productNum = "1";
    private ComAppApplication comAppApplication;
    private GoodsDetail goodsDetail;
    private TextView tv_add_to_car_goods_detail;
    private CustomProgress customProgress;
    private ImageView left_icon;
    private String pointUser;
    private LinearLayout ll_shopping_cart_goods_detail;
    private TextView tv_num_goods_detail;
    private TextView tv_shoucang_goods_detail;
    private ImageView iv_shoucang_goods_detail;
    private LinearLayout ll_attention_goods_detail;

    private boolean isSelf;
    private ImageView iv_detail;
    private LinearLayout ll_kefu_goods_detail;
    private ImageView iv_hq;
    private ImageView right_image;

    private String url;
    private String title;
    private String imageUrl;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private LinearLayout ll_person_detail;
    private TextView tv_linkmanPerson_detail;
    private TextView tv_phonePerson_detail;
    private LinearLayout ll_company_detail;
    private TextView tv_companyName_detail;
    private TextView tv_linkmanCompany_detail;
    private TextView tv_phoneCompany_detail;
    private TextView tv_addressCompany_detail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_goods_detail);
        setTitleBar("商品详情");
//        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        getData();
        click();
    }

    public void init() {
//        right_text = (TextView) findViewById(R.id.right_text);
//        right_text.setVisibility(View.VISIBLE);
//        right_text.setText("更多");
        left_icon = (ImageView) findViewById(R.id.left_icon);
        right_image = (ImageView) findViewById(R.id.right_image);
        right_image.setImageResource(R.drawable.share);
        right_image.setVisibility(View.VISIBLE);
        customProgress = CustomProgress.show(GoodsDetailActivity.this, "", true);
        isSelf = getIntent().getBooleanExtra("isSelf", false);
        productId = getIntent().getStringExtra("productId");
        pointUser = getIntent().getStringExtra("pointUser");
        url = "http://www.decxagri.com/product/" + productId;
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        tv_detail_goods_detail = (TextView) findViewById(R.id.tv_detail_goods_detail);
        tv_price_goods_detail = (TextView) findViewById(R.id.tv_price_goods_detail);
        tv_buy_goods_detail = (TextView) findViewById(R.id.tv_buy_goods_detail);
        tv_cangdanhao_goods_detail = (TextView) findViewById(R.id.tv_cangdanhao_goods_detail);
        tv_huowuzhonglei_goods_detail = (TextView) findViewById(R.id.tv_huowuzhonglei_goods_detail);
        tv_weight_goods_detail = (TextView) findViewById(R.id.tv_weight_goods_detail);
        tv_in_time_goods_detail = (TextView) findViewById(R.id.tv_in_time_goods_detail);
        tv_producing_area_goods_detail = (TextView) findViewById(R.id.tv_producing_area_goods_detail);
        tv_xuetou_goods_detail = (TextView) findViewById(R.id.tv_xuetou_goods_detail);
        tv_userful_life_goods_detail = (TextView) findViewById(R.id.tv_userful_life_goods_detail);
        tv_name_goods_detail = (TextView) findViewById(R.id.tv_name_goods_detail);
        tv_phone_goods_detail = (TextView) findViewById(R.id.tv_phone_goods_detail);
        tv_emailgoods_detail = (TextView) findViewById(R.id.tv_emailgoods_detail);
        tv_address_goods_detail = (TextView) findViewById(R.id.tv_address_goods_detail);
        tv_goods_detail_goods_detail = (TextView) findViewById(R.id.tv_goods_detail_goods_detail);
        tv_add_to_car_goods_detail = (TextView) findViewById(R.id.tv_add_to_car_goods_detail);
        ll_shopping_cart_goods_detail = (LinearLayout) findViewById(R.id.ll_shopping_cart_goods_detail);
        tv_num_goods_detail = (TextView) findViewById(R.id.tv_num_goods_detail);
        tv_shoucang_goods_detail = (TextView) findViewById(R.id.tv_shoucang_goods_detail);
        iv_shoucang_goods_detail = (ImageView) findViewById(R.id.iv_shoucang_goods_detail);
        ll_attention_goods_detail = (LinearLayout) findViewById(R.id.ll_attention_goods_detail);
        ll_kefu_goods_detail = (LinearLayout) findViewById(R.id.ll_kefu_goods_detail);
        //个人信息
        ll_person_detail = (LinearLayout) findViewById(R.id.ll_person_detail);
        tv_linkmanPerson_detail = (TextView) findViewById(R.id.tv_linkmanPerson_detail);
        tv_phonePerson_detail = (TextView) findViewById(R.id.tv_phonePerson_detail);
        //公司信息
        ll_company_detail = (LinearLayout) findViewById(R.id.ll_company_detail);
        tv_companyName_detail = (TextView) findViewById(R.id.tv_companyName_detail);
        tv_linkmanCompany_detail = (TextView) findViewById(R.id.tv_linkmanCompany_detail);
        tv_phoneCompany_detail = (TextView) findViewById(R.id.tv_phoneCompany_detail);
        tv_addressCompany_detail = (TextView) findViewById(R.id.tv_addressCompany_detail);

        iv_hq = (ImageView) findViewById(R.id.iv_hq);
        //获取屏幕宽度

        int widgh = this.getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, widgh);
        iv_detail.setLayoutParams(params);
        iv_detail.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void click() {
        tv_buy_goods_detail.setOnClickListener(this);
        tv_add_to_car_goods_detail.setOnClickListener(this);
        left_icon.setOnClickListener(this);
        ll_shopping_cart_goods_detail.setOnClickListener(this);
        ll_attention_goods_detail.setOnClickListener(this);
        ll_kefu_goods_detail.setOnClickListener(this);
        right_image.setOnClickListener(this);
    }

    //    获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_GoodsDetail", productId + "-------" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                goodsDetail = JsonUtils.parse(obj, GoodsDetail.class);
                if (status.equals("1")) {
                    //成功
//                    Log.d("result_GoodsDetail_goodsDetail",goodsDetail+"");
                    fillData();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePostCheck(DefineUtil.PRODUCT_DETAIL, GoodsDetailUrl.postGoodsDetailUrl(DefineUtil.USERID, productId), GoodsDetailActivity.this);
    }

    //    填充数据
    public void fillData() {
        if (goodsDetail != null && !"".equals(goodsDetail)) {
            title = goodsDetail.getProductName();
            imageUrl = goodsDetail.getProductLogo();
            tv_detail_goods_detail.setText(goodsDetail.getProductName());
            tv_price_goods_detail.setText(goodsDetail.getProductMoney());
            tv_cangdanhao_goods_detail.setText(goodsDetail.getProductListId());
            tv_huowuzhonglei_goods_detail.setText(goodsDetail.getCategoryName());
            tv_weight_goods_detail.setText(goodsDetail.getProductSellNum() + "吨");
            if (goodsDetail.getInnerTime() != null) {
                tv_in_time_goods_detail.setText(Timestamp.getDateTo(goodsDetail.getInnerTime()));
            }
            tv_producing_area_goods_detail.setText(goodsDetail.getGoodsPlace());
            tv_xuetou_goods_detail.setText(goodsDetail.getMark());
            if (goodsDetail.getProductAvailable() != null) {
                tv_userful_life_goods_detail.setText(Timestamp.getDateTo(goodsDetail.getProductAvailable()));
            }
            tv_name_goods_detail.setText(goodsDetail.getDepotResponsible());
            tv_phone_goods_detail.setText(goodsDetail.getResponsibleMobile());
            tv_emailgoods_detail.setText(goodsDetail.getResponsibleEmail());
            tv_address_goods_detail.setText(goodsDetail.getDepotAddress());
            tv_goods_detail_goods_detail.setText(goodsDetail.getProductAppDesc());
            if (goodsDetail.getIsCollect().equals("1")) {
                tv_shoucang_goods_detail.setText("已收藏");
                iv_shoucang_goods_detail.setImageResource(R.drawable.guanzhu_checked);
            } else {
                tv_shoucang_goods_detail.setText("收藏");
                iv_shoucang_goods_detail.setImageResource(R.drawable.guanzhu);
            }

            if (goodsDetail.getProductLogo() != null && !"".equals(goodsDetail.getProductLogo())) {
                XBitmap.displayImage(iv_detail, goodsDetail.getProductLogo(), GoodsDetailActivity.this);
            }

            if (goodsDetail.getProductOwnerProve() != null && !"".equals(goodsDetail.getProductOwnerProve())) {
                XBitmap.displayImage(iv_hq, goodsDetail.getProductOwnerProve(), GoodsDetailActivity.this);
            }

            //判断
            //如果是公司，
            if (goodsDetail.getUserType() == 1) {
                ll_company_detail.setVisibility(View.VISIBLE);
                ll_person_detail.setVisibility(View.GONE);
                //如果非匿名
                if (goodsDetail.getAnonymous() == 0) {
                    tv_companyName_detail.setText(goodsDetail.getSellUserCompany());
                    tv_linkmanCompany_detail.setText(goodsDetail.getUserJname());
                    tv_phoneCompany_detail.setText(goodsDetail.getSellUserPhone());
                    tv_addressCompany_detail.setText(goodsDetail.getUserZcdz());
                } else {

                    if (goodsDetail.getSellUserCompany()!=null&&goodsDetail.getSellUserCompany().length() > 3) {
                        tv_companyName_detail.setText(goodsDetail.getSellUserCompany().substring(0, 3) + transStar(goodsDetail.getSellUserCompany().length() - 3));
                    }else {
                        tv_companyName_detail.setText(goodsDetail.getSellUserCompany());
                    }
                    if (goodsDetail.getUserJname().length() > 1) {
                        tv_linkmanCompany_detail.setText(goodsDetail.getUserJname().substring(0, 1) + transStar(goodsDetail.getUserJname().length() - 1));
                    }else {
                        tv_linkmanCompany_detail.setText(goodsDetail.getUserJname());
                    }
                    if (goodsDetail.getSellUserPhone().length() > 3) {
                        tv_phoneCompany_detail.setText(goodsDetail.getSellUserPhone().substring(0, 3) + transStar(goodsDetail.getSellUserPhone().length() - 3));
                    }else {
                        tv_phoneCompany_detail.setText(goodsDetail.getSellUserPhone());
                    }
                    if (goodsDetail.getUserZcdz().length() > 3) {
                        tv_addressCompany_detail.setText(goodsDetail.getUserZcdz().substring(0, 3) + transStar(goodsDetail.getUserZcdz().length() - 3));
                    }else {
                        tv_addressCompany_detail.setText(goodsDetail.getUserZcdz());
                    }
                }

            } else {
                ll_company_detail.setVisibility(View.GONE);
                ll_person_detail.setVisibility(View.VISIBLE);
                //如果非匿名
                if (goodsDetail.getAnonymous() == 0) {
                    tv_linkmanPerson_detail.setText(goodsDetail.getUserTname());
                    tv_phonePerson_detail.setText(goodsDetail.getSellUserPhone());
                } else {
                    if (goodsDetail.getUserTname().length() > 1) {
                        tv_linkmanPerson_detail.setText(goodsDetail.getUserTname().substring(0, 1) + transStar(goodsDetail.getUserTname().length() - 1));
                    }else {
                        tv_linkmanPerson_detail.setText(goodsDetail.getUserTname());
                    }
                    if (goodsDetail.getSellUserPhone().length() > 3) {
                        tv_phonePerson_detail.setText(goodsDetail.getSellUserPhone().substring(0, 3) + transStar(goodsDetail.getSellUserPhone().length() - 3));
                    }else {
                        tv_phonePerson_detail.setText(goodsDetail.getSellUserPhone());
                    }
                }
            }

        }
//        //判断是否已经登录，若登录则显示购物车个数，否则不显示
//        if (comAppApplication.getLogin() != null && !"".equals(comAppApplication.getLogin())) {
//            if (DefineUtil.NUM != 0) {
//                //假如已登录
//                tv_num_goods_detail.setVisibility(View.VISIBLE);
//                tv_num_goods_detail.setText(DefineUtil.NUM + "");
//            } else {
//                tv_num_goods_detail.setVisibility(View.INVISIBLE);
//            }
//        } else {
//            tv_num_goods_detail.setVisibility(View.INVISIBLE);
//        }

        //假如已收藏，就显示收藏，若未收藏，就显示为收藏
//        if (已收藏){
//            tv_shoucang_goods_detail.setText("已收藏");
//            cb_shoucang_goods_detail.setChecked(true);
//        }else {
//            tv_shoucang_goods_detail.setText("收藏");
//            cb_shoucang_goods_detail.setChecked(false);
//        }

    }

    public String transStar(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += "*";
        }
        return str;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //判断是否已经登录，若登录则显示购物车个数，否则不显示
        if (comAppApplication.getLogin() != null && !"".equals(comAppApplication.getLogin())) {
            if (DefineUtil.NUM != 0) {
                //假如已登录
                tv_num_goods_detail.setVisibility(View.VISIBLE);
                DefineUtil.NUM = Double.parseDouble(decimalFormat.format(DefineUtil.NUM));
                tv_num_goods_detail.setText(DefineUtil.NUM + "");
            } else {
                tv_num_goods_detail.setVisibility(View.INVISIBLE);
            }
        } else {
            tv_num_goods_detail.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
//            addCart();
        } else if (requestCode == 2 && resultCode == 1) {
            Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
            startActivity(intent);
        } else if (requestCode == 3 && resultCode == 1) {
            getData();
        }
    }


    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_image:
                ShareUtil.Share(GoodsDetailActivity.this, title, url);
                break;
            case R.id.tv_boda:
                //打电话，并取消dialog
//                Intent intent1 = new Intent(Intent.ACTION_CALL);
//                intent1.setData(Uri.parse("tel:"+AppUtils.toStringTrim_TV(tv_phone_call)));
//                startActivity(intent1);

//                String[] email = {"771915131@qq.com"}; // 需要注意，email必须以数组形式传入
//                Intent intent1 = new Intent(Intent.ACTION_SEND);
//                intent1.setType("message/rfc822"); // 设置邮件格式
//                intent1.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
//                intent1.putExtra(Intent.EXTRA_CC, email); // 抄送人
//                intent1.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
//                intent1.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
//                startActivity(Intent.createChooser(intent1, "请选择邮件类应用"));

                // 必须明确使用mailto前缀来修饰邮件地址,如果使用
// intent.putExtra(Intent.EXTRA_EMAIL, email)，结果将匹配不到任何应用
                Uri uri = Uri.parse("mailto:" + AppUtils.toStringTrim_TV(tv_phone_call));
                String[] email = {AppUtils.toStringTrim_TV(tv_phone_call)};
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri);
                intent1.putExtra(Intent.EXTRA_CC, email); // 抄送人
                intent1.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
                intent1.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
                startActivity(Intent.createChooser(intent1, "请选择邮件类应用"));

                builder.dismiss();
                break;
            case R.id.tv_quxiao:
                builder.dismiss();
                break;
            case R.id.ll_kefu_goods_detail:
                showDailog();
                break;
            case R.id.tv_buy_goods_detail:
//                立即购买
                if (comAppApplication.getLogin() == null || comAppApplication.getLogin().equals("")) {
                    intent.setClass(GoodsDetailActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 2);
                } else {
                    intent.setClass(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_add_to_car_goods_detail:
                //加入购物车
                //判断是否已经登录，若登录则添加，否则跳转到登录界面
                if (comAppApplication.getLogin() == null || comAppApplication.getLogin().equals("")) {
                    intent.setClass(GoodsDetailActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    //判断是否是从挂单进去，若从挂单进入则无法购买自己的
                    if (isSelf) {
                        AppUtils.toastText(GoodsDetailActivity.this, "不能购买自己出售的商品");
                    } else {
                        //判断剩余重量是否为0
                        if (goodsDetail.getProductSellNum() == 0) {
                            AppUtils.toastText(GoodsDetailActivity.this, "商品已卖完");
                        } else {
                            if (goodsDetail.getProductSellNum() >= 1) {
                                productNum = "1";
                            } else {
                                productNum = String.valueOf(goodsDetail.getProductSellNum());
                            }
                            addCart();
                        }
                    }
                }
                break;
            case R.id.left_icon:
                intent.setClass(GoodsDetailActivity.this, ShoppingCartFragment.class);
                GoodsDetailActivity.this.setResult(1, intent);
                GoodsDetailActivity.this.finish();
                break;
            case R.id.ll_shopping_cart_goods_detail:
                if (comAppApplication.getLogin() == null || comAppApplication.getLogin().equals("")) {
                    intent.setClass(GoodsDetailActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 3);
                } else {
                    intent.setClass(GoodsDetailActivity.this, ShoppingCartActivity.class);
//                    intent.putExtra("isGoods", true);
                    startActivityForResult(intent, 3);
                }
                break;
            case R.id.ll_attention_goods_detail:
                //先判断是否已经登录，未登录则调到登录，已登录则判断是否收藏
                if (comAppApplication.getLogin() == null || comAppApplication.getLogin().equals("")) {
                    intent.setClass(GoodsDetailActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 3);
                } else {
                    if (goodsDetail.getIsCollect() != null && !"".equals(goodsDetail.getIsCollect())) {
                        if (goodsDetail.getIsCollect().equals("0")) {
                            //未收藏则收藏
                            customProgress = CustomProgress.show(GoodsDetailActivity.this, "", true);
                            ShouCang();
                        } else {
                            //已收藏则删除
                            customProgress = CustomProgress.show(GoodsDetailActivity.this, "", true);
                            deleteSC();
                        }
                    }
                }
        }
    }


    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(GoodsDetailActivity.this, ShoppingCartFragment.class);
            GoodsDetailActivity.this.setResult(1, intent);
            GoodsDetailActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    //添加到购物车
    public void addCart() {
        customProgress = CustomProgress.show(GoodsDetailActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_class_ada", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    AppUtils.toastText(GoodsDetailActivity.this, message);
                    DefineUtil.NUM += Double.parseDouble(productNum);
                    DefineUtil.NUM = Double.parseDouble(decimalFormat.format(DefineUtil.NUM));
                    tv_num_goods_detail.setVisibility(View.VISIBLE);
                    tv_num_goods_detail.setText(DefineUtil.NUM + "");
                    Intent intent = new Intent();
                    intent.setAction("MainActivity");
                    sendBroadcast(intent);
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.ADDCART, ShoppingCartUrl.postAddCartUrl(DefineUtil.USERID, DefineUtil.TOKEN, productId, productNum, pointUser), GoodsDetailActivity.this);
    }


    //收藏
    public void ShouCang() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_shoucang", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    tv_shoucang_goods_detail.setText("已收藏");
                    iv_shoucang_goods_detail.setImageResource(R.drawable.guanzhu_checked);
                    goodsDetail.setIsCollect("1");
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.COLLECT, GoodsDetailUrl.postShouUrl(DefineUtil.TOKEN, DefineUtil.USERID, productId), GoodsDetailActivity.this);
    }

    //取消收藏
    public void deleteSC() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_delete", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    tv_shoucang_goods_detail.setText("收藏");
                    iv_shoucang_goods_detail.setImageResource(R.drawable.guanzhu);
                    goodsDetail.setIsCollect("0");
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.DELETE_COLLECT, GoodsDetailUrl.postDeleteUrl(DefineUtil.TOKEN, DefineUtil.USERID, productId), GoodsDetailActivity.this);
    }

    private Dialog dialog;
    private TextView tv_phone_call;
    private AlertDialog builder;
    private TextView tv_quxiao;
    private TextView tv_boda;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) GoodsDetailActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_alpha_dialog, null);
        tv_phone_call = (TextView) view.findViewById(R.id.tv_phone_call);
        tv_quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        tv_boda = (TextView) view.findViewById(R.id.tv_boda);
        builder = new AlertDialog.Builder(GoodsDetailActivity.this).create();
//        builder.create();
        builder.setView(view);
        Window window = builder.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置透明度为0.75
//        lp.alpha = 0.55f;
//        window.setAttributes(lp);
        builder.show();
//        window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//        builder.setCancelable(false);
//        dialog = builder.show();
        tv_quxiao.setOnClickListener(this);
        tv_boda.setOnClickListener(this);
    }


}
