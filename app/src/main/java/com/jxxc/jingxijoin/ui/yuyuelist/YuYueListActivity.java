package com.jxxc.jingxijoin.ui.yuyuelist;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentManagementEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.orderlist.OrderListActivity;
import com.jxxc.jingxijoin.ui.yuyuebiao.YuYueBiaoActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueListActivity extends MVPBaseActivity<YuYueListContract.View, YuYueListPresenter> implements YuYueListContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_yuyue_notDispatch)
    TextView tv_yuyue_notDispatch;
    @BindView(R.id.tv_yuyue_dispatch)
    TextView tv_yuyue_dispatch;
    @BindView(R.id.tv_yuyue_complete)
    TextView tv_yuyue_complete;
    @BindView(R.id.tv_yuyue_cancel)
    TextView tv_yuyue_cancel;
    @BindView(R.id.tv_yuyue_overtime)
    TextView tv_yuyue_overtime;
    @BindView(R.id.ll_look_all_order)
    LinearLayout ll_look_all_order;
    @BindView(R.id.iv_liji_fenpei)
    ImageView iv_liji_fenpei;
    @Override
    protected int layoutId() {
        return R.layout.yuyue_list_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("预约单管理");
        mPresenter.appointmentManagement();
    }

    @OnClick({R.id.tv_back,R.id.ll_look_all_order,R.id.iv_liji_fenpei})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.ll_look_all_order://查看全部订单
                ZzRouter.gotoActivity(this, OrderListActivity.class);
                break;
            case R.id.iv_liji_fenpei://立即分配
                ZzRouter.gotoActivity(this, YuYueBiaoActivity.class);
                break;
            default:
        }
    }

    //返回数据
    @Override
    public void appointmentManagementCallBack(AppointmentManagementEntity data) {
        tv_yuyue_notDispatch.setText(data.notDispatch);
        tv_yuyue_dispatch.setText(data.dispatch);
        tv_yuyue_complete.setText(data.complete);
        tv_yuyue_cancel.setText(data.cancel);
        tv_yuyue_overtime.setText(data.overtime);
    }
}
