package com.jxxc.jingxijoin.ui.brokerage;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.CommissionListEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;


/**
 * 佣金对账单
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BrokerageActivity extends MVPBaseActivity<BrokerageContract.View, BrokeragePresenter> implements BrokerageContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.tv_brokerage_date)
    TextView tv_brokerage_date;
    @BindView(R.id.ll_brokerage_date)
    LinearLayout ll_brokerage_date;
    @BindView(R.id.rb_user_all)
    RadioButton rb_user_all;
    @BindView(R.id.rb_user_yuqi)
    RadioButton rb_user_yuqi;
    private int offset = 2;
    private BrokerageAdapter adapter;
    private List<CommissionListEntity> list = new ArrayList<>();
    private String ny;//年月
    private int tabType = 0;//0 已结算 1待结算

    @Override
    protected int layoutId() {
        return R.layout.brokerage_activity;
    }

    @Override
    public void initData() {
        tv_title.setText("佣金对账单");
        initAdapter();
        onRefresh();
        siteData();

    }

    private void initAdapter() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.public_all));
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BrokerageAdapter(R.layout.brokerage_adapter, new ArrayList<CommissionListEntity>());
        rvList.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, rvList);
        adapter.setEmptyView(R.layout.layout_nothing);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(BrokerageActivity.this, BrokerageDetailsActivity.class);
//                intent.putExtra("id",list.get(position).id);
//                startActivity(intent);
            }
        });
    }

    //站点数据
    private void siteData(){
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.languages);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] languages = getResources().getStringArray(R.array.languages);
                toast(BrokerageActivity.this,"你点击的是:"+languages[pos]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @OnClick({R.id.tv_back,R.id.ll_brokerage_date,R.id.rb_user_all,R.id.rb_user_yuqi})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.ll_brokerage_date://选择日期
                getTime();
                break;
            case R.id.rb_user_yuqi://未结算
                StyledDialog.buildLoading("加载中").setActivity(this).show();
                tabType = 1;
                mPresenter.commissionList(1,20,tabType,"");
                break;
            case R.id.rb_user_all://已结算
                StyledDialog.buildLoading("加载中").setActivity(this).show();
                tabType = 0;
                mPresenter.commissionList(1,20,tabType,"");
                break;
            default:
        }
    }

    //时间选择器
    private void getTime(){
        //获取当前时间日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        int year = Integer.valueOf(simpleDateFormat.format(date).substring(0,4));
        int month = Integer.valueOf(simpleDateFormat.format(date).substring(5,7));
        int day = Integer.valueOf(simpleDateFormat.format(date).substring(8,10));

        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);//年月日
        picker.setRangeStart(2018, 1);//日期起点
        picker.setRangeEnd(year, month);//日期终点
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                tv_brokerage_date.setText(year + "-" + month);
            }
        });
        picker.show();
    }

    @Override
    public void onRefresh() {
        offset = 2;
        mPresenter.commissionList(1,20,tabType,"");
    }

    @Override
    public void onLoadMoreRequested() {
        swipeLayout.setRefreshing(false);
        mPresenter.commissionListMore(offset,20,tabType,"");
    }

    @Override
    public void commissionListCallBack(List<CommissionListEntity> data) {
        list = data;
        swipeLayout.setRefreshing(false);
        adapter.setNewData(data);
        adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void commissionListMoreCallBack(List<CommissionListEntity> data) {
        list.addAll(data);
        swipeLayout.setRefreshing(false);
        offset++;
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (data.size() < 20) {
            adapter.loadMoreEnd();
        }
    }
}
