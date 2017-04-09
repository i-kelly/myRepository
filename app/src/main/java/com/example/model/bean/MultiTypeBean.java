package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   MultiTypeBean
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 20:08
 *  @描述：
 */

import com.google.gson.Gson;

import java.util.List;

public class MultiTypeBean
        extends Entity
{

    /**
     *"state": "0",
     */
    public String state;
    /**
     * obj : {"bannerList":[{"onSale":"1","bdResouceId":"5","shopId":"","bindType":"5","adImgUrl":"http: //img.siigee.com/group1/M00/00/21/Cqlw5VjRDSSALJySAACImwjvuJw945_640x400.jpg","adTitle":""}],"brandElebration":"","adHomePageList":[{"onSale":"1","bdResouceId":"5","shopId":"","bindType":"5","adImgUrl":"http: //img.siigee.com/group1/M00/00/22/Cqlw5VjaD96AaOWgAAJhMk_CDyg819_640x320.jpg","adTitle":""}],"sgInternational":"","rdProductList":[{"onSale":"1","shopId":"57","productUrl":"http: //img.siigee.com/group1/M00/00/23/Cqlw5Vjd1CaAHbrkAAEbYG8vEWc944_280x280.jpg","productName":"都柏林2017新款夏装圆领宽松趣味图案连衣裙预卡卡西","productId":"310"}],"weekSelect":"","weekSelling":"","fashionPoint":"","videoGuide":"","brandHomePageList":[{"onSale":"","bdResouceId":"57","shopId":"","bindType":"3","adImgUrl":"http: //img.siigee.com/group1/M00/00/24/Cqlw5VjeKFWAQQh6AAG2bHEkg0I963_640x320.jpg","adTitle":""}]}
     */

    public ObjBean obj;

    public static MultiTypeBean objectFromData(String str) {

        return new Gson().fromJson(str, MultiTypeBean.class);
    }

    public static class ObjBean {
        /**
         * bannerList : [{"onSale":"1","bdResouceId":"5","shopId":"","bindType":"5","adImgUrl":"http: //img.siigee.com/group1/M00/00/21/Cqlw5VjRDSSALJySAACImwjvuJw945_640x400.jpg","adTitle":""}]
         * brandElebration :
         * adHomePageList : [{"onSale":"1","bdResouceId":"5","shopId":"","bindType":"5","adImgUrl":"http: //img.siigee.com/group1/M00/00/22/Cqlw5VjaD96AaOWgAAJhMk_CDyg819_640x320.jpg","adTitle":""}]
         * sgInternational :
         * rdProductList : [{"onSale":"1","shopId":"57","productUrl":"http: //img.siigee.com/group1/M00/00/23/Cqlw5Vjd1CaAHbrkAAEbYG8vEWc944_280x280.jpg","productName":"都柏林2017新款夏装圆领宽松趣味图案连衣裙预卡卡西","productId":"310"}]
         * weekSelect :
         * weekSelling :
         * fashionPoint :
         * videoGuide :
         * brandHomePageList : [{"onSale":"","bdResouceId":"57","shopId":"","bindType":"3","adImgUrl":"http: //img.siigee.com/group1/M00/00/24/Cqlw5VjeKFWAQQh6AAG2bHEkg0I963_640x320.jpg","adTitle":""}]
         */

        public String                      brandElebration;
        public String                      sgInternational;
        public String                      weekSelect;
        public String                      weekSelling;
        public String                      fashionPoint;
        public String                      videoGuide;
        public List<BannerListBean>        bannerList;
        public List<AdHomePageListBean>    adHomePageList;
        public List<RdProductListBean>     rdProductList;
        public List<BrandHomePageListBean> brandHomePageList;

        public static ObjBean objectFromData(String str) {

            return new Gson().fromJson(str, ObjBean.class);
        }

        public static class BannerListBean {
            /**
             * onSale : 1
             * bdResouceId : 5
             * shopId :
             * bindType : 5
             * adImgUrl : http: //img.siigee.com/group1/M00/00/21/Cqlw5VjRDSSALJySAACImwjvuJw945_640x400.jpg
             * adTitle :
             */

            public String onSale;
            public String bdResouceId;
            public String shopId;
            public String bindType;
            public String adImgUrl;
            public String adTitle;

            public static BannerListBean objectFromData(String str) {

                return new Gson().fromJson(str, BannerListBean.class);
            }
        }

        public static class AdHomePageListBean {
            /**
             * onSale : 1
             * bdResouceId : 5
             * shopId :
             * bindType : 5
             * adImgUrl : http: //img.siigee.com/group1/M00/00/22/Cqlw5VjaD96AaOWgAAJhMk_CDyg819_640x320.jpg
             * adTitle :
             */

            public String onSale;
            public String bdResouceId;
            public String shopId;
            public String bindType;
            public String adImgUrl;
            public String adTitle;

            public static AdHomePageListBean objectFromData(String str) {

                return new Gson().fromJson(str, AdHomePageListBean.class);
            }
        }

        public static class RdProductListBean {
            /**
             * onSale : 1
             * shopId : 57
             * productUrl : http: //img.siigee.com/group1/M00/00/23/Cqlw5Vjd1CaAHbrkAAEbYG8vEWc944_280x280.jpg
             * productName : 都柏林2017新款夏装圆领宽松趣味图案连衣裙预卡卡西
             * productId : 310
             */

            public String onSale;
            public String shopId;
            public String productUrl;
            public String productName;
            public String productId;

            public static RdProductListBean objectFromData(String str) {

                return new Gson().fromJson(str, RdProductListBean.class);
            }
        }

        public static class BrandHomePageListBean {
            /**
             * onSale :
             * bdResouceId : 57
             * shopId :
             * bindType : 3
             * adImgUrl : http: //img.siigee.com/group1/M00/00/24/Cqlw5VjeKFWAQQh6AAG2bHEkg0I963_640x320.jpg
             * adTitle :
             */

            public String onSale;
            public String bdResouceId;
            public String shopId;
            public String bindType;
            public String adImgUrl;
            public String adTitle;

            public static BrandHomePageListBean objectFromData(String str) {

                return new Gson().fromJson(str, BrandHomePageListBean.class);
            }
        }
    }
}

