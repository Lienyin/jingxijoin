package com.jxxc.jingxijoin.ui.orderdetails;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.GetOrderEntity;
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

public class OrderDetailsPresenter extends BasePresenterImpl<OrderDetailsContract.View> implements OrderDetailsContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 订单详情
     * @param orderId
     */
    @Override
    public void getOrder(String orderId) {
        OkGo.<HttpResult<GetOrderEntity>>post(Api.GET_ORDER)
                .params("orderId",orderId)
                .execute(new JsonCallback<HttpResult<GetOrderEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<GetOrderEntity>> response) {
                        GetOrderEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.getOrderCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
