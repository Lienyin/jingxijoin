package com.jxxc.jingxijoin.ui.brokerage;

import com.jxxc.jingxijoin.entity.backparameter.CommissionListEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BrokerageContract {
    interface View extends BaseView {
        void commissionListCallBack(List<CommissionListEntity> data);
        void commissionListMoreCallBack(List<CommissionListEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void commissionList(int offset, int limit, int tabType, String statsTime);
        void commissionListMore(int offset, int limit, int tabType, String statsTime);
    }
}
