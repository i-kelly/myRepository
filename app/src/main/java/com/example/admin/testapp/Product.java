package com.example.admin.testapp;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 17:44
 * @desc TODO 类作用
 */
public class Product {
    private static final String TAG = "Product";
    private int    rate;
    private String price;
    private String monthSales;
    private String description;
    private String    name;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMonthSales(String monthSales) {
        this.monthSales = monthSales;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    private String imagePath;
    private int    leftNum;

    public int getRate() {
        return rate;
    }

    public String getPrice() {
        return price;
    }

    public String getMonthSales() {
        return monthSales;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getLeftNum() {
        return leftNum;
    }
}
