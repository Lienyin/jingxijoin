package com.jxxc.jingxijoin.ui.withdrawdepositrecord;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.DrawMoneyRecordEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 提现记录
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WithdrawDepositRecordActivity extends MVPBaseActivity<WithdrawDepositRecordContract.View, WithdrawDepositRecordPresenter> implements WithdrawDepositRecordContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private int offset = 2;
    private WithdrawDepositRecordAdapter adapter;
    @Override
    protected int layoutId() {
        return R.layout.withdraw_deposit_record_activity;
    }

    @Override
    public void initData() {
        tv_title.setText("提现记录");
        onRefresh();
        initAdapter();
    }

    private void initAdapter() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.public_all));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WithdrawDepositRecordAdapter(R.layout.withdraw_deposit_record_adapter, new ArrayList<DrawMoneyRecordEntity>());
        rvList.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, rvList);
        adapter.setEmptyView(R.layout.layout_nothing);
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

    @Override
    public void onRefresh() {
        offset = 2;
        mPresenter.DrawMoneyRecord(1,10);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeLayout.setRefreshing(false);
        mPresenter.DrawMoneyRecordMore(offset,10);
    }

    /**
     * 返回数据
     * @param data
     */
    @Override
    public void DrawMoneyRecordCallBack(List<DrawMoneyRecordEntity> data) {
        swipeLayout.setRefreshing(false);
        adapter.setNewData(data);
        adapter.disableLoadMoreIfNotFullPage();
    }

    /**
     * 返回更多数据
     * @param data
     */
    @Override
    public void DrawMoneyRecordMoreCallBack(List<DrawMoneyRecordEntity> data) {
        swipeLayout.setRefreshing(false);
        offset++;
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (data.size() < 10) {
            adapter.loadMoreEnd();
        }
    }
}
