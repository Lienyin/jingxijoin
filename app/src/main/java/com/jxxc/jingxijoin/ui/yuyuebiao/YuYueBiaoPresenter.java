package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentManagementEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueBiaoPresenter extends BasePresenterImpl<YuYueBiaoContract.View> implements YuYueBiaoContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 预约列表
     * @param queryDate
     */
    @Override
    public void appointmentList(String queryDate) {
        OkGo.<HttpResult<AppointmentListEntity>>post(Api.APPOINTMENT_LIST)
                .params("queryDate",queryDate)
                .execute(new JsonCallback<HttpResult<AppointmentListEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<AppointmentListEntity>> response) {
                        AppointmentListEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.appointmentListCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 预约详情
     * @param appointmentStartTime
     * @param appointmentEndTime
     */
    @Override
    public void appointmentInfo(String appointmentStartTime, String appointmentEndTime) {
        OkGo.<HttpResult<AppointmentInfoEntity>>post(Api.APPOINTMENT_INFO)
                .params("appointmentStartTime",appointmentStartTime)
                .params("appointmentEndTime",appointmentEndTime)
                .execute(new JsonCallback<HttpResult<AppointmentInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<AppointmentInfoEntity>> response) {
                        AppointmentInfoEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.appointmentInfoCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 排班
     * @param orderId
     * @param technicianId
     */
    @Override
    public void dispatch(String orderId, String technicianId) {
        OkGo.<HttpResult>post(Api.DISPATCH)
                .params("orderId",orderId)
                .params("technicianId",technicianId)
                .params("carportId","0")
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            mView.dispatchCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 预约单管理
     */
    @Override
    public void appointmentManagement() {
        OkGo.<HttpResult<AppointmentManagementEntity>>post(Api.APPOINTMENT_MANAGEMENT)
                .tag(this)
                .execute(new JsonCallback<HttpResult<AppointmentManagementEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<AppointmentManagementEntity>> response) {
                        AppointmentManagementEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.appointmentManagementCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
