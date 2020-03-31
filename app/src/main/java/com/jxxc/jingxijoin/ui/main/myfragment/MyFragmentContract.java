package com.jxxc.jingxijoin.ui.main.myfragment;


import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFragmentContract {
    interface View extends BaseView {
    }

    interface  Presenter extends BasePresenter<View> {
    }
}
