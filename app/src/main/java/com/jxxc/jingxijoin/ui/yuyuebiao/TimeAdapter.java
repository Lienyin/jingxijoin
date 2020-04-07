package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;

import java.util.List;

public class TimeAdapter extends BaseAdapter {
    private Context context;
    private int defaultSelection=-1;
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
            holder.ll_time_bg = convertView.findViewById(R.id.ll_time_bg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AppointmentListEntity data = list.get(position);
        holder.tv_time_name.setText(data.title);
        holder.tv_time_name.setTextColor(context.getResources().getColor(R.color.set_bg));
        holder.tv_time_num.setText(Html.fromHtml("<font color=\"#cccccc\">时间已过</font>"));
        if (position == defaultSelection) {// 选中时设置单纯颜色
            holder.ll_time_bg.setSelected(true);
        } else {// 未选中时设置selector
            holder.ll_time_bg.setSelected(false);
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv_time_num;
        TextView tv_time_name;
        LinearLayout ll_time_bg;
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
