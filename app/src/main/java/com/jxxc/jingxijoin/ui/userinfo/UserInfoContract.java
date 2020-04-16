package com.jxxc.jingxijoin.ui.userinfo;


import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserInfoContract {
    interface View extends BaseView {
        void getUserInfoCallBack(UserInfoEntity data);
        void uploadImageCallBack();
    }

    interface  Presenter extends BasePresenter<View> {
        void getUserInfo();
        void initImageSelecter();
        void gotoImageSelect(UserInfoActivity activity, int requestCodeChoose);
        /**
         * 上传头像
         * @param s 头像路径
         */
        void uploadImage(String s);
    }
}
