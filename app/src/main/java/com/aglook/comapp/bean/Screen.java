package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/18.
 */
public class Screen {
    private String productId;
    private String productName;
    private String goodPlace;
    private String goodType;
    private String productSellNum;
    private String productMoney;
    private String productLogo;
    private String isAppoint;

    public String getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(String isAppoint) {
        this.isAppoint = isAppoint;
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

    public String getGoodPlace() {
        return goodPlace;
    }

    public void setGoodPlace(String goodPlace) {
        this.goodPlace = goodPlace;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getProductSellNum() {
        return productSellNum;
    }

    public void setProductSellNum(String productSellNum) {
        this.productSellNum = productSellNum;
    }

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", goodPlace='" + goodPlace + '\'' +
                ", goodType='" + goodType + '\'' +
                ", productSellNum='" + productSellNum + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", isAppoint='" + isAppoint + '\'' +
                '}';
    }
}
