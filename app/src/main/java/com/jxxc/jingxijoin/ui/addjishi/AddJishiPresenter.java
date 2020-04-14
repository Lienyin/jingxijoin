package com.jxxc.jingxijoin.ui.addjishi;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.MD5Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddJishiPresenter extends BasePresenterImpl<AddJishiContract.View> implements AddJishiContract.Presenter{

    ISListConfig config;

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
     */
    @Override
    public void technicianEdit(String realName,
                               String idCart,String phonenumber,
                               String idCartImg,String password) {
        OkGo.<HttpResult>post(Api.TECHNICIAN_EDIT)
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

    @Override
    public void gotoImageSelect(AddJishiActivity activity, int requestCodeChoose) {
        ISNav.getInstance().toListActivity(activity, config, requestCodeChoose);
    }

    /**
     * @param s 头像路径(上传文件接口)
     */
    @Override
    public void uploadImage(String s) {
        OkGo.<HttpResult<UpdateInfoEntity>>post(Api.UPLOAD_FILE)
                .params("file",new File(s))
                .isMultipart(true)
                .execute(new JsonCallback<HttpResult<UpdateInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<UpdateInfoEntity>> response) {
                        UpdateInfoEntity d = response.body().data;
                        if (response.body().code == 0) {
                            //上传文件成功，添加技师接口
                            mView.uploadImageCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 添加技师
     * @param realName
     * @param idCart
     * @param phonenumber
     * @param idCartImg
     * @param password
     */
    @Override
    public void technicianAdd(String realName,
                              String idCart, String phonenumber,
                              String idCartImg, String password) {
        OkGo.<HttpResult>post(Api.TECHNICIAN_ADD)
                .params("realName",realName)
                .params("idCart",idCart)
                .params("phonenumber",phonenumber)
                .params("idCartImg",idCartImg)
                .params("password", MD5Utils.shaPassword(password).trim().toUpperCase())
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        StyledDialog.dismissLoading();
                        if (response.body().code==0){
                            mView.technicianAddCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

}
