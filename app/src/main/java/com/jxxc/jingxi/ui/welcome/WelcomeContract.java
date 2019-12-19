package com.jxxc.jingxi.ui.welcome;

import android.support.v4.view.ViewPager;

import com.jxxc.jingxi.mvp.BaseView;
import com.jxxc.jingxi.mvp.BasePresenter;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WelcomeContract {
    interface View extends BaseView {
        void gotoMainNow();
    }

    interface  Presenter extends BasePresenter<View> {
        void isShowViewPager(ViewPager viewPager, boolean isfirstlogin);

        void querySetting();
    }
}
