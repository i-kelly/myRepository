package com.example.present;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.present
 *  @文件名:   MultiTypePresenter
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 11:06
 *  @描述：    多类型页面presenter
 */

import com.example.admin.constants.Constants;
import com.example.base.BasePresenter;
import com.example.model.bean.MultiTypeBean;
import com.example.model.helper.DataManager;
import com.example.model.helper.HUDCallBack;
import com.example.present.contract.MultiTypeContract;


public class MultiTypePresenter
        extends BasePresenter<MultiTypeContract.View>
        implements MultiTypeContract.Presenter {
    private int tag = 0;

    @Override
    public void getData() {
        DataManager.getInstance()
                   .getData2(new HUDCallBack<MultiTypeBean>(this, Constants.SIIGEE) {
                       @Override
                       public void onSuccess(MultiTypeBean bean) {
                           if (isViewAttached()) {
                               getView().showBanner(bean.obj.bannerList);
                               getView().showAdList(bean.obj.adHomePageList);
                               getView().showBrandList(bean.obj.brandHomePageList);
                               getView().showRdList(bean.obj.rdProductList);
                               getView().onSuccess();
                           }
                       }
                   }, tag);
    }


   /* @Override
    protected void cancelRequest() {
        DataManager.getInstance()
                   .cancelRequest(tag);
    }*/
}
