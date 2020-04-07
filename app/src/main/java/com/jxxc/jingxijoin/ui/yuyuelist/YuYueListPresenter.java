package com.jxxc.jingxijoin.ui.yuyuelist;

import android.content.Context;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentManagementEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueListPresenter extends BasePresenterImpl<YuYueListContract.View> implements YuYueListContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

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
