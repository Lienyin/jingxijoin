package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;

import java.util.List;

public class SortAdapter extends BaseAdapter {
    private Context context;
    private int defaultSelection=0;
    private List<AppointmentInfoEntity.Order> list;

    public SortAdapter(Context context){
        this.context=context;
    }

    public void setData(List<AppointmentInfoEntity.Order> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_adapter,null);
            holder.tv_order_id = convertView.findViewById(R.id.tv_order_id);
            holder.tv_order_gongwei = convertView.findViewById(R.id.tv_order_gongwei);
            holder.tv_order_time = convertView.findViewById(R.id.tv_order_time);
            holder.tv_order_jishi = convertView.findViewById(R.id.tv_order_jishi);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AppointmentInfoEntity.Order data = list.get(position);
        holder.tv_order_id.setText(data.orderId);
        holder.tv_order_time.setText(data.appointmentTime);
        return convertView;
    }

    class ViewHolder{
        TextView tv_order_id;
        TextView tv_order_gongwei;
        TextView tv_order_time;
        TextView tv_order_jishi;
    }

/**
     * @param position
     * ���ø���״̬��item
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
