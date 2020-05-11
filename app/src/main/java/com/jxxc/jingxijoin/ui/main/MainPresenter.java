package com.jxxc.jingxijoin.ui.main;

import android.content.Context;

import com.hss01248.dialog.StyledDialog;
import com.jxxc.jingxijoin.BuildConfig;
import com.jxxc.jingxijoin.dialog.UpdataHintDialog;
import com.jxxc.jingxijoin.dialog.UpdateProgressDialog;
import com.jxxc.jingxijoin.entity.backparameter.LatestVersionEntity;
import com.jxxc.jingxijoin.entity.backparameter.UserInfoEntity;
import com.jxxc.jingxijoin.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.jxxc.jingxijoin.Api;
import com.jxxc.jingxijoin.ConfigApplication;
import com.jxxc.jingxijoin.http.EventCenter;
import com.jxxc.jingxijoin.http.HttpResult;
import com.jxxc.jingxijoin.http.JsonCallback;
import com.jxxc.jingxijoin.mvp.BasePresenterImpl;
import com.jxxc.jingxijoin.utils.AppUtils;
import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadStatus;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{

    private UpdataHintDialog dialog;

    @Override
    protected void onEventComing(EventCenter center) {

    }

    @Override
    public void latestVersion(int type) {
        OkGo.<HttpResult<LatestVersionEntity>>post(Api.LATEST_VERSION)
                .params("type",type)
                .execute(new JsonCallback<HttpResult<LatestVersionEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<LatestVersionEntity>> response) {
                        LatestVersionEntity version = response.body().data;
                        if (response.body().code == 0){
                            SPUtils.put(SPUtils.K_FILE_URL,version.staticUrl);
                            String url = "http://"+version.url;
                            String memo = version.memo;
                            String ver = version.version;
                            if (!AppUtils.isEmpty(version)) {
                                if (ver.contains(".")) {
                                    String vOnline = ver.replace(".", "").trim();
                                    String versionName = BuildConfig.VERSION_NAME;
                                    String vLoal = versionName.replace(".", "").trim();
                                    if (Integer.parseInt(vOnline) > Integer.parseInt(vLoal)) {
                                        if (version.isForce == 1) {//是否强制更新
                                            updateAPK(url, memo, true,ver);
                                        } else {
                                            updateAPK(url, memo, false,ver);
                                        }
                                    }else{
                                        toast(mContext,"已是最新版");
                                    }
                                }
                            }
                            //mView.latestVersionCallBack();
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 用户信息
     */
    @Override
    public void getUserInfo() {
        OkGo.<HttpResult<UserInfoEntity>>post(Api.USER_INFO)
                .tag(this)
                .execute(new JsonCallback<HttpResult<UserInfoEntity>>() {
                    @Override
                    public void onSuccess(Response<HttpResult<UserInfoEntity>> response) {
                        StyledDialog.dismissLoading();
                        UserInfoEntity userInfoEntity = response.body().data;
                        if (response.body().code == 0){
                            mView.getUserInfoCallBack(userInfoEntity);
                        }else{
                            toast(mContext,response.body().message);
                        }
                    }
                });
    }

    /**
     * 下载apk并安装
     */
    public void updateAPK(final String url, String memo, boolean isMust, String versions) {
        if (!AppUtils.isEmpty(mView)) {
            mView.updateCB(isMust);
        }
        if (isMust) {
            String msg = null;
            if (!AppUtils.isEmpty(memo)) {
                msg = memo+ "\n如不升级将退出应用";
            } else {
                msg = "如不升级将退出应用";
            }
            dialog = new UpdataHintDialog(mContext);
            dialog.showShareDialog(false, msg, "退出应用",versions);
            dialog.setOnFenxiangClickListener(new UpdataHintDialog.OnFenxiangClickListener() {
                @Override
                public void onFenxiangClick(int shareType) {
                    if (shareType == 1) {
                        if (!AppUtils.isEmpty(url)) {  //开启更新
                            startDownloadAPK(url);
                        }
                    }else{
                        ConfigApplication.exit(true);
                    }
                }
            });
        } else {
            String msg = null;
            if (!AppUtils.isEmpty(memo)) {
                msg = memo;
            } else {
                msg = "有更好的版本等着你，快更新吧！";
            }
            dialog = new UpdataHintDialog(mContext);
            dialog.showShareDialog(true, msg, "暂不更新",versions);
            dialog.setOnFenxiangClickListener(new UpdataHintDialog.OnFenxiangClickListener() {
                @Override
                public void onFenxiangClick(int shareType) {
                    if (shareType == 1) {
                        if (!AppUtils.isEmpty(url)) {  //开启更新
                            startDownloadAPK(url);
                        }
                    }else{
                        dialog.cleanDialog();
                    }
                }
            });
        }

    }

    /**
     * 安装apk
     *
     * @param url
     */
    private void startDownloadAPK(String url) {
        UpdateProgressDialog.show(mView.getContext());
        final RxDownload mRxDownload = RxDownload.getInstance(mView.getContext());
        mRxDownload.download(url, "jingxi_hehuoren.apk", ConfigApplication.CACHA_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DownloadStatus>() {
                    @Override
                    public void accept(DownloadStatus downloadStatus) throws Exception {

                        double v = (double) downloadStatus.getDownloadSize() / downloadStatus.getTotalSize();
                        int progress = (int) (v * 100);
                        UpdateProgressDialog.setProgress(progress,
                                downloadStatus.getFormatDownloadSize() + "/" + downloadStatus.getFormatTotalSize(),
                                downloadStatus.getDownloadSize());
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        toast(mContext, "下载失败");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (!AppUtils.isEmpty(mView)) {
                            //下载成功
                            UpdateProgressDialog.dismiss();
                            File file = mRxDownload.getRealFiles("jingxi_hehuoren.apk", ConfigApplication.CACHA_URL)[0];
                            Context context = mView.getContext().getApplicationContext();
                            AppUtils.installApk(context, file, BuildConfig.APPLICATION_ID + ".provider");
                        }

                    }
                });
    }
}
