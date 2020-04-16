package com.jxxc.jingxijoin.ui.finance;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.DeviceReportEntity;
import com.jxxc.jingxijoin.entity.backparameter.FinanceReportEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FinanceActivity extends MVPBaseActivity<FinanceContract.View, FinancePresenter> implements FinanceContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rb_bao_financing)
    RadioButton rb_bao_financing;
    @BindView(R.id.rb_bao_equipment)
    RadioButton rb_bao_equipment;
    @BindView(R.id.rb_bao_eq_sanyue)
    RadioButton rb_bao_eq_sanyue;
    @BindView(R.id.rb_bao_eq_liuyue)
    RadioButton rb_bao_eq_liuyue;
    @BindView(R.id.rb_bao_eq_yinian)
    RadioButton rb_bao_eq_yinian;
    @BindView(R.id.rb_bao_sanyue)
    RadioButton rb_bao_sanyue;
    @BindView(R.id.rb_bao_liuyue)
    RadioButton rb_bao_liuyue;
    @BindView(R.id.rb_bao_yinian)
    RadioButton rb_bao_yinian;
    @BindView(R.id.financing_view)
    View financing_view;
    @BindView(R.id.equipment_view)
    View equipment_view;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.tv_bao_totalRent)
    TextView tv_bao_totalRent;
    @BindView(R.id.tv_bao_eq_totalRent)
    TextView tv_bao_eq_totalRent;
    @BindView(R.id.tv_title_one)
    TextView tv_title_one;
    @BindView(R.id.tv_title_head)
    TextView tv_title_head;
    @BindView(R.id.ll_bao_view)
    LinearLayout ll_bao_view;
    @BindView(R.id.ll_bao_view_null)
    LinearLayout ll_bao_view_null;
    @BindView(R.id.ll_financing_view)
    LinearLayout ll_financing_view;
    @BindView(R.id.ll_data_all)
    RadioButton ll_data_all;
    @BindView(R.id.ll_data_car)
    RadioButton ll_data_car;
    @BindView(R.id.ll_data_battery)
    RadioButton ll_data_battery;
    @BindView(R.id.ll_data_gui)
    RadioButton ll_data_gui;
    private DeviceReportEntity lineChartData;
    private SwipeRefreshLayout swipeLayout;
    private List<String> time = new ArrayList<>();

    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    @Override
    protected int layoutId() {
        return R.layout.finance_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("财务报表");
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.public_all));
        //站长看不见提现信息
