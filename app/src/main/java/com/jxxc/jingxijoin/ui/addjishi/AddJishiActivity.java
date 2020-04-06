package com.jxxc.jingxijoin.ui.addjishi;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddJishiActivity extends MVPBaseActivity<AddJishiContract.View, AddJishiPresenter> implements AddJishiContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_jishi_baocun)
    TextView tv_jishi_baocun;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.et_jishi_number)
    EditText et_jishi_number;
    @BindView(R.id.et_jishi_name)
    EditText et_jishi_name;
    @BindView(R.id.et_jishi_idcar)
    EditText et_jishi_idcar;
    @BindView(R.id.et_jishi_phone)
    EditText et_jishi_phone;
    @BindView(R.id.et_jishi_phone_plus)
    EditText et_jishi_phone_plus;
    @BindView(R.id.et_jishi_password)
    EditText et_jishi_password;
    private String type="";
    private String technicianId="";
    @Override
    protected int layoutId() {
        return R.layout.add_jishi_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_company_name.setText(SPUtils.get(SPUtils.K_COMPANY_NAME,""));
        type = getIntent().getStringExtra("type");
        technicianId = getIntent().getStringExtra("technicianId");
        if ("1".equals(type)){
            //查看
            tv_title.setText("技师详情");
            tv_jishi_baocun.setVisibility(View.GONE);
            mPresenter.technicianInfo(technicianId);
        }else if ("2".equals(type)){
            //修改
            tv_title.setText("修改技师");
            tv_jishi_baocun.setVisibility(View.VISIBLE);
            mPresenter.technicianInfo(technicianId);
            //mPresenter.technicianEdit();
        }else{
            //添加技师
            tv_title.setText("添加技师");
            tv_jishi_baocun.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.tv_back,R.id.tv_jishi_baocun})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.tv_jishi_baocun://保存
                if ("修改技师".equals(tv_title.getText().toString())){
                    if (AppUtils.isEmpty(et_jishi_name.getText().toString().trim())){
                        toast(this,"请输入技师姓名");
                    }else if (AppUtils.isEmpty(et_jishi_idcar.getText().toString().trim())){
                        toast(this,"请输入身份证号");
                    }else if (AppUtils.isEmpty(et_jishi_phone.getText().toString().trim())){
                        toast(this,"请输入手机号码");
                    }else if (AppUtils.isEmpty(et_jishi_password.getText().toString().trim())){
                        toast(this,"请输入账户密码");
                    }else{
                        mPresenter.technicianEdit("",
                                et_jishi_name.getText().toString().trim(),
                                et_jishi_idcar.getText().toString().trim(),
                                et_jishi_phone.getText().toString().trim(),
                                et_jishi_phone_plus.getText().toString().trim(),
                                et_jishi_password.getText().toString().trim());
                    }
                }else if ("添加技师".equals(tv_title.getText().toString())){
                    if (AppUtils.isEmpty(et_jishi_name.getText().toString().trim())){
                        toast(this,"请输入技师姓名");
                    }else if (AppUtils.isEmpty(et_jishi_idcar.getText().toString().trim())){
                        toast(this,"请输入身份证号");
                    }else if (AppUtils.isEmpty(et_jishi_phone.getText().toString().trim())){
                        toast(this,"请输入手机号码");
                    }else if (AppUtils.isEmpty(et_jishi_password.getText().toString().trim())){
                        toast(this,"请输入账户密码");
                    }else{
                        mPresenter.technicianAdd("",
                                et_jishi_name.getText().toString().trim(),
                                et_jishi_idcar.getText().toString().trim(),
                                et_jishi_phone.getText().toString().trim(),
                                et_jishi_phone_plus.getText().toString().trim(),
                                et_jishi_password.getText().toString().trim());
                    }
                }
                break;
            default:
        }
    }

    //技师详情
    @Override
    public void technicianInfoCallBack(TechnicianInfoEntity data) {
        et_jishi_number.setText("");
        et_jishi_name.setText(data.realName);
        et_jishi_idcar.setText(data.idCart);
        et_jishi_phone.setText(data.phonenumber);
        et_jishi_phone_plus.setText(data.phonenumber);
    }

    //修改技师
    @Override
    public void technicianEditCallBack() {

    }

    //添加技师
    @Override
    public void technicianAddCallBack() {

    }
}
