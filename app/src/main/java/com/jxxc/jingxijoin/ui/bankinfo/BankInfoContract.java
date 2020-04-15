package com.jxxc.jingxijoin.ui.bankinfo;

import com.jxxc.jingxijoin.entity.backparameter.BankEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BankInfoContract {
    interface View extends BaseView {
        void GetUserBankCallback(BankEntity data);
        void UpdateCompanyBankCardCallback();
    }

    interface  Presenter extends BasePresenter<View> {
        void GetUserBank();
        void UpdateCompanyBankCard(String linkMobile, String bankName, String bankBranchName, String bankCardNumber, String bankCardMan);
    }
}
