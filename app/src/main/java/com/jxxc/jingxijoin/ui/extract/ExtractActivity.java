package com.jxxc.jingxijoin.ui.extract;


import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.DrawMoneyApplyEntity;
import com.jxxc.jingxijoin.entity.backparameter.GetAccountInfoEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.bankinfo.BankInfoActivity;
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
    @BindView(R.id.tv_yue_money)
    TextView tv_yue_money;
    @BindView(R.id.tv_jilu)
    LinearLayout tv_jilu;
    @BindView(R.id.ll_zhang_dan)
    LinearLayout ll_zhang_dan;
    @BindView(R.id.ti_xian_money)
    EditText ti_xian_money;
    @BindView(R.id.btn_ti_xian)
    Button btn_ti_xian;
    private String bankCardNumber="";
    private String bankName="";
    private double money;
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

        ti_xian_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                if (temp.contains(".")) {
                    int posDot = temp.indexOf(".");
                    if (posDot <= 0) return;
                    if (temp.length() - posDot - 1 > 2) {
                        s.delete(posDot + 3, posDot + 4);
                    }
                    String number = ti_xian_money.getText().toString();
                    if (!TextUtils.isEmpty(number)) {
                        if (number.startsWith(".")) {
                            ti_xian_money.setText("0.");
                            CharSequence cs = ti_xian_money.getText();
                            if (cs instanceof Spannable) {
                                Selection.setSelection((Spannable) cs, cs.length());
                            }
                        } else {
                            money = Double.parseDouble(number);
                        }
                    }
                } else {
                    money = Double.parseDouble(ti_xian_money.getText().toString());
                }
            }
        });
    }

    @OnClick({R.id.tv_back,R.id.btn_ti_xian,R.id.tv_jilu,R.id.ll_zhang_dan,R.id.tv_ti_xian_type})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.btn_ti_xian://提现
                if (AppUtils.isEmpty(ti_xian_money.getText().toString().trim())){
                    toast(this,"请输入提现金额");
                }else if (money <= 0) {
                    toast(this, "提现金额要大于等于0元");
                }else {
                    StyledDialog.buildLoading("正在提现").setActivity(this).show();
                    mPresenter.drawMoneyApply(ti_xian_money.getText().toString().trim());
                }
                break;
            case R.id.tv_jilu://提现记录
                ZzRouter.gotoActivity(this, WithdrawDepositRecordActivity.class);
                break;
            case R.id.ll_zhang_dan://佣金账单
                ZzRouter.gotoActivity(this, BrokerageActivity.class);
                break;
            case R.id.tv_ti_xian_type://换绑
                ZzRouter.gotoActivity(this, BankInfoActivity.class);
                break;
            default:
        }
    }

    //提现接口返回数据
    @Override
    public void drawMoneyApplyCallBack(DrawMoneyApplyEntity data) {
        Intent intent = new Intent(this, ExtractSucceedActivity.class);
        intent.putExtra("bankCardNumber",bankCardNumber);
        intent.putExtra("tixianTime",data.applyTime);
        intent.putExtra("bankName",bankName);
        startActivity(intent);
        finish();
    }

    //提现账户返回数据
    @Override
    public void getAccountInfoCallBack(GetAccountInfoEntity data) {
        if (!AppUtils.isEmpty(data)){
            bankName = data.bankName;
            bankCardNumber = data.bankCardNumber.substring(data.bankCardNumber.length()-4,data.bankCardNumber.length());
            tv_ti_xian_type.setText("提现到"+data.bankName);
            tv_yue_money.setText("可用余额"+data.availableBalance+"元");
        }
    }
}
