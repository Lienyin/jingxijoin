package com.jxxc.jingxijoin.ui.addjishi;

import android.content.Context;

import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.MD5Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddJishiPresenter extends BasePresenterImpl<AddJishiContract.View> implements AddJishiContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 技师详情
     * @param technicianId
     */
    @Override
    public void technicianInfo(String technicianId) {
        OkGo.<HttpResult<TechnicianInfoEntity>>post(Api.TECHNICIAN_INFO)
                .params("technicianId",technicianId)
                .execute(new JsonCallback<HttpResult<TechnicianInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<TechnicianInfoEntity>> response) {
                        TechnicianInfoEntity d = response.body().data;
                        if (response.body().code==0){
                            mView.technicianInfoCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 修改技师
     * @param technicianId
     */
    @Override
    public void technicianEdit(String technicianId,String realName,
                               String idCart,String phonenumber,
                               String idCartImg,String password) {
        OkGo.<HttpResult>post(Api.TECHNICIAN_EDIT)
                .params("technicianId",technicianId)
                .params("realName",realName)
                .params("idCart",idCart)
                .params("phonenumber",phonenumber)
                .params("idCartImg",idCartImg)
                .params("password", MD5Utils.shaPassword(password).trim().toUpperCase())
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            mView.technicianEditCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 添加技师
     * @param technicianId
     * @param realName
     * @param idCart
     * @param phonenumber
     * @param idCartImg
     * @param password
     */
    @Override
    public void technicianAdd(String technicianId, String realName,
                              String idCart, String phonenumber,
                              String idCartImg, String password) {
        OkGo.<HttpResult>post(Api.TECHNICIAN_ADD)
                .params("technicianId",technicianId)
                .params("realName",realName)
                .params("idCart",idCart)
                .params("phonenumber",phonenumber)
                .params("idCartImg",idCartImg)
                .params("password", MD5Utils.shaPassword(password).trim().toUpperCase())
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            mView.technicianAddCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
