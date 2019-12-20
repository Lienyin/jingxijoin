package com.jxxc.jingxijoin.ui.usercenter;


import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;


/**
 * 个人中心--我的
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UsercenterActivity extends MVPBaseActivity<UsercenterContract.View, UsercenterPresenter> implements UsercenterContract.View {

    @Override
    protected int layoutId() {
        return R.layout.activity_user_center;
    }

    @Override
    public void initData() {

    }
}
