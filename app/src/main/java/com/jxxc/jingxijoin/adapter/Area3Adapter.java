package com.jxxc.jingxijoin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;

import java.util.List;

public class Area3Adapter extends BaseAdapter {
    private Context context;
    private List<AreaEntity.Citys.Districts> list;

    public Area3Adapter(Context context){
        this.context=context;
    }

    public void setData(List<AreaEntity.Citys.Districts> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.stie_plus3_adapter,null);
            holder.tv_site_name = convertView.findViewById(R.id.tv_site_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AreaEntity.Citys.Districts data = list.get(position);
        holder.tv_site_name.setText(data.value);
        return convertView;
    }

    class ViewHolder{
        TextView tv_site_name;
    }
}
