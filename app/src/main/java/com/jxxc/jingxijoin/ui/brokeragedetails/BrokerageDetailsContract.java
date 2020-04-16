package com.jxxc.jingxijoin.ui.brokeragedetails;


import com.jxxc.jingxijoin.entity.backparameter.BrokerageDetailsEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BrokerageDetailsContract {
    interface View extends BaseView {
        void OrderDetailCallBack(List<BrokerageDetailsEntity> data);
        void OrderDetailMoreCallBack(List<BrokerageDetailsEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void OrderDetail(int pageNum, int pageSize, String id, String idType);
        void OrderDetailMore(int pageNum, int pageSize, String id, String idType);
    }
}
