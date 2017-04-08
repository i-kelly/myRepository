package com.example.present;

import com.example.base.BasePresenter;
import com.example.model.ExampleModel;
import com.example.model.bean.BaseBean;
import com.example.model.bean.ExampleBean;
import com.example.model.helper.HUDCallBack;
import com.example.present.contract.ExampleContract;

import okhttp3.Response;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:26
 * @desc present用例
 */
public class ExamplePresent
        extends BasePresenter<ExampleContract.View, ExampleModel>
        implements ExampleContract.Presenter {


    @Override
    public void getDetailData(String id) {
        mModel.getNetData(id, new HUDCallBack<BaseBean<ExampleBean>>("") {
            @Override
            public void onSuccess(Response response, BaseBean<ExampleBean> bean, int flag) {
                if (mView != null) {
                    mView.showContent(bean.data);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                if (mView != null) {
                    mView.showError(response.body().toString());
                }
            }
        });
    }

    @Override
    public void getExtraData(int id) {

    }


    @Override
    protected ExampleModel getModel() {
        return new ExampleModel();
    }
}
