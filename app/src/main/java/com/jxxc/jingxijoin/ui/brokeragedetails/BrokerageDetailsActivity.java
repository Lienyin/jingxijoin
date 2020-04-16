package com.jxxc.jingxijoin.ui.brokeragedetails;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.BrokerageDetailsEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 分佣详情
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BrokerageDetailsActivity extends MVPBaseActivity<BrokerageDetailsContract.View, BrokerageDetailsPresenter> implements BrokerageDetailsContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private int offset = 2;
    private String id="";
    private String idType="0";
    private BrokerageDetailsAdapter adapter;
    @Override
    protected int layoutId() {
        return R.layout.brokerage_details_activity;
    }

    @Override
    public void initData() {
        tv_title.setText("对账单明细");
//        id = getIntent().getStringExtra("id");
//        idType = getIntent().getStringExtra("idType");//0余额，1佣金
        initAdapter();
        onRefresh();
    }

    private void initAdapter() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.public_all));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BrokerageDetailsAdapter(R.layout.brokerage_details_adapter, new ArrayList<BrokerageDetailsEntity>());
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
        mPresenter.OrderDetail(1,20,id,idType);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeLayout.setRefreshing(false);
        mPresenter.OrderDetailMore(offset,20,id,idType);
    }

    @Override
    public void OrderDetailCallBack(List<BrokerageDetailsEntity> data) {
        swipeLayout.setRefreshing(false);
        adapter.setNewData(data);
        if (data.size() < 20){
            adapter.loadMoreEnd();
        }else{
            adapter.disableLoadMoreIfNotFullPage();
        }
    }

    @Override
    public void OrderDetailMoreCallBack(List<BrokerageDetailsEntity> data) {
        swipeLayout.setRefreshing(false);
        offset++;
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (data.size() < 20) {
            adapter.loadMoreEnd();
        }
    }
}
