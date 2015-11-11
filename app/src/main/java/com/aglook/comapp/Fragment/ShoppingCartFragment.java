package com.aglook.comapp.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.ConfirmOrderActivity;
import com.aglook.comapp.Activity.LoginActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ShoppingCartAdapter;
import com.aglook.comapp.bean.ShoppingCart;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class ShoppingCartFragment extends Fragment implements View.OnClickListener {
    private PullToRefreshListView lv_shopping_cart;
    private ShoppingCartAdapter adapter;
    private CheckBox cb_top_right_shopping_cart;
    private LinearLayout ll_buy_bottom_shopping_cart;
    private RelativeLayout rl_edit_bottom_shopping_cart;
    private List<ShoppingCart> mList;
    private ShoppingCart shoppingCart;
    private CheckBox cb_edit_shopping_cart;
    private TextView tv_delete_shopping_cart;
    private CheckBox cb_buy_shopping_cart;
    private TextView tv_shopping_cart_jiesuan;
    private TextView tv_total_shopping_cart_fragment;
    private int allNum = 0;
    private double allMoney = 0;
    private LinearLayout ll_empty_shopping_cart;
    private RelativeLayout ll_full_content;
    private TextView tv_denglu_shopping_cart;
    private Intent intent;
    private TextView tv_num_delete;
    private LinearLayout ll_shopping_cart_jiesuan;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_shopping_cart_fragment, null);
        initView(view);
        click();
        return view;
    }

    public void initView(View view) {
        lv_shopping_cart = (PullToRefreshListView) view.findViewById(R.id.lv_shopping_cart);
        mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setNum(1);
            shoppingCart.setChecked(false);
            shoppingCart.setPrice(10);
            mList.add(shoppingCart);
        }
        adapter = new ShoppingCartAdapter(getActivity(), mList, new ShoppingCartAdapter.CallBackData() {
            @Override
            public void callBack(int num, double total) {
                allMoney = total;
                allNum = num;
                tv_shopping_cart_jiesuan.setText("(" + num + ")");
                tv_total_shopping_cart_fragment.setText(total + "");
            }
        });
        lv_shopping_cart.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv_shopping_cart.setMode(PullToRefreshBase.Mode.BOTH);

        cb_top_right_shopping_cart = (CheckBox) view.findViewById(R.id.cb_top_right_shopping_cart);
        ll_buy_bottom_shopping_cart = (LinearLayout) view.findViewById(R.id.ll_buy_bottom_shopping_cart);
        rl_edit_bottom_shopping_cart = (RelativeLayout) view.findViewById(R.id.rl_edit_bottom_shopping_cart);
//        编辑的全选
        cb_edit_shopping_cart = (CheckBox) view.findViewById(R.id.cb_edit_shopping_cart);
//        编辑的删除
        tv_delete_shopping_cart = (TextView) view.findViewById(R.id.tv_delete_shopping_cart);

        //购买
        cb_buy_shopping_cart = (CheckBox) view.findViewById(R.id.cb_buy_shopping_cart);
        tv_shopping_cart_jiesuan = (TextView) view.findViewById(R.id.tv_shopping_cart_jiesuan);
        tv_total_shopping_cart_fragment = (TextView) view.findViewById(R.id.tv_total_shopping_cart_fragment);

        //空购物车
        ll_empty_shopping_cart = (LinearLayout) view.findViewById(R.id.ll_empty_shopping_cart);
//        有数据购物车
        ll_full_content = (RelativeLayout) view.findViewById(R.id.ll_full_content);
//        登录按钮
        tv_denglu_shopping_cart = (TextView) view.findViewById(R.id.tv_denglu_shopping_cart);

