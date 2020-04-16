package com.jxxc.jingxijoin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.adapter.Area1Adapter;
import com.jxxc.jingxijoin.adapter.Area2Adapter;
import com.jxxc.jingxijoin.adapter.Area3Adapter;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 31373 on 2018/5/28.
 */

public class AreaDialog {

    private Context context;
    private Dialog dialog;
    private View view;
    private ListView lv_site1;
    private ListView lv_site2;
    private ListView lv_site3;
    private Area1Adapter area1Adapter;
    private Area2Adapter area2Adapter;
    private Area3Adapter area3Adapter;
    private List<AreaEntity> list = new ArrayList<>();
    private List<AreaEntity.Citys> listSite = new ArrayList<>();
    private List<AreaEntity.Citys.Districts> listSiteData = new ArrayList<>();
    private int provinceId;
    private String provinceName;
    private int cityId;
    private String cityName;
    private int districtId;
    private String districtName;

    public AreaDialog(Context context){
        this(context,true);
    }

    public AreaDialog(final Context context, boolean isLuck) {
        this.context = context;
        dialog = new Dialog(context, R.style.Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.site_plus_dialog, null);
        //让dialog显示在屏幕的中间
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //指定dialog布局的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);
        lv_site1 = view.findViewById(R.id.lv_site1);
        lv_site2 = view.findViewById(R.id.lv_site2);
        lv_site3 = view.findViewById(R.id.lv_site3);

        area1Adapter = new Area1Adapter(context);
        area1Adapter.setData(list);
        lv_site1.setAdapter(area1Adapter);

        area2Adapter = new Area2Adapter(context);
        area2Adapter.setData(listSite);
        lv_site2.setAdapter(area2Adapter);

        area3Adapter = new Area3Adapter(context);
        area3Adapter.setData(listSiteData);
        lv_site3.setAdapter(area3Adapter);

        //一级设置二级站点
        lv_site1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listSite = list.get(position).citys;//二级站点
                listSiteData = listSite.get(0).districts;//直选三级站点(默认第一个)
                cityId = listSite.get(0).name;
                cityName = listSite.get(0).value;

                provinceId = list.get(position).name;
                provinceName = list.get(position).value;
                area2Adapter.setData(list.get(position).citys);
                area2Adapter.notifyDataSetChanged();

                area3Adapter.setData(listSite.get(0).districts);
                area3Adapter.notifyDataSetChanged();
            }
        });
        //二级设置三级站点
        lv_site2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listSiteData = listSite.get(position).districts;//三级站点
                cityId = listSite.get(position).name;
                cityName = listSite.get(position).value;
                area3Adapter.setData(listSite.get(position).districts);
                area3Adapter.notifyDataSetChanged();
            }
        });
        //三级选站点
        lv_site3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                districtId = listSiteData.get(position).name;
                districtName = listSiteData.get(position).value;
                onFenxiangClickListener.onFenxiangClick(provinceId, provinceName,cityId,cityName,districtId,districtName);
                cleanDialog();
            }
        });
    }

    public void showShareDialog(boolean outTouchCancel,List<AreaEntity> siteData) {
        if (list.size()>0){list.clear();}
        list.addAll(siteData);//一级
        listSite = siteData.get(0).citys;//二级
        listSiteData = siteData.get(0).citys.get(0).districts;//三级

        provinceId = list.get(0).name;
        provinceName = list.get(0).value;
        area1Adapter.setData(list);
        area1Adapter.notifyDataSetChanged();

        cityId = listSite.get(0).name;
        cityName = listSite.get(0).value;
        area2Adapter.setData(listSite);
        area2Adapter.notifyDataSetChanged();

        districtId = listSiteData.get(0).name;
        districtName = listSiteData.get(0).value;
        area3Adapter.setData(listSiteData);
        area3Adapter.notifyDataSetChanged();

        dialog.setCanceledOnTouchOutside(outTouchCancel);
        dialog.show();
    }

    public void cleanDialog(){
        dialog.cancel();
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener{
        void onFenxiangClick(int provinceId, String provinceName, int cityId, String cityName, int districtId, String districtName);
    }
}

