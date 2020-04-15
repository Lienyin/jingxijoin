package com.jxxc.jingxijoin.ui.bankinfo;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.BankEntity;
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

public class BankInfoPresenter extends BasePresenterImpl<BankInfoContract.View> implements BankInfoContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 用户绑卡信息
     */
    @Override
    public void GetUserBank() {
        OkGo.<HttpResult<BankEntity>>post(Api.GET_ACCOUNT_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<BankEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<BankEntity>> response) {
                        BankEntity bankEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.GetUserBankCallback(bankEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 绑卡
     * @param linkMobile
     * @param bankName
     * @param bankBranchName
     * @param bankCardNumber
     * @param bankCardMan
     */
    @Override
    public void UpdateCompanyBankCard(String linkMobile, final String bankName, String bankBranchName, String bankCardNumber, String bankCardMan) {
        OkGo.<HttpResult>post(Api.BINDING_ACCOUNT)
                .params("phonenumber",linkMobile)
                .params("bankName",bankName)
                .params("bankBranchName",bankBranchName)
                .params("bankCardNumber",bankCardNumber)
                .params("bankCardMan",bankCardMan)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code == 0){
                            SPUtils.put(mView.getContext(),SPUtils.K_BANK_NAME,bankName);
                            mView.UpdateCompanyBankCardCallback();
                            toast(mContext,response.body().message);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
