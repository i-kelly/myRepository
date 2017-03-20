package com.example.present;

import com.example.base.BasePresenter;
import com.example.model.ExampleModel;
import com.example.present.contract.ExampleContract;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:26
 * @desc  present用例
 */
public class ExamplePresent extends BasePresenter<ExampleContract.View,ExampleModel> implements ExampleContract.Presenter {
    private static final String TAG = "ExamplePresent";


    public ExamplePresent() {

    }

    @Override
    public void getDetailData(int id) {
        mModel.getNetData();
    }

    @Override
    public void getExtraData(int id) {

    }


}
