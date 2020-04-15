package com.jxxc.jingxijoin.ui.brokerage;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.CommissionListEntity;

import java.util.List;

/**
 * @authorfeisher on 2017/11/21.14:58
 * email:458079442@qq.com
 */

public class BrokerageAdapter extends BaseQuickAdapter<CommissionListEntity, BaseViewHolder> {

    public BrokerageAdapter(@LayoutRes int layoutResId, @Nullable List<CommissionListEntity> data) {
        super(layoutResId, data);
    }

    protected void convert(BaseViewHolder helper, final CommissionListEntity item) {
        helper.setText(R.id.tv_brokerage_time, item.statsDate);
//        helper.setText(R.id.tv_brokerage_zq, item.cycleName);
        helper.setText(R.id.tv_brokerage_money, "￥ "+item.commission);


    }
}
