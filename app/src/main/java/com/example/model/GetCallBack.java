package com.example.model;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model
 *  @文件名:   GetCallBack
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 13:22
 *  @描述：    TODO
 */

import android.support.annotation.NonNull;

import com.example.model.bean.Entity;


public interface GetCallBack {
    /**
     * 请求list数据
     */
    interface GetTasksCallback<T extends Entity>
            extends CallBack<T>
    {

        void onTaskLoaded(T beans);

        void onDataNotAvailable();
    }

    /**
     * 请求object数据
     */
    interface GetTaskCallback<T extends Entity>
            extends CallBack<T>
    {

        String getParams();

        void onTaskLoaded(T bean);

        void onDataNotAvailable();
    }


    void getListDatas(@NonNull GetTasksCallback callback);

    void getObjData(@NonNull GetTaskCallback callback);
}
