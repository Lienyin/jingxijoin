package com.jxxc.jingxijoin.ui.qrcoed;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.QrcodeEntity;
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

public class QrcoedPresenter extends BasePresenterImpl<QrcoedContract.View> implements QrcoedContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 获取二维码
     */
    @Override
    public void getQrcoed() {
        OkGo.<HttpResult<QrcodeEntity>>post(Api.GET_QRCOED)
                .tag(this)
                .execute(new JsonCallback<HttpResult<QrcodeEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<QrcodeEntity>> response) {
                        StyledDialog.dismissLoading();
                        QrcodeEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.getQrcoedCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
