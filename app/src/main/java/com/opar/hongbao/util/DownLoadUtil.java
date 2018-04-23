package com.opar.hongbao.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.webkit.MimeTypeMap;

import com.opar.hongbao.R;
import com.opar.hongbao.data.AdConfig;
import com.umeng.commonsdk.statistics.common.MLog;

import java.io.File;
import java.util.List;

public class DownLoadUtil {

    private static DownLoadUtil instance;

    private DownloadManager downloadManager;

    public static DownLoadUtil getInstance(){
        if(instance == null){
            instance = new DownLoadUtil();
        }
        return instance;
    }

    /**
     * 判断有没有安装该apk
     * @param packageName
     * @param context
     * @return
     */
    private boolean isAvilible(Context context,String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * 弹窗提示用户安装与否
     * @param adConfig
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showInstallApkOrNo(final Activity context, final AdConfig adConfig){
        if(adConfig == null || !adConfig.isAd())
            return;
        //已经下载
        if(SharedPreferencesUtil.getBoolean(context,"hasDownLoad"))
            return;
        if (!isAvilible(context ,adConfig.getPkgName())) {
            Dialog builder = new AlertDialog.Builder(context)
                    .setTitle("功能解锁提示")
                    .setMessage(context.getResources().getString(R.string.app_lock_tip))
                    .setIcon(R.mipmap.ic_launcher)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("去下载", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setData(Uri.parse(adConfig.getAddress()));
                            context.startActivity(intent);
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            context.finish();
                        }
                    })
                    .create();
            builder.show();
        }else{
            //下载过就存下来标记
            SharedPreferencesUtil.saveBoolean(context,"hasDownLoad",true);
        }
    }

}
