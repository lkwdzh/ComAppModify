package com.aglook.comapp.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.ConfirmOrderActivity;
import com.aglook.comapp.Activity.GoodsDetailActivity;
import com.aglook.comapp.Activity.LoginActivity;
import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ShoppingCartAdapter;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.io.Serializable;
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
    private double allNum = 0;
    private double allMoney = 0;
    private LinearLayout ll_empty_shopping_cart;
    private RelativeLayout ll_full_content;
    private TextView tv_denglu_shopping_cart;
    private Intent intent;
    private TextView tv_num_delete;
    private LinearLayout ll_shopping_cart_jiesuan;
    private CustomProgress customProgress;
    private ComAppApplication comAppApplication;
    private Button btn_login;
    private boolean isEditting = false;
    private onShoppingCartClick shoppingCartClick;
    private LinearLayout ll_weidenglu_shopping_cart;
    private String cartId;
    private String productNum;
    private String deleteFlag = "1";//删除
    private boolean isConfirm;
    private int page = 1;
    private TextView tv_zonge_shop_cart;
    private View emptyView;
    private View viewAll;
    private LinearLayout ll_cb_bottom;

    //选中的list
//    private List<ShoppingCart>selectedList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         viewAll = View.inflate(getActivity(), R.layout.layout_shopping_cart_fragment, null);
        comAppApplication = (ComAppApplication) getActivity().getApplication();
        initView(viewAll);
        click();

        return viewAll;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof onShoppingCartClick) {
            shoppingCartClick = (onShoppingCartClick) getActivity();
        }
    }

    public void initView(View view) {
        emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.empty_shop_cart, null);
        lv_shopping_cart = (PullToRefreshListView) view.findViewById(R.id.lv_shopping_cart);
        mList = new ArrayList<>();
        adapter = new ShoppingCartAdapter(getActivity(), mList, new ShoppingCartAdapter.CallBackData() {
            @Override
            public void callBack(double num, double total) {
                allMoney = total;
                allNum = num;
                DefineUtil.NUM = num;
                Intent intent1 = new Intent();
                intent1.setAction("MainActivity");
                getActivity().sendBroadcast(intent1);
                tv_shopping_cart_jiesuan.setText("(" + num + ")");
                tv_total_shopping_cart_fragment.setText(total + "");
                tv_zonge_shop_cart.setText(total+"");
//                selectedList.clear();
//                for (int i = 0; i < mList.size(); i++) {
//                    if (mList.get(i).isChecked()){
//                        selectedList.add(mList.get(i));
//                    }
//                }
            }
        });
        lv_shopping_cart.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv_shopping_cart.setMode(PullToRefreshBase.Mode.DISABLED);

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
        tv_zonge_shop_cart = (TextView) view.findViewById(R.id.tv_zonge_shop_cart);
        //空购物车
        ll_empty_shopping_cart = (LinearLayout) view.findViewById(R.id.ll_empty_shopping_cart);
//        有数据购物车
        ll_full_content = (RelativeLayout) view.findViewById(R.id.ll_full_content);
//        登录按钮
        tv_denglu_shopping_cart = (TextView) view.findViewById(R.id.tv_denglu_shopping_cart);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        ll_cb_bottom = (LinearLayout) view.findViewById(R.id.ll_cb_bottom);
        //如果未登录
        if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
            unLogin();
        } else {
            customProgress = CustomProgress.show(getActivity(), "加载中···", true);
            isLogined();
        }
