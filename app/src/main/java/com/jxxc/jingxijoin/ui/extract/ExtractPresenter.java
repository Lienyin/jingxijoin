package com.jxxc.jingxijoin.ui.extract;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.GetAccountInfoEntity;
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

public class ExtractPresenter extends BasePresenterImpl<ExtractContract.View> implements ExtractContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void drawMoneyApply(String money) {
        OkGo.<HttpResult>post(Api.DRAW_MONEY_APPLY)
                .params("money",money)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            mView.drawMoneyApplyCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 提现账户
     */
    @Override
    public void getAccountInfo() {
        OkGo.<HttpResult<GetAccountInfoEntity>>post(Api.GET_ACCOUNT_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<GetAccountInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<GetAccountInfoEntity>> response) {
                        StyledDialog.dismissLoading();
                        GetAccountInfoEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.getAccountInfoCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
