package com.jxxc.jingxijoin.ui.brokerage;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.CommissionListEntity;
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

public class BrokeragePresenter extends BasePresenterImpl<BrokerageContract.View> implements BrokerageContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void commissionList(int offset, int limit,int tabType,String statsTime) {
        OkGo.<HttpResult<List<CommissionListEntity>>>post(Api.COMMISSION_DETAIL)
                .params("offset",offset)
                .params("limit",limit)
                .params("tabType",tabType)
                .params("statsTime",statsTime)
                .execute(new JsonCallback<HttpResult<List<CommissionListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<CommissionListEntity>>> response) {
                        StyledDialog.dismissLoading();
                        List<CommissionListEntity> d = response.body().data;
                        if (response.body().code==0){
                            mView.commissionListCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    @Override
    public void commissionListMore(int offset, int limit,int tabType,String statsTime) {
        OkGo.<HttpResult<List<CommissionListEntity>>>post(Api.COMMISSION_DETAIL)
                .params("offset",offset)
                .params("limit",limit)
                .params("tabType",tabType)
                .params("statsTime",statsTime)
                .execute(new JsonCallback<HttpResult<List<CommissionListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<CommissionListEntity>>> response) {
                        List<CommissionListEntity> d = response.body().data;
                        if (response.body().code==0){
                            mView.commissionListMoreCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
