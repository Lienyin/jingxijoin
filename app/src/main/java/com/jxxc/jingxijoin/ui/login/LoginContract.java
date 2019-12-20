package com.jxxc.jingxijoin.ui.login;

import com.jxxc.jingxijoin.mvp.BaseView;
import com.jxxc.jingxijoin.mvp.BasePresenter;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginCallBack();
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String userName,String passWord);
    }
}
