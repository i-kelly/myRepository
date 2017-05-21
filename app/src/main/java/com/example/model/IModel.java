package com.example.model;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 15:35
 * @desc model层 接口
 */
public interface IModel {

   <T> void getData(int flag, IResult IResult);
}
