package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   ReqBean
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 12:51
 *  @描述：    请求基础bean
 */

import util.DetectTool;

public class ReqBean
        extends Entity {
    public String m = DetectTool.getType();
    public String u = DetectTool.getToken();
    public String v = DetectTool.getVersionName();
    public String i = DetectTool.getIMEI();
    public String t = DetectTool.getTS();
}
