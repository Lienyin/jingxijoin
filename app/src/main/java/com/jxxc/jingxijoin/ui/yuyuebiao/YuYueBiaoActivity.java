package com.jxxc.jingxijoin.ui.yuyuebiao;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.dialog.SortDialog;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentListEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.HorizontalListView;
import com.jxxc.jingxijoin.utils.MyGridView;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YuYueBiaoActivity extends MVPBaseActivity<YuYueBiaoContract.View, YuYueBiaoPresenter> implements YuYueBiaoContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.gv_weekOf_date)
    HorizontalListView gv_weekOf_date;
    @BindView(R.id.gv_time_data)
    MyGridView gv_time_data;
    private TimeAdapter timeAdapter;
    private WeekOfAdapter weekOfAdapter;
    private String dateStr ="";
    private SortDialog sortDialog;
    @Override
    protected int layoutId() {
        return R.layout.yuyue_biao_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("预约单日程表");

        //日期设置
        weekOfAdapter = new WeekOfAdapter(this);
        weekOfAdapter.setData(test(30));
        gv_weekOf_date.setAdapter(weekOfAdapter);
        //获取当前日期
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String queryDate = formatter.format(date);//今天日期
        dateStr = queryDate;//默认日期
        //dateStr = "2020-04-06";//默认日期
        mPresenter.appointmentList(dateStr);

        sortDialog = new SortDialog(this);

        //获取周几
        gv_weekOf_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                weekOfAdapter.setSelectPosition(position);
                Calendar date = Calendar.getInstance();
                String year = String.valueOf(date.get(Calendar.YEAR));
                dateStr = year+"-"+test(30).get(position).toString().substring(0,5);
                mPresenter.appointmentList(dateStr);
            }
        });
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            default:
        }
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> test(int intervals ) {
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i < intervals; i++) {
            fetureDaysList.add(getFetureDate(i));
        }
        return fetureDaysList;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        Log.e(null, result);
        return result+getWeekOfDate(today);
    }
    /**
          * 获取当前日期是星期几<br>
          * @param dt
          * @return 当前日期是星期几
          */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"\n 周日", "\n 周一", "\n 周二", "\n 周三", "\n 周四", "\n 周五", "\n 周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    //预约列表接口返回数据
    @Override
    public void appointmentListCallBack(List<AppointmentListEntity> data) {
        if (data.size()>0){
            timeAdapter = new TimeAdapter(this);
            timeAdapter.setData(data);
            gv_time_data.setAdapter(timeAdapter);

            timeAdapter.setOnFenxiangClickListener(new TimeAdapter.OnFenxiangClickListener() {
                @Override
                public void onFenxiangClick(String statTime,String endTime) {
                    mPresenter.appointmentInfo(dateStr+" "+statTime,dateStr+" "+endTime);
                }
            });
        }
    }

    //预约详情返回数据
    @Override
    public void appointmentInfoCallBack(AppointmentInfoEntity data) {
        sortDialog.showShareDialog(true,data);
        sortDialog.setOnFenxiangClickListener(new SortDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String orderId, String technicianId) {
                mPresenter.dispatch(orderId,technicianId);
            }
        });
    }

    //排班返回数据
    @Override
    public void dispatchCallBack() {
        mPresenter.appointmentList(dateStr);
    }
}
