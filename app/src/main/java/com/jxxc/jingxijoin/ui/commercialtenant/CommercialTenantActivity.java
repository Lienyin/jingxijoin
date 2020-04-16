package com.jxxc.jingxijoin.ui.commercialtenant;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.ConfigApplication;
import com.jxxc.jingxijoin.R;
import com.jxxc.jingxijoin.dialog.AreaDialog;
import com.jxxc.jingxijoin.entity.backparameter.AreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.SelectAllAreaEntity;
import com.jxxc.jingxijoin.entity.backparameter.UpdateInfoEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.mvp.MVPBaseActivity;
import com.jxxc.jingxijoin.utils.AnimUtils;
import com.jxxc.jingxijoin.utils.AppUtils;
import com.jxxc.jingxijoin.utils.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.TimePicker;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CommercialTenantActivity extends MVPBaseActivity<CommercialTenantContract.View, CommercialTenantPresenter> implements CommercialTenantContract.View {

    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_commercial_name)
    EditText et_commercial_name;
    @BindView(R.id.et_faren_bank_number)
    EditText et_faren_bank_number;
    @BindView(R.id.et_faren_bank_open)
    EditText et_faren_bank_open;
    @BindView(R.id.et_faren_info)
    EditText et_faren_info;
    @BindView(R.id.et_faren_phone)
    EditText et_faren_phone;
    @BindView(R.id.tv_ssq)
    TextView tv_ssq;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_baocun_update)
    TextView tv_baocun_update;
    @BindView(R.id.rb_commercial_dd)
    RadioButton rb_commercial_dd;
    @BindView(R.id.rb_commercial_sm)
    RadioButton rb_commercial_sm;
    @BindView(R.id.ll_select_address)
    LinearLayout ll_select_address;
    @BindView(R.id.rv_ic_add)
    RecyclerView rvIcAdd;
    @BindView(R.id.rv_list_images)
    RecyclerView rvListImages;
    private List<SelectAllAreaEntity> shengList = new ArrayList<>();
    private AreaDialog areaDialog;
    private String provinceId,provinceName;
    private String cityId,cityName;
    private String districtId,districtName;
    private int timeType=0;//时间类型（0开始时间，1结束时间）
    private static final int REQUEST_CODE_CHOOSE = 110;
    private final static int SAOMA_REQUEST_CODE = 1554;
    private ImagesAdapter mImagesAdapter;
    private AddAdapter addAdapter;
    private List<String> uriList = new ArrayList<>();
    private ArrayList<String> addList = new ArrayList<>();
    private int pathListNum=0;//网站照片数量
    private int isPath=0;//0未修改，1修改


    @Override
    protected int layoutId() {
        return R.layout.commercial_tenant_activity;
    }

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);//状态栏颜色
        tv_title.setText("商家信息");
        StyledDialog.buildLoading("加载中").setActivity(this).show();
        mPresenter.getUserInfo();
        mPresenter.initImageSelecter();

        areaDialog = new AreaDialog(this);
        areaDialog.setOnFenxiangClickListener(new AreaDialog.OnFenxiangClickListener() {
            @Override
            public void onFenxiangClick(int pId, String pName, int cId,String cName, int dId, String dName) {
                provinceId = pId+"";
                cityId = cId+"";
                districtId = dId+"";
                provinceName = pName;
                cityName = cName;
                districtName = dName;
                tv_ssq.setText(provinceName+"/"+cityName+"/"+districtName);
            }
        });
    }

    public void initView(){
        //图片适配器
        rvListImages.setLayoutManager(new GridLayoutManager(this, 4));
        mImagesAdapter = new ImagesAdapter(R.layout.item_malfunction_repair_image, uriList);
        rvListImages.setAdapter(mImagesAdapter);
        //加号
        if (mImagesAdapter.getData().size()<3) {
            addList.clear();
            addList.addAll(mImagesAdapter.getData());
            addList.add("");
        }
        rvIcAdd.setLayoutManager(new GridLayoutManager(this, 4));
        addAdapter = new AddAdapter(R.layout.item_malfunction_repair_add, addList,"");
        rvIcAdd.setAdapter(addAdapter);
        addAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_item) {
                    mPresenter.gotoImageSelect(CommercialTenantActivity.this, REQUEST_CODE_CHOOSE);
                }else if (view.getId() == R.id.fl_delete) {
                    mImagesAdapter.remove(position);
                    if (mImagesAdapter.getData().size()<=3) {
                        addList.clear();
                        addList.addAll(mImagesAdapter.getData());
                        addList.add("");
                    }
                    pathListNum = mImagesAdapter.getData().size();
                    addAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @OnClick({R.id.tv_back,R.id.rb_commercial_dd,R.id.rb_commercial_sm,R.id.ll_select_address,
    R.id.tv_start_time,R.id.tv_end_time,R.id.tv_baocun_update})
    public void onViewClicked(View view) {
        AnimUtils.clickAnimator(view);
        switch (view.getId()) {
            case R.id.tv_back://返回
                finish();
                break;
            case R.id.rb_commercial_dd://到店服务
                //finish();
                break;
            case R.id.rb_commercial_sm://上门洗车
                //finish();
                break;
            case R.id.ll_select_address://选择省市区
                StyledDialog.buildLoading("加载中").setActivity(this).show();
                mPresenter.selectAllArea();
                break;
            case R.id.tv_start_time://开始时间
                timeType = 0;
                getTime();
                break;
            case R.id.tv_end_time://结束时间
                timeType = 1;
                getTime();
                break;
            case R.id.tv_baocun_update://保存修改
                StyledDialog.buildLoading("正在修改").setActivity(this).show();
                mPresenter.updateInfo(
                        et_commercial_name.getText().toString().trim(),
                        "",
                        "",
                        "",
                        "",
                        et_faren_info.getText().toString().trim(),
                        et_faren_phone.getText().toString().trim(),
                        tv_start_time.getText().toString().trim(),
                        tv_end_time.getText().toString().trim(),
                        provinceId,
                        cityId,
                        districtId);
                break;
            default:
        }
    }

    //信息接口返回数据
    @Override
    public void getUserInfoCallBack(UserInfoEntity data) {
        et_commercial_name.setText(data.companyName);
        tv_ssq.setText(data.province+"/"+data.city+"/"+data.district);
        tv_start_time.setText(data.officeTime);
        tv_end_time.setText(data.closingTime);
        et_faren_info.setText(data.contacts);
        et_faren_phone.setText(data.contactsNumber);
        et_faren_bank_number.setText(data.bankCardNumber);
        et_faren_bank_open.setText(data.bankName);

        //设置站点图片
        final ArrayList<String> path3 = new ArrayList<>();
        String siteImg1 = data.imgUrl;
//        String siteImg2 = data.imgUrl;
//        String siteImg3 = data.imgUrl;
        //https:/boss.zhizukj.com/ydb/admin/manage/file/download?fileName=2019-11-30T14:29:21.067Z-1575095361067.jpg
        if (!AppUtils.isEmpty(siteImg1)){
            path3.add(siteImg1);
        }
//        if (!AppUtils.isEmpty(siteImg2)){
//            path3.add(siteImg2);
//        }
//        if (!AppUtils.isEmpty(siteImg3)){
//            path3.add(siteImg3);
//        }
        pathListNum = path3.size();
        mImagesAdapter.setNewData(path3);
        if (mImagesAdapter.getData().size()<=3) {
            addList.clear();
            addList.addAll(mImagesAdapter.getData());
            addList.add("");
        }
        addAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectAllAreaCallBack(List<AreaEntity> data) {
        areaDialog.showShareDialog(true,data);
    }

    //修改成功返回数据
    @Override
    public void updateInfoCallBack() {
        finish();
    }

    //上传文件返回数据
    @Override
    public void uploadImageCallBack(UpdateInfoEntity data) {

    }

    //时间选择器
    private void getTime(){
        //获取当前时间日期格式
        TimePicker timePicker = new TimePicker(this,TimePicker.HOUR_24);//时分
        timePicker.setRangeStart(00,00);//开始的时分
        timePicker.setRangeEnd(23,59);//结束的时分
        timePicker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                if (timeType == 0){
                    tv_start_time.setText(hour + ":" + minute);
                }else{
                    tv_end_time.setText(hour + ":" + minute);
                }
            }
        });
        timePicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            final List<String> pathList = data.getStringArrayListExtra("result");
            final ArrayList<String> path2 = new ArrayList<>();
            Luban.with(this)
                    .load(pathList)
                    .ignoreBy(50)
                    .setTargetDir(ConfigApplication.CACHA_URL)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
//                            StyledDialog.buildLoading("正在压缩图片").show();
                        }

                        @Override
                        public void onSuccess(File file) {
                            String absolutePath = file.getAbsolutePath();
                            path2.add(absolutePath);
                            pathListNum = path2.size();//获取照片数组大小
                            isPath = 1;//修改了照片数组
                            mImagesAdapter.setNewData(path2);
                            if (mImagesAdapter.getData().size()<=3) {
                                addList.clear();
                                addList.addAll(mImagesAdapter.getData());
                                addList.add("");
                            }
                            addAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mImagesAdapter.setNewData(pathList);
                            if (mImagesAdapter.getData().size()<=3) {
                                addList.clear();
                                addList.addAll(mImagesAdapter.getData());
                                addList.add("");
                            }
                            addAdapter.notifyDataSetChanged();
                        }
                    }).launch();    //启动压缩

        }
    }
}
