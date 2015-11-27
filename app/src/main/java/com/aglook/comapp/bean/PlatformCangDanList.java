package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/24.
 */
public class PlatformCangDanList {
    private String orderId;//交易订单id
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
                '}';
    }
}
