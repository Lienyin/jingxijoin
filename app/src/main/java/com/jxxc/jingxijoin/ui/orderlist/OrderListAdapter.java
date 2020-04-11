package com.jxxc.jingxijoin.ui.orderlist;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.OrderListEntity;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @authorfeisher on 2017/11/21.14:58
 * email:458079442@qq.com
 */

public class OrderListAdapter extends BaseQuickAdapter<OrderListEntity, BaseViewHolder> {

    private List<OrderListEntity> list;

    public OrderListAdapter(@LayoutRes int layoutResId, @Nullable List<OrderListEntity> data) {
        super(layoutResId, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderListEntity item) {
        helper.setText(R.id.tv_dating_order_static, item.orderId);
        helper.setText(R.id.tv_dating_order_car_number, item.carNum);
        helper.setText(R.id.tv_dating_car_type, item.brandType);
        helper.setText(R.id.tv_dating_order_time, item.appointmentTime);
        helper.setText(R.id.tv_order_time, item.startTime);
        helper.setText(R.id.tv_dating_order_memo, item.remark);
        helper.setText(R.id.tv_dating_order_money, "￥"+item.price);
        //0上门服务 1进店服务
        if (item.serviceType==1){
            helper.setText(R.id.tv_fuwu_type, "上门服务");
        }else{
            helper.setText(R.id.tv_fuwu_type, "进店服务");
        }

        if (item.color==1){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_1);
        }else if (item.color==2){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_2);
        }else if (item.color==3){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_3);
        }else if (item.color==4){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_4);
        }else if (item.color==5){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_5);
        }else if (item.color==6){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_6);
        }else if (item.color==7){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_7);
        }else if (item.color==8){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_8);
        }else if (item.color==9){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_9);
        }else if (item.color==10){
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_10);
        }else{
            helper.setBackgroundRes(R.id.view_bg,R.drawable.car_color_8);
        }
        //订单状态 0待支付 1已支付待接单 2已接单待服务 3服务中 4服务已完成 5取消订单
        if (item.status==0){
            helper.setVisible(R.id.tv_diao_du,false);
            helper.setText(R.id.tv_dating_order_count_down,"待支付");
        }else if (item.status == 1){
            //不会有状态1
            helper.setText(R.id.tv_dating_order_count_down,"待接单");
            helper.setVisible(R.id.tv_diao_du,false);
        }else if (item.status == 2){//待服务
            helper.setText(R.id.tv_dating_order_count_down,"待服务");
            helper.setVisible(R.id.tv_diao_du,true);
        }else if (item.status == 3){//服务中
            helper.setVisible(R.id.tv_diao_du,false);
            helper.setText(R.id.tv_dating_order_count_down,"服务中");
            //服务截至时间-当前时间
//            int jzTIme =  Integer.parseInt(getTime(item.getCanCompleteTime()));//截至时间
//            int dqTime = Integer.parseInt(getTime(getDQTime()));//当前时间
//            if (jzTIme-dqTime>0){
//                String str = "";
//                int s = jzTIme-dqTime;
//                int time = s/3600;
//                if (time>=1){
//                    int h = s/3600;//小时
//                    s = s-h*3600;//剩余秒数
//                    int m = s/60;//分钟
//                    s = s-m*60;//秒数
//                    if (h>=10){
//                        helper.setText(R.id.tv_dating_order_count_down,"  "+h+":"+m+":"+s);
//                    }else{
//                        helper.setText(R.id.tv_dating_order_count_down,"  0"+h+":"+m+":"+s);
//                    }
//                }else{
//                    int m = s/60;//分钟
//                    s = s-m*60;//秒数
//                    //str = getStrTimeMinute((jzTIme-dqTime)+"");//服务剩余时间
//                    if (m>=10){
//                        helper.setText(R.id.tv_dating_order_count_down,m+":"+s);
//                    }else{
//                        helper.setText(R.id.tv_dating_order_count_down,"  0"+m+":"+s);
//                    }
//                }
//            }else{
//            }
        }else if (item.status == 4){//服务已完成
            helper.setText(R.id.tv_dating_order_count_down,"已完成");
            helper.setVisible(R.id.tv_diao_du,false);
        }else{
            helper.setText(R.id.tv_dating_order_count_down,"订单取消");
            helper.setVisible(R.id.tv_diao_du,false);
        }
        helper.setOnClickListener(R.id.tv_dating_order_kehu, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppUtils.isEmpty(item.phonenumber)){
                    AppUtils.callPhone(mContext,item.phonenumber);
                }else{
                    Toast.makeText(mContext,"暂无联系方式",Toast.LENGTH_LONG).show();
                }
            }
        });
        helper.setOnClickListener(R.id.tv_diao_du, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前日期
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy");
                Date date = new Date(System.currentTimeMillis());
                String year = formatter.format(date);//年
                //月/日
                String YD = item.appointmentTime.substring(0,2)+"-"+item.appointmentTime.substring(3,5);
                String starthm = item.appointmentTime.substring(7,12);
                String endhm = item.appointmentTime.substring(13,18);

                String appointmentStartTime = year+"-"+YD+" "+starthm;
                String appointmentEndTime = year+"-"+YD+" "+endhm;
                onFenxiangClickListener.onFenxiangClick(item.orderId,1,appointmentStartTime,
                        appointmentEndTime);//调度
            }
        });
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener{
        void onFenxiangClick(String orderId, int type,String startTime,String endTime);
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }

    //获取当前时间
    public String getDQTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    int result = 0;
    private Thread thread;
    public void start() {
        thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (list == null || result == list.size()) {
                            break;
                        }
                        sleep(1);
                        for (OrderListEntity person : list) {
                            if (!"时间到".equals(person.getCanCompleteTime())) {
                                if ("1".equals(person.getCanCompleteTime())) {
                                    person.setCanCompleteTime("时间到");
                                    result++;
                                } else {
                                    person.setCanCompleteTime(person.getCanCompleteTime());
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
