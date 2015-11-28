package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/24.
 */
public class CangDanList implements Serializable{
    private int id;//id
    private String listId;//原始仓单号
    private String mark;//唛头
    private String innerTime;//入仓时间
    private CangDanListCategory pshCategory;//种类
    private String goodsOwner;//货权人
    private String goodsOwnerMobile;//货权人电话
    private String goodsOwnerPhone;//手机
    private String goodsOwnerEmail;//货权人邮箱
    private String goodsOwnerProve;//货权证明图片
    private String innerWeight;//入库重量
    private long weightUse;//正在出售数量
    private long weightUseable;//未出售数量
    private String goodsPlace;//产地
    private String goodsType;//货物种类
    private String depotQuality;//货物级别描述
    private String depotResponsible;//仓库负责人
    private String responsibleMobile;//负责人电话
    private String responsiblePhone;//负责人手机号
    private String responsibleEmail;//负责人邮箱
    private String depotAddr;//仓库地址
    private String getlistId;
    private String getlistPic;//图片
    private String getlistWeight;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getInnerTime() {
        return innerTime;
    }

    public void setInnerTime(String innerTime) {
        this.innerTime = innerTime;
    }

    public CangDanListCategory getPshCategory() {
        return pshCategory;
    }

    public void setPshCategory(CangDanListCategory pshCategory) {
        this.pshCategory = pshCategory;
    }

    public String getGoodsOwner() {
        return goodsOwner;
    }

    public void setGoodsOwner(String goodsOwner) {
        this.goodsOwner = goodsOwner;
    }

    public String getGoodsOwnerMobile() {
        return goodsOwnerMobile;
    }

    public void setGoodsOwnerMobile(String goodsOwnerMobile) {
        this.goodsOwnerMobile = goodsOwnerMobile;
    }

    public String getGoodsOwnerProve() {
        return goodsOwnerProve;
    }

    public void setGoodsOwnerProve(String goodsOwnerProve) {
        this.goodsOwnerProve = goodsOwnerProve;
    }

    public String getInnerWeight() {
        return innerWeight;
    }

    public void setInnerWeight(String innerWeight) {
        this.innerWeight = innerWeight;
    }

    public long getWeightUse() {
        return weightUse;
    }

    public void setWeightUse(long weightUse) {
        this.weightUse = weightUse;
    }

    public long getWeightUseable() {
        return weightUseable;
    }

    public void setWeightUseable(long weightUseable) {
        this.weightUseable = weightUseable;
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

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getDepotAddr() {
        return depotAddr;
    }

    public void setDepotAddr(String depotAddr) {
        this.depotAddr = depotAddr;
    }

    public String getGetlistId() {
        return getlistId;
    }

    public void setGetlistId(String getlistId) {
        this.getlistId = getlistId;
    }

    public String getGetlistPic() {
        return getlistPic;
    }

    public void setGetlistPic(String getlistPic) {
        this.getlistPic = getlistPic;
    }

    public String getGoodsOwnerPhone() {
        return goodsOwnerPhone;
    }

    public void setGoodsOwnerPhone(String goodsOwnerPhone) {
        this.goodsOwnerPhone = goodsOwnerPhone;
    }

    public String getGoodsOwnerEmail() {
        return goodsOwnerEmail;
    }

    public void setGoodsOwnerEmail(String goodsOwnerEmail) {
        this.goodsOwnerEmail = goodsOwnerEmail;
    }

    public String getGetlistWeight() {
        return getlistWeight;
    }

    public void setGetlistWeight(String getlistWeight) {
        this.getlistWeight = getlistWeight;
    }

    @Override
    public String toString() {
        return "CangDanList{" +
                "id=" + id +
                ", listId='" + listId + '\'' +
                ", getlistId='" + getlistId + '\'' +
                ", getlistPic='" + getlistPic + '\'' +
                ", mark='" + mark + '\'' +
                ", goodsOwner='" + goodsOwner + '\'' +
                ", goodsOwnerMobile='" + goodsOwnerMobile + '\'' +
                ", goodsOwnerPhone='" + goodsOwnerPhone + '\'' +
                ", goodsOwnerEmail='" + goodsOwnerEmail + '\'' +
                ", goodsOwnerProve='" + goodsOwnerProve + '\'' +
                ", getlistWeight='" + getlistWeight + '\'' +
                ", innerWeight='" + innerWeight + '\'' +
                ", weightUse=" + weightUse +
                ", weightUseable=" + weightUseable +
                ", innerTime='" + innerTime + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", depotQuality='" + depotQuality + '\'' +
                ", depotResponsible='" + depotResponsible + '\'' +
                ", responsibleMobile='" + responsibleMobile + '\'' +
                ", responsiblePhone='" + responsiblePhone + '\'' +
                ", responsibleEmail='" + responsibleEmail + '\'' +
                ", depotAddr='" + depotAddr + '\'' +
                ", pshCategory=" + pshCategory +
                '}';
    }
}
