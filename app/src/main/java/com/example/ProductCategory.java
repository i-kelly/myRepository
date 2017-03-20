package com.example;

import com.example.admin.testapp.Product;

import java.util.List;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 17:28
 * @desc 商品分类
 */
public class ProductCategory {
    private static final String TAG = "ProductCategory";
    private String        name;
    private List<Product> products;
    private String        description;

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }
}
