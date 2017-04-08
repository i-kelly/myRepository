package com.example.model.bean;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-04-08 17:08
 * @desc 品牌列表
 */
public class BrandBean extends Entity{

    /**
     * onSale :
     * bdResouceId : 98
     * shopId :
     * bindType : 3
     * adImgUrl : http://img.siigee.com/group1/M00/00/21/Cqlw5VjQxkSAD08hAAPgYwUysG0474_640x320.jpg
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
