package com.jxxc.jingxijoin.ui.bankinfo;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.dialog.PassWordDialog;
import com.jxxc.jingxijoin.entity.backparameter.BankEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.MD5Utils;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.nestia.biometriclib.BiometricPromptManager;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 银行卡信息
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BankInfoActivity extends MVPBaseActivity<BankInfoContract.View, BankInfoPresenter> implements BankInfoContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_update_company_bank_card)
    Button btn_update_company_bank_card;
    @BindView(R.id.et_user_name)
    EditText et_user_name;
    @BindView(R.id.et_user_bank_number)
    EditText et_user_bank_number;
    @BindView(R.id.et_user_kaihui_hang)
    EditText et_user_kaihui_hang;
    @BindView(R.id.et_user_bank_wangidan)
    EditText et_user_bank_wangidan;
    @BindView(R.id.et_user_phone)
    EditText et_user_phone;
    private BiometricPromptManager mManager;
    private boolean isHardwareDetected;
    private boolean hasEnrolledFingerprints;
    private boolean isKeyguardSecure;
    private PassWordDialog dialog;

    @Override
    protected int layoutId() {
        return R.layout.bank_info_activity;
    }

    @Override
    public void initData() {
        tv_title.setText("银行卡");
        mPresenter.GetUserBank();
        dialog = new PassWordDialog(this);
        mManager = BiometricPromptManager.from(this);
        StringBuilder stringBuilder = new StringBuilder();
        isHardwareDetected = mManager.isHardwareDetected();//是否检测到硬件
        hasEnrolledFingerprints = mManager.hasEnrolledFingerprints();//已登记的指纹
        isKeyguardSecure = mManager.isKeyguardSecure();//密钥保护是否安全

        dialog.setOnFenxiangClickListener(new PassWordDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String password) {
                String pd = SPUtils.get(SPUtils.K_PASS_WORD,"0");
                if (MD5Utils.md5(password).equals(pd)){
                    commit();
                }else{
                    toast(BankInfoActivity.this,"密码不一致");
                }
            }
        });
    }

    @OnClick({R.id.tv_back,R.id.btn_update_company_bank_card})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.btn_update_company_bank_card://提交
                if (AppUtils.isEmpty(et_user_name.getText().toString())){
                    toast(this,"输入姓名");
                }else if (AppUtils.isEmpty(et_user_bank_number.getText().toString())){
                    toast(this,"输入银行卡号");
                }else if (AppUtils.isEmpty(et_user_kaihui_hang.getText().toString())){
                    toast(this,"输入开户行");
                }else if (AppUtils.isEmpty(et_user_bank_wangidan.getText().toString())){
                    toast(this,"输入银行网点");
                }else if (AppUtils.isEmpty(et_user_phone.getText().toString())){
                    toast(this,"输入手机号码");
                }else{
                    //使用指纹认证
                    if (isHardwareDetected==true&&hasEnrolledFingerprints==true&&isKeyguardSecure==true){
                        if (mManager.isBiometricPromptEnable()) {
                            mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                                @Override
                                public void onUsePassword() {
                                    Toast.makeText(BankInfoActivity.this, "使用密码", Toast.LENGTH_SHORT).show();
                                    dialog.showShareDialog(true);
                                }

                                @Override
                                public void onSucceeded() {
                                    Toast.makeText(BankInfoActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                    commit();
                                }

                                @Override
                                public void onFailed() {
                                    Toast.makeText(BankInfoActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(int code, String reason) {
                                    Toast.makeText(BankInfoActivity.this, "错误", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancel() {
                                    Toast.makeText(BankInfoActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else{
                        //使用密码认证
                        dialog.showShareDialog(true);
                    }
                }
                break;
            default:
        }
    }

    //提交接口
    private void commit(){
        mPresenter.UpdateCompanyBankCard(
                et_user_phone.getText().toString(),
                et_user_kaihui_hang.getText().toString(),
                et_user_bank_wangidan.getText().toString(),
                et_user_bank_number.getText().toString(),
                et_user_name.getText().toString());
    }
    /**
     * 银行卡信息
     * @param data
     */
    @Override
    public void GetUserBankCallback(BankEntity data) {
        et_user_name.setText(data.bankCardMan);
        et_user_bank_number.setText(data.bankCardNumber);
        et_user_kaihui_hang.setText(data.bankName);
        et_user_bank_wangidan.setText(data.bankBranchName);
        et_user_phone.setText(data.linkMobile);
    }

    /**
     * 绑卡返回数据
     */
    @Override
    public void UpdateCompanyBankCardCallback() {
        finish();
    }
}
