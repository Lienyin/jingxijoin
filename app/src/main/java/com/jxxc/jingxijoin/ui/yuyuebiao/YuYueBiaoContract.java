package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueBiaoContract {
    interface View extends BaseView {
        void appointmentListCallBack(List<AppointmentListEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void appointmentList(String queryDate);
    }
}
