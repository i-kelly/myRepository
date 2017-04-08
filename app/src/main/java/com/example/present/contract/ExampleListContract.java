package com.example.present.contract;


import com.example.base.BaseView;
import com.example.present.IPresenter;

import java.util.List;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 15:39
 * @desc 契约类（列表数据）
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
