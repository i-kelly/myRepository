package com.example.present;

import com.example.base.BasePresenter;
import com.example.model.ExampleModel;
import com.example.model.GetCallBack;
import com.example.model.bean.ExampleBean;
import com.example.present.contract.ExampleContract;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:26
 * @desc present用例
 */
public class ExamplePresent
        extends BasePresenter<ExampleContract.View, ExampleModel>
        implements ExampleContract.Presenter, GetCallBack.GetTaskCallback<ExampleBean>
{


    @Override
    public void getDetailData() {
        mModel.getData(this);
    }


    @Override
    public void getExtraData(int id) {

    }


    @Override
    protected ExampleModel getModel() {
        return new ExampleModel();
    }


    @Override
    public String getParams() {
        return mView.getText();
    }


    @Override
    public void onTaskLoaded(ExampleBean bean) {
        if (mView != null) {
            mView.showContent(bean);
        }
    }


    @Override
    public void onDataNotAvailable() {
        if (mView != null) {
            mView.showError("");
        }
    }


    @Override
    public void onSuccess(ExampleBean bean) {

    }


    @Override
    public void onFailure(String msg) {

    }
}
