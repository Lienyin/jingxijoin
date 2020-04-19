package com.jxxc.jingxijoin.ui.main;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.LatestVersionEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.ConfigApplication;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.AppUtils;
import java.io.File;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void latestVersion(int type) {
        OkGo.<HttpResult<LatestVersionEntity>>post(Api.LATEST_VERSION)
                .params("type",type)
                .execute(new JsonCallback<HttpResult<LatestVersionEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<LatestVersionEntity>> response) {
                        LatestVersionEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.latestVersionCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
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
                        UserInfoEntity userInfoEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.getUserInfoCallBack(userInfoEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
