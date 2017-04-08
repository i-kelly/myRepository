package com.example.model.bean;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-04-08 17:02
 * @desc TODO 类作用
 */
public class BannerBean extends Entity{

    /**
     * onSale : 1
     * bdResouceId : 309
     * shopId : 99
     * bindType : 2
     * adImgUrl : http://img.siigee.com/group1/M00/00/21/Cqlw5VjQwJGAC2JzAAEI9pVRXYo686_640x400.jpg
     * adTitle :
     */

    private String onSale;
    private String bdResouceId;
    private String shopId;
    private String bindType;
    private String adImgUrl;
    private String adTitle;

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public String getBdResouceId() {
        return bdResouceId;
    }

    public void setBdResouceId(String bdResouceId) {
        this.bdResouceId = bdResouceId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getAdImgUrl() {
        return adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }
}
