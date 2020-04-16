package com.jxxc.jingxijoin.ui.commercialtenant;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommercialTenantPresenter extends BasePresenterImpl<CommercialTenantContract.View> implements CommercialTenantContract.Presenter{

    ISListConfig config;

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

    @Override
    public void gotoImageSelect(CommercialTenantActivity malfunctionRepairActivity, int requestCodeChoose) {
        ISNav.getInstance().toListActivity(malfunctionRepairActivity, config, requestCodeChoose);
    }

    /**
     * 更新信息
     * @param companyName
     * @param address
     * @param imgUrl
     * @param companyLogo
     * @param businessLicense
     * @param contacts
     * @param contactsNumber
     * @param officeTime
     * @param closingTime
     * @param province
     * @param city
     * @param district
     */
    @Override
    public void updateInfo(String companyName, String address, String imgUrl,
                           String companyLogo, String businessLicense, String contacts,
                           String contactsNumber, String officeTime, String closingTime,
                           String province, String city, String district) {
        OkGo.<HttpResult>post(Api.UPDATE_INFO)
                .params("companyName",companyName)
                .params("address",address)
                .params("imgUrl",imgUrl)
                .params("companyLogo",companyLogo)
                .params("businessLicense",businessLicense)
                .params("contacts",contacts)
                .params("contactsNumber",contactsNumber)
                .params("officeTime",officeTime)
                .params("closingTime",closingTime)
                .params("province",province)
                .params("city",city)
                .params("district",district)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        if (response.body().code==0){
                            mView.updateInfoCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    @Override
    public void initImageSelecter() {
        config = new ISListConfig.Builder()
                .multiSelect(true)
                .rememberSelected(true)
                .btnBgColor(Color.TRANSPARENT)
                .btnTextColor(Color.WHITE)
                .statusBarColor(ContextCompat.getColor(mView.getContext().getApplicationContext(), R.color.public_all))
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
}
