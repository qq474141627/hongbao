package com.opar.mobile.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.opar.mobile.data.AdConfig;
import com.opar.mobile.data.BannerAdConfig;
import com.opar.mobile.data.UpdateAppConfig;
import com.opar.mobile.inteface.ICallBack;
import com.opar.mobile.view.dialog.CommonDialog;
import com.opar.mobile.view.dialog.WonderfulDialog;
import com.umeng.onlineconfig.OnlineConfigAgent;

public class UmUtil {

    public static void toAd(Activity context, ICallBack<BannerAdConfig> iCallBack){
        //广告
        String AdConfigJson = OnlineConfigAgent.getInstance().getConfigParams(context,"AdConfig");
        if(TextUtils.isEmpty(AdConfigJson))
            return;
        LogUtils.e(AdConfigJson);
        try {
            AdConfig adConfig = JSON.parseObject(AdConfigJson,AdConfig.class);
            if(adConfig.isBannerAdOn() && adConfig.getBannerAd() != null){
                iCallBack.onSuccess(adConfig.getBannerAd());
            }
            if(adConfig.isWonderAdOn() && adConfig.getWonderAd() != null){
                new WonderfulDialog(context).showDialog(adConfig.getWonderAd());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void toUpdate(final Context context){
        //升级提示
        String UpdateAppConfigJson = OnlineConfigAgent.getInstance().getConfigParams(context,"UpdateAppConfig");
        if(TextUtils.isEmpty(UpdateAppConfigJson)){
            return;
        }
        LogUtils.e(UpdateAppConfigJson);
        try {
            final UpdateAppConfig updateAppConfig = JSON.parseObject(UpdateAppConfigJson,UpdateAppConfig.class);
            if(updateAppConfig != null){
                int localVersion = PhoneUtil.getLocalVersion(context);
                //无需更新
                if(localVersion == updateAppConfig.getVersion() || localVersion == 0)
                    return;
                //需要更新
                new CommonDialog.Builder(context)
                        .setTitle(updateAppConfig.getTitle())
                        .setMessage(updateAppConfig.getMessage())
                        .setConfirmButton("升级", new CommonDialog.OnButtonClickListener() {
                            @Override
                            public void onButtonClick(Dialog dialog) {
                                try{
                                    Uri uri = Uri.parse(updateAppConfig.getLink());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    context.startActivity(intent);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setCancelButton("取消", null)
                        .create().show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
