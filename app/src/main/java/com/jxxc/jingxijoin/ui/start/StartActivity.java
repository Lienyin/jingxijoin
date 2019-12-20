package com.jxxc.jingxijoin.ui.start;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.ui.login.LoginActivity;
import com.jxxc.jingxijoin.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StartActivity extends Activity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        //轮询器
        timer = new Timer();
        TimerTask task =new TimerTask(){
            public void run(){
                ZzRouter.gotoActivity(StartActivity.this, MainActivity.class);
            }
        };
        timer.schedule(task,2000);//延迟2秒启动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
