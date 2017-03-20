package com.example.model.bean;

import com.google.gson.Gson;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:31
 * @desc json解析实体类
 */
public class ExampleBean extends Entity{
    private static final String TAG = "ExampleBean";

    /**
     * state : 0
     * obj : {"expiryNumber":"","nonProfitFound":"098195.00","isPopup":"0"}
     */

    public String state;
    public ObjBean obj;

    public static ExampleBean objectFromData(String str) {

        return new Gson().fromJson(str, ExampleBean.class);
    }

    public static class ObjBean {
        /**
         * expiryNumber :
         * nonProfitFound : 098195.00
         * isPopup : 0
         */

        public String expiryNumber;
        public String nonProfitFound;
        public String isPopup;

        public static ObjBean objectFromData(String str) {

            return new Gson().fromJson(str, ObjBean.class);
        }
    }
}
