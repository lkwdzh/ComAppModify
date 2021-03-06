package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/12.
 */
public class HomePageList {
    private String productId;//产品id
    private String productName;//产品名称
    private String goodPlace;//产地
    private String goodType;//等级
    private String productSellNum;//重量
    private String productMoney;//价格
    private String  isAppoint;//是否是指定
    private String productLogo;//图像
    private String productLogoSmall;//缩略图

    public String getProductLogoSmall() {
        return productLogoSmall;
    }

    public void setProductLogoSmall(String productLogoSmall) {
        this.productLogoSmall = productLogoSmall;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

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

    @Override
    public String toString() {
        return "HomePage{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", goodPlace='" + goodPlace + '\'' +
                ", goodType='" + goodType + '\'' +
                ", productSellNum='" + productSellNum + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", isAppoint='" + isAppoint + '\'' +
                ", productLogo='" + productLogo + '\'' +
                '}';
    }
}
