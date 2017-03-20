package com.example.present.contract;


import com.example.base.BaseView;
import com.example.present.IPresenter;

import java.util.List;

/**
 * Created by codeest on 16/11/27.
 */

public interface ExampleListContract {

    interface View<T> extends BaseView {

        void showContent(List<T> bean);

        void showMoreContent(List<T> listBean, int start, int end);
    }

    interface Presenter extends IPresenter<View> {

        void getGoldData(String type);

        void getMoreGoldData();
    }
}
