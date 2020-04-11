package com.jxxc.jingxijoin.ui.orderlist;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.dialog.SortDialog;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.OrderListEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.orderdetails.OrderDetailsActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderListActivity extends MVPBaseActivity<OrderListContract.View, OrderListPresenter> implements OrderListContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rb_work_order_all)
    RadioButton rb_work_order_all;
    @BindView(R.id.rb_work_order_dai_jie)
    RadioButton rb_work_order_dai_jie;
    @BindView(R.id.rb_work_order_jin_xing)
    RadioButton rb_work_order_jin_xing;
    @BindView(R.id.rb_work_order_cancel)
    RadioButton rb_work_order_cancel;
    private OrderListAdapter adapter;
    private int offset = 2;
    private String orderType = "";//状态 不传查默认所有 ( 0, “待支付”),( 1, “已支付待接单”),( 2, “已接单待服务”),( 3, “服务中”),( 4, “服务已完成”),( 5, “取消订单”)
    private double locationLatitude;
    private double locationLongitude;
    private List<OrderListEntity> list = new ArrayList<>();
    private String oId;//订单id
    private SortDialog sortDialog;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //adapter.notifyDataSetChanged();
                //每隔1秒更新一次界面，如果只需要精确到秒的倒计时此处改成1000即可
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.order_list_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("我的订单");
        initAdapter();
        onRefresh();
        sortDialog = new SortDialog(this);
    }

    private void initAdapter() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.public_all));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderListAdapter(R.layout.order_list_adapter, new ArrayList<OrderListEntity>());
        rvList.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, rvList);
        adapter.setEmptyView(R.layout.layout_nothing);
        adapter.start();
        handler.sendEmptyMessageDelayed(1, 1000);

        //隐藏侧滑删除功能(2019/08/05)
        //rvList.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter.setOnFenxiangClickListener(new OrderListAdapter.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String orderId, int type,String startTime,String endTime) {
                oId = orderId;
                switch (type) {
                    case 1://调度
                        StyledDialog.buildLoading("请求中").setActivity(OrderListActivity.this).show();
                        mPresenter.appointmentInfo(startTime,endTime);
                        break;
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZzRouter.gotoActivity(OrderListActivity.this, OrderDetailsActivity.class, list.get(position).orderId);
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.rb_work_order_all, R.id.rb_work_order_dai_jie,
            R.id.rb_work_order_jin_xing, R.id.rb_work_order_cancel})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.rb_work_order_all://全部
                orderType = "";
                mPresenter.myOrder("", 1, 10);
                break;
            case R.id.rb_work_order_dai_jie://待服务+服务中
                orderType = "2";
                mPresenter.myOrder("2", 1, 10);
                break;
            case R.id.rb_work_order_jin_xing://已完成
                orderType = "4";
                mPresenter.myOrder("4", 1, 10);
                break;
            case R.id.rb_work_order_cancel://取消
                orderType = "5";
                mPresenter.myOrder("5", 1, 10);
                break;
            default:
        }
    }

    @Override
    public void onRefresh() {
        offset = 2;
        mPresenter.myOrder(orderType, 1, 10);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeLayout.setRefreshing(false);
        mPresenter.myOrderMore(orderType, offset, 10);
    }

    @Override
    public void myOrderCallBack(List<OrderListEntity> data) {
        list = data;
        swipeLayout.setRefreshing(false);
        adapter.setNewData(data);
        if (data.size() < 10) {
            adapter.loadMoreEnd();
        } else {
            adapter.disableLoadMoreIfNotFullPage();
        }
    }

    @Override
    public void myOrderMoreCallBack(List<OrderListEntity> data) {
        list.addAll(data);
        swipeLayout.setRefreshing(false);
        offset++;
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (data.size() < 10) {
            adapter.loadMoreEnd();
        }
    }

    //预约详情
    @Override
    public void appointmentInfoCallBack(AppointmentInfoEntity data) {
        sortDialog.showShareDialog(true,data);
        sortDialog.setOnFenxiangClickListener(new SortDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String orderId, String technicianId) {
                mPresenter.dispatch(orderId,technicianId);
            }
        });
    }

    //调度返回数据
    @Override
    public void dispatchCallBack() {
        onRefresh();
    }

}
