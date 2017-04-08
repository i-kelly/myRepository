package com.example.model.bean;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-04-08 17:11
 * @desc TODO 类作用
 */
public class AdBean extends Entity{
    private static final String TAG = "AdListBean";

    /**
     * onSale : 1
     * shopId : 97
     * productUrl : http://img.siigee.com/group1/M00/00/20/Cqlw5VjPYciAG8moAAHFC_o7Ktg209_280x280.jpg
     * productName : 女士肩部打结背带裙
     * productId : 303
     */

    private String onSale;
    private String shopId;
    private String productUrl;
    private String productName;
    private String productId;

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
