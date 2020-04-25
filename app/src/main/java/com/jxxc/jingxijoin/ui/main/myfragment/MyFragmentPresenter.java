package com.jxxc.jingxijoin.ui.main.myfragment;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFragmentPresenter extends BasePresenterImpl<MyFragmentContract.View> implements MyFragmentContract.Presenter{
    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void userInfo() {
        OkGo.<HttpResult<UserInfoEntity>>post(Api.USER_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<UserInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<UserInfoEntity>> response) {
                        StyledDialog.dismissLoading();
                        UserInfoEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.userInfoCallBack(d);
                            SPUtils.put(SPUtils.K_SESSION_MOBILE,d.phonenumber);
                            SPUtils.put(SPUtils.K_AVATAR,d.avatar);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
