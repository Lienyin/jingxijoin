package com.jxxc.jingxijoin.ui.bindingaccount;

import android.widget.TextView;

import com.jxxc.jingxijoin.entity.backparameter.AccountInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindingAccountContract {
    interface View extends BaseView {
        void bindingAccountCallBack();
        void getAccountInfoCallBack(AccountInfoEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void bindingAccount(String alipayAccount, String openId, String code);
        void getCode(String phonenumber, TextView textView);
        void getAccountInfo();
    }
}
