package com.example.rx;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.rx
 *  @文件名:   MultiPresenter
 *  @创建者:   Admin
 *  @创建时间:  2017/7/3 0:25
 *  @描述：    TODO
 */

import com.example.model.bean.BaseBean;
import com.example.model.bean.MultiTypeBean;
import com.example.present.contract.MultiTypeContract;
import com.example.retrofit.HttpUtils;
import com.example.retrofit.RetrofitHelper;

public class MultiPresenter
        extends RxPresenter<MultiTypeContract.View> {

    private RetrofitHelper mRetrofitHelper;

    public MultiPresenter() {
        mRetrofitHelper = new RetrofitHelper(HttpUtils.getInstance().provideMyService());
    }

    public void getData() {
        addSubscribe(mRetrofitHelper.getData()
                                    .compose(RxUtil.<BaseBean<MultiTypeBean>>rxSchedulerHelper())
                                    .compose(RxUtil.<MultiTypeBean>handleResult())
//                                    .map(new Function<MultiTypeBean, MultiTypeBean>() {
//                                        @Override
//                                        public MultiTypeBean apply(BaseBean<MultiTypeBean> baseBean) {
//                                            MultiTypeBean multiTypeBean = baseBean.getData();
//                                            return multiTypeBean;
//                                        }
//                                    })
                                    .subscribeWith(new CommonSubscriber<MultiTypeBean>(getView()) {
                                        @Override
                                        public void onNext(MultiTypeBean bean) {
//                                            if (isViewAttached()) {
                                                getView().showBanner(bean.obj.bannerList);
                                                getView().showAdList(bean.obj.adHomePageList);
                                                getView().showBrandList(bean.obj.brandHomePageList);
                                                getView().showRdList(bean.obj.rdProductList);
                                                getView().onSuccess();
//                                            }
                                        }
                                    }));
    }
}
