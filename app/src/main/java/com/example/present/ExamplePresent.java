package com.example.present;

import com.example.base.BasePresenter;
import com.example.present.contract.ExampleContract;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:26
 * @desc present用例
 */
public class ExamplePresent
        extends BasePresenter<ExampleContract.View>
        implements ExampleContract.Presenter {


    @Override
    public void getDetailData() {
    }


    @Override
    public void getExtraData(int id) {
        if (isViewAttached()) {
            //            return getView().getText();
        }

    }

    @Override
    public void onFailure(String msg, int tag) {
        if (isViewAttached()) {
            getView().showError("");
        }
    }


}