//        if ("2".equals(SPUtils.get(SPUtils.K_USER_TYPE,""))){
//            financing_view.setVisibility(View.GONE);
//            equipment_view.setVisibility(View.VISIBLE);
//            rb_bao_equipment.setChecked(true);
//            rb_bao_eq_sanyue.setChecked(true);
//            mPresenter.deviceReport(0);
//        }else{
//            financing_view.setVisibility(View.VISIBLE);
//            equipment_view.setVisibility(View.GONE);
//            ll_bao_view_null.setVisibility(View.GONE);
//            ll_financing_view.setVisibility(View.VISIBLE);
//            mPresenter.financeReport(0);
//        }
        financing_view.setVisibility(View.VISIBLE);
        equipment_view.setVisibility(View.GONE);
        ll_bao_view_null.setVisibility(View.GONE);
        ll_financing_view.setVisibility(View.VISIBLE);
        mPresenter.financeReport(3);
    }

    @OnClick({R.id.tv_back,R.id.rb_bao_financing,R.id.rb_bao_equipment,R.id.rb_bao_sanyue,
    R.id.rb_bao_liuyue,R.id.rb_bao_yinian,R.id.rb_bao_eq_sanyue,R.id.rb_bao_eq_liuyue,
    R.id.rb_bao_eq_yinian,R.id.ll_data_all,R.id.ll_data_car,R.id.ll_data_battery,R.id.ll_data_gui})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()){
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.rb_bao_financing://财务报表
                financing_view.setVisibility(View.VISIBLE);
                equipment_view.setVisibility(View.GONE);
                //站长看不见提现信息
//                if ("2".equals(SPUtils.get(SPUtils.K_USER_TYPE,""))){
//                    ll_financing_view.setVisibility(View.GONE);
//                    ll_bao_view_null.setVisibility(View.VISIBLE);
//                }else{
//                    ll_bao_view_null.setVisibility(View.GONE);
//                    ll_financing_view.setVisibility(View.VISIBLE);
//                    mPresenter.financeReport(0);
//                }
                ll_bao_view_null.setVisibility(View.GONE);
                ll_financing_view.setVisibility(View.VISIBLE);
                mPresenter.financeReport(3);
                break;
            case R.id.rb_bao_equipment://设备报表
                equipment_view.setVisibility(View.VISIBLE);
                financing_view.setVisibility(View.GONE);
                mPresenter.deviceReport(0);
                break;
            case R.id.rb_bao_sanyue://财务报表三个月
                mPresenter.financeReport(3);
                tv_title_head.setText("过去三个月的累计租金（元）");
                break;
            case R.id.rb_bao_liuyue://财务报表半年
                mPresenter.financeReport(6);
                tv_title_head.setText("过去半年的累计租金（元）");
                break;
            case R.id.rb_bao_yinian://财务报表一年
                mPresenter.financeReport(12);
                tv_title_head.setText("过去一年的累计租金（元）");
                break;
            case R.id.rb_bao_eq_sanyue://设备报表三个月
                mPresenter.deviceReport(0);
                tv_title_one.setText("过去三个月的累计车电柜数");
                break;
            case R.id.rb_bao_eq_liuyue://设备报表三个月
                mPresenter.deviceReport(1);
                tv_title_one.setText("过去半年的累计车电柜数");
                break;
            case R.id.rb_bao_eq_yinian://设备报表三个月
                mPresenter.deviceReport(2);
                tv_title_one.setText("过去一年的累计车电柜数");
                break;
            case R.id.ll_data_all://全部
                lineChart.clear();
                setViewLine(lineChartData);
                break;
            case R.id.ll_data_car://车
                lineChart.clear();
                addBatteryLine(lineChartData, "", getResources().getColor(R.color.car_bao));
                break;
            case R.id.ll_data_battery://电
                lineChart.clear();
                showLineChart(lineChartData, "", getResources().getColor(R.color.battery_bao));
                break;
            case R.id.ll_data_gui://柜
                lineChart.clear();
                addGuiLine(lineChartData, "", getResources().getColor(R.color.public_all));
                break;
        }
    }

    //-----------柱状图--------------
    /**
     * 财务报表返回数据
     * @param data
     */
    @Override
    public void financeReportCallBack(FinanceReportEntity data) {
        swipeLayout.setRefreshing(false);
        if (data.twelveMonthStats.size()>0){
            initBarChart(barChart);
            tv_bao_totalRent.setText(data.threeMonthsStats);

            //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
            LinkedHashMap<String, List<Float>> chartDataMap = new LinkedHashMap<>();
            List<String> xValues = new ArrayList<>();
            List<Float> yValue1 = new ArrayList<>();
            List<Float> yValue2 = new ArrayList<>();
            List<Integer> colors = Arrays.asList(
                    getResources().getColor(R.color.public_all), getResources().getColor(R.color.home_yellow_n)
            );

            List<FinanceReportEntity.FinanceReport> valueList = data.twelveMonthStats;
            List<FinanceReportEntity.FinanceReport> avgValueList = data.twelveMonthStats;
            for (FinanceReportEntity.FinanceReport valueBean : valueList) {
                xValues.add(valueBean.date);//时间
                yValue1.add((float) valueBean.orderPrice);//租金
            }
            for (FinanceReportEntity.FinanceReport valueAvgBean : avgValueList) {
                yValue2.add((float) valueAvgBean.commission);//佣金
            }
            chartDataMap.put("累计订单金额（元）", yValue1);
            chartDataMap.put("累计佣金（元）", yValue2);
            showBarChart(xValues, chartDataMap, colors);


            barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    //ZzRouter.gotoActivity((Activity) context, SectorMapActivity.class,e.getData());
                }

                @Override
                public void onNothingSelected() {
                }
            });
        }else{
            toast(this,"暂无报表数据");
        }
    }

    /**
     * 初始化BarChart图表
     */
    private void initBarChart(BarChart barChart) {
        /***图表设置***/
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        //显示边框
        //barChart.setDrawBorders(true);
        barChart.setDrawBorders(false);
        //设置动画效果
        barChart.animateY(1000, Easing.EasingOption.Linear);
        barChart.animateX(1000, Easing.EasingOption.Linear);

        barChart.setDoubleTapToZoomEnabled(false);
        //禁止拖拽
        barChart.setDragEnabled(false);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //禁止所有事件
        //barChart.setTouchEnabled(false);

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);

        xAxis.setDrawAxisLine(false);
