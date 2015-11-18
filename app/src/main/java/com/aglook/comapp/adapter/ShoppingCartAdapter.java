package com.aglook.comapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.List;

/**
 * Created by aglook on 2015/11/2.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Activity context;
    private List<ShoppingCart> list;
    private CallBackData callBackData;
    private int num = 0;
    private double total = 0;
    private String cartId;
    private String productNum;
    private CustomProgress customProgress;

    private String deleteFlag;
    private boolean isEditting;

    public ShoppingCartAdapter(Activity context, List<ShoppingCart> list, CallBackData callBackData) {
        this.context = context;
        this.list = list;
//        if (context instanceof CallBackData){
//            callBackData=(CallBackData)context;
//        }
        this.callBackData = callBackData;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_shoppring_cart_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ShoppingCart shoppingCart = list.get(position);

        holder.tv_count_shopping_cart_listview.setText(shoppingCart.getProductNum() + "");
        holder.cb_shopping_cart_listview.setChecked(shoppingCart.isChecked());
        XBitmap.displayImage(holder.iv_shopping_cart_listview, shoppingCart.getProductLogo(), context);
        holder.tv_price_shopping_cart_listview.setText("￥" + shoppingCart.getProductMoney());
        holder.tv_name_shopping_cast_listview.setText(shoppingCart.getProductName());
        //将小计添加到实体类中
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTotal(list.get(i).getProductNum() * list.get(i).getProductMoney());

        }
        holder.tv_total_shopping_cart_listview.setText(shoppingCart.getTotal() + "");


        //假如是是编辑状态，则可以选择，若不是。则不可选择
        if (isEditting) {
            holder.cb_shopping_cart_listview.setVisibility(View.VISIBLE);
        } else {
            holder.cb_shopping_cart_listview.setVisibility(View.INVISIBLE);
        }


        dataChange();
        //点击增加按钮
        holder.iv_add_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int numAdd = Integer.parseInt(AppUtils.toStringTrim_TV(holder.tv_count_shopping_cart_listview));
                int numAdd = list.get(position).getProductNum();
                //给产品id赋值
                cartId = list.get(position).getCartId();
                numAdd++;
                shoppingCart.setProductNum(numAdd);
                holder.tv_count_shopping_cart_listview.setText(numAdd + "");

                double total = numAdd * shoppingCart.getProductMoney();
                holder.tv_total_shopping_cart_listview.setText(total + "");
                shoppingCart.setTotal(total);
                productNum = numAdd + "";
                addCart();
            }
        });


//        点击减少按钮
        holder.iv_cut_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int numCut = Integer.parseInt(AppUtils.toStringTrim_TV(holder.tv_count_shopping_cart_listview));
                int numCut = list.get(position).getProductNum();
                //给产品id赋值
                cartId = list.get(position).getCartId();
                if (numCut > 0) {
                    numCut--;
                } else {
                    numCut = 0;
                }
                shoppingCart.setProductNum(numCut);
                holder.tv_count_shopping_cart_listview.setText(numCut + "");
                double total = numCut * shoppingCart.getProductMoney();
                holder.tv_total_shopping_cart_listview.setText(total + "");
                shoppingCart.setTotal(total);
                productNum = numCut + "";
                addCart();
            }
        });

        holder.cb_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCart.setChecked(!shoppingCart.isChecked());
                dataChange();
            }
        });

        return convertView;
    }

//    //编辑的方法
//    public void editDetlete(){
//        int ednum=0;
//        int edtotal=0;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).isChecked()){
//                ednum++;
//            }
//        }
//        callBackData.callBack(ednum,edtotal);
//    }


    //记得把datachange与addcart交换
    public void dataChange() {
        num = 0;
        total = 0;
        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).isChecked()) {
            num += list.get(i).getProductNum();
            total += list.get(i).getTotal();
//            }
        }
        callBackData.callBack(num, total);
    }


    //定义是否是编辑状态
    public void isEditting(boolean isEditting) {
        this.isEditting = isEditting;
    }

    class ViewHolder {
        ImageView iv_cut_shopping_cart_listview;
        ImageView iv_add_shopping_cart_listview;
        CheckBox cb_shopping_cart_listview;
        ImageView iv_shopping_cart_listview;
        TextView tv_name_shopping_cast_listview;
        TextView tv_price_shopping_cart_listview;
        TextView tv_count_shopping_cart_listview;
        TextView tv_total_shopping_cart_listview;

        ViewHolder(View view) {
            cb_shopping_cart_listview = (CheckBox) view.findViewById(R.id.cb_shopping_cart_listview);
            iv_cut_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_cut_shopping_cart_listview);
            iv_add_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_add_shopping_cart_listview);
            iv_shopping_cart_listview = (ImageView) view.findViewById(R.id.iv_shopping_cart_listview);
            tv_name_shopping_cast_listview = (TextView) view.findViewById(R.id.tv_name_shopping_cast_listview);
            tv_price_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_price_shopping_cart_listview);
            tv_count_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_count_shopping_cart_listview);
            tv_total_shopping_cart_listview = (TextView) view.findViewById(R.id.tv_total_shopping_cart_listview);
        }
    }

    public interface CallBackData {
        public void callBack(int num, double total);
    }


    //编辑 商品
    public void addCart() {
        customProgress = CustomProgress.show(context, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    dataChange();
                } else {
                    AppUtils.toastText(context, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.EDIT_CART, ShoppingCartUrl.postDeleteUrl(DefineUtil.USERID, DefineUtil.TOKEN, cartId, productNum, "0"), context);
    }

}
