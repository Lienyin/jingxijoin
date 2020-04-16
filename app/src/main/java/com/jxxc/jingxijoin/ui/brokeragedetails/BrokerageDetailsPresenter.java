package com.jxxc.jingxijoin.ui.brokeragedetails;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.BrokerageDetailsEntity;
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

public class BrokerageDetailsPresenter extends BasePresenterImpl<BrokerageDetailsContract.View> implements BrokerageDetailsContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void OrderDetail(int pageNum, int pageSize, String id,String idType) {
        if ("0".equals(idType)){//0余额，1佣金
            OkGo.<HttpResult<List<BrokerageDetailsEntity>>>post(Api.COMMISSION_DETAIL)
                    .params("pageNum",pageNum)
                    .params("pageSize",pageSize)
                    .execute(new JsonCallback<HttpResult<List<BrokerageDetailsEntity>>>() {
                        @Override
                        public void onSuccess(Response<HttpResult<List<BrokerageDetailsEntity>>> response) {
                            List<BrokerageDetailsEntity> detailsEntities = response.body().data;
                            if (response.body().code == 0){
                                mView.OrderDetailCallBack(detailsEntities);
                            }else{
                                toast(mContext,response.body().message);
                            }
                        }
                    });
        }else{
            OkGo.<HttpResult<List<BrokerageDetailsEntity>>>post(Api.COMMISSION_DETAIL)
                    .params("pageNum",pageNum)
                    .params("pageSize",pageSize)
                    .execute(new JsonCallback<HttpResult<List<BrokerageDetailsEntity>>>() {
                        @Override
                        public void onSuccess(Response<HttpResult<List<BrokerageDetailsEntity>>> response) {
                            List<BrokerageDetailsEntity> detailsEntities = response.body().data;
                            if (response.body().code == 0){
                                mView.OrderDetailCallBack(detailsEntities);
                            }else{
                                toast(mContext,response.body().message);
                            }
                        }
                    });
        }
    }

    @Override
    public void OrderDetailMore(int pageNum, int pageSize, String id,String idType) {
        if ("0".equals(idType)) {//0余额，1佣金
            OkGo.<HttpResult<List<BrokerageDetailsEntity>>>post(Api.COMMISSION_DETAIL)
                    .params("pageNum", pageNum)
                    .params("pageSize", pageSize)
                    .execute(new JsonCallback<HttpResult<List<BrokerageDetailsEntity>>>() {
                        @Override
                        public void onSuccess(Response<HttpResult<List<BrokerageDetailsEntity>>> response) {
                            List<BrokerageDetailsEntity> detailsEntities = response.body().data;
                            if (response.body().code == 0) {
                                mView.OrderDetailMoreCallBack(detailsEntities);
                            } else {
                                toast(mContext, response.body().message);
                            }
                        }
                    });
        }else{
            OkGo.<HttpResult<List<BrokerageDetailsEntity>>>post(Api.COMMISSION_DETAIL)
                    .params("pageNum", pageNum)
                    .params("pageSize", pageSize)
                    .execute(new JsonCallback<HttpResult<List<BrokerageDetailsEntity>>>() {
                        @Override
                        public void onSuccess(Response<HttpResult<List<BrokerageDetailsEntity>>> response) {
                            List<BrokerageDetailsEntity> detailsEntities = response.body().data;
                            if (response.body().code == 0) {
                                mView.OrderDetailMoreCallBack(detailsEntities);
                            } else {
                                toast(mContext, response.body().message);
                            }
                        }
                    });
        }
    }
}
