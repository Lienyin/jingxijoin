package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.List;

public class WeekOfAdapter extends BaseAdapter {
    private Context context;
    private int defaultSelection=0;
    private List<String> list;
    private List<AppointmentListEntity.DayNum> dayNum;

    public WeekOfAdapter(Context context){
        this.context=context;
    }

    public void setData(List<String> list,List<AppointmentListEntity.DayNum> dayNum){
        this.list = list;
        this.dayNum = dayNum;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.weekof_adapter,null);
            holder.tv_time_name = convertView.findViewById(R.id.tv_time_name);
            holder.ll_time_bg = convertView.findViewById(R.id.ll_time_bg);
            holder.tv_order_number = convertView.findViewById(R.id.tv_order_number);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String data = list.get(position);
        holder.tv_time_name.setText(list.get(position));
        if (!AppUtils.isEmpty(dayNum)){
            holder.tv_order_number.setText(dayNum.get(position).num+"");
        }
        if (position == defaultSelection) {// 选中时设置单纯颜色
            holder.ll_time_bg.setSelected(true);
            holder.tv_order_number.setBackgroundResource(R.drawable.order_number_bg);
        } else {// 未选中时设置selector
            holder.ll_time_bg.setSelected(false);
            holder.tv_order_number.setBackgroundResource(R.drawable.order_number_bg_no);
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv_time_name;
        TextView tv_order_number;
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
