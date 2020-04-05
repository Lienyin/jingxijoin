package com.jxxc.jingxijoin.ui.orderdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.utils.GlideImgManager;
import com.jxxc.jingxijoin.entity.backparameter.GetOrderEntity;

import java.util.List;

public class OrderDetailsDataAdapter extends BaseAdapter {
    private Context context;
    private List<GetOrderEntity.Products> list;
    private int defaultSelection = -1;

    public OrderDetailsDataAdapter(Context context){
        this.context=context;
    }

    public void setData(List<GetOrderEntity.Products> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_data_adapter,null);
            holder.iv_home_fuwu = convertView.findViewById(R.id.iv_home_fuwu);
            holder.home_fuwu_name = convertView.findViewById(R.id.home_fuwu_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        GetOrderEntity.Products data = list.get(position);
        GlideImgManager.loadImage(context, data.imgUrl, holder.iv_home_fuwu);
        holder.home_fuwu_name.setText(data.productName);
        return convertView;
    }

    class ViewHolder{
        ImageView iv_home_fuwu;
        TextView home_fuwu_name;
    }

    /**
     * @param position
     * 设置高亮状态的item
     */
    public void setSelectPosition(int position) {
        if (!(position < 0 || position > list.size())) {
            defaultSelection = position;
            notifyDataSetChanged();
        }else{
            defaultSelection = -1;
            notifyDataSetChanged();
        }
    }
}