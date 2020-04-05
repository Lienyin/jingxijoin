package com.jxxc.jingxijoin.ui.main.firstfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseFragment;
import com.jxxc.jingxijoin.ui.jishimanagement.JishiManagementActivity;
import com.jxxc.jingxijoin.ui.orderlist.OrderListActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

@SuppressLint("ValidFragment")
public class FirstFragment extends MVPBaseFragment<FirseFramentContract.View, FirseFramentPresenter> implements View.OnClickListener, FirseFramentContract.View, SwipeRefreshLayout.OnRefreshListener {
    private Context context;
    private LinearLayout ll_jishi,ll_order;

    public FirstFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        ll_jishi = view.findViewById(R.id.ll_jishi);
        ll_order = view.findViewById(R.id.ll_order);
        ll_jishi.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.ll_jishi://技师
                ZzRouter.gotoActivity((Activity) context, JishiManagementActivity.class);
                break;
            case R.id.ll_order://订单
                ZzRouter.gotoActivity((Activity) context, OrderListActivity.class);
                break;
        }
    }

    //刷新
    @Override
    public void onRefresh() {

    }
}
