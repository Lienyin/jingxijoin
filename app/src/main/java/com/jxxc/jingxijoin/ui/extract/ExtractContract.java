package com.jxxc.jingxijoin.ui.extract;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.GetAccountInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExtractContract {
    interface View extends BaseView {
        void drawMoneyApplyCallBack();
        void getAccountInfoCallBack(GetAccountInfoEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void drawMoneyApply(String money);
        void getAccountInfo();
    }
}
