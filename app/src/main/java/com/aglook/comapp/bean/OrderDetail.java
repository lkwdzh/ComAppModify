package com.aglook.comapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aglook on 2016/1/21.
 */
public class OrderDetail implements Serializable{
    private String orderStatus;
     private List<AllOrderDataList> orderDetailList;
    private OrderDetailOrder order;
    private String userSeat;

    public String getUserSeat() {
        return userSeat;
    }

    public void setUserSeat(String userSeat) {
        this.userSeat = userSeat;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<AllOrderDataList> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<AllOrderDataList> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public OrderDetailOrder getOrder() {
        return order;
    }

    public void setOrder(OrderDetailOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderStatus='" + orderStatus + '\'' +
                ", orderDetailList=" + orderDetailList +
                ", order=" + order +
                ", userSeat='" + userSeat + '\'' +
                '}';
    }
}
