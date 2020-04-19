package com.jxxc.jingxijoin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;


/**
 * 排班
 */
public class GuanJianDialog implements View.OnClickListener {

    private Context context;
    private Dialog dialog;
    private View view;
    private LinearLayout ll_commti;
    private EditText et_max_num,et_fen_yong;

    public GuanJianDialog(Context context) {
        this(context, true);
    }

    public GuanJianDialog(final Context context, boolean isLuck) {
        this.context = context;
        dialog = new Dialog(context, R.style.Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.guan_jian_dialog, null);
        //让dialog显示在屏幕的中间
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        //指定dialog布局的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);

        et_fen_yong = view.findViewById(R.id.et_fen_yong);
        et_max_num = view.findViewById(R.id.et_max_num);

        ll_commti = view.findViewById(R.id.ll_commti);
        ll_commti.setOnClickListener(this);

    }

    public void showShareDialog(boolean outTouchCancel) {
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
            case R.id.ll_commti://提交
                if (AppUtils.isEmpty(et_fen_yong.getText().toString().trim())){
                    Toast.makeText(context,"请输入数量",Toast.LENGTH_SHORT).show();
                }else if (AppUtils.isEmpty(et_max_num.getText().toString().trim())){
                    Toast.makeText(context,"请输入分佣百分比",Toast.LENGTH_SHORT).show();
                }else{
                    onFenxiangClickListener.onFenxiangClick(et_fen_yong.getText().toString().trim(),
                            et_max_num.getText().toString().trim());
                    cleanDialog();
                }
                break;
        }
    }

    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener {
        void onFenxiangClick(String maxNum, String fenYong);
    }
}
