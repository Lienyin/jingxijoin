package com.jxxc.jingxijoin.ui.yuyuelist;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.AppointmentManagementEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueListContract {
    interface View extends BaseView {
        void appointmentManagementCallBack(AppointmentManagementEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void appointmentManagement();
    }
}
