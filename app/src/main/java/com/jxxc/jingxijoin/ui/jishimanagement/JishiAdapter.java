package com.jxxc.jingxijoin.ui.jishimanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.QueryListEntity;
import com.jxxc.jingxijoin.ui.orderlist.OrderListAdapter;
import com.jxxc.jingxijoin.utils.GlideImgManager;

import java.util.List;

public class JishiAdapter extends BaseAdapter {
    private Context context;
    private List<QueryListEntity> list;

    public JishiAdapter(Context context){
        this.context=context;
    }

    public void setData(List<QueryListEntity> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.jiishi_adapter,null);
            holder.iv_jishi_hand = convertView.findViewById(R.id.iv_jishi_hand);
            holder.tv_jishi_number = convertView.findViewById(R.id.tv_jishi_number);
            holder.tv_jishi_name = convertView.findViewById(R.id.tv_jishi_name);
            holder.iv_jishi_jibie = convertView.findViewById(R.id.iv_jishi_jibie);
            holder.iv_jishi_look = convertView.findViewById(R.id.iv_jishi_look);
            holder.iv_jishi_update = convertView.findViewById(R.id.iv_jishi_update);
            holder.iv_jishi_dec = convertView.findViewById(R.id.iv_jishi_dec);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        QueryListEntity data = list.get(position);
        GlideImgManager.loadCircleImage(context, data.avatar, holder.iv_jishi_hand);
        holder.tv_jishi_number.setText("编号:"+data.technicianCode);
        holder.tv_jishi_name.setText(data.realName);
        if (data.grade>0&&data.grade<=1){
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_13);
        }else if (data.grade>1&&data.grade<=2){
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_21);
        }else if (data.grade>2&&data.grade<=3){
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_23);
        }else if (data.grade>3&&data.grade<=4){
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_25);
        }else if (data.grade>4&&data.grade<=5){
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_27);
        }else{
            holder.iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_27);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView iv_jishi_hand;
        TextView tv_jishi_number;
        TextView tv_jishi_name;
        ImageView iv_jishi_jibie;
        ImageView iv_jishi_look;
        ImageView iv_jishi_update;
        ImageView iv_jishi_dec;
    }

    private OrderListAdapter.OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OrderListAdapter.OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener{
        void onFenxiangClick(String orderId, int type);
    }
}
