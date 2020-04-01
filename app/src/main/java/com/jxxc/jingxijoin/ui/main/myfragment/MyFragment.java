package com.jxxc.jingxijoin.ui.main.myfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseFragment;
import com.jxxc.jingxijoin.ui.bindingaccount.BindingAccountActivity;
import com.jxxc.jingxijoin.ui.commercialtenant.CommercialTenantActivity;
import com.jxxc.jingxijoin.ui.message.MessageActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

@SuppressLint("ValidFragment")
public class MyFragment extends MVPBaseFragment<MyFragmentContract.View, MyFragmentPresenter> implements View.OnClickListener, MyFragmentContract.View {
    private Context context;
    private TextView tv_user_name;
    private LinearLayout ll_tixian;
    private LinearLayout ll_commercial_tenant_info;
    private LinearLayout ll_msg;

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
        ll_tixian.setOnClickListener(this);
        ll_commercial_tenant_info.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        AnimUtils.clickAnimator(v);
        switch (v.getId()){
            case R.id.ll_tixian://提现
                ZzRouter.gotoActivity((Activity) context, BindingAccountActivity.class);
                break;
            case R.id.ll_commercial_tenant_info://商户信息
                ZzRouter.gotoActivity((Activity) context, CommercialTenantActivity.class);
                break;
            case R.id.ll_msg://消息
                ZzRouter.gotoActivity((Activity) context, MessageActivity.class);
                break;
        }
    }
}