//        rightAxis.setDrawAxisLine(false);
//        leftAxis.setDrawAxisLine(false);
        rightAxis.setEnabled(false);//不显示右侧Y轴

        //不显示右下角描述内容
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        barChart.setDrawGridBackground(false);
        //不显示X轴网格线
        xAxis.setDrawGridLines(false);
        //右侧Y轴网格线设置为虚线
        rightAxis.enableGridDashedLine(10f, 10f, 0f);

        /***折线图例 标签 设置***/
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }

    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(1f);
        //显示柱状图顶部值
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(10f);
        barDataSet.setValueTextColor(color);
    }

    public void showBarChart(final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists, @ColorRes List<Integer> colors) {
        List<IBarDataSet> dataSets = new ArrayList<>();
        int currentPosition = 0;//用于柱状图颜色集合的index
        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();
            for (int i = 0; i < yValueList.size(); i++) {
                //entries.add(new BarEntry(i, yValueList.get(i)));
                entries.add(new BarEntry(i, yValueList.get(i), xValues.get(i)+","+name));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);
            currentPosition++;
        }

        //X轴自定义值
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) Math.abs(value) % xValues.size());
            }
        });
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(xValues.size());
        //将X轴的值显示在中央
        xAxis.setCenterAxisLabels(true);
        //右侧Y轴自定义值（暂不自定义）
