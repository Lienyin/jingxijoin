package com.jxxc.jingxijoin.ui.welcome;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.jxxc.jingxijoin.ui.start.StartActivity;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 欢迎界面
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WelcomeActivity extends MVPBaseActivity<WelcomeContract.View, WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.btn_enter)
    Button button;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int layoutId() {
        isCheckToken = false;
        return R.layout.activity_welcome;
    }
    @Override
    public void initData() {
        //控制是否显示系统状态栏，默认显示(可不写)
        mPresenter.querySetting();
        boolean isfirstlogin =  SPUtils.get(this,"ISFIRSTLOGIN", true);
        if (isfirstlogin){
            SPUtils.put(this,"ISFIRSTLOGIN", false);
            mPresenter.isShowViewPager(viewPager,isfirstlogin);
        }else{
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
//        AnimUtils.clickAnimator(button);
        gotoMainNow();
    }

    @Override
    public void gotoMainNow() {
        SPUtils.put(this,"ISFIRSTLOGIN", false);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (!AppUtils.isEmpty(viewPager)) {
            viewPager.clearOnPageChangeListeners();
            viewPager.clearDisappearingChildren();
            viewPager.removeAllViewsInLayout();
        }
        super.onDestroy();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
