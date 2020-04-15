package com.jxxc.jingxijoin.ui.withdrawdepositrecord;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.DrawMoneyRecordEntity;
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

public class WithdrawDepositRecordPresenter extends BasePresenterImpl<WithdrawDepositRecordContract.View> implements WithdrawDepositRecordContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 提现记录
     * @param offset
     * @param limit
     */
    @Override
    public void DrawMoneyRecord(int offset, int limit) {
        OkGo.<HttpResult<List<DrawMoneyRecordEntity>>>post(Api.DRAW_MONEY_RECORD)
                .params("offset",offset)
                .params("limit",limit)
                .execute(new JsonCallback<HttpResult<List<DrawMoneyRecordEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<DrawMoneyRecordEntity>>> response) {
                        List<DrawMoneyRecordEntity> drawMoneyRecordEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.DrawMoneyRecordCallBack(drawMoneyRecordEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    @Override
    public void DrawMoneyRecordMore(int offset, int limit) {
        OkGo.<HttpResult<List<DrawMoneyRecordEntity>>>post(Api.DRAW_MONEY_RECORD)
                .params("offset",offset)
                .params("limit",limit)
                .execute(new JsonCallback<HttpResult<List<DrawMoneyRecordEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<DrawMoneyRecordEntity>>> response) {
                        List<DrawMoneyRecordEntity> drawMoneyRecordEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.DrawMoneyRecordMoreCallBack(drawMoneyRecordEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