//        unLogin();
//        去结算
        ll_shopping_cart_jiesuan = (LinearLayout) view.findViewById(R.id.ll_shopping_cart_jiesuan);
    }

    //    如果未登录
    public void unLogin() {
        ll_empty_shopping_cart.setVisibility(View.VISIBLE);
        ll_full_content.setVisibility(View.GONE);
        cb_top_right_shopping_cart.setVisibility(View.GONE);
        tv_denglu_shopping_cart.setOnClickListener(this);

    }

    public void click() {
        cb_top_right_shopping_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果选中就是编辑模式，否则是购买模式
                if (isChecked) {
                    rl_edit_bottom_shopping_cart.setVisibility(View.VISIBLE);
                    ll_buy_bottom_shopping_cart.setVisibility(View.GONE);
                    cb_top_right_shopping_cart.setText("完成");
                    cb_buy_shopping_cart.setChecked(false);
                } else {
                    rl_edit_bottom_shopping_cart.setVisibility(View.GONE);
                    ll_buy_bottom_shopping_cart.setVisibility(View.VISIBLE);
                    cb_top_right_shopping_cart.setText("编辑");
                    cb_edit_shopping_cart.setChecked(false);
                }
            }
        });

        //编辑的全选
        cb_edit_shopping_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                    }

                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        //购买的全选
        cb_buy_shopping_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    allMoney = 0;
                    allNum = 0;
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                        allNum += mList.get(i).getNum();
                        allMoney += mList.get(i).getTotal();
                    }
                    tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
                    tv_total_shopping_cart_fragment.setText(allMoney + "");

                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                    allMoney = 0;
                    allNum = 0;
                    tv_shopping_cart_jiesuan.setText("(" + 0 + ")");
                    tv_total_shopping_cart_fragment.setText(0 + "");
                }
//                tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
//                tv_total_shopping_cart_fragment.setText(allMoney + "");
                adapter.notifyDataSetChanged();
            }
        });

        tv_delete_shopping_cart.setOnClickListener(this);
        ll_shopping_cart_jiesuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_denglu_shopping_cart:
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_delete_shopping_cart:
                showDailog();
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                dialog.dismiss();
                break;
            case R.id.ll_shopping_cart_jiesuan:
                intent.setClass(getActivity(), ConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    // fragment的替换
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.ll_frag_pre_container, fragment);

        transaction.commitAllowingStateLoss();
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        closeWindow();
//    }

    private Dialog dialog;
    private TextView tv_delete_order;
    public void showDailog(){
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.layout_cart_delete, null);
        tv_num_delete = (TextView) view.findViewById(R.id.tv_num_delete);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }
    private Button btn_cancel_delete;
    private Button btn_confirm_delete;

//    //    popupwindow
//    private PopupWindow popupWindow;
//    private View popupView;
//
//    public void showWindow(View view) {
//        if (popupWindow == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            popupView = layoutInflater.inflate(R.layout.layout_cart_delete, null);
//            tv_num_delete = (TextView) popupView.findViewById(R.id.tv_num_delete);
//            btn_cancel_delete = (Button) popupView.findViewById(R.id.btn_cancel_delete);
//            btn_confirm_delete = (Button) popupView.findViewById(R.id.btn_confirm_delete);
//            popupWindow = new PopupWindow(popupView, 500, 300);
//        }
//        backgroundAlpha(0.5f);
//        cb_top_right_shopping_cart.setClickable(false);
////        使其聚焦
////        popupWindow.setFocusable(true);
//        //允许在外点击消失
////        popupWindow.setOutsideTouchable(true);
////        popupWindow.update();
////        点击back 返回
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        btn_cancel_delete.setOnClickListener(this);
//        btn_confirm_delete.setOnClickListener(this);
//    }
//
//    public void closeWindow() {
//        if (popupWindow != null) {
//            popupWindow.dismiss();
//        }
//        backgroundAlpha(1f);
//        cb_top_right_shopping_cart.setClickable(true);
//    }
//
//    /**
//     * 设置添加屏幕的背景透明度
//     *
//     * @param bgAlpha
//     */
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        getActivity().getWindow().setAttributes(lp);
//    }

}
