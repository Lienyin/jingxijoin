package com.jxxc.jingxijoin.ui.main;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.LatestVersionEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.main.firstfragment.FirstFragment;
import com.jxxc.jingxijoin.ui.main.myfragment.MyFragment;
import com.jxxc.jingxijoin.ui.main.secondfragment.SecondFragment;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import cn.jpush.android.api.JPushInterface;


/**
 * 主界面
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View, View.OnClickListener {

    private TextView topOrder;
    private TextView tabDeal;
    private TextView tabMore;
    private FrameLayout ly_content;

    private FirstFragment f1;
    private SecondFragment f2;
    private MyFragment f3;
    private FragmentManager fragmentManager;
    private long exitTime = 0;
    public static String registrationId;
    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);
        bindView();
        mPresenter.latestVersion(3);
        //极光推送id
//        String pToken = JPushInterface.getRegistrationID(this);//18071adc03923faee46
//        Log.i("TAG","[MyReceiver] getRegistrationID===="+pToken);
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topOrder = (TextView)this.findViewById(R.id.txt_order);
        tabDeal = (TextView)this.findViewById(R.id.txt_deal);
        tabMore = (TextView)this.findViewById(R.id.txt_more);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabDeal.setOnClickListener(this);
        tabMore.setOnClickListener(this);
        topOrder.setOnClickListener(this);
        //默认显示第一个Fragment
        tabDeal.performClick();//自动触发首页按钮
    }

    //重置所有文本的选中状态
    public void selected(){
        tabDeal.setSelected(false);
        tabMore.setSelected(false);
        topOrder.setSelected(false);
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_deal:
                StatusBarUtil.setStatusBarMode(this, true, R.color.public_all);
                selected();
                tabDeal.setSelected(true);
                if(f1==null){
                    f1 = new FirstFragment(this);
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case R.id.txt_more:
                StatusBarUtil.setStatusBarMode(this, true, R.color.white);
                selected();
                tabMore.setSelected(true);
                if(f2==null){
                    f2 = new SecondFragment(this);
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;
            case R.id.txt_order:
                StatusBarUtil.setStatusBarMode(this, true, R.color.white);
                selected();
                topOrder.setSelected(true);
                if(f3==null){
                    f3 = new MyFragment(this);
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;
        }

        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            toast(MainActivity.this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

    //刷新数据
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //查询app最新版本返回接口
    @Override
    public void latestVersionCallBack(LatestVersionEntity data) {
        SPUtils.put(SPUtils.K_FILE_URL,data.staticUrl);
    }
}
