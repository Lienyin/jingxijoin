package com.jxxc.jingxijoin.ui.login;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.back_Login;
import com.jxxc.jingxijoin.entity.requestparameter.req_Login;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.MD5Utils;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    private CountDownTimer timer;
    /**
     * 登录接口
     * @param loginName
     * @param password
     */
    @Override
    public void login(String loginName,String password) {
        OkGo.<HttpResult<back_Login>>post(Api.LOGIN)
                .params("loginName",loginName)
                .params("password",MD5Utils.shaPassword(password).trim().toUpperCase())
                .execute(new JsonCallback<HttpResult<back_Login>>(){
                    @Override
                    public void onSuccess(Response<HttpResult<back_Login>> response) {
                        hideLoading();
                        back_Login d = response.body().data;
                        if (response.body().code == 0){
                            SPUtils.put(SPUtils.K_TOKEN,d.token);
                            SPUtils.put(SPUtils.K_ACCOUNT,loginName);
                            SPUtils.put(SPUtils.K_PASS_WORD,MD5Utils.md5(password));
                            mView.loginCallBack();
                        }else {
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 验证码登录
     * @param phonenumber
     * @param code
     */
    @Override
    public void loginCode(final String phonenumber,String code) {
        OkGo.<HttpResult<back_Login>>post(Api.LOGIN_CODE)
                .params("phonenumber",phonenumber)
                .params("code", code)
                .execute(new JsonCallback<HttpResult<back_Login>>(){
                    @Override
                    public void onSuccess(Response<HttpResult<back_Login>> response) {
                        hideLoading();
                        back_Login d = response.body().data;
                        if (response.body().code == 0){
                            SPUtils.put(SPUtils.K_TOKEN,d.token);
                            SPUtils.put(SPUtils.K_ACCOUNT,phonenumber);
                            mView.loginCallBack();
                        }else {
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 获取短信验证码
     * @param phonenumber
     * @param tvAuthCode
     */
    @Override
    public void getCode(String phonenumber, final TextView tvAuthCode) {
        OkGo.<HttpResult>post(Api.SMS_CODE)
                .params("phonenumber", phonenumber)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        StyledDialog.dismissLoading();
                        HttpResult data = response.body();
                        if (data.code == 0) {
                            timer = initCountDownTimer(tvAuthCode);
                            timer.start();
                            toast(mContext,"验证码已发送");
                        } else {
                            toast(mContext,data.message);
                        }
                    }
                });
    }

    @NonNull
    private CountDownTimer initCountDownTimer(final TextView tvAuthCode) {
        return new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (AppUtils.isEmpty(mView)) {
                    return;
                }
                if (tvAuthCode != null) {
                    tvAuthCode.setEnabled(false);
                    tvAuthCode.setTextColor(ContextCompat.getColor(mView.getContext(), R.color.public_all));
                    tvAuthCode.setText("重新发送(" + (millisUntilFinished / 1000) + ")");
                }
            }

            @Override
            public void onFinish() {
                if (AppUtils.isEmpty(mView)) {
                    return;
                }
                if (tvAuthCode != null) {
                    tvAuthCode.setEnabled(true);
                    tvAuthCode.setTextColor(ContextCompat.getColor(mView.getContext(), R.color.black));
                    tvAuthCode.setText("获取验证码");
                }
            }
        };
    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
