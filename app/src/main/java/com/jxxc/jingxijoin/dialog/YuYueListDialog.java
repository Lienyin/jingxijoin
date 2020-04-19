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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.AppointmentInfoEntity;
import com.jxxc.jingxijoin.ui.yuyuebiao.SortAdapter;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;

import java.util.Map;
import java.util.Set;


/**
 * 排班
 */
public class YuYueListDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private View view;
    private ImageView iv_liji_fenpei;
    private LinearLayout ll_cancel;
    private TextView tv_yuyue_notDispatch;

    public YuYueListDialog(Context context) {
        this(context, true);
    }

    public YuYueListDialog(final Context context, boolean isLuck) {
        this.context = context;
        dialog = new Dialog(context, R.style.Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.yu_yue_list_dialog, null);
        //让dialog显示在屏幕的中间
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        //指定dialog布局的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);

        iv_liji_fenpei = view.findViewById(R.id.iv_liji_fenpei);
        tv_yuyue_notDispatch = view.findViewById(R.id.tv_yuyue_notDispatch);
        ll_cancel = view.findViewById(R.id.ll_cancel);
        iv_liji_fenpei.setOnClickListener(this);
        ll_cancel.setOnClickListener(this);

    }


    public void showShareDialog(boolean outTouchCancel,String orderNum) {
        tv_yuyue_notDispatch.setText(orderNum);
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
            case R.id.ll_cancel://关闭
                cleanDialog();
                break;
            case R.id.iv_liji_fenpei://
                cleanDialog();
                break;
        }
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener {
        void onFenxiangClick(String orderId, String technicianId);
    }
}
