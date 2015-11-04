package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/3.
 */
public class ShoppingCart {
    private int num;
    private boolean isChecked;
    private double price;
    private double total;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "num=" + num +
                ", isChecked=" + isChecked +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
