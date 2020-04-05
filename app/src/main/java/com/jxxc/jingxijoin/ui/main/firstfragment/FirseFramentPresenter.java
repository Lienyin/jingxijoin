package com.jxxc.jingxijoin.ui.main.firstfragment;


import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.BasEarningsEntity;
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

public class FirseFramentPresenter extends BasePresenterImpl<FirseFramentContract.View> implements FirseFramentContract.Presenter{
    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 基础收益(首页)
     */
    @Override
    public void basEarnings() {
        OkGo.<HttpResult<BasEarningsEntity>>post(Api.BAS_EARNINGS)
                .tag(this)
                .execute(new JsonCallback<HttpResult<BasEarningsEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<BasEarningsEntity>> response) {
                        StyledDialog.dismissLoading();
                        BasEarningsEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.basEarningsCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
