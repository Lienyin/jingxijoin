package com.jxxc.jingxijoin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.utils.AnimUtils;


/**
 * Created by 31373 on 2018/5/28.
 * 密码认证
 */

public class PassWordDialog implements View.OnClickListener{

    private Context context;
    private Dialog dialog;
    private View view;
    private TextView tv_qr_cancel,tv_qr_confirm;
    private EditText et_qr_number;

    public PassWordDialog(Context context){
        this(context,true);
    }

    public PassWordDialog(final Context context, boolean isLuck) {
        this.context = context;
        dialog = new Dialog(context, R.style.Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.pass_word_dialog, null);
        //让dialog显示在屏幕的中间
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        //指定dialog布局的宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);

        et_qr_number = (EditText) view.findViewById(R.id.et_qr_number);
        tv_qr_confirm = (TextView) view.findViewById(R.id.tv_qr_confirm);
        tv_qr_cancel = (TextView) view.findViewById(R.id.tv_qr_cancel);
        tv_qr_confirm.setOnClickListener(this);
        tv_qr_cancel.setOnClickListener(this);

    }

    public void showShareDialog(boolean outTouchCancel) {
        dialog.setCanceledOnTouchOutside(outTouchCancel);
        dialog.show();
    }

    public void cleanDialog(){
        dialog.cancel();
    }

    @Override
    public void onClick(View v) {
        AnimUtils.clickAnimator(view);
        switch (v.getId()){
            case R.id.tv_qr_cancel://取消
                cleanDialog();
                break;
            case R.id.tv_qr_confirm://确定
                onFenxiangClickListener.onFenxiangClick(et_qr_number.getText().toString());
                et_qr_number.setText("");
                cleanDialog();
                break;
        }
    }
    private OnFenxiangClickListener onFenxiangClickListener;

    public void setOnFenxiangClickListener(OnFenxiangClickListener onFenxiangClickListener) {
        this.onFenxiangClickListener = onFenxiangClickListener;
    }

    public interface OnFenxiangClickListener{
        void onFenxiangClick(String password);
    }
}

