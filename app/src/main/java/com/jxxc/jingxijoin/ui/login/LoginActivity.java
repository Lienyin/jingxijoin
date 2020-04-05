package com.jxxc.jingxijoin.ui.login;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.ui.main.MainActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_ms_code)
    EditText et_ms_code;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_get_code)
    TextView tv_get_code;
    @BindView(R.id.tv_login_fangshi)
    TextView tv_login_fangshi;
    @BindView(R.id.rb_xieyi)
    RadioButton rb_xieyi;
    @BindView(R.id.ll_password_view)
    LinearLayout ll_password_view;
    @BindView(R.id.ll_code_view)
    LinearLayout ll_code_view;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);
        if (!AppUtils.isEmpty(SPUtils.get(SPUtils.K_ACCOUNT,""))){
            etAccount.setText(SPUtils.get(SPUtils.K_ACCOUNT,""));
        }
    }

    @OnClick({R.id.btn_login,R.id.tv_get_code,R.id.tv_login_fangshi})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.btn_login:
                if (ll_password_view.getVisibility()==View.VISIBLE){
                    if (AppUtils.isEmpty(etAccount.getText().toString())){
                        toast(this,"请输入您的手机号码");
                    }else if ((AppUtils.isEmpty(et_password.getText().toString()))){
                        toast(this,"请输入您的账户密码");
                    }else if (rb_xieyi.isChecked()==false){
                        toast(this,"请阅读并同意协议");
                    }else{
                        StyledDialog.buildLoading("正在登录").setActivity(this).show();
                        mPresenter.login(etAccount.getText().toString(),et_password.getText().toString());
                    }
                }else{
                    if (AppUtils.isEmpty(etAccount.getText().toString())){
                        toast(this,"请输入您的手机号码");
                    }else if ((AppUtils.isEmpty(et_ms_code.getText().toString()))){
                        toast(this,"请输入您的验证码");
                    }else if (rb_xieyi.isChecked()==false){
                        toast(this,"请阅读并同意协议");
                    }else{
                        StyledDialog.buildLoading("正在登录").setActivity(this).show();
                        mPresenter.loginCode(etAccount.getText().toString(),et_ms_code.getText().toString());
                    }
                }
                break;
            case R.id.tv_get_code://发送给验证码
                if (AppUtils.isEmpty(etAccount.getText().toString())){
                    toast(this,"手机号不能为空");
                }else{
                    StyledDialog.buildLoading("正在发送").setActivity(this).show();
                    mPresenter.getCode(etAccount.getText().toString(),tv_get_code);
                }
                break;
            case R.id.tv_login_fangshi:
                if ("短信验证码登录".equals(tv_login_fangshi.getText().toString())){
                    tv_login_fangshi.setText("账号登录");
                    ll_password_view.setVisibility(View.GONE);
                    ll_code_view.setVisibility(View.VISIBLE);
                    et_password.setText("");
                }else{
                    tv_login_fangshi.setText("短信验证码登录");
                    ll_password_view.setVisibility(View.VISIBLE);
                    ll_code_view.setVisibility(View.GONE);
                    et_ms_code.setText("");
                }
                break;
            default:
        }
    }

    @Override
    public void loginCallBack() {
        ZzRouter.gotoActivity(this, MainActivity.class);
    }
}
