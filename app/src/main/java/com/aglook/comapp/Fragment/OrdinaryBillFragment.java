package com.aglook.comapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.OrderDetail;
import com.aglook.comapp.bean.OrderDetailOrder;

/**
 * Created by aglook on 2016/1/21.
 */
public class OrdinaryBillFragment extends Fragment {
    private OrderDetail orderDetail;
    private TextView tv_billAddress_ordinary;
    private TextView tv_billTai_ordinary;
    private TextView tv_billType_ordinary;
    private TextView tv_billMessage_ordinary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ordinary_bill,null);
        orderDetail = (OrderDetail) getArguments().getSerializable("orderDetail");
        init(view);
        fillData();
        return view;
    }

    public void init(View view){
        tv_billAddress_ordinary = (TextView) view.findViewById(R.id.tv_billAddress_ordinary);
        tv_billTai_ordinary = (TextView) view.findViewById(R.id.tv_billTai_ordinary);
        tv_billType_ordinary = (TextView) view.findViewById(R.id.tv_billType_ordinary);
        tv_billMessage_ordinary = (TextView) view.findViewById(R.id.tv_billMessage_ordinary);
    }

    public void fillData(){
        OrderDetailOrder order = orderDetail.getOrder();
        if (order!=null&&!"".equals(order)){
        tv_billAddress_ordinary.setText(order.getUserAddress());
            tv_billTai_ordinary.setText(order.getUserRise());
            tv_billType_ordinary.setText("普通发票");
            tv_billMessage_ordinary.setText(order.getOrderText());

        }
    }
}
