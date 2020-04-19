package com.jxxc.jingxijoin.ui.addjishi;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddJishiContract {
    interface View extends BaseView {
        void technicianInfoCallBack(TechnicianInfoEntity data);
        void technicianEditCallBack();
        void technicianAddCallBack();
        void updateInfoCallBack();
        void uploadImageCallBack(UpdateInfoEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void technicianInfo(String technicianId);
        void technicianEdit(String technicianId,String realName,
                           String idCart,String phonenumber,
                           String idCartImg,String commissionRatio,String password);
        void technicianAdd(String realName,
                           String idCart,String phonenumber,
                           String idCartImg,String commissionRatio,
                           String password);

        void initImageSelecter();
        void gotoImageSelect(AddJishiActivity activity, int requestCodeChoose);
        void uploadImage(String s);
    }
}
