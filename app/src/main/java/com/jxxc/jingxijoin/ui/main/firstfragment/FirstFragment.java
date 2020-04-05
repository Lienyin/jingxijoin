package com.jxxc.jingxijoin.ui.main.firstfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.BasEarningsEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseFragment;
import com.jxxc.jingxijoin.ui.jishimanagement.JishiManagementActivity;
import com.jxxc.jingxijoin.ui.orderlist.OrderListActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.GlideImgManager;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

@SuppressLint("ValidFragment")
public class FirstFragment extends MVPBaseFragment<FirseFramentContract.View, FirseFramentPresenter> implements View.OnClickListener, FirseFramentContract.View, SwipeRefreshLayout.OnRefreshListener {
    private Context context;
    private LinearLayout ll_jishi,ll_order;
    private TextView tv_home_name,tv_fuwu_type,tv_home_type;
    private ImageView iv_home_logo,iv_home_level;
    private TextView tv_today_order,tv_benyue_shouyi,tv_ke_tixian;
    private TextView tv_yuyue_number,tv_home_order_num,tv_home_settle_order_num;

    public FirstFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        ll_jishi = view.findViewById(R.id.ll_jishi);
        ll_order = view.findViewById(R.id.ll_order);
        tv_home_name = view.findViewById(R.id.tv_home_name);
        iv_home_logo = view.findViewById(R.id.iv_home_logo);
        iv_home_level = view.findViewById(R.id.iv_home_level);
        tv_home_type = view.findViewById(R.id.tv_home_type);
        tv_fuwu_type = view.findViewById(R.id.tv_fuwu_type);
        tv_today_order = view.findViewById(R.id.tv_today_order);
        tv_benyue_shouyi = view.findViewById(R.id.tv_benyue_shouyi);
        tv_ke_tixian = view.findViewById(R.id.tv_ke_tixian);
        tv_yuyue_number = view.findViewById(R.id.tv_yuyue_number);
        tv_home_order_num = view.findViewById(R.id.tv_home_order_num);
        tv_home_settle_order_num = view.findViewById(R.id.tv_home_settle_order_num);
        ll_jishi.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        StyledDialog.buildLoading("加载中").setActivity((Activity) context).show();
        mPresenter.basEarnings();
        return view;
    }

    @Override
    public void onClick(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.ll_jishi://技师
                ZzRouter.gotoActivity((Activity) context, JishiManagementActivity.class);
                break;
            case R.id.ll_order://订单
                ZzRouter.gotoActivity((Activity) context, OrderListActivity.class);
                break;
        }
    }

    //刷新
    @Override
    public void onRefresh() {

    }

    //基础收益(首页)
    @Override
    public void basEarningsCallBack(BasEarningsEntity data) {
        GlideImgManager.loadCircleImage(context, data.companyLogo, iv_home_logo);
        tv_home_name.setText(data.companyName);
        if (data.starLevel>0&&data.starLevel<=1){
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_13);
        }else if (data.starLevel>1&&data.starLevel<=2){
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_21);
        }else if (data.starLevel>2&&data.starLevel<=3){
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_23);
        }else if (data.starLevel>3&&data.starLevel<=4){
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_25);
        }else if (data.starLevel>4&&data.starLevel<=5){
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_27);
        }else{
            iv_home_level.setBackgroundResource(R.mipmap.icon_user_27);
        }
        //服务类型 1到店服务 2上门洗车 3到店服务/上门洗车
        if (data.setviceType==1){
            tv_fuwu_type.setText("到店服务");
        }else if (data.setviceType==2){
            tv_fuwu_type.setText("上门洗车");
        }else{
            tv_fuwu_type.setText("到店服务/上门洗车");
        }
        //加盟商类型 1.直营店2.加盟店3.合作店
        if (data.companyType==1){
            tv_home_type.setText("直营店");
        }else if (data.companyType==2){
            tv_home_type.setText("加盟店");
        }else{
            tv_home_type.setText("合作店");
        }
        tv_today_order.setText(data.dayOrderNum);
        tv_benyue_shouyi.setText(data.monthEarnings);
        tv_ke_tixian.setText(data.availableBalance);
        tv_yuyue_number.setText("+"+data.appointmentOrderNum);
        tv_home_order_num.setText("+"+data.orderNum);
        tv_home_settle_order_num.setText("+"+data.settleOrderNum);
    }
}
