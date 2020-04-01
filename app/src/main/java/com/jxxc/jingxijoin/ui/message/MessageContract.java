package com.jxxc.jingxijoin.ui.message;

import com.jxxc.jingxijoin.entity.backparameter.MessageListEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessageContract {
    interface View extends BaseView {
        void MessageListCallBack(List<MessageListEntity> data);
        void MessageListMoreCallBack(List<MessageListEntity> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void MessageList(int pageNum, int pageSize);
        void MessageListMore(int pageNum, int pageSize);
    }
}
