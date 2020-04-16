package com.jxxc.jingxijoin.ui.commercialtenant;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommercialTenantContract {
    interface View extends BaseView {
        void getUserInfoCallBack(UserInfoEntity data);
        void selectAllAreaCallBack(List<AreaEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void getUserInfo();
        void selectAllArea();
    }
}
