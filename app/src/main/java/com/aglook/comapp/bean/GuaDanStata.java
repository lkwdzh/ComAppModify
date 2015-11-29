package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/29.
 */
public class GuaDanStata {
    private String productListid;//原始仓单编号
    private String productName;//商品名称
    private String productSellid;//交易编号
    private String  productNum;//剩余量
    private String productSellnum;//没用
    private GuaDanStataLi list;

    public String getProductListid() {
        return productListid;
    }

    public void setProductListid(String productListid) {
        this.productListid = productListid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSellid() {
        return productSellid;
    }

    public void setProductSellid(String productSellid) {
        this.productSellid = productSellid;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductSellnum() {
        return productSellnum;
    }

    public void setProductSellnum(String productSellnum) {
        this.productSellnum = productSellnum;
    }

    public GuaDanStataLi getList() {
        return list;
    }

    public void setList(GuaDanStataLi list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GuaDanStata{" +
                "productListid='" + productListid + '\'' +
                ", productName='" + productName + '\'' +
                ", productSellid='" + productSellid + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productSellnum='" + productSellnum + '\'' +
                ", list=" + list +
                '}';
    }
}
