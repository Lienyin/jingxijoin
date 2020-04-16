package com.jxxc.jingxijoin.ui.commercialtenant;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.List;

/**
 * @explain 加号适配器
 * @author feisher.qq:458079442
 * @time 2017/11/7 17:09.
 */
public class AddAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    List<String> data;
    private String id="";
    public AddAdapter(@LayoutRes int layoutResId, @Nullable List<String> data,String id) {
        super(layoutResId, data);
        this.data = data;
        this.id = id;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        ImageView view = helper.getView(R.id.iv_item);
        view.setBackgroundResource(R.mipmap.tianjiazhaopian);
        helper.addOnClickListener(R.id.iv_item);
        if (helper.getAdapterPosition() == data.size()-1) {
            helper.setVisible(R.id.fl_delete, false);//隐藏
            if (helper.getAdapterPosition() ==6) {
                helper.setVisible(R.id.iv_item, false);
            }else {
                helper.setVisible(R.id.iv_item, true);
            }
        }else {
            helper.setVisible(R.id.iv_item, false);
            if (!AppUtils.isEmpty(id)){
                helper.setVisible(R.id.fl_delete,false);//隐藏
            }else{
                helper.setVisible(R.id.fl_delete,true);//显示
            }
            //helper.setVisible(R.id.fl_delete, true);//显示
        }
        helper.addOnClickListener(R.id.fl_delete);
    }


}