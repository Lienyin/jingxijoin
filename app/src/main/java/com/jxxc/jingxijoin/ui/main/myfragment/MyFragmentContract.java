package com.jxxc.jingxijoin.ui.main.myfragment;


import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFragmentContract {
    interface View extends BaseView {
        void userInfoCallBack(UserInfoEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void userInfo();
    }
}
