package com.jxxc.jingxijoin.ui.jishimanagement;


import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.entity.backparameter.QueryListEntity;
import com.jxxc.jingxijoin.http.ZzRouter;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.ui.addjishi.AddJishiActivity;
import com.jxxc.jingxijoin.ui.orderlist.OrderListAdapter;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class JishiManagementActivity extends MVPBaseActivity<JishiManagementContract.View, JishiManagementPresenter> implements JishiManagementContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_affirm)
    TextView tv_affirm;
    @BindView(R.id.lv_jishi_data)
    ListView lv_jishi_data;

    private JishiAdapter jishiAdapter;
    @Override
    protected int layoutId() {
        return R.layout.jishi_management_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("技师管理");
        tv_affirm.setVisibility(View.VISIBLE);
        tv_affirm.setText("+");
        mPresenter.queryList();
    }

    @OnClick({R.id.tv_back,R.id.tv_affirm})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.tv_affirm://添加技师
                ZzRouter.gotoActivity(this, AddJishiActivity.class);
                break;
            default:
        }
    }

    //技师管理返回数据
    @Override
    public void queryListCallBack(List<QueryListEntity> data) {
        jishiAdapter = new JishiAdapter(this);
        jishiAdapter.setData(data);
        lv_jishi_data.setAdapter(jishiAdapter);

        jishiAdapter.setOnFenxiangClickListener(new JishiAdapter.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(String technicianId, int type) {
                switch (type){
                    case 1:
                    case 2:
                        Intent intent = new Intent(JishiManagementActivity.this,AddJishiActivity.class);
                        intent.putExtra("type",type+"");
                        intent.putExtra("technicianId",technicianId);
                        startActivity(intent);
                        break;
                    case 3://删除

                        break;
                }
            }
        });
    }
}
