package com.jxxc.jingxijoin.ui.yuyuebiao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortAdapter extends BaseAdapter {
    private Context context;
    private List<AppointmentInfoEntity.Order> list;
    private List<AppointmentInfoEntity.Carport> carportList;
    private List<AppointmentInfoEntity.Tech> techList;
    private Map<Integer,String> carId = new HashMap<>();
    private Map<String,String> techId = new HashMap<>();
    private String orderId="";
    private String oId="";
    private String time="";

    public SortAdapter(Context context){
        this.context=context;
    }

    public void setData(List<AppointmentInfoEntity.Order> list,
                        List<AppointmentInfoEntity.Carport> carportList,
                        List<AppointmentInfoEntity.Tech> techList){
        this.list = list;
        this.carportList = carportList;
        this.techList = techList;
    }

    public void setData(String orderId,
                        List<AppointmentInfoEntity.Carport> carportList,
                        List<AppointmentInfoEntity.Tech> techList,String time){
        this.orderId = orderId;
        this.carportList = carportList;
        this.techList = techList;
        this.time = time;
    }

    @Override
    public int getCount() {
        int num = 0;
        if (!AppUtils.isEmpty(orderId)){
            num = 1;
        }else{
            num = list.size();
        }
        return num;
    }

    @Override
    public Object getItem(int position) {
        AppointmentInfoEntity.Order nums;
        if (!AppUtils.isEmpty(orderId)){
            nums = null;
        }else{
            nums = list.get(position);
        }
        return nums;
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
        if (!AppUtils.isEmpty(orderId)){
            oId = orderId;
            holder.tv_order_id.setText(orderId);
            holder.tv_order_time.setText(time);
        }else{
            AppointmentInfoEntity.Order data = list.get(position);
            holder.tv_order_id.setText(data.orderId);
            holder.tv_order_time.setText(data.appointmentTime);
            oId = data.orderId;
        }
        ViewHolder finalHolder = holder;
        holder.tv_order_gongwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = new String[carportList.size()];
                for (int i=0;i<carportList.size();i++){
                    items[i] = carportList.get(i).carportId;
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("选择洗车工位")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finalHolder.tv_order_gongwei.setText(items[which]+"");
                                carId.put(position,items[which]);
                            }
                        });
                dialog.show();
            }
        });
        holder.tv_order_jishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = new String[techList.size()];
                for (int i=0;i<techList.size();i++){
                    items[i] = (i+1)+"-技师"+techList.get(i).realName+"(随时可以服务)";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("选择洗车技师")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finalHolder.tv_order_jishi.setText(items[which]+"");
                                techId.put(oId,techList.get(which).technicianId);
                            }
                        });
                dialog.show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_order_id;
        TextView tv_order_gongwei;
        TextView tv_order_time;
        TextView tv_order_jishi;
    }

   public Map<Integer,String> getCarId(){
        return carId;
   }
   public Map<String,String> getTechId(){
        return techId;
   }
}
