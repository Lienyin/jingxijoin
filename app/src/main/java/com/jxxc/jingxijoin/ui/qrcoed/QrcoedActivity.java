package com.jxxc.jingxijoin.ui.qrcoed;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.QrcodeEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class QrcoedActivity extends MVPBaseActivity<QrcoedContract.View, QrcoedPresenter> implements QrcoedContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_qr_code)
    ImageView iv_qr_code;
    @Override
    protected int layoutId() {
        return R.layout.qrcoed_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("商家二维码");
        StyledDialog.buildLoading("加载中").setActivity(this).show();
        mPresenter.getQrcoed();
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            default:
        }
    }

    //二维码返回数据
    @Override
    public void getQrcoedCallBack(QrcodeEntity data) {
        String URL = data.qrcode;
        Bitmap mBitmap = CodeUtils.createImage(URL, 150, 150,
                BitmapFactory.decodeResource(getResources(), R.mipmap.wx_logo));
        iv_qr_code.setImageBitmap(mBitmap);
    }
}
