package com.jxxc.jingxijoin.ui.orderdetails;

import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.GetOrderEntity;
import com.jxxc.jingxijoin.mvp.BaseView;
import com.jxxc.jingxijoin.mvp.BasePresenter;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderDetailsContract {
    interface View extends BaseView {
        void getOrderCallBack(GetOrderEntity data);
        void appointmentInfoCallBack(AppointmentInfoEntity data);
        void dispatchCallBack();
    }

    interface  Presenter extends BasePresenter<View> {
        void getOrder(String orderId);
        void appointmentInfo(String appointmentStartTime,String appointmentEndTime);
        void dispatch(String orderId,String technicianId);
    }
}
