package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2016/1/21.
 */
public class OrderDetailOrder implements Serializable{

    /**
     * orderId : 160120182140158    订单ID
     * orderOutid : 0
     * orderMoney : 1234             订单金额
     * orderProductMoney : 1234
     * counterFee : 0
     * orderQuanId : 0
     * orderQuanName :
     * orderQuanMoney : 0
     * orderPointGet : 0
     * orderPointUse : 0
     * orderPointMoney : 0
     * orderWlId :
     * orderWlName :
     * orderWlMoney : 0
     * orderAtime : 1453285300  下单时间
     * orderPtime : 0   付款时间
     * orderStime : 0  提货时间
     * orderFtime : 0  完成时间
     * orderPayway :
     * orderComment : true
     * orderState : notpay  订单状态
     * orderText : |            订单留言
     * orderClosetext :     订单关闭原因
     * userId : 243
     * userName : 白客
     * userTname : lkj      收货姓名
     * userPhone : 13562386555      收货手机
     * userTel :
     * userAddress : 广东河源不知道地方      发票地址
     * userRise :           发票抬头
     * fType :              发票类型0普通，1.增值
     * userCaty :       增值税发票公司
     * userNnumb :          纳税人识别号
     * userZcdz :           注册地址
     * userTels :           公司联系电话
     * userBanks :          开会银行
     * userBnumb :    银行账号
     */

    private long orderId;
    private int orderOutid;
    private int orderMoney;
    private int orderProductMoney;
    private int counterFee;
    private int orderQuanId;
    private String orderQuanName;
    private int orderQuanMoney;
    private int orderPointGet;
    private int orderPointUse;
    private int orderPointMoney;
    private String orderWlId;
    private String orderWlName;
    private int orderWlMoney;
    private int orderAtime;
    private int orderPtime;
    private int orderStime;
    private int orderFtime;
    private String orderPayway;
    private boolean orderComment;
    private String orderState;
    private String orderText;
    private String orderClosetext;
    private int userId;
    private String userName;
    private String userTname;
    private String userPhone;
    private String userTel;
    private String userAddress;
    private String userRise;
    private String fType;
    private String userCaty;
    private String userNnumb;
    private String userZcdz;
    private String userTels;
    private String userBanks;
    private String userBnumb;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setOrderOutid(int orderOutid) {
        this.orderOutid = orderOutid;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }

    public void setOrderProductMoney(int orderProductMoney) {
        this.orderProductMoney = orderProductMoney;
    }

    public void setCounterFee(int counterFee) {
        this.counterFee = counterFee;
    }

    public void setOrderQuanId(int orderQuanId) {
        this.orderQuanId = orderQuanId;
    }

    public void setOrderQuanName(String orderQuanName) {
        this.orderQuanName = orderQuanName;
    }

    public void setOrderQuanMoney(int orderQuanMoney) {
        this.orderQuanMoney = orderQuanMoney;
    }

    public void setOrderPointGet(int orderPointGet) {
        this.orderPointGet = orderPointGet;
    }

    public void setOrderPointUse(int orderPointUse) {
        this.orderPointUse = orderPointUse;
    }

    public void setOrderPointMoney(int orderPointMoney) {
        this.orderPointMoney = orderPointMoney;
    }

    public void setOrderWlId(String orderWlId) {
        this.orderWlId = orderWlId;
    }

    public void setOrderWlName(String orderWlName) {
        this.orderWlName = orderWlName;
    }

    public void setOrderWlMoney(int orderWlMoney) {
        this.orderWlMoney = orderWlMoney;
    }

    public void setOrderAtime(int orderAtime) {
        this.orderAtime = orderAtime;
    }

    public void setOrderPtime(int orderPtime) {
        this.orderPtime = orderPtime;
    }

    public void setOrderStime(int orderStime) {
        this.orderStime = orderStime;
    }

    public void setOrderFtime(int orderFtime) {
        this.orderFtime = orderFtime;
    }

    public void setOrderPayway(String orderPayway) {
        this.orderPayway = orderPayway;
    }

