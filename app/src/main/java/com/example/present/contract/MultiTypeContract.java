package com.example.present.contract;

import com.example.base.BaseView;
import com.example.model.bean.AdBean;
import com.example.model.bean.BannerBean;
import com.example.model.bean.BrandBean;
import com.example.model.bean.ExampleBean;
import com.example.present.IPresenter;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:23
 * @desc 契约类
 */
public interface MultiTypeContract {

    interface View extends BaseView {

        void showBanner(BannerBean bean);

        void showBrandList(BrandBean bean);

        void showAdList(AdBean bean);

        void onSuccess();

        void fail();


    }

    interface Presenter extends IPresenter<View> {

        void getData();

    }
}
