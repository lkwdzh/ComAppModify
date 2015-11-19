package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/3.
 */
public class ShoppingCart implements Serializable{
    private String cartId;
    private String productId;
    private int productNum;
    private boolean isChecked;
    private double productMoney;
    private String productName;
    private String productLogo;
    private double total;
    //手续费
    private double costMoney;

    private String pointUser;

    public String getPointUser() {
        return pointUser;
    }

    public void setPointUser(String pointUser) {
        this.pointUser = pointUser;
    }

    public double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(double costMoney) {
        this.costMoney = costMoney;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(double productMoney) {
        this.productMoney = productMoney;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", productId='" + productId + '\'' +
                ", productNum=" + productNum +
                ", isChecked=" + isChecked +
                ", productMoney=" + productMoney +
                ", productName='" + productName + '\'' +
                ", productLogo='" + productLogo + '\'' +
                ", total=" + total +
                ", costMoney=" + costMoney +
                ", pointUser='" + pointUser + '\'' +
                '}';
    }
}
