package com.aglook.comapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ShoppingCartAdapter;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.util.AppUtils;
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

    }

    public void click() {
        cb_top_right_shopping_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
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
                    allMoney=0;
                    allNum=0;
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                        allNum+=mList.get(i).getNum();
                        allMoney+=mList.get(i).getTotal();
                    }
                    tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
                    AppUtils.toastText(getActivity(),allNum+"");
                tv_total_shopping_cart_fragment.setText(allMoney + "");

                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                    allMoney=0;
                    allNum=0;
                    tv_shopping_cart_jiesuan.setText("(" + 0 + ")");
                tv_total_shopping_cart_fragment.setText(0 + "");
                }
//                tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
//                tv_total_shopping_cart_fragment.setText(allMoney + "");
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    // fragment的替换
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.ll_frag_pre_container, fragment);

        transaction.commitAllowingStateLoss();
    }


}
