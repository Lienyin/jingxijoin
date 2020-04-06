package com.jxxc.jingxijoin.ui.jishimanagement;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.QueryListEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class JishiManagementContract {
    interface View extends BaseView {
        void queryListCallBack(List<QueryListEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void queryList();
    }
}
