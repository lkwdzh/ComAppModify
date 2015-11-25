package com.aglook.comapp.bean;

import java.util.Arrays;

/**
 * Created by aglook on 2015/11/24.
 */
public class PlatformCangDanDetail {
    private String orderId;//交易订单id
    private String orderStateorderState;//交易订单状态
    private String[] orderdataId;//平台仓单id
    private String productId;//购买产品id
    private String productName;//名称
    private String productLogo;//图片
    private String productMoney;//出售价格
    private String productNum;//
    private String innerWeight;//总量
    private String weightUser;//出售数量
    private String weightUseable;//未出售数量
    private String storage;//仓储费
    private String counter;//手续费
    private String productListId;//原始仓单id

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStateorderState() {
        return orderStateorderState;
    }

    public void setOrderStateorderState(String orderStateorderState) {
        this.orderStateorderState = orderStateorderState;
    }

    public String[] getOrderdataId() {
        return orderdataId;
    }

    public void setOrderdataId(String[] orderdataId) {
        this.orderdataId = orderdataId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getInnerWeight() {
        return innerWeight;
    }

    public void setInnerWeight(String innerWeight) {
        this.innerWeight = innerWeight;
    }

    public String getWeightUser() {
        return weightUser;
    }

    public void setWeightUser(String weightUser) {
        this.weightUser = weightUser;
    }

    public String getWeightUseable() {
        return weightUseable;
    }

    public void setWeightUseable(String weightUseable) {
        this.weightUseable = weightUseable;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    @Override
    public String toString() {
        return "PlatformCangDanDetail{" +
                "orderId='" + orderId + '\'' +
                ", orderStateorderState='" + orderStateorderState + '\'' +
                ", orderdataId=" + Arrays.toString(orderdataId) +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", productNum='" + productNum + '\'' +
                ", innerWeight='" + innerWeight + '\'' +
                ", weightUser='" + weightUser + '\'' +
                ", weightUseable='" + weightUseable + '\'' +
                ", storage='" + storage + '\'' +
                ", counter='" + counter + '\'' +
                ", productListId='" + productListId + '\'' +
                '}';
    }
}
