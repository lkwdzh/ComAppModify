package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/13.
 */
public class GoodsDetail {
    private String productId;//产品id
    private String productName;//
    private String productMoney;//
    private String productListId;//
    private String productSellNum;//
    private String productAppDesc;//
    private String productAvailable;//
    private String productLogo;//
    private String categoryName;//
    private String innerTime;//
    private String goodsPlace;//
    private String goodsType;//
    private String mark;//
    private String depotResponsible;//
    private String responsibleMobile;//
    private String responsibleEmail;//
    private String depotAddress;//

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

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getProductSellNum() {
        return productSellNum;
    }

    public void setProductSellNum(String productSellNum) {
        this.productSellNum = productSellNum;
    }

    public String getProductAppDesc() {
        return productAppDesc;
    }

    public void setProductAppDesc(String productAppDesc) {
        this.productAppDesc = productAppDesc;
    }

    public String getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(String productAvailable) {
        this.productAvailable = productAvailable;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getInnerTime() {
        return innerTime;
    }

    public void setInnerTime(String innerTime) {
        this.innerTime = innerTime;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDepotResponsible() {
        return depotResponsible;
    }

    public void setDepotResponsible(String depotResponsible) {
        this.depotResponsible = depotResponsible;
    }

    public String getResponsibleMobile() {
        return responsibleMobile;
    }

    public void setResponsibleMobile(String responsibleMobile) {
        this.responsibleMobile = responsibleMobile;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
    }

    @Override
    public String toString() {
        return "GoodsDetail{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", productListId='" + productListId + '\'' +
                ", productSellNum='" + productSellNum + '\'' +
                ", productAppDesc='" + productAppDesc + '\'' +
                ", productAvailable='" + productAvailable + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", innerTime='" + innerTime + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", mark='" + mark + '\'' +
                ", depotResponsible='" + depotResponsible + '\'' +
                ", responsibleMobile='" + responsibleMobile + '\'' +
                ", responsibleEmail='" + responsibleEmail + '\'' +
                ", depotAddress='" + depotAddress + '\'' +
                '}';
    }
}
