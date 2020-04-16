package com.jxxc.jingxijoin.ui.userinfo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserInfoPresenter extends BasePresenterImpl<UserInfoContract.View> implements UserInfoContract.Presenter{

    ISListConfig config;
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

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void gotoImageSelect(UserInfoActivity activity, int requestCodeChoose) {
        ISNav.getInstance().toListActivity(activity, config, requestCodeChoose);
    }

    /**
     * 选择图片
     */
    @Override
    public void initImageSelecter() {
        config = new ISListConfig.Builder()
                .multiSelect(false)
                .rememberSelected(true)
                .btnBgColor(Color.TRANSPARENT)
                .btnTextColor(Color.WHITE)
                .statusBarColor(ContextCompat.getColor(mView.getContext().getApplicationContext(), R.color.gray))
                .backResId(R.mipmap.back)
                .title("图片选择")
                .titleColor(Color.WHITE)
                .titleBgColor(ContextCompat.getColor(mView.getContext().getApplicationContext(),R.color.public_all))
                .cropSize(1, 1, 200, 200)
                .needCrop(false)
                .needCamera(true)
                .maxNum(1)
                .build();
    }

    /**
     * @param s 头像路径
     */
    @Override
    public void uploadImage(String s) {
        OkGo.<HttpResult<UpdateInfoEntity>>post(Api.UPLOAD_FILE)
                .params("userPic",new File(s))
                .isMultipart(true)
                .execute(new JsonCallback<HttpResult<UpdateInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<UpdateInfoEntity>> response) {
                        StyledDialog.dismissLoading();
                        UpdateInfoEntity d = response.body().data;
                        if (response.body().code == 0) {
                            mView.uploadImageCallBack(d.fileName);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 修改头像
     * @param avatar
     */
    @Override
    public void updateUserInfo(String avatar) {
        OkGo.<HttpResult>post(Api.UPDATE_USER_INFO)
                .params("avatar",avatar)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        StyledDialog.dismissLoading();
                        if (response.body().code == 0) {
                            mView.updateUserInfoCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
