package com.jxxc.jingxijoin.ui.jishimanagement;


import android.view.View;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.addjishi.AddJishiActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class JishiManagementActivity extends MVPBaseActivity<JishiManagementContract.View, JishiManagementPresenter> implements JishiManagementContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_affirm)
    TextView tv_affirm;

    private JishiAdapter jishiAdapter;
    @Override
    protected int layoutId() {
        return R.layout.jishi_management_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("技师管理");
        tv_affirm.setVisibility(View.VISIBLE);
        tv_affirm.setText("+");
    }

    @OnClick({R.id.tv_back,R.id.tv_affirm})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.tv_affirm://添加技师
                ZzRouter.gotoActivity(this, AddJishiActivity.class);
                break;
            default:
        }
    }
}
