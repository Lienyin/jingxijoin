package com.jxxc.jingxijoin.ui.orderlist;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.OrderListEntity;
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

public class OrderListPresenter extends BasePresenterImpl<OrderListContract.View> implements OrderListContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void myOrder(String status,int pageNum,int pageSize) {
        OkGo.<HttpResult<List<OrderListEntity>>>post(Api.MY_ORDER)
                .params("status",status)
                .params("pageNum",pageNum)
                .params("pageSize",pageSize)
                .execute(new JsonCallback<HttpResult<List<OrderListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<OrderListEntity>>> response) {
                        List<OrderListEntity> d = response.body().data;
                        if (response.body().code == 0){
                            mView.myOrderCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    @Override
    public void myOrderMore(String status,int pageNum,int pageSize) {
        OkGo.<HttpResult<List<OrderListEntity>>>post(Api.MY_ORDER)
                .params("status",status)
                .params("pageNum",pageNum)
                .params("pageSize",pageSize)
                .execute(new JsonCallback<HttpResult<List<OrderListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<OrderListEntity>>> response) {
                        List<OrderListEntity> d = response.body().data;
                        if (response.body().code == 0){
                            mView.myOrderMoreCallBack(d);
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
                        StyledDialog.dismissLoading();
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
     * 调度
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
}
