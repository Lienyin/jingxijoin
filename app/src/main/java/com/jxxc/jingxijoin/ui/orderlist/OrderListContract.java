package com.jxxc.jingxijoin.ui.orderlist;

import com.jxxc.jingxijoin.entity.backparameter.OrderListEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderListContract {
    interface View extends BaseView {
        void myOrderCallBack(List<OrderListEntity> data);
        void myOrderMoreCallBack(List<OrderListEntity> data);

    }

    interface  Presenter extends BasePresenter<View> {
        void myOrder(String status, int pageNum, int pageSize);
        void myOrderMore(String status, int pageNum, int pageSize);

    }
}
