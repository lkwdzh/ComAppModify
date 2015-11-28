package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/24.
 */
public class PlatformCangDanList {
    private String orderId;//平台仓单号
    private String orderState;//交易订单状态
    private String  orderdataId;//平台仓单id
    private String productId;//购买产品id
    private String productName;//名称
    private String productLogo;//图片
    private String productMoney;//出售价格
    private String productNum;
    private String innerWeight;//总量
    private String weightUser;//出售数量
    private String weightUseable;//未出售数量
    private String storage;//仓储费
    private String counter;//手续费
    private String originalListId;//原始仓单号
    private String orderAtime;//创建时间
    private String orderPtime;//交易时间

    public String getOriginalListId() {
        return originalListId;
    }

    public void setOriginalListId(String originalListId) {
        this.originalListId = originalListId;
    }

    public String getOrderAtime() {
        return orderAtime;
    }

    public void setOrderAtime(String orderAtime) {
        this.orderAtime = orderAtime;
    }

    public String getOrderPtime() {
        return orderPtime;
    }

    public void setOrderPtime(String orderPtime) {
        this.orderPtime = orderPtime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderdataId() {
        return orderdataId;
    }

    public void setOrderdataId(String orderdataId) {
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

    @Override
    public String toString() {
        return "PlatformCangDanList{" +
                "orderId='" + orderId + '\'' +
                ", orderState='" + orderState + '\'' +
                ", orderdataId='" + orderdataId + '\'' +
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
                ", originalListId='" + originalListId + '\'' +
                ", orderAtime='" + orderAtime + '\'' +
                ", orderPtime='" + orderPtime + '\'' +
                '}';
    }
}
