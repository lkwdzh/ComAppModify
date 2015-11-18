package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/18.
 */
public class AllOrder {
    private String  orderId;//订单号
    private String orderTime;//单独时间
    private double money;//钱
    private String orderStatus;//支付状态
    private List<AllOrderDataList>orderDateList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<AllOrderDataList> getOrderDateList() {
        return orderDateList;
    }

    public void setOrderDateList(List<AllOrderDataList> orderDateList) {
        this.orderDateList = orderDateList;
    }

    @Override
    public String toString() {
        return "AllOrder{" +
                "orderId='" + orderId + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", money=" + money +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDateList=" + orderDateList +
                '}';
    }
}
