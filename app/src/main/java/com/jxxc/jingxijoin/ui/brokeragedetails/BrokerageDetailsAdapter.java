package com.jxxc.jingxijoin.ui.brokeragedetails;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.BrokerageDetailsEntity;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.List;

/**
 * @authorfeisher on 2017/11/21.14:58
 * email:458079442@qq.com
 */

public class BrokerageDetailsAdapter extends BaseQuickAdapter<BrokerageDetailsEntity, BaseViewHolder> {

    public BrokerageDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<BrokerageDetailsEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BrokerageDetailsEntity item) {
        helper.setText(R.id.tv_brokerage_details_user, item.orderId);
        helper.setText(R.id.tv_brokerage_details_zq, "￥"+item.orderMoney);
        helper.setText(R.id.tv_brokerage_details_money, "￥"+item.technicianMoney);


    }
}
