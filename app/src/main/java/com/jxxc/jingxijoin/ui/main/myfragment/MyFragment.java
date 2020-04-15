package com.jxxc.jingxijoin.ui.main.myfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseFragment;
import com.jxxc.jingxijoin.ui.bankinfo.BankInfoActivity;
import com.jxxc.jingxijoin.ui.bindingaccount.BindingAccountActivity;
import com.jxxc.jingxijoin.ui.commercialtenant.CommercialTenantActivity;
import com.jxxc.jingxijoin.ui.extract.ExtractActivity;
import com.jxxc.jingxijoin.ui.message.MessageActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.GlideImgManager;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

@SuppressLint("ValidFragment")
public class MyFragment extends MVPBaseFragment<MyFragmentContract.View, MyFragmentPresenter> implements View.OnClickListener, MyFragmentContract.View {
    private Context context;
    private TextView tv_user_name,tv_company_type,tv_is_bangding;
    private LinearLayout ll_tixian;
    private LinearLayout ll_commercial_tenant_info;
    private LinearLayout ll_msg;
    private ImageView iv_user_head;
    private int BindingAccount;

    public MyFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,container,false);
        ll_tixian = view.findViewById(R.id.ll_tixian);
        ll_commercial_tenant_info = view.findViewById(R.id.ll_commercial_tenant_info);
        ll_msg = view.findViewById(R.id.ll_msg);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        iv_user_head = view.findViewById(R.id.iv_user_head);
        tv_company_type = view.findViewById(R.id.tv_company_type);
        tv_is_bangding = view.findViewById(R.id.tv_is_bangding);
        ll_tixian.setOnClickListener(this);
        ll_commercial_tenant_info.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        mPresenter.userInfo();
        return view;
    }

    @Override
    public void onClick(View v) {
        AnimUtils.clickAnimator(v);
        switch (v.getId()){
            case R.id.ll_tixian://提现
                if (BindingAccount==0){
                    //绑银行卡
                    ZzRouter.gotoActivity((Activity) context, BankInfoActivity.class);
                }else{
                    //提现
                    ZzRouter.gotoActivity((Activity) context, ExtractActivity.class);
                }
                break;
            case R.id.ll_commercial_tenant_info://商户信息
                ZzRouter.gotoActivity((Activity) context, CommercialTenantActivity.class);
                break;
            case R.id.ll_msg://消息
                ZzRouter.gotoActivity((Activity) context, MessageActivity.class);
                break;
        }
    }

    //个人信息返回数据
    @Override
    public void userInfoCallBack(UserInfoEntity data) {
        GlideImgManager.loadCircleImage(context, data.companyLogo, iv_user_head);
        tv_user_name.setText(data.companyName);
        //类型 1.直营店2.加盟店3.合作店
        if (data.companyType==1){
            tv_company_type.setText("直营店");
        }else if (data.companyType==2){
            tv_company_type.setText("加盟店");
        }else{
            tv_company_type.setText("合作店");
        }
        BindingAccount = data.isBindingAccount;
        //是否绑定体现账户 0否 1是
        if (data.isBindingAccount==0){
            tv_is_bangding.setText("未绑定");
        }else{
            tv_is_bangding.setText("已绑定");
        }
    }
}
