package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/13.
 */
public class GoodsDetail {
    private String productId;//产品id
    private String productName;//产品名称
    private String productMoney;//价格
    private String productListId;//仓单号
    private String productSellNum;//可用重量
    private String productAppDesc;//
    private String productAvailable;//挂单有效期
    private String productLogo;//图片
    private String categoryName;//货物种类
    private String innerTime;//入库时间
    private String goodsPlace;//货物产地
    private String goodsType;//货物类型
    private String mark;//噱头
    private String depotResponsible;//仓库负责人
    private String responsibleMobile;//电话
    private String responsibleEmail;//邮箱
    private String depotAddress;//仓库地址
    private String isCollect;//0:未收藏，1：已收藏

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
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
                ", isCollect='" + isCollect + '\'' +
                '}';
    }
}
