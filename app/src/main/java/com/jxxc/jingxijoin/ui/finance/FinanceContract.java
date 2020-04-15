package com.jxxc.jingxijoin.ui.finance;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.DeviceReportEntity;
import com.jxxc.jingxijoin.entity.backparameter.FinanceReportEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FinanceContract {
    interface View extends BaseView {
        void financeReportCallBack(FinanceReportEntity data);
        void deviceReportCallBack(DeviceReportEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void financeReport(int tabType);
        void deviceReport(int tabType);
    }
}
