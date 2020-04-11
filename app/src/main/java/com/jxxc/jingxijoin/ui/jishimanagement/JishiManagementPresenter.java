package com.jxxc.jingxijoin.ui.jishimanagement;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.entity.backparameter.QueryListEntity;
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

public class JishiManagementPresenter extends BasePresenterImpl<JishiManagementContract.View> implements JishiManagementContract.Presenter{

    @Override
    protected void onEventComing(EventCenter center) {

    }

    /**
     * 技师管理
     */
    @Override
    public void queryList() {
        OkGo.<HttpResult<List<QueryListEntity>>>post(Api.QUERY_LIST)
                .tag(this)
                .execute(new JsonCallback<HttpResult<List<QueryListEntity>>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<List<QueryListEntity>>> response) {
                        List<QueryListEntity> d = response.body().data;
                        if (response.body().code==0){
                            mView.queryListCallBack(d);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 删除技师
     * @param technicianId
     */
    @Override
    public void remove(String technicianId) {
        OkGo.<HttpResult>post(Api.TECHNICIAN_REMOVE)
                .params("technicianId",technicianId)
                .execute(new JsonCallback<HttpResult>() {
                    @Override
                    public void onSuccess(Response<HttpResult> response) {
                        StyledDialog.dismissLoading();
                        if (response.body().code==0){
                            mView.removeCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }
}
