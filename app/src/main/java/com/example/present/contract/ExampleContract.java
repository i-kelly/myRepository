package com.example.present.contract;

import com.example.base.BaseView;
import com.example.model.bean.ExampleBean;
import com.example.present.IPresenter;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:23
 * @desc TODO 类作用
 */
public interface ExampleContract {

    interface View extends BaseView {

        void showContent(ExampleBean bean);

        void onSuccess();

        void  fail();


    }

    interface  Presenter extends IPresenter<View> {

        void getDetailData(String id);

        void getExtraData(int id);

    }
}
