package com.example.present;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.present
 *  @文件名:   MultiTypePresenter
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 11:06
 *  @描述：    多类型页面presenter
 */

import com.example.base.BasePresenter;
import com.example.model.GetCallBack;
import com.example.model.MultiTypeModel;
import com.example.model.bean.MultiTypeBean;
import com.example.present.contract.MultiTypeContract;

public class MultiTypePresenter
        extends BasePresenter<MultiTypeContract.View, MultiTypeModel>
        implements MultiTypeContract.Presenter, GetCallBack.GetTasksCallback<MultiTypeBean>
{
    @Override
    protected MultiTypeModel getModel() {
        return new MultiTypeModel();
    }

    @Override
    public void getData() {
        mModel.getData(this);
    }


    @Override
    public void onSuccess(MultiTypeBean bean) {
        mView.showBanner(bean.obj.bannerList);
        mView.showAdList(bean.obj.adHomePageList);
        mView.showBrandList(bean.obj.brandHomePageList);
        mView.showRdList(bean.obj.rdProductList);
        mView.onSuccess();
    }

    @Override
    public void onFailure(String msg) {
        mView.showError(msg);
    }

    @Override
    public void onTaskLoaded(MultiTypeBean bean) {

    }

    @Override
    public void onDataNotAvailable() {

    }
}
