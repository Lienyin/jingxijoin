package com.jxxc.jingxijoin.ui.commercialtenant;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.dialog.AreaDialog;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommercialTenantActivity extends MVPBaseActivity<CommercialTenantContract.View, CommercialTenantPresenter> implements CommercialTenantContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_commercial_name)
    EditText et_commercial_name;
    @BindView(R.id.et_faren_bank_number)
    EditText et_faren_bank_number;
    @BindView(R.id.et_faren_bank_open)
    EditText et_faren_bank_open;
    @BindView(R.id.et_faren_info)
    EditText et_faren_info;
    @BindView(R.id.et_faren_phone)
    EditText et_faren_phone;
    @BindView(R.id.tv_ssq)
    TextView tv_ssq;
    @BindView(R.id.tv_commercial_time)
    TextView tv_commercial_time;
    @BindView(R.id.rb_commercial_dd)
    RadioButton rb_commercial_dd;
    @BindView(R.id.rb_commercial_sm)
    RadioButton rb_commercial_sm;
    @BindView(R.id.ll_select_address)
    LinearLayout ll_select_address;
    private List<SelectAllAreaEntity> shengList = new ArrayList<>();
    private AreaDialog areaDialog;
    private String provinceId,provinceName;
    private String cityId,cityName;
    private String districtId,districtName;


    @Override
    protected int layoutId() {
        return R.layout.commercial_tenant_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("商家信息");
        StyledDialog.buildLoading("加载中").setActivity(this).show();
        mPresenter.getUserInfo();

        areaDialog = new AreaDialog(this);
        areaDialog.setOnFenxiangClickListener(new AreaDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(int pId, String pName, int cId,String cName, int dId, String dName) {
                provinceId = pId+"";
                cityId = cId+"";
                districtId = dId+"";
                provinceName = pName;
                cityName = cName;
                districtName = dName;
                tv_ssq.setText(provinceName+"/"+cityName+"/"+districtName);
            }
        });
    }

    @OnClick({R.id.tv_back,R.id.rb_commercial_dd,R.id.rb_commercial_sm,R.id.ll_select_address})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.rb_commercial_dd://到店服务
                //finish();
                break;
            case R.id.rb_commercial_sm://上门洗车
                //finish();
                break;
            case R.id.ll_select_address://选择省市区
                StyledDialog.buildLoading("加载中").setActivity(this).show();
                mPresenter.selectAllArea();
                break;
            default:
        }
    }

    //信息接口返回数据
    @Override
    public void getUserInfoCallBack(UserInfoEntity data) {
        et_commercial_name.setText(data.companyName);
        tv_ssq.setText(data.province+"/"+data.city+"/"+data.district);
        tv_commercial_time.setText(data.officeTime+"~"+data.closingTime);
        et_faren_info.setText(data.contacts);
        et_faren_phone.setText(data.contactsNumber);
        et_faren_bank_number.setText(data.bankCardNumber);
        et_faren_bank_open.setText(data.bankName);
    }

    @Override
    public void selectAllAreaCallBack(List<AreaEntity> data) {
        areaDialog.showShareDialog(true,data);
    }
}
