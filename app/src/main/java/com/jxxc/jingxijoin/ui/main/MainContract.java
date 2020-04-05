package com.jxxc.jingxijoin.ui.main;

import com.jxxc.jingxijoin.entity.backparameter.LatestVersionEntity;
import com.jxxc.jingxijoin.mvp.BaseView;
import com.jxxc.jingxijoin.mvp.BasePresenter;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        void latestVersionCallBack(LatestVersionEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void latestVersion(int type);
    }
}
