package com.jxxc.jingxijoin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.ui.yuyuebiao.SortAdapter;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 排班
 */
public class SortDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private View view;
    private ListView lv_pai_ban_data;
    private Button btn_queding_paiban,btn_xi_che_cancel;
    private AppointmentInfoEntity appointmentInfoEntity;
    private SortAdapter sortAdapter;

    public SortDialog(Context context) {
        this(context, true);
    }

    public SortDialog(final Context context, boolean isLuck) {
        this.context = context;
        dialog = new Dialog(context, R.style.Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.activity_dialog, null);
        //让dialog显示在屏幕的中间
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        //指定dialog布局的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);

        btn_xi_che_cancel = view.findViewById(R.id.btn_xi_che_cancel);
        btn_queding_paiban =  view.findViewById(R.id.btn_queding_paiban);
        lv_pai_ban_data =  view.findViewById(R.id.lv_pai_ban_data);
        btn_xi_che_cancel.setOnClickListener(this);
        btn_queding_paiban.setOnClickListener(this);

    }


    public void showShareDialog(boolean outTouchCancel, AppointmentInfoEntity data,
                                String orderId,String oIdTime) {
        appointmentInfoEntity = data;
        if (!AppUtils.isEmpty(orderId)){
            sortAdapter = new SortAdapter(context);
            sortAdapter.setData(orderId,data.carport,data.tech,oIdTime);
            lv_pai_ban_data.setAdapter(sortAdapter);
        }else{
            if (data.order.size()>0){
                sortAdapter = new SortAdapter(context);
                sortAdapter.setData(data.order,data.carport,data.tech);
                lv_pai_ban_data.setAdapter(sortAdapter);
            }
        }

        dialog.setCanceledOnTouchOutside(outTouchCancel);
        dialog.show();
    }

    public void cleanDialog() {
        dialog.cancel();
    }

    @Override
    public void onClick(View v) {
        AnimUtils.clickAnimator(view);
        switch (v.getId()) {
            case R.id.btn_xi_che_cancel://关闭
                cleanDialog();
                break;
            case R.id.btn_queding_paiban://确定排班
//                Set<Map.Entry<Integer, String>> carId = sortAdapter.getCarId().entrySet();
//                for (Map.Entry<Integer, String> me : carId){
//                    // 根据键值对对象获取键和值
//                    Integer key = me.getKey();
//                    String value = me.getValue();
//                    Log.i("TAG",key + "-@--" + value);
//                }

                Set<Map.Entry<String, String>> techId = sortAdapter.getTechId().entrySet();
                for (Map.Entry<String, String> me : techId){
                    // 根据键值对对象获取键和值
                    String key = me.getKey();
                    String value = me.getValue();
                    Log.i("TAG",key + "---" + value);
                    onFenxiangClickListener.onFenxiangClick(key,value);
                }
                cleanDialog();
                break;
        }
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener {
        void onFenxiangClick(String orderId,String technicianId);
    }
}