//        去结算
        ll_shopping_cart_jiesuan = (LinearLayout) view.findViewById(R.id.ll_shopping_cart_jiesuan);

        ll_weidenglu_shopping_cart = (LinearLayout) view.findViewById(R.id.ll_weidenglu_shopping_cart);

        IntentFilter filter = new IntentFilter();
        filter.addAction("Shopping");
        getActivity().registerReceiver(myReceiver,filter);
        ll_buy_bottom_shopping_cart.setVisibility(View.VISIBLE);
    }



    //接收广播
    private BroadcastReceiver myReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //刷新
            isConfirm = true;

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        cb_top_right_shopping_cart.setChecked(false);
        if (isConfirm){
//            initView(viewAll);
            getCartListData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁
        getActivity().unregisterReceiver(myReceiver);
    }

    //    如果未登录
    public void unLogin() {
        ll_empty_shopping_cart.setVisibility(View.VISIBLE);
        ll_full_content.setVisibility(View.GONE);
        cb_top_right_shopping_cart.setVisibility(View.GONE);
        tv_denglu_shopping_cart.setOnClickListener(this);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        //如果未登录
//        if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
//            unLogin();
//        } else {
//            isLogined();
//        }
//    }

    //    如果已登录
    public void isLogined() {
        ll_empty_shopping_cart.setVisibility(View.GONE);
        ll_full_content.setVisibility(View.VISIBLE);
        cb_top_right_shopping_cart.setVisibility(View.VISIBLE);
        //判断是否有数据，如果有数据
        getCartListData();
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
//                    cb_buy_shopping_cart.setChecked(false);
//                    ll_cb_bottom.setVisibility(View.VISIBLE);
                    adapter.isEditting(true);
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
//                    adapter.notifyDataSetChanged();
                } else {
                    rl_edit_bottom_shopping_cart.setVisibility(View.GONE);
                    ll_buy_bottom_shopping_cart.setVisibility(View.VISIBLE);
                    cb_top_right_shopping_cart.setText("编辑");
//                    cb_edit_shopping_cart.setChecked(false);
//                    ll_cb_bottom.setVisibility(View.INVISIBLE);
                    adapter.isEditting(false);
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                    }
                }
                    adapter.notifyDataSetChanged();
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

//        //购买的全选
//        cb_buy_shopping_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                selectedList.clear();
//
//                if (isChecked) {
//                    allMoney = 0;
//                    allNum = 0;
//                    for (int i = 0; i < mList.size(); i++) {
//                        mList.get(i).setChecked(true);
//                        allNum += mList.get(i).getProductNum();
//                        allMoney += mList.get(i).getTotal();
//                    }
//                    tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
//                    tv_total_shopping_cart_fragment.setText(allMoney + "");
//
//                    selectedList.addAll(mList);
//                } else {
//                    for (int i = 0; i < mList.size(); i++) {
//                        mList.get(i).setChecked(false);
//                    }
//                    allMoney = 0;
//                    allNum = 0;
//                    tv_shopping_cart_jiesuan.setText("(" + 0 + ")");
//                    tv_total_shopping_cart_fragment.setText(0 + "");
//                    selectedList.clear();
//                }
////                tv_shopping_cart_jiesuan.setText("(" + allNum + ")");
////                tv_total_shopping_cart_fragment.setText(allMoney + "");
//                adapter.notifyDataSetChanged();
//            }
//        });

        tv_delete_shopping_cart.setOnClickListener(this);
        ll_shopping_cart_jiesuan.setOnClickListener(this);

//        listview的item点击进入商品详情
        lv_shopping_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getActivity(), GoodsDetailActivity.class);
                intent1.putExtra("productId", mList.get(position - 1).getProductId());
                isToGoodsDetail = true;
                startActivityForResult(intent1, 2);
            }
        });

        btn_login.setOnClickListener(this);
    }

    private boolean isToGoodsDetail;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            isLogined();
        } else if (requestCode == 2 && resultCode == 1) {
            isDelete = false;
            customProgress = CustomProgress.show(getActivity(), "加载中···", true);
            getCartListData();
        }
//        else if (requestCode == 3 && resultCode == 1) {
//            isConfirm = true;
//            getCartListData();
//        }
    }



    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_denglu_shopping_cart:
                intent.setClass(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_delete_shopping_cart:
                int ss=0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChecked()){
                        ss++;
                    }
                }
                if (ss==0){
                    return;
                }else {
                    showDailog();
                }
                break;
            case R.id.btn_cancel_delete:
                dialog.dismiss();
                break;
            case R.id.btn_confirm_delete:
                deleteCart();
                dialog.dismiss();

                break;
            case R.id.ll_shopping_cart_jiesuan:
                intent.setClass(getActivity(), ConfirmOrderActivity.class);
                intent.putExtra("CharList", (Serializable) mList);
                startActivity(intent);