//        rightAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return ((int) (value * 100)) + "%";
//            }
//        });

        BarData data = new BarData(dataSets);
        int barAmount = dataLists.size(); //需要显示柱状图的类别 数量
        float groupSpace = 0.3f; //柱状图组之间的间距
        float barWidth = (1f - groupSpace) / barAmount;
        float barSpace = 0f;
        data.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);
    }

    @Override
    public void onRefresh() {
        //站长看不见提现信息
        if (rb_bao_financing.isChecked() == true){
            //站长看不见提现信息
            if ("2".equals(SPUtils.get(SPUtils.K_USER_TYPE,""))){
                swipeLayout.setRefreshing(false);
            }else{
                if (rb_bao_sanyue.isChecked() == true){
                    mPresenter.financeReport(3);
                }else if (rb_bao_liuyue.isChecked() == true){
                    mPresenter.financeReport(6);
                }else if (rb_bao_yinian.isChecked() == true){
                    mPresenter.financeReport(12);
                }
            }
        }else{
            if (rb_bao_eq_sanyue.isChecked() == true){
                mPresenter.deviceReport(0);
            }else if (rb_bao_eq_liuyue.isChecked() == true){
                mPresenter.deviceReport(1);
            }else if (rb_bao_eq_yinian.isChecked() == true){
                mPresenter.deviceReport(2);
            }
        }
    }

    //--------------------------折线图----------------------------
    /**
     * 设备报表返回数据
     * @param data
     */
    @Override
    public void deviceReportCallBack(DeviceReportEntity data) {
        lineChartData =data;
        swipeLayout.setRefreshing(false);
        tv_bao_eq_totalRent.setText(data.totalNum);
        if (data.report.size()>0){
            initChart(lineChart);

            if (ll_data_all.isChecked() ==true){
                setViewLine(data);
            }else if (ll_data_car.isChecked() == true){
                addBatteryLine(data, "", getResources().getColor(R.color.car_bao));
            }else if (ll_data_battery.isChecked() == true){
                showLineChart(data, "", getResources().getColor(R.color.battery_bao));
            }else if (ll_data_battery.isChecked() == true){
                addGuiLine(data, "", getResources().getColor(R.color.public_all));
            }
            setMarkerView();
        }else {
            toast(this,"暂无报表数据");
        }
    }

    private void setViewLine(DeviceReportEntity data){
        int che=0;//a
        int dian=0;//b
        int gui=0;//c
        for (int i=0;i<data.report.size();i++){
            che += data.report.get(i).carNum;
            dian += data.report.get(i).orderNum;
            gui += data.report.get(i).cabinetNum;
        }
        //showLineChart1车，showLineChart2柜，showLineChart3电
        if (che > dian){
            if (dian > gui){
                //che最大
                showLineChart1(data, "", getResources().getColor(R.color.car_bao));
                showLineChart(data, "", getResources().getColor(R.color.battery_bao));
                addGuiLine(data, "", getResources().getColor(R.color.public_all));
            }else{
                if (che > dian){
                    //che最大
                    showLineChart1(data, "", getResources().getColor(R.color.car_bao));
                    showLineChart(data, "", getResources().getColor(R.color.battery_bao));
                    addGuiLine(data, "", getResources().getColor(R.color.public_all));
                }else{
                    //gui最大
                    showLineChart2(data, "", getResources().getColor(R.color.public_all));
                    addBatteryLine(data, "", getResources().getColor(R.color.car_bao));
                    showLineChart(data, "", getResources().getColor(R.color.battery_bao));
                }
            }
        }else{
            if (gui < che){
                //dian最大
                showLineChart3(data, "", getResources().getColor(R.color.battery_bao));
                addBatteryLine(data, "", getResources().getColor(R.color.car_bao));
                addGuiLine(data, "", getResources().getColor(R.color.public_all));
            }else{
                if (gui > dian){
                    //gui最大
                    showLineChart2(data, "", getResources().getColor(R.color.public_all));
                    addBatteryLine(data, "", getResources().getColor(R.color.car_bao));
                    showLineChart(data, "", getResources().getColor(R.color.battery_bao));
                }else{
                    //dian最大
                    showLineChart3(data, "", getResources().getColor(R.color.battery_bao));
                    addBatteryLine(data, "", getResources().getColor(R.color.car_bao));
                    addGuiLine(data, "", getResources().getColor(R.color.public_all));
                }
            }
        }
    }
    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        rightYaxis.setEnabled(false);

        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);

        //不显示右下角描述内容
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);

        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //设置不显示曲线名称
        legend.setEnabled(false);
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        //lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawValues(false);//不显示文字
        lineDataSet.setDrawCircles(false);//不显示点
        //lineDataSet.setValueTextColor(color);//设置文字颜色
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     *  添加电曲线
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.orderNum);//电
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        if (ll_data_battery.isChecked() == true){
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
        }else {
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            lineChart.getLineData().addDataSet(lineDataSet);
            lineChart.invalidate();
        }
    }
    /**
     * 添加车曲线
     */
    private void addBatteryLine(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.carNum);//车
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        //设置Y轴上数据
//        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return dataList.report.get((int) (value)).carNum + "";
//            }
//        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        if (ll_data_car.isChecked() ==true){
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
        }else{
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            lineChart.getLineData().addDataSet(lineDataSet);
            lineChart.invalidate();
        }

    }
    /**
     * 添加换电柜曲线
     */
    private void addGuiLine(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.cabinetNum);//柜子
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        //设置Y轴上数据
//        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return dataList.report.get((int) (value)).cabinetNum + "";
//            }
//        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        if (ll_data_gui.isChecked() == true){
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
        }else{
            LineDataSet lineDataSet = new LineDataSet(entries, name);
            initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
            lineChart.getLineData().addDataSet(lineDataSet);
            lineChart.invalidate();
        }
    }

    /**
     * 设置线条填充背景颜色
     *
     * @param drawable
     */
    public void setChartFillDrawable(Drawable drawable) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }
    /**
     * 设置 可以显示X Y 轴自定义值的 MarkerView
     */
    public void setMarkerView() {
        LineChartMarkView mv = new LineChartMarkView(this, xAxis.getValueFormatter());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }

    //时间转换
    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    /**
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart1(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.carNum);
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }
    /**
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart2(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.cabinetNum);
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }
    /**
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart3(final DeviceReportEntity dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.report.size(); i++) {
            DeviceReportEntity.DeviceReport data = dataList.report.get(i);
            Entry entry = new Entry(i, (float) data.orderNum);
            entries.add(entry);
        }
        //设置X轴上数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String tradeDate = dataList.report.get((int) value % dataList.report.size()).statsDate;
                return formatDate(tradeDate);
            }
        });
        if (rb_bao_eq_sanyue.isChecked() == true){
            xAxis.setLabelCount(6,false);
        }else if (rb_bao_eq_liuyue.isChecked() == true){
            xAxis.setLabelCount(7,false);
        }else if (rb_bao_eq_yinian.isChecked() == true){
            xAxis.setLabelCount(8,false);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }
}
