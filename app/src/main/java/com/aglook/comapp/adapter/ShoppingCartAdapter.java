package com.aglook.comapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by aglook on 2015/11/2.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Activity context;
    private List<ShoppingCart> list;
    private CallBackData callBackData;
    private double num = 0;
    private double total = 0;
    private String cartId;
    private String productNum;
    private CustomProgress customProgress;

    private String deleteFlag;
    private boolean isEditting;

    private int indexAdd;
    private double numAdd;
    private int index;
    private ImageView iv_cut_shopping_input;
    private EditText et_count_shopping_cart_input;
    private ImageView iv_add_shopping_cart_input;

    private boolean isInput;

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

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
            list.get(i).setTotal(Double.parseDouble(decimalFormat.format(list.get(i).getProductNum() * list.get(i).getProductMoney())));

        }
        holder.tv_total_shopping_cart_listview.setText(shoppingCart.getTotal() + "");
        holder.tv_count_shopping_cart_listview.setTag(position);

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
                numAdd = list.get(position).getProductNum();
                indexAdd = position;
                //给产品id赋值
                cartId = list.get(position).getCartId();
                numAdd++;
                numAdd = Double.parseDouble(decimalFormat.format(numAdd));
                productNum = numAdd + "";
                Log.d("result__numAdd", numAdd + "___");
                list.get(position).setProductNum(numAdd);
                holder.tv_count_shopping_cart_listview.setText(numAdd + "");

                double total = numAdd * list.get(position).getProductMoney();
                total=Double.parseDouble(decimalFormat.format(total));
                list.get(position).setTotal(total);
                holder.tv_total_shopping_cart_listview.setText(total + "");
                addCart();
            }
        });


//        点击减少按钮
        holder.iv_cut_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int numCut = Integer.parseInt(AppUtils.toStringTrim_TV(holder.tv_count_shopping_cart_listview));
                numAdd = list.get(position).getProductNum();
                indexAdd = position;
                //给产品id赋值
                cartId = list.get(position).getCartId();
                if (numAdd > 1) {
                    numAdd--;
                    numAdd = Double.parseDouble(decimalFormat.format(numAdd));
                } else {
                    return;

                }
                Log.d("result_numCut", numAdd + "___");
                productNum = numAdd + "";
                list.get(position).setProductNum(numAdd);
                holder.tv_count_shopping_cart_listview.setText(numAdd + "");
                double total = numAdd * list.get(position).getProductMoney();
                total=Double.parseDouble(decimalFormat.format(total));
                list.get(position).setTotal(total);
                holder.tv_total_shopping_cart_listview.setText(total + "");
                addCart();
            }
        });
        holder.tv_count_shopping_cart_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (int) v.getTag();
                isInput = true;
                showDailog(index);
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


    //
    public void dataChange() {
        num = 0;
        total = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
            num += list.get(i).getProductNum();
            total += list.get(i).getTotal();
            }
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
        public void callBack(double num, double total);
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
                Log.d("result___", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {

                    dataChange();
                    if (isInput) {
                        isInput = false;
                        notifyDataSetChanged();
                    }
                } else {
                    indexAdd--;
                    list.get(indexAdd).setProductNum(numAdd);
                    Log.d("result___ADD", numAdd + "____" + indexAdd);
                    double total = numAdd * list.get(indexAdd).getProductMoney();
                    list.get(indexAdd).setTotal(total);
                    notifyDataSetChanged();
                    AppUtils.toastText(context, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                indexAdd--;
                list.get(indexAdd).setProductNum(numAdd);
                Log.d("result___ADD", numAdd + "____" + indexAdd);
                double total = numAdd * list.get(indexAdd).getProductMoney();
                list.get(indexAdd).setTotal(total);
                notifyDataSetChanged();
            }
        }.datePost(DefineUtil.EDIT_CART, ShoppingCartUrl.postDeleteUrl(DefineUtil.USERID, DefineUtil.TOKEN, cartId, productNum, "0"), context);
    }


    private Dialog dialog;

    public void showDailog(final int index) {

        numAdd = list.get(index).getProductNum();
//                indexAdd = position;
        //给产品id赋值
        cartId = list.get(index).getCartId();
        productNum = numAdd + "";

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_input, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        iv_cut_shopping_input = (ImageView) view.findViewById(R.id.iv_cut_shopping_input);
        et_count_shopping_cart_input = (EditText) view.findViewById(R.id.et_count_shopping_cart_input);
        et_count_shopping_cart_input.setText(String.valueOf(list.get(index).getProductNum()));
        et_count_shopping_cart_input.setSelection(String.valueOf(list.get(index).getProductNum()).length());
        iv_add_shopping_cart_input = (ImageView) view.findViewById(R.id.iv_add_shopping_cart_input);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btn_cancel_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
                if ( imm.isActive( ) ) {
                    AppUtils.toastText(context,"键盘");
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

                }
            }
        });
        btn_confirm_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productNum = et_count_shopping_cart_input.getText().toString();
                if (productNum == null) {
                    AppUtils.toastText(context, "数量不能为空");
                    return;
                }
                list.get(index).setProductNum(Double.parseDouble(productNum));
                double total = Double.parseDouble(productNum) * list.get(index).getProductMoney();
                total=Double.parseDouble(decimalFormat.format(total));
                list.get(index).setTotal(total);
                addCart();
                dialog.dismiss();
                InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
                if ( imm.isActive( ) ) {
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

                }
            }
        });


        //点击增加按钮
        iv_add_shopping_cart_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numAdd = Double.parseDouble(et_count_shopping_cart_input.getText().toString());
                numAdd++;
                et_count_shopping_cart_input.setText(numAdd + "");
                et_count_shopping_cart_input.setSelection(String.valueOf(numAdd).length());
            }
        });


//        点击减少按钮
        iv_cut_shopping_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numAdd = Double.parseDouble(et_count_shopping_cart_input.getText().toString());
                if (numAdd > 1) {
                    numAdd--;
                } else {
                    return;

                }
                et_count_shopping_cart_input.setText(numAdd + "");
                et_count_shopping_cart_input.setSelection(String.valueOf(numAdd).length());
            }
        });
        //监听Edittext数据变化
        et_count_shopping_cart_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int d = temp.indexOf(".");
                if (d < 0) {
//                if (temp.length() > 3) {
//
//                    s.delete(3, 4);
//                }
                    return;
                }
                if (temp.length() - d - 1 > 2) {
                    s.delete(d + 3, d + 4);
                } else if (d == 0) {
                    s.delete(d, d + 1);
                }
            }
        });
    }

    private Button btn_cancel_delete;
    private Button btn_confirm_delete;


}
