package com.jxxc.jingxijoin.ui.extract;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.GetAccountInfoEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.brokerage.BrokerageActivity;
import com.jxxc.jingxijoin.ui.extractsucceed.ExtractSucceedActivity;
import com.jxxc.jingxijoin.ui.withdrawdepositrecord.WithdrawDepositRecordActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExtractActivity extends MVPBaseActivity<ExtractContract.View, ExtractPresenter> implements ExtractContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_ti_xian_type)
    TextView tv_ti_xian_type;
    @BindView(R.id.tv_jilu)
    LinearLayout tv_jilu;
    @BindView(R.id.ll_zhang_dan)
    LinearLayout ll_zhang_dan;
    @BindView(R.id.ti_xian_money)
    EditText ti_xian_money;
    @BindView(R.id.btn_ti_xian)
    Button btn_ti_xian;
    private String tixianType="";//1支付宝 2微信
    private String tixianTime="";
    @Override
    protected int layoutId() {
        return R.layout.extract_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("提现");
        StyledDialog.buildLoading("数据加载中").setActivity(this).show();
        mPresenter.getAccountInfo();
    }

    @OnClick({R.id.tv_back,R.id.btn_ti_xian,R.id.tv_jilu,R.id.ll_zhang_dan})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.btn_ti_xian://提现
                if (AppUtils.isEmpty(ti_xian_money.getText().toString().trim())){
                    toast(this,"请输入提现金额");
                }else {
                    mPresenter.drawMoneyApply(ti_xian_money.getText().toString().trim());
                }
                break;
            case R.id.tv_jilu://提现记录
                ZzRouter.gotoActivity(this, WithdrawDepositRecordActivity.class);
                break;
            case R.id.ll_zhang_dan://佣金账单
                ZzRouter.gotoActivity(this, BrokerageActivity.class);
                break;
            default:
        }
    }

    //提现接口返回数据
    @Override
    public void drawMoneyApplyCallBack() {
        Intent intent = new Intent(this, ExtractSucceedActivity.class);
        intent.putExtra("tixianType",tixianType);
        intent.putExtra("tixianTime",tixianTime);
        startActivity(intent);
    }

    //提现账户返回数据
    @Override
    public void getAccountInfoCallBack(GetAccountInfoEntity data) {
        if (!AppUtils.isEmpty(data.alipayAccount)){
            tixianType = "1";
            tv_ti_xian_type.setText("提现到支付宝");
        }else if (!AppUtils.isEmpty(data.openId)){
            tixianType = "2";
            tv_ti_xian_type.setText("提现到微信");
        }else{
            tv_ti_xian_type.setText("绑定提现账户");
            tixianType = "0";
        }
    }
}
