package com.jxxc.jingxijoin.ui.commercialtenant;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
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

public class CommercialTenantPresenter extends BasePresenterImpl<CommercialTenantContract.View> implements CommercialTenantContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 用户信息
     */
    @Override
    public void getUserInfo() {
        OkGo.<HttpResult<UserInfoEntity>>post(Api.USER_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<UserInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<UserInfoEntity>> response) {
                        StyledDialog.dismissLoading();
                        UserInfoEntity userInfoEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.getUserInfoCallBack(userInfoEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 省市区
     */
    @Override
    public void selectAllArea() {
        OkGo.<HttpResult<List<AreaEntity>>>post(Api.SELECT_ALL_AREA)
                .tag(this)
                .execute(new JsonCallback<HttpResult<List<AreaEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<AreaEntity>>> response) {
                        StyledDialog.dismissLoading();
                        List<AreaEntity> d = response.body().data;
                        if (response.body().code==0){
                            mView.selectAllAreaCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