//                startActivityForResult(intent, 3);
                break;
            case R.id.btn_login:
                if (comAppApplication.getLogin() == null || comAppApplication.getLogin().equals("")) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    //跳转到首页
                    shoppingCartClick.onCartClick(0);
                }
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


    private Dialog dialog;
    private TextView tv_delete_order;
    //选中个数
    private int deleteSeleteNum = 0;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_cart_delete, null);
        tv_num_delete = (TextView) view.findViewById(R.id.tv_num_delete);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        getSelete();
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
        tv_num_delete.setText(deleteSeleteNum + "");
    }

    private Button btn_cancel_delete;
    private Button btn_confirm_delete;

    //    获取购物车列表
    public void getCartListData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_getCartList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ShoppingCart> list = new ArrayList<ShoppingCart>();
                list = JsonUtils.parseList(obj, ShoppingCart.class);
                int num = 0;
                if (status.equals("1")) {
                        if (isDelete) {
                            mList.clear();
                            isDelete = false;
                        }

                        if (isToGoodsDetail) {
                            mList.clear();
                            isToGoodsDetail = false;
                        }

                        if (isConfirm) {
                            mList.clear();
                            isConfirm = false;
                        }
                    if (list != null && list.size() != 0) {
                        mList.addAll(list);

                        for (int i = 0; i < mList.size(); i++) {
                            num+=mList.get(i).getProductNum();
                            mList.get(i).setChecked(true);
                            DefineUtil.NUM=num;
                        }

                    } else {
                        if (isConfirm) {
                            mList.clear();
                            isConfirm = false;
                        }
                        if (isDelete) {

                            ll_empty_shopping_cart.setVisibility(View.VISIBLE);
                            ll_full_content.setVisibility(View.GONE);
                            ll_weidenglu_shopping_cart.setVisibility(View.GONE);
                            cb_top_right_shopping_cart.setVisibility(View.GONE);
                            isDelete = false;
                        }
                        DefineUtil.NUM=0;
                    }
                    if (mList == null || mList.size() == 0) {
                        ll_empty_shopping_cart.setVisibility(View.VISIBLE);
                        ll_full_content.setVisibility(View.GONE);
                        ll_weidenglu_shopping_cart.setVisibility(View.GONE);
                        cb_top_right_shopping_cart.setVisibility(View.GONE);

                    }
                } else {
                    if (mList == null || mList.size() == 0) {
                        ll_empty_shopping_cart.setVisibility(View.VISIBLE);
                        ll_full_content.setVisibility(View.GONE);
                        ll_weidenglu_shopping_cart.setVisibility(View.GONE);
                        cb_top_right_shopping_cart.setVisibility(View.GONE);

                    }
                    AppUtils.toastText(getActivity(), message);
                }
                Intent intent1 = new Intent();
                intent1.setAction("MainActivity");
                getActivity().sendBroadcast(intent1);
                adapter.notifyDataSetChanged();
//                lv_shopping_cart.setEmptyView(emptyView);
//                DefineUtil.NUM=mList.size();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CARTLIST, ShoppingCartUrl.postCartListUrl(DefineUtil.USERID, DefineUtil.TOKEN), getActivity());

    }

    //    写一个回调接口，当点击时跳转到首页
    public interface onShoppingCartClick {
        public void onCartClick(int position);
    }

    private List<Integer> selectItem;

    //获取选中的位置
    public void getSelete() {
        selectItem = new ArrayList<>();
        final List<String> cartIdList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                cartIdList.add(mList.get(i).getCartId());
                selectItem.add(i);
            }
        }
        if (cartIdList.size() != 0) {
            String str = cartIdList.toString();
            cartId = str.substring(1, str.length() - 1).replaceAll(" ", "");
            deleteSeleteNum = cartIdList.size();
        }
    }

    private boolean isDelete;

    //    // 删除商品
    public void deleteCart() {
        customProgress = CustomProgress.show(getActivity(), "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    //假如成功，则移除mList中的数据
//                    List<ShoppingCart> reList = new ArrayList<ShoppingCart>();
//                    for (int i = 0; i < selectItem.size(); i++) {
////                        mList.remove(selectItem.get(i));
//                        reList.add(mList.get(selectItem.get(i)));
//                    }
//                    mList.removeAll(reList);
                    isDelete = true;
                    getCartListData();
                    adapter.notifyDataSetChanged();
                } else {
                    AppUtils.toastText(getActivity(), message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.EDIT_CART, ShoppingCartUrl.postDeleteUrl(DefineUtil.USERID, DefineUtil.TOKEN, cartId, productNum, deleteFlag), getActivity());
    }
}
