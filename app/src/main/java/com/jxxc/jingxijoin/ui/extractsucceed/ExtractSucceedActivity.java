package com.jxxc.jingxijoin.ui.extractsucceed;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExtractSucceedActivity extends MVPBaseActivity<ExtractSucceedContract.View, ExtractSucceedPresenter> implements ExtractSucceedContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_hint_text)
    TextView tv_hint_text;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @Override
    protected int layoutId() {
        return R.layout.extract_succeed_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("结果详情");
        tv_hint_text.setText("");
    }

    @OnClick({R.id.tv_back,R.id.btn_ok})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.btn_ok://结果详情
                finish();
                break;
            default:
        }
    }
}
