package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/28.
 */
public class PickUpList {
    private String id;//": 7,
    private String getId;//": "t20151128145115",提货仓单号
    private String userId;//": 13,用户id
    private String originListid;//": "201511045639xxxxxxxx",原始仓单号
    private String getWeight;//": 1,
    private String getWeightRemain;//": 1,
    private String getAtime;//": 1448693475,申请提货时间
    private String orderdataId;//": 0,     交易订单详细ID 0 原始仓单
    private String type;//": null,0 原始仓单 1 平台仓单
    private String isget;//": 1,   0 取消 1 提货中2 已提完
    private String getCtime;//": null,取消提货时间
    private String getFtime;//": null,提货完成时间
    private String orderId;//": null,交易订单号
    private String productName;//": "早稻",产品名称
    private String productNum;//": null,
    private String goodsPlace;//": "湖北",产地
    private String goodsType;//": "23",种类
    private String depotQuality;//": "二级",质量
    private String depotAddr;//": "地址",仓库地址
    private String categoryName;//": "早稻",种类名称
    private String categoryTitle;//": "早稻" 种类标题
    private String innerTime;//入仓时间
    private String productLogo;//图片

    public String getProductLogo() {
        return productLogo;
    }

    public void setProductLogo(String productLogo) {
        this.productLogo = productLogo;
    }

    public String getInnerTime() {
        return innerTime;
    }

    public void setInnerTime(String innerTime) {
        this.innerTime = innerTime;
    }

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

    @Override
    public String toString() {
        return "PickUpList{" +
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
                '}';
    }
}
