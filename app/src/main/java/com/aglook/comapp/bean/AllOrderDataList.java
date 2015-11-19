package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/18.
 */
public class AllOrderDataList implements Serializable{
    private String orderdataId;//订单数据号
    private String orderId;//订单号
    private String productId;//产品id
    private String productName;//名称
    private String productLogo;//图标
    private double productMoney;//单价
    private double productMoneyYh;//
    private int productNum;//个数
    private String proruleKey;
    private String proruleName;
    private String harvestCode;
    private String innerWeight;
    private String weightUse;
    private String weightUseable;
    private String storage;
    private String counter;

    public String getOrderdataId() {
        return orderdataId;
    }

    public void setOrderdataId(String orderdataId) {
        this.orderdataId = orderdataId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(double productMoney) {
        this.productMoney = productMoney;
    }

    public double getProductMoneyYh() {
        return productMoneyYh;
    }

    public void setProductMoneyYh(double productMoneyYh) {
        this.productMoneyYh = productMoneyYh;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProruleKey() {
        return proruleKey;
    }

    public void setProruleKey(String proruleKey) {
        this.proruleKey = proruleKey;
    }

    public String getProruleName() {
        return proruleName;
    }

    public void setProruleName(String proruleName) {
        this.proruleName = proruleName;
    }

    public String getHarvestCode() {
        return harvestCode;
    }

    public void setHarvestCode(String harvestCode) {
        this.harvestCode = harvestCode;
    }

    public String getInnerWeight() {
        return innerWeight;
    }

    public void setInnerWeight(String innerWeight) {
        this.innerWeight = innerWeight;
    }

    public String getWeightUse() {
        return weightUse;
    }

    public void setWeightUse(String weightUse) {
        this.weightUse = weightUse;
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
        return "AllOrderDataList{" +
                "orderdataId='" + orderdataId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", productMoney=" + productMoney +
                ", productMoneyYh=" + productMoneyYh +
                ", productNum=" + productNum +
                ", proruleKey='" + proruleKey + '\'' +
                ", proruleName='" + proruleName + '\'' +
                ", harvestCode='" + harvestCode + '\'' +
                ", innerWeight='" + innerWeight + '\'' +
                ", weightUse='" + weightUse + '\'' +
                ", weightUseable='" + weightUseable + '\'' +
                ", storage='" + storage + '\'' +
                ", counter='" + counter + '\'' +
                '}';
    }
}
