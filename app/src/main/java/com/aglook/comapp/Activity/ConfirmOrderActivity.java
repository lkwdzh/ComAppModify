package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ConfirmOrderAdapter;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.ConfirmOrderUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.aglook.comapp.R.id;

public class ConfirmOrderActivity extends BaseActivity {

    private TextView tv_name_confirm_order;
    private TextView tv_phone_confirm_order;
    private ListView lv_confirm_order;
    private TextView tv_money_confirm_order;
    private TextView tv_confirm_confirm_order;
    private ConfirmOrderAdapter adapter;
    private ComAppApplication comAppApplication;
    private Login login;
    private List<ShoppingCart> mList = new ArrayList<>();
    private double goodsMoney;
    private double costMoney;
    private double allMoney;
    private TextView tv_zonge_confirm_order;
    private TextView tv_shouxufei_confirm_order;
    private String text;
    private String cartids;
    private ImageView left_icon;

    public static ConfirmOrderActivity instance = null;


    @Override
    public void initView() {
        setContentView(R.layout.activity_confirm_order);
        setTitleBar("确认订单");
        instance = this;
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        click();
    }

    public void init() {
        login = comAppApplication.getLogin();
        mList = (List<ShoppingCart>) getIntent().getSerializableExtra("CharList");
        tv_name_confirm_order = (TextView) findViewById(id.tv_name_confirm_order);
        tv_phone_confirm_order = (TextView) findViewById(id.tv_phone_confirm_order);
        lv_confirm_order = (ListView) findViewById(id.lv_confirm_order);
        tv_money_confirm_order = (TextView) findViewById(id.tv_money_confirm_order);
        tv_confirm_confirm_order = (TextView) findViewById(id.tv_confirm_confirm_order);
        View view = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.footview_confirm_order, null);
        lv_confirm_order.addFooterView(view);
        tv_zonge_confirm_order = (TextView) view.findViewById(id.tv_zonge_confirm_order);
        tv_shouxufei_confirm_order = (TextView) view.findViewById(id.tv_shouxufei_confirm_order);
        left_icon = (ImageView) findViewById(id.left_icon);
        addCostMoney();
    }

    //填充数据
    public void fillData() {
        if (login != null) {
            tv_name_confirm_order.setText(login.getPshUser().getUsername());
            tv_phone_confirm_order.setText(login.getPshUser().getUserPhone());
            tv_zonge_confirm_order.setText(goodsMoney + "");
            tv_shouxufei_confirm_order.setText(costMoney + "");
            tv_money_confirm_order.setText(allMoney + "");
        }
    }

    private DecimalFormat df;

    /**
     * 先判断是否要计算手续费，若需要，则再判断是否是指定买家，
     * 若不是，则计算手续费
     */
    //计算手续费并且添加到实体类中
    public void addCostMoney() {
        df = new DecimalFormat("#.00");
        for (int i = 0; i < mList.size(); i++) {
            ShoppingCart cart = mList.get(i);
            double d1 = cart.getProductMoney() * cart.getProductNum() * login.getPshUser().getRate();

            String format = df.format(d1);
//            判断是否要计算手续费
            if (login.getIsNeedFee() != null && !"".equals(login.getIsNeedFee())) {
                if (login.getIsNeedFee().equals("1")) {
                    //需要计算手续费
//                    判断是否指定买家
                    if (cart.getPointUser() != null && !"".equals(cart.getPointUser())) {
                        if (cart.getPointUser().equals("0")) {
                            //不是指定买家，需要手续费
                            cart.setCostMoney(Double.parseDouble(format));
                        } else {
                            cart.setCostMoney(0.0);
                        }
                    }
                } else {
                    cart.setCostMoney(0.0);
                }
            }

        }
        adapter = new ConfirmOrderAdapter(ConfirmOrderActivity.this, mList);
        lv_confirm_order.setAdapter(adapter);

        //计算各种总额
        for (int i = 0; i < mList.size(); i++) {
            ShoppingCart cart = mList.get(i);
            goodsMoney += cart.getProductMoney() * cart.getProductNum();
            costMoney += cart.getCostMoney();
            String format = df.format(costMoney);
            costMoney = Double.parseDouble(format);
        }
        allMoney = goodsMoney + costMoney;

        fillData();
        putJSON();
    }


    /**
     * 拼接json字符串
     */
    public void putJSON() {
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
//        List<String >list=new ArrayList<>();
        //TODO 拼接字符串
        for (int i = 0; i < mList.size(); i++) {
            jsonObject = new JSONObject();
            try {
                jsonObject.put("cartId", mList.get(i).getCartId());
                jsonObject.put("fee", mList.get(i).getCostMoney());
                jsonArray.add(i, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            list.add(mList.get(i).getCartId());
            cartids = jsonArray.toString();
        }
//        cartids=list.toString().substring(1,list.size()-1);
    }

    public void click() {
        tv_confirm_confirm_order.setOnClickListener(this);
        left_icon.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_cancel_pay_popup:
                ConfirmOrderActivity.this.setResult(1);
                ConfirmOrderActivity.this.finish();
                dialog.dismiss();
                break;
            case R.id.btn_confirm_pay_popup:
                intent.setClass(ConfirmOrderActivity.this, CardListActivity.class);
                ConfirmOrderActivity.this.finish();
                DefineUtil.FLAG = 1;
                startActivity(intent);
                dialog.dismiss();
                break;
            case R.id.tv_confirm_confirm_order:
                getData();
                break;
            case R.id.left_icon:
//                intent.setClass(ConfirmOrderActivity.this, ShoppingCartFragment.class);
                ConfirmOrderActivity.this.setResult(1);
                ConfirmOrderActivity.this.finish();
                break;
        }
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            Intent intent = new Intent(ConfirmOrderActivity.this, ShoppingCartFragment.class);
            ConfirmOrderActivity.this.setResult(1);
            ConfirmOrderActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    private Dialog dialog;
    private Button btn_cancel_pay_popup;
    private Button btn_confirm_pay_popup;
    private TextView tv_money_pay_popup;
    private TextView tv_card_pay_popup;
    private EditText et_input_pay_popup;
    private TextView tv_yanzheng_pay_popup;

    public void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(ConfirmOrderActivity.this);
        View inView = inflater.inflate(R.layout.layout_pay_dialog, null);
        btn_cancel_pay_popup = (Button) inView.findViewById(id.btn_cancel_pay_popup);
        btn_confirm_pay_popup = (Button) inView.findViewById(id.btn_confirm_pay_popup);
        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmOrderActivity.this);
        builder.create();
        builder.setView(inView);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_pay_popup.setOnClickListener(this);
        btn_confirm_pay_popup.setOnClickListener(this);
    }

    private String orderId;
    private String money;
    private String totalFee;

    //确认订单
    public void getData() {

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_confirmOrder", cartids + "--------" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    //TODO 成功后，会调用支付,获取订单号
                    orderId = JsonUtils.getJsonParam(obj, "orderId");
                    money = JsonUtils.getJsonParam(obj, "money");
                    totalFee = String.valueOf(Double.parseDouble(money) - Double.parseDouble(JsonUtils.getJsonParam(obj, "totalFee")));
                    //发送广播
                    Intent intentBroad = new Intent();
                    intentBroad.setAction("Shopping");
                    sendBroadcast(intentBroad);
                    AppUtils.toastText(ConfirmOrderActivity.this, DefineUtil.BANKBAND+"");
                    Log.d("result_confirm_De",DefineUtil.BANKBAND+"");
                    Log.d("result_confirm_con",comAppApplication.getLogin()+"");
                    Log.d("result_confirm",comAppApplication.getLogin().isBankBind()+"");
                    if (DefineUtil.BANKBAND) {
                        Intent intent = new Intent(ConfirmOrderActivity.this, PayActivity.class);
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("money", money);
                        startActivity(intent);
//                    ConfirmOrderActivity.this.setResult(1);
                        ConfirmOrderActivity.this.finish();
                    } else {
                        AppUtils.toastText(ConfirmOrderActivity.this, "尚未绑定银行卡");
                        showDialog();
                    }
                } else {
                    AppUtils.toastText(ConfirmOrderActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CREATE_ORDER, ConfirmOrderUrl.postConfirmOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, cartids, String.valueOf(allMoney), text, String.valueOf(costMoney)), ConfirmOrderActivity.this);
    }

}
