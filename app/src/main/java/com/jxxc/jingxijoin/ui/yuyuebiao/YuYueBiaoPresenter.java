package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;
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
        OkGo.<HttpResult<List<AppointmentListEntity>>>post(Api.APPOINTMENT_LIST)
                .params("queryDate",queryDate)
                .execute(new JsonCallback<HttpResult<List<AppointmentListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<AppointmentListEntity>>> response) {
                        List<AppointmentListEntity> d = response.body().data;
                        if (response.body().code==0){
                            mView.appointmentListCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
