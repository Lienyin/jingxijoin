package com.jxxc.jingxijoin.ui.userinfo;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.ConfigApplication;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.GlideImgManager;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * 个人信息
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserInfoActivity extends MVPBaseActivity<UserInfoContract.View, UserInfoPresenter> implements UserInfoContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_user_info_name)
    TextView tv_user_info_name;
    @BindView(R.id.tv_user_info_phone)
    TextView tv_user_info_phone;
    @BindView(R.id.tv_user_info_site_name)
    TextView tv_user_info_site_name;
    @BindView(R.id.ll_user_info_qr)
    LinearLayout ll_user_info_qr;
    @BindView(R.id.ll_user_info_address)
    LinearLayout ll_user_info_address;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;
    private static final int REQUEST_CODE_CHOOSE = 1100;

    @Override
    protected int layoutId() {
        return R.layout.user_info_activity;
    }

    @Override
    public void initData() {
        tv_title.setText("个人信息");
        mPresenter.getUserInfo();
        mPresenter.initImageSelecter();
    }

    @OnClick({R.id.tv_back,R.id.ll_user_info_qr,R.id.ll_user_info_address,R.id.iv_user_head})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.ll_user_info_qr://二维码名片
                //ZzRouter.gotoActivity(this, QRVisitingCardActivity.class);
                break;
            case R.id.ll_user_info_address://地址
                //ZzRouter.gotoActivity(this, AddressActivity.class);
                //ZzRouter.gotoActivity(this, CreationAddressActivity.class);
                break;
            case R.id.iv_user_head://头像
                mPresenter.gotoImageSelect(this, REQUEST_CODE_CHOOSE);
                break;
            default:
        }
    }

    @Override
    public void getUserInfoCallBack(UserInfoEntity data) {
        tv_user_info_name.setText(data.loginName);
        tv_user_info_phone.setText(data.phonenumber);
        tv_user_info_site_name.setText(data.companyName);
        tv_address.setText(data.address);
        GlideImgManager.loadCircleImage(this, data.avatar, iv_user_head);
    }

    @Override
    public void uploadImageCallBack() {
        mPresenter.getUserInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final List<String> pathList = data.getStringArrayListExtra("result");
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
                                StyledDialog.buildLoading("正在上传头像").setActivity(UserInfoActivity.this).show();
                            }
                            @Override public void onSuccess(File f) {
                                mPresenter.uploadImage(f.getAbsolutePath());
                            }
                            @Override public void onError(Throwable e) {
                                mPresenter.uploadImage(pathList.get(0));
                            }
                        }).launch();    //启动压缩

            }
        }
    }
}
