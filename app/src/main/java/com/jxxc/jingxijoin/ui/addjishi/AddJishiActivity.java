package com.jxxc.jingxijoin.ui.addjishi;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.ConfigApplication;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.TechnicianInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.GlideImgManager;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


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
    TextView et_jishi_number;
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
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;
    @BindView(R.id.ll_jishi_bianhao)
    LinearLayout ll_jishi_bianhao;
    private String type="";
    private String technicianId="";
    private String AbsolutePath="";
    private static final int REQUEST_CODE_CHOOSE = 1100;
    private List<String> pathList;

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
        mPresenter.initImageSelecter();
        if ("1".equals(type)){
            //查看
            tv_title.setText("技师详情");
            tv_jishi_baocun.setVisibility(View.GONE);
            ll_jishi_bianhao.setVisibility(View.VISIBLE);
            mPresenter.technicianInfo(technicianId);
            et_jishi_name.setFocusable(false);
            et_jishi_idcar.setFocusable(false);
            et_jishi_phone.setFocusable(false);
            et_jishi_phone_plus.setFocusable(false);
            et_jishi_password.setFocusable(false);
            iv_user_head.setFocusable(false);
        }else if ("2".equals(type)){
            //修改
            tv_title.setText("修改技师");
            tv_jishi_baocun.setVisibility(View.VISIBLE);
            ll_jishi_bianhao.setVisibility(View.VISIBLE);
            mPresenter.technicianInfo(technicianId);
            //mPresenter.technicianEdit();
        }else{
            //添加技师
            tv_title.setText("添加技师");
            tv_jishi_baocun.setVisibility(View.VISIBLE);
            ll_jishi_bianhao.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_back,R.id.tv_jishi_baocun,R.id.iv_user_head})
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
                        StyledDialog.buildLoading("正在提交数据").setActivity(AddJishiActivity.this).show();
                        if (pathList.size()>0){
                            //需要修改头像
                            mPresenter.uploadImage(AbsolutePath);
                        }else{
                            mPresenter.technicianEdit(
                                    et_jishi_name.getText().toString().trim(),
                                    et_jishi_idcar.getText().toString().trim(),
                                    et_jishi_phone.getText().toString().trim(),
                                    AbsolutePath,
                                    et_jishi_password.getText().toString().trim());
                        }
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
                    }else if (pathList.size()<=0){
                        toast(this,"请添加头像");
                    }else{
                        StyledDialog.buildLoading("正在提交数据").setActivity(AddJishiActivity.this).show();
                        mPresenter.uploadImage(AbsolutePath);
                    }
                }
                break;
            case R.id.iv_user_head:
                mPresenter.gotoImageSelect(this, REQUEST_CODE_CHOOSE);
                break;
            default:
        }
    }

    //技师详情
    @Override
    public void technicianInfoCallBack(TechnicianInfoEntity data) {
        et_jishi_number.setText(data.technicianCode);
        et_jishi_name.setText(data.realName);
        et_jishi_idcar.setText(data.idCart);
        et_jishi_phone.setText(data.phonenumber);
        et_jishi_phone_plus.setText(data.phonenumber);
        AbsolutePath = data.idCartImg;
        GlideImgManager.loadRectangleImage(this, data.idCartImg, iv_user_head);
    }

    //修改技师
    @Override
    public void technicianEditCallBack() {

    }

    //添加技师返回数据
    @Override
    public void technicianAddCallBack() {
        toast(this,"添加成功");
        finish();
    }

    @Override
    public void updateInfoCallBack() {

    }

    //上传图片返回数据
    @Override
    public void uploadImageCallBack(UpdateInfoEntity data) {
        if ("修改技师".equals(tv_title.getText().toString())){
            mPresenter.technicianEdit(
                    et_jishi_name.getText().toString().trim(),
                    et_jishi_idcar.getText().toString().trim(),
                    et_jishi_phone.getText().toString().trim(),
                    data.fileName,
                    et_jishi_password.getText().toString().trim());
        }else if ("添加技师".equals(tv_title.getText().toString())){
            mPresenter.technicianAdd(
                    et_jishi_name.getText().toString().trim(),
                    et_jishi_idcar.getText().toString().trim(),
                    et_jishi_phone.getText().toString().trim(),
                    data.fileName,
                    et_jishi_password.getText().toString().trim());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            pathList = data.getStringArrayListExtra("result");
            if (!AppUtils.isEmpty(pathList)) {
                GlideImgManager.loadRectangleImage(this, pathList.get(0), iv_user_head);
                Luban.with(this)
                        .load(pathList.get(0))
                        .ignoreBy(50)
                        .setTargetDir(ConfigApplication.CACHA_URL)
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                //toast(IDCardActivity.this,"正在上传身份证", TastyToast.WARNING);
                            }
                            @Override public void onSuccess(File f) {
                                AbsolutePath = f.getAbsolutePath();
                            }
                            @Override public void onError(Throwable e) {
                                AbsolutePath = pathList.get(0);
                            }
                        }).launch();    //启动压缩

            }
        }
    }
}
