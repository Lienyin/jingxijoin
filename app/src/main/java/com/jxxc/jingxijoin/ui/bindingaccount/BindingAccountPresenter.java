package com.jxxc.jingxijoin.ui.bindingaccount;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AccountInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindingAccountPresenter extends BasePresenterImpl<BindingAccountContract.View> implements BindingAccountContract.Presenter{

    private CountDownTimer timer;

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 绑定账户
     * @param alipayAccount
     * @param openId
     * @param code
     */
    @Override
    public void bindingAccount(String alipayAccount,String openId,String code) {
        OkGo.<HttpResult>post(Api.BINDING_TIXIAN)
                .params("alipayAccount",alipayAccount)
                .params("openId",openId)
                .params("code",code)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        StyledDialog.dismissLoading();
                        if (response.body().code==0){
                            mView.bindingAccountCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 获取验证码
     * @param phonenumber
     */
    @Override
    public void getCode(String phonenumber, final TextView textView) {
        OkGo.<HttpResult>post(Api.SMS_CODE)
                .params("phonenumber",phonenumber)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            timer = initCountDownTimer(textView);
                            timer.start();
                            toast(mContext,"验证码已发送");
                        }else{
                            toast(mContext,response.body().message);
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

    /**
     * 查询账户信息
     */
    @Override
    public void getAccountInfo() {
        OkGo.<HttpResult<AccountInfoEntity>>post(Api.GET_ACCOUNT_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<AccountInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<AccountInfoEntity>> response) {
                        AccountInfoEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.getAccountInfoCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
