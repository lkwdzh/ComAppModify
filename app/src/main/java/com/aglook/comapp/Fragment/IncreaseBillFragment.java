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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aglook on 2016/1/21.
 */
public class IncreaseBillFragment extends Fragment {

    @Bind(R.id.tv_billAddress_increase)
    TextView tvBillAddressIncrease;
    @Bind(R.id.tv_billTai_increase)
    TextView tvBillTaiIncrease;
    @Bind(R.id.tv_billType_increase)
    TextView tvBillTypeIncrease;
    @Bind(R.id.tv_companyName_increase)
    TextView tvCompanyNameIncrease;
    @Bind(R.id.tv_billNum_increase)
    TextView tvBillNumIncrease;
    @Bind(R.id.tv_companyAddress_increase)
    TextView tvCompanyAddressIncrease;
    @Bind(R.id.tv_companyPhone_increase)
    TextView tvCompanyPhoneIncrease;
    @Bind(R.id.tv_bank_increase)
    TextView tvBankIncrease;
    @Bind(R.id.tv_bankNum_increase)
    TextView tvBankNumIncrease;
    @Bind(R.id.tv_billMessage_increase)
    TextView tvBillMessageIncrease;

    private OrderDetail orderDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_increase_bill, null);
        ButterKnife.bind(this, view);
        orderDetail = (OrderDetail) getArguments().getSerializable("orderDetail");
        fillData();
        return view;
    }


    public void fillData() {
        OrderDetailOrder order = orderDetail.getOrder();
        if (order != null && !"".equals(order)) {
            tvBillAddressIncrease.setText(order.getUserAddress());
            tvBankIncrease.setText(order.getUserRise());
            tvBillTypeIncrease.setText("增值税发票");
            tvCompanyNameIncrease.setText(order.getUserCaty());
            tvBillNumIncrease.setText(order.getUserNnumb());
            tvCompanyAddressIncrease.setText(order.getUserZcdz());
            tvCompanyPhoneIncrease.setText(order.getUserTels());
            tvBankIncrease.setText(order.getUserBanks());
            tvBankNumIncrease.setText(order.getUserBnumb());
            tvBillMessageIncrease.setText(order.getOrderText());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
