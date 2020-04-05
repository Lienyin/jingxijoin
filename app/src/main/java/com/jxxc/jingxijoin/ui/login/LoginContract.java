package com.jxxc.jingxijoin.ui.login;

import android.widget.TextView;

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
        void getCode(String phonenumber, TextView tvAuthCode);
        void login(String loginName,String password);
        void loginCode(String phonenumber,String code);
    }
}
