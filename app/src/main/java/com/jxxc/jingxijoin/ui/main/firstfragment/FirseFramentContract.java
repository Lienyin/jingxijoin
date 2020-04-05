package com.jxxc.jingxijoin.ui.main.firstfragment;


import com.jxxc.jingxijoin.entity.backparameter.BasEarningsEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FirseFramentContract {
    interface View extends BaseView {
        void basEarningsCallBack(BasEarningsEntity data);
    }

    interface  Presenter extends BasePresenter<View> {
        void basEarnings();
    }
}
