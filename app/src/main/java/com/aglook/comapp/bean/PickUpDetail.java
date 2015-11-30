package com.aglook.comapp.bean;

import java.util.List;

/**
 * Created by aglook on 2015/11/30.
 */
public class PickUpDetail {

    private String  id;//":1,提货单id
    private String  getId;//":"2147483647",提单号
    private String  userId;//":1,
    private String  originListid;//":"201511045639c51f390fc",原始仓单
    private String  getWeight;//":10,提货总量
    private String  getWeightRemain;//":10,提货余量
    private String  getAtime;//":1446692159,提货单创建时间
    private String  orderdataId;//":0,平台仓单id ,为0是原始仓单
    private String  type;//":null,0为原始仓单,1为平台仓单
    private String  isget;//":1,0取消 ;1提货中;2提货成功
    private String  getCtime;//":1446804627,
    private String  getFtime;//":null,
    private String  orderId;//":null,
    private String  productName;//":"早稻",产品名称
    private String  productNum;//":null,
    private String  goodsPlace;//":"湖北",产地
    private String  goodsType;//":"23",货物种类id
    private String  depotQuality;//":"二级",等级
    private String  depotAddr;//":"地址",仓库地址
    private String  categoryName;//":"早稻",种类名称
    private String  categoryTitle;//":"早稻",种类标题
    private List<ModfyDriverList>driverList;//":提货司机列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOriginListid() {
        return originListid;
    }

    public void setOriginListid(String originListid) {
        this.originListid = originListid;
    }

    public String getGetWeight() {
        return getWeight;
    }

    public void setGetWeight(String getWeight) {
        this.getWeight = getWeight;
    }

    public String getGetWeightRemain() {
        return getWeightRemain;
    }

    public void setGetWeightRemain(String getWeightRemain) {
        this.getWeightRemain = getWeightRemain;
    }

    public String getGetAtime() {
        return getAtime;
    }

    public void setGetAtime(String getAtime) {
        this.getAtime = getAtime;
    }

    public String getOrderdataId() {
        return orderdataId;
    }

    public void setOrderdataId(String orderdataId) {
        this.orderdataId = orderdataId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsget() {
        return isget;
    }

    public void setIsget(String isget) {
        this.isget = isget;
    }

    public String getGetCtime() {
        return getCtime;
    }

    public void setGetCtime(String getCtime) {
        this.getCtime = getCtime;
    }

    public String getGetFtime() {
        return getFtime;
    }

    public void setGetFtime(String getFtime) {
        this.getFtime = getFtime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
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

    public String getDepotAddr() {
        return depotAddr;
    }

    public void setDepotAddr(String depotAddr) {
        this.depotAddr = depotAddr;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<ModfyDriverList> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<ModfyDriverList> driverList) {
        this.driverList = driverList;
    }

    @Override
    public String toString() {
        return "PickUpDetail{" +
                "id='" + id + '\'' +
                ", getId='" + getId + '\'' +
                ", userId='" + userId + '\'' +
                ", originListid='" + originListid + '\'' +
                ", getWeight='" + getWeight + '\'' +
                ", getWeightRemain='" + getWeightRemain + '\'' +
                ", getAtime='" + getAtime + '\'' +
                ", orderdataId='" + orderdataId + '\'' +
                ", type='" + type + '\'' +
                ", isget='" + isget + '\'' +
                ", getCtime='" + getCtime + '\'' +
                ", getFtime='" + getFtime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", productNum='" + productNum + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", depotQuality='" + depotQuality + '\'' +
                ", depotAddr='" + depotAddr + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", driverList=" + driverList +
                '}';
    }
}
