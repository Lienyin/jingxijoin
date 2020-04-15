package com.jxxc.jingxijoin.ui.withdrawdepositrecord;


import com.jxxc.jingxijoin.entity.backparameter.DrawMoneyRecordEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WithdrawDepositRecordContract {
    interface View extends BaseView {
        void DrawMoneyRecordCallBack(List<DrawMoneyRecordEntity> data);
        void DrawMoneyRecordMoreCallBack(List<DrawMoneyRecordEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void DrawMoneyRecord(int offset, int limit);
        void DrawMoneyRecordMore(int offset, int limit);
    }
}
