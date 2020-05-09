package com.jxxc.jingxijoin.ui.orderdetails;


import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.dialog.SortDialog;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.GetOrderEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.GlideImgManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 订单详情
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderDetailsActivity extends MVPBaseActivity<OrderDetailsContract.View, OrderDetailsPresenter> implements OrderDetailsContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_order_icon)
    ImageView iv_order_icon;
    @BindView(R.id.tv_order_details_static)
    TextView tv_order_details_static;
    @BindView(R.id.tv_fuwu_start_time)
    TextView tv_fuwu_start_time;
    @BindView(R.id.tv_fuwu_wancheng_time)
    TextView tv_fuwu_wancheng_time;
    @BindView(R.id.tv_dating_order_static)
    TextView tv_dating_order_static;
    @BindView(R.id.tv_dating_order_car_number)
    TextView tv_dating_order_car_number;
    @BindView(R.id.tv_dating_car_type)
    TextView tv_dating_car_type;
    @BindView(R.id.tv_details_jishi_name)
    TextView tv_details_jishi_name;
    @BindView(R.id.tv_dating_order_money)
    TextView tv_dating_order_money;
    @BindView(R.id.tv_fuwu_type)
    TextView tv_fuwu_type;
    @BindView(R.id.tv_dating_order_time)
    TextView tv_dating_order_time;
    @BindView(R.id.tv_dating_order_memo)
    TextView tv_dating_order_memo;
    @BindView(R.id.tv_diao_du)
    TextView tv_diao_du;
    @BindView(R.id.tv_order_xia_time)
    TextView tv_order_xia_time;
    @BindView(R.id.tv_order_pay_type)
    TextView tv_order_pay_type;
    @BindView(R.id.tv_ping_jia_context)
    TextView tv_ping_jia_context;
    @BindView(R.id.view_bg)
    View view_bg;
    @BindView(R.id.iv_jishi_hand)
    ImageView iv_jishi_hand;
    @BindView(R.id.iv_jishi_jibie)
    ImageView iv_jishi_jibie;
    @BindView(R.id.iv_ping_jia_level)
    ImageView iv_ping_jia_level;
    @BindView(R.id.gv_fuwu_data)
    GridView gv_fuwu_data;
    @BindView(R.id.ll_ping_jia)
    LinearLayout ll_ping_jia;
    @BindView(R.id.tv_dating_order_kehu)
    TextView tv_dating_order_kehu;
    @BindView(R.id.tv_dating_order_count_down)
    TextView tv_dating_order_count_down;
    @BindView(R.id.tv_fuwu_itme)
    TextView tv_fuwu_itme;
    private String orderId="";
    private String orderIdTime="";
    private OrderDetailsDataAdapter adapter;
    private GetOrderEntity getOrderEntity;
    private SortDialog sortDialog;

    @Override
    protected int layoutId() {
        return R.layout.acivity_order_details;
    }

    @Override
    public void initData() {
        tv_title.setText("订单详情");
        orderId = ZzRouter.getIntentData(this,String.class);
        mPresenter.getOrder(orderId);

        sortDialog = new SortDialog(this);
        sortDialog.setOnFenxiangClickListener(new SortDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String orderId, String technicianId) {
                mPresenter.dispatch(orderId,technicianId);
            }
        });
    }
    @OnClick({R.id.tv_back,R.id.tv_diao_du,R.id.tv_dating_order_kehu})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_diao_du://调度
                //获取当前日期
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy");
                Date date = new Date(System.currentTimeMillis());
                String year = formatter.format(date);//年
                //月/日
                String YD = getOrderEntity.appointmentTime.substring(0,2)+"-"+getOrderEntity.appointmentTime.substring(3,5);
                String starthm = getOrderEntity.appointmentTime.substring(7,12);
                String endhm = getOrderEntity.appointmentTime.substring(13,18);
                String appointmentStartTime = year+"-"+YD+" "+starthm;
                String appointmentEndTime = year+"-"+YD+" "+endhm;
                mPresenter.appointmentInfo(appointmentStartTime,appointmentEndTime);
                break;
            case R.id.tv_dating_order_kehu:
                AppUtils.callPhone(this,getOrderEntity.phonenumber);
                break;
            default:
        }
    }

    //订单详情返回数据
    @Override
    public void getOrderCallBack(GetOrderEntity data) {
        getOrderEntity = data;
        adapter = new OrderDetailsDataAdapter(this);
        adapter.setData(data.products);
        gv_fuwu_data.setAdapter(adapter);

        orderIdTime = data.appointmentTime;
        tv_dating_order_static.setText(data.orderId);
        tv_dating_order_car_number.setText(data.carNum);
        tv_dating_car_type.setText(data.brandType);
        GlideImgManager.loadCircleImage(this, data.technicianAvatar, iv_jishi_hand);
        tv_details_jishi_name.setText(data.technicianRealName);
        tv_dating_order_money.setText("￥"+data.price);
        tv_dating_order_time.setText(data.appointmentTime);
        tv_dating_order_memo.setText(data.remark);
        tv_order_xia_time.setText(data.createTime);
        tv_order_pay_type.setText(data.payType);
        tv_dating_order_count_down.setText(data.statusName);
        //评价内容
        if (!AppUtils.isEmpty(data.customerCommentTime)){
            ll_ping_jia.setVisibility(View.VISIBLE);
            tv_fuwu_itme.setText(data.customerCommentTime);
            if (data.starLevel>0&&data.starLevel<=1){
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_13);
            }else if (data.starLevel>1&&data.starLevel<=2){
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_21);
            }else if (data.starLevel>2&&data.starLevel<=3){
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_23);
            }else if (data.starLevel>3&&data.starLevel<=4){
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_25);
            }else if (data.starLevel>4&&data.starLevel<=5){
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_27);
            }else{
                iv_ping_jia_level.setBackgroundResource(R.mipmap.icon_user_27);
            }
            tv_ping_jia_context.setText(data.commentContent);
        }
        //0上门服务 1进店服务
        if (data.serviceType==1){
            tv_fuwu_type.setText("上门服务");
        }else{
            tv_fuwu_type.setText("进店服务");
        }

        if (data.technicianGrade >0&&data.technicianGrade <=1){
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_13);
        }else if (data.technicianGrade >1&&data.technicianGrade <=2){
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_21);
        }else if (data.technicianGrade >2&&data.technicianGrade <=3){
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_23);
        }else if (data.technicianGrade >3&&data.technicianGrade <=4){
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_25);
        }else if (data.technicianGrade >4&&data.technicianGrade <=5){
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_27);
        }else{
            iv_jishi_jibie.setBackgroundResource(R.mipmap.icon_user_27);
        }
        if (data.color==1){
            view_bg.setBackgroundResource(R.drawable.car_color_1);
        }else if (data.color==2){
            view_bg.setBackgroundResource(R.drawable.car_color_2);
        }else if (data.color==3){
            view_bg.setBackgroundResource(R.drawable.car_color_3);
        }else if (data.color==4){
            view_bg.setBackgroundResource(R.drawable.car_color_4);
        }else if (data.color==5){
            view_bg.setBackgroundResource(R.drawable.car_color_5);
        }else if (data.color==6){
            view_bg.setBackgroundResource(R.drawable.car_color_6);
        }else if (data.color==7){
            view_bg.setBackgroundResource(R.drawable.car_color_7);
        }else if (data.color==8){
            view_bg.setBackgroundResource(R.drawable.car_color_8);
        }else if (data.color==9){
            view_bg.setBackgroundResource(R.drawable.car_color_9);
        }else if (data.color==10){
            view_bg.setBackgroundResource(R.drawable.car_color_10);
        }else{
            view_bg.setBackgroundResource(R.drawable.car_color_8);
        }
        //订单状态 0待支付 1已支付待接单 2已接单待服务 3服务中 4服务已完成 5取消订单
        if (data.status==0){
            //待支付
            tv_order_details_static.setText("待支付");
            tv_fuwu_start_time.setText("等待支付");
        }else if (data.status==1){
            //已支付待接单
            tv_order_details_static.setText("已支付待接单");
            tv_fuwu_start_time.setText("等待接单");
            tv_diao_du.setVisibility(View.VISIBLE);
        }else if (data.status==2){
            //已接单待服务
            tv_order_details_static.setText("已接单待服务");
            tv_fuwu_start_time.setText("等待服务");
            tv_diao_du.setVisibility(View.VISIBLE);
        }else if (data.status==3){
            //服务中
            tv_order_details_static.setText("服务中");
            tv_fuwu_start_time.setText("服务开始时间:"+data.startTime);
        }else if (data.status==4){
            //服务已完成
            iv_order_icon.setVisibility(View.VISIBLE);
            tv_order_details_static.setText("已完成服务");
            tv_fuwu_start_time.setText("服务开始时间:"+data.startTime);
            tv_fuwu_wancheng_time.setText("服务完成时间:"+data.endTime);
            tv_fuwu_wancheng_time.setVisibility(View.VISIBLE);
        }else if (data.status==5){
            //取消订单
            tv_order_details_static.setText("订单取消");
            tv_fuwu_start_time.setText("订单已取消");
        }
    }

    //预约详情
    @Override
    public void appointmentInfoCallBack(AppointmentInfoEntity data) {
        sortDialog.showShareDialog(true,data,orderId,orderIdTime);
        sortDialog.setOnFenxiangClickListener(new SortDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String orderId, String technicianId) {
                mPresenter.dispatch(orderId,technicianId);
            }
        });
    }

    //调度返回数据
    @Override
    public void dispatchCallBack() {
        mPresenter.getOrder(orderId);
    }
}
