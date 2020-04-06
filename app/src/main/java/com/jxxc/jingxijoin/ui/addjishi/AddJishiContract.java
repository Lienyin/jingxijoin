package com.jxxc.jingxijoin.ui.addjishi;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
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
    }

    interface  Presenter extends BasePresenter<View> {
        void technicianInfo(String technicianId);
        void technicianEdit(String technicianId,String realName,
                           String idCart,String phonenumber,
                           String idCartImg,String password);
        void technicianAdd(String technicianId,String realName,
                           String idCart,String phonenumber,
                           String idCartImg,String password);
    }
}
