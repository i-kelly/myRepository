package com.example.present.contract;

import com.example.base.IView;
import com.example.model.bean.ExampleBean;
import com.example.present.IPresenter;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:23
 * @desc 契约类
 */
public interface ExampleContract {

    interface View
            extends IView
    {

        void showContent(ExampleBean bean);

        String getText();


    }

    interface Presenter
            extends IPresenter<View>
    {

        void getDetailData();

        void getExtraData(int id);

    }
}
