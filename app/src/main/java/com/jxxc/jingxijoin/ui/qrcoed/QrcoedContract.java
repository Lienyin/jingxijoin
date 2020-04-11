package com.jxxc.jingxijoin.ui.qrcoed;

import android.content.Context;

import com.jxxc.jingxijoin.entity.backparameter.QrcodeEntity;
import com.jxxc.jingxijoin.mvp.BasePresenter;
import com.jxxc.jingxijoin.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class QrcoedContract {
    interface View extends BaseView {
        void getQrcoedCallBack(QrcodeEntity data);

    }
    interface  Presenter extends BasePresenter<View> {
        void getQrcoed();
    }
}
