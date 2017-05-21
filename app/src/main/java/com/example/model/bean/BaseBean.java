package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   BaseBean
 *  @创建者:   Admin
 *  @创建时间:  2017/3/20 22:45
 *  @描述：    基类bean
 */

public class BaseBean<T> {

    /**
     * message : 提交成功
     * data : {"state":"0","obj":{"expiryNumber":"","nonProfitFound":"098195.00","isPopup":"0"},"msg":"查询成功"}
     * code : 000
     */

    public String message;
    public T      data;
    public String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public <K> K getData() {
        return (K) message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
