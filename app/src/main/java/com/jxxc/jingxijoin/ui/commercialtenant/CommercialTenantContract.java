package com.jxxc.jingxijoin.ui.commercialtenant;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
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
        void updateInfoCallBack();
        void uploadImageCallBack(String yyzz,String dianPuLogin);
    }

    interface  Presenter extends BasePresenter<View> {
        void getUserInfo();
        void selectAllArea();

        void initImageSelecter();
        void gotoImageSelect(CommercialTenantActivity malfunctionRepairActivity, int requestCodeChoose);

        void uploadImage(String s,int type);
        void updateInfo(String companyName,String address,String imgUrl,String companyLogo,String businessLicense
                ,String contacts,String contactsNumber,String officeTime,String closingTime,String province
                ,String city,String district);
    }
}
