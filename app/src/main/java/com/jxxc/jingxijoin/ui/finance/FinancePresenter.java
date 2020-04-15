package com.jxxc.jingxijoin.ui.finance;

import android.content.Context;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.DeviceReportEntity;
import com.jxxc.jingxijoin.entity.backparameter.FinanceReportEntity;
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

public class FinancePresenter extends BasePresenterImpl<FinanceContract.View> implements FinanceContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 财务报表
     * @param tabType
     */
    @Override
    public void financeReport(int tabType) {
        OkGo.<HttpResult<FinanceReportEntity>>post(Api.STATS)
                .params("tabType",tabType)
                .execute(new JsonCallback<HttpResult<FinanceReportEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<FinanceReportEntity>> response) {
                        FinanceReportEntity d = response.body().data;
                        if (response.body().code == 0){
                            mView.financeReportCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 设备报表
     * @param tabType
     */
    @Override
    public void deviceReport(int tabType) {
        OkGo.<HttpResult<DeviceReportEntity>>post(Api.STATS)
                .params("tabType",tabType)
                .execute(new JsonCallback<HttpResult<DeviceReportEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<DeviceReportEntity>> response) {
                        DeviceReportEntity d = response.body().data;
                        if (response.body().code == 0){
                            mView.deviceReportCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
