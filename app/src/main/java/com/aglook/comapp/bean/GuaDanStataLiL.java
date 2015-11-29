package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/29.
 */
public class GuaDanStataLiL {
   private String  orderdataId;//": 52, 交易编号
    private String  orderId;//": "2015111811142289679",商品名称
    private String  productId;//": "11",商品id
    private String  productName;//": "红芸豆",商品名称
    private String  productLogo;//":图片
    private String  productMoney;//": 20000.00,商品价格
    private String  productMoneyYh;//": 0.00,优惠价格
    private String  productNum;//": 2,总量
    private String  proruleKey;//": "",
    private String  proruleName;//": "",
    private String  harvestCode;//": null,
    private String  innerWeight;//": 2,总量
    private String  weightUse;//": 0,交易数量
    private String  weightUseable;//": 2,可用数量
    private String  storage;//": 60,仓储费
    private String  counter;//": 20,手续费
    private String  orderState;//": "success",订单状态notpay(待支付)
   // success(支付成功)
    //close(关闭交易)
    private String  productListId;//": "201511045639c51f390fc",原始仓单号
    private String  orderAtime;//": 1447816462,创建时间
    private String  orderPtime;//": 0,支付时间
    private String  orderFtime;//": 0完成时间

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

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }

    public String getProductMoneyYh() {
        return productMoneyYh;
    }

    public void setProductMoneyYh(String productMoneyYh) {
        this.productMoneyYh = productMoneyYh;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getProductListId() {
        return productListId;
    }

    public void setProductListId(String productListId) {
        this.productListId = productListId;
    }

    public String getOrderAtime() {
        return orderAtime;
    }

    public void setOrderAtime(String orderAtime) {
        this.orderAtime = orderAtime;
    }

    public String getOrderPtime() {
        return orderPtime;
    }

    public void setOrderPtime(String orderPtime) {
        this.orderPtime = orderPtime;
    }

    public String getOrderFtime() {
        return orderFtime;
    }

    public void setOrderFtime(String orderFtime) {
        this.orderFtime = orderFtime;
    }

    @Override
    public String toString() {
        return "GuaDanStataLiL{" +
                "orderdataId='" + orderdataId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", productMoneyYh='" + productMoneyYh + '\'' +
                ", productNum='" + productNum + '\'' +
                ", proruleKey='" + proruleKey + '\'' +
                ", proruleName='" + proruleName + '\'' +
                ", harvestCode='" + harvestCode + '\'' +
                ", innerWeight='" + innerWeight + '\'' +
                ", weightUse='" + weightUse + '\'' +
                ", weightUseable='" + weightUseable + '\'' +
                ", storage='" + storage + '\'' +
                ", counter='" + counter + '\'' +
                ", orderState='" + orderState + '\'' +
                ", productListId='" + productListId + '\'' +
                ", orderAtime='" + orderAtime + '\'' +
                ", orderPtime='" + orderPtime + '\'' +
                ", orderFtime='" + orderFtime + '\'' +
                '}';
    }
}
