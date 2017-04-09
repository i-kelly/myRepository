package com.example.present.contract;

import com.example.base.IView;
import com.example.model.bean.MultiTypeBean;
import com.example.present.IPresenter;

import java.util.List;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:23
 * @desc 契约类
 */
public interface MultiTypeContract {

    interface View
            extends IView
    {


        void showBanner(List<MultiTypeBean.ObjBean.BannerListBean> bean);

        void showAdList(List<MultiTypeBean.ObjBean.AdHomePageListBean> bean);

        void showRdList(List<MultiTypeBean.ObjBean.RdProductListBean> bean);

        void showBrandList(List<MultiTypeBean.ObjBean.BrandHomePageListBean> bean);

        void onSuccess();

        void fail();


    }

    interface Presenter
            extends IPresenter<View>
    {

        void getData();

    }
}
