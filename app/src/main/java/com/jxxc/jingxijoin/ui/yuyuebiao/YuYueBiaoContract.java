package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
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
        void appointmentInfoCallBack(AppointmentInfoEntity data);
        void dispatchCallBack();
    }

    interface  Presenter extends BasePresenter<View> {
        void appointmentList(String queryDate);
        void appointmentInfo(String appointmentStartTime,String appointmentEndTime);
        void dispatch(String orderId,String technicianId);
    }
}
