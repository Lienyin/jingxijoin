package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;

import java.util.List;

public class TimeAdapter extends BaseAdapter {
    private Context context;
    private List<AppointmentListEntity> list;

    public TimeAdapter(Context context){
        this.context=context;
    }

    public void setData(List<AppointmentListEntity> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.time_adapter,null);
            holder.tv_time_num = convertView.findViewById(R.id.tv_time_num);
            holder.tv_time_name = convertView.findViewById(R.id.tv_time_name);
            holder.tv_static_name = convertView.findViewById(R.id.tv_static_name);
            holder.iv_icon_static = convertView.findViewById(R.id.iv_icon_static);
            holder.tv_order_id = convertView.findViewById(R.id.tv_order_id);
            holder.ll_time_bg = convertView.findViewById(R.id.ll_time_bg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AppointmentListEntity data = list.get(position);
        holder.tv_time_name.setText(data.title);
        //订单状态 1待调度 2待服务 3服务中
        if (data.status==1){
            holder.ll_time_bg.setBackgroundResource(R.drawable.green_bian_huang);
            holder.tv_static_name.setText("待排X"+data.num);
            holder.tv_time_num.setVisibility(View.INVISIBLE);
            holder.tv_time_num.setText("");
            holder.iv_icon_static.setVisibility(View.VISIBLE);
            holder.iv_icon_static.setImageResource(R.mipmap.add_time_yuyue_bai);
            holder.tv_order_id.setVisibility(View.VISIBLE);
            holder.tv_order_id.setText(data.orderId);
        }else if(data.status==2){
            holder.ll_time_bg.setBackgroundResource(R.drawable.green_bian_lvs);
            holder.tv_static_name.setText("已排班");
            holder.tv_time_num.setVisibility(View.VISIBLE);
            holder.tv_time_num.setText("技师编号"+data.technicianCode);
            holder.iv_icon_static.setVisibility(View.VISIBLE);
            holder.iv_icon_static.setImageResource(R.mipmap.time_yuyue_bai);
            holder.tv_order_id.setVisibility(View.VISIBLE);
            holder.tv_order_id.setText(data.orderId);
        }else if(data.status==3){
            holder.ll_time_bg.setBackgroundResource(R.drawable.green_bian_lv);
            holder.tv_static_name.setText("服务中");
            holder.tv_time_num.setVisibility(View.VISIBLE);
            holder.tv_time_num.setText("技师编号"+data.technicianCode);
            holder.iv_icon_static.setVisibility(View.VISIBLE);
            holder.iv_icon_static.setImageResource(R.mipmap.xi_che);
            holder.tv_order_id.setVisibility(View.VISIBLE);
            holder.tv_order_id.setText(data.orderId);
        }else{
            holder.ll_time_bg.setBackgroundResource(R.drawable.green_bian_owei_yuyue);
            holder.tv_static_name.setText("未预约");
            holder.tv_time_num.setVisibility(View.INVISIBLE);
            holder.tv_time_num.setText("");
            holder.iv_icon_static.setVisibility(View.INVISIBLE);
            holder.tv_order_id.setVisibility(View.INVISIBLE);
        }

        holder.iv_icon_static.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.status==1){
                    onFenxiangClickListener.onFenxiangClick(data.title.substring(0,5),data.title.substring(6,11));
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_time_num;
        TextView tv_time_name;
        TextView tv_static_name;
        TextView tv_order_id;
        ImageView iv_icon_static;
        LinearLayout ll_time_bg;
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener{
        void onFenxiangClick(String statTime,String endTime);
    }
}