    public void setOrderComment(boolean orderComment) {
        this.orderComment = orderComment;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public void setOrderClosetext(String orderClosetext) {
        this.orderClosetext = orderClosetext;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserTname(String userTname) {
        this.userTname = userTname;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserRise(String userRise) {
        this.userRise = userRise;
    }

    public void setFType(String fType) {
        this.fType = fType;
    }

    public void setUserCaty(String userCaty) {
        this.userCaty = userCaty;
    }

    public void setUserNnumb(String userNnumb) {
        this.userNnumb = userNnumb;
    }

    public void setUserZcdz(String userZcdz) {
        this.userZcdz = userZcdz;
    }

    public void setUserTels(String userTels) {
        this.userTels = userTels;
    }

    public void setUserBanks(String userBanks) {
        this.userBanks = userBanks;
    }

    public void setUserBnumb(String userBnumb) {
        this.userBnumb = userBnumb;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getOrderOutid() {
        return orderOutid;
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public int getOrderProductMoney() {
        return orderProductMoney;
    }

    public int getCounterFee() {
        return counterFee;
    }

    public int getOrderQuanId() {
        return orderQuanId;
    }

    public String getOrderQuanName() {
        return orderQuanName;
    }

    public int getOrderQuanMoney() {
        return orderQuanMoney;
    }

    public int getOrderPointGet() {
        return orderPointGet;
    }

    public int getOrderPointUse() {
        return orderPointUse;
    }

    public int getOrderPointMoney() {
        return orderPointMoney;
    }

    public String getOrderWlId() {
        return orderWlId;
    }

    public String getOrderWlName() {
        return orderWlName;
    }

    public int getOrderWlMoney() {
        return orderWlMoney;
    }

    public int getOrderAtime() {
        return orderAtime;
    }

    public int getOrderPtime() {
        return orderPtime;
    }

    public int getOrderStime() {
        return orderStime;
    }

    public int getOrderFtime() {
        return orderFtime;
    }

    public String getOrderPayway() {
        return orderPayway;
    }

    public boolean isOrderComment() {
        return orderComment;
    }

    public String getOrderState() {
        return orderState;
    }

    public String getOrderText() {
        return orderText;
    }

    public String getOrderClosetext() {
        return orderClosetext;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTname() {
        return userTname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserTel() {
        return userTel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserRise() {
        return userRise;
    }

    public String getFType() {
        return fType;
    }

    public String getUserCaty() {
        return userCaty;
    }

    public String getUserNnumb() {
        return userNnumb;
    }

    public String getUserZcdz() {
        return userZcdz;
    }

    public String getUserTels() {
        return userTels;
    }

    public String getUserBanks() {
        return userBanks;
    }

    public String getUserBnumb() {
        return userBnumb;
    }

    @Override
    public String toString() {
        return "OrderDetailOrder{" +
                "orderId=" + orderId +
                ", orderOutid=" + orderOutid +
                ", orderMoney=" + orderMoney +
                ", orderProductMoney=" + orderProductMoney +
                ", counterFee=" + counterFee +
                ", orderQuanId=" + orderQuanId +
                ", orderQuanName='" + orderQuanName + '\'' +
                ", orderQuanMoney=" + orderQuanMoney +
                ", orderPointGet=" + orderPointGet +
                ", orderPointUse=" + orderPointUse +
                ", orderPointMoney=" + orderPointMoney +
                ", orderWlId='" + orderWlId + '\'' +
                ", orderWlName='" + orderWlName + '\'' +
                ", orderWlMoney=" + orderWlMoney +
                ", orderAtime=" + orderAtime +
                ", orderPtime=" + orderPtime +
                ", orderStime=" + orderStime +
                ", orderFtime=" + orderFtime +
                ", orderPayway='" + orderPayway + '\'' +
                ", orderComment=" + orderComment +
                ", orderState='" + orderState + '\'' +
                ", orderText='" + orderText + '\'' +
                ", orderClosetext='" + orderClosetext + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userTname='" + userTname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userRise='" + userRise + '\'' +
                ", fType='" + fType + '\'' +
                ", userCaty='" + userCaty + '\'' +
                ", userNnumb='" + userNnumb + '\'' +
                ", userZcdz='" + userZcdz + '\'' +
                ", userTels='" + userTels + '\'' +
                ", userBanks='" + userBanks + '\'' +
                ", userBnumb='" + userBnumb + '\'' +
                '}';
    }
}
