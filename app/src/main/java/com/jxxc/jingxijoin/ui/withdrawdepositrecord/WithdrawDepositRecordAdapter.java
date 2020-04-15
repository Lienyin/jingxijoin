package com.jxxc.jingxijoin.ui.withdrawdepositrecord;

import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.DrawMoneyRecordEntity;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.List;

/**
 * @authorfeisher on 2017/11/21.14:58
 * email:458079442@qq.com
 */

public class WithdrawDepositRecordAdapter extends BaseQuickAdapter<DrawMoneyRecordEntity, BaseViewHolder> {

    public WithdrawDepositRecordAdapter(@LayoutRes int layoutResId, @Nullable List<DrawMoneyRecordEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DrawMoneyRecordEntity item) {
        helper.setText(R.id.tv_withdraw_deposit_date, item.applyTime);
        helper.setText(R.id.tv_withdraw_deposit_money, "￥"+item.money);
        //状态 0未审核 1已审核 -1已驳回
        if (item.status==0){
            helper.setText(R.id.tv_withdraw_deposit_money_static, "未审核");
        }else if (item.status==1){
            helper.setText(R.id.tv_withdraw_deposit_money_static, "已审核");
        }else{
            helper.setText(R.id.tv_withdraw_deposit_money_static, "已驳回");
        }

        if (!AppUtils.isEmpty(item.remitPhoto)){
            helper.setVisible(R.id.iv_bohui_hint,true);
        }else{
            helper.setVisible(R.id.iv_bohui_hint,false);
        }
        helper.setOnClickListener(R.id.iv_bohui_hint, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("驳回说明")
                        .setMessage(item.remitPhoto)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create().show();
            }
        });
    }
}
