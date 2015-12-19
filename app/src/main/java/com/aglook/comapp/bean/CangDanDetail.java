package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/29.
 */
public class CangDanDetail {
    private String productName;//商品名称
    private String originalListId;//原始仓单编号
    private String orderdataId;//平台仓单id
    private String goodsPlace;//产地
    private String innerTime;//入口时间
    private String weightUseable;//可以使用的数量
    private String mark;//唛头
    private String productMoney;//价格
    private String innerWeight;//入口总量
    private String weightUse;//可用
    private String categoryName;//种类名称
    private String depotQuality;//货物质量
    private String depotResponsible;//仓库负责人
    private String responsibleMobile;//仓库负责人座机
    private String responsibleEmail;//仓库负责人邮箱
    private String depotAddress;//仓库地址
    private String productDesc;//商品描述
    private String validTime;//有效时间
    private List<Buyer> customerList;
    private String productOwnerProve;//货权图片
    private String productLogo;//货物图片

    public String getProductOwnerProve() {
        return productOwnerProve;
    }

    public void setProductOwnerProve(String productOwnerProve) {
        this.productOwnerProve = productOwnerProve;
    }

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public List<Buyer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Buyer> customerList) {
        this.customerList = customerList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginalListId() {
        return originalListId;
    }

    public void setOriginalListId(String originalListId) {
        this.originalListId = originalListId;
    }

    public String getOrderdataId() {
        return orderdataId;
    }

    public void setOrderdataId(String orderdataId) {
        this.orderdataId = orderdataId;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getInnerTime() {
        return innerTime;
    }

    public void setInnerTime(String innerTime) {
        this.innerTime = innerTime;
    }

    public String getWeightUseable() {
        return weightUseable;
    }

    public void setWeightUseable(String weightUseable) {
        this.weightUseable = weightUseable;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDepotQuality() {
        return depotQuality;
    }

    public void setDepotQuality(String depotQuality) {
        this.depotQuality = depotQuality;
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
        return "CangDanDetail{" +
                "productName='" + productName + '\'' +
                ", originalListId='" + originalListId + '\'' +
                ", orderdataId='" + orderdataId + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", innerTime='" + innerTime + '\'' +
                ", weightUseable='" + weightUseable + '\'' +
                ", mark='" + mark + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", innerWeight='" + innerWeight + '\'' +
                ", weightUse='" + weightUse + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", depotQuality='" + depotQuality + '\'' +
                ", depotResponsible='" + depotResponsible + '\'' +
                ", responsibleMobile='" + responsibleMobile + '\'' +
                ", responsibleEmail='" + responsibleEmail + '\'' +
                ", depotAddress='" + depotAddress + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", validTime='" + validTime + '\'' +
                ", customerList=" + customerList +
                ", productOwnerProve='" + productOwnerProve + '\'' +
                ", productLogo='" + productLogo + '\'' +
                '}';
    }
}
