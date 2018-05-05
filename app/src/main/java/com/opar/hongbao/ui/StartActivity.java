package com.opar.hongbao.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.opar.hongbao.Config;
import com.opar.hongbao.R;
import com.opar.hongbao.service.LuckyMoneyNotificationService;
import com.opar.hongbao.service.LuckyMoneyService;
import com.opar.hongbao.util.EventBusMsg;
import com.opar.hongbao.util.ISuccessCallBack;
import com.opar.hongbao.util.SharedPreferencesUtil;
import com.opar.mobile.base.BaseActivity;
import com.opar.mobile.data.BannerAdConfig;
import com.opar.mobile.inteface.ICallBack;
import com.opar.mobile.utils.ToastUtil;
import com.opar.mobile.utils.UmUtil;
import com.opar.mobile.view.NewsView;
import com.opar.mobile.view.SwitchView;
import com.umeng.onlineconfig.OnlineConfigAgent;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class StartActivity extends BaseActivity{

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.tv_total_count)
    TextView tvTotalCount;
    @BindView(R.id.tv_wechat_cout)
    TextView tvWechatCout;
    @BindView(R.id.tv_qq_cout)
    TextView tvQqCout;
    @BindView(R.id.switch_service)
    SwitchView switchService;
    @BindView(R.id.switch_notification)
    SwitchView switchNotification;
    @BindView(R.id.ll_guide)
    LinearLayout llGuide;
    @BindView(R.id.switch_wechat)
    SwitchView switchWechat;
    @BindView(R.id.switch_qq)
    SwitchView switchQq;
    @BindView(R.id.ll_wechat_mode)
    LinearLayout llWechatMode;
    @BindView(R.id.tv_wechat_mode)
    TextView tvWechatMode;
    @BindView(R.id.ll_wechat_delay)
    LinearLayout llWechatDelay;
    @BindView(R.id.tv_wechat_delay)
    TextView tvWechatDelay;
    @BindView(R.id.switch_wechat_screen)
    SwitchView switchWechatScreen;
    @BindView(R.id.llBanner)
    LinearLayout llBanner;

    boolean changeByUser = true;
    private final String[] WXModels = new String[] { "自动抢抢全部红包", "只自动抢单聊红包" ,"只自动抢群聊红包","只通知手动抢"};
    private final String[] delays = new String[] { "不延迟", "防作弊延迟0.2秒" ,"防作弊延迟0.5秒","防作弊延迟1秒"};
    private final Integer[] delayTimes = new Integer[] { 0, 200 ,500,1000};

    private int selectWXModel = Config.WX_MODE_0;//当前选中的模式
    private int selectWXDelay = 0;//当前选中的延时模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setTitle(R.string.app_name);
        setLeftBtn(false,null);
        EventBus.getDefault().register(this);
//        OnlineConfigAgent.getInstance().setDebugMode(true);
        OnlineConfigAgent.getInstance().updateOnlineConfig(this);
        switchService.setOnSwitchStateChangeListener(new SwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (!changeByUser) {
                    changeByUser = true;
//                    return;
                }
                if (isOn && !LuckyMoneyService.isRunning()) {
                    openAccessibilityServiceSettings();
                }
            }
        });
        switchNotification.setOnSwitchStateChangeListener(new SwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (!changeByUser) {
                    changeByUser = true;
//                    return;
                }
                if(isOn && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Toast.makeText(StartActivity.this, "该功能只支持安卓4.3以上的系统", Toast.LENGTH_SHORT).show();
                    return;
                }
                Config.getConfig(context).setNotificationServiceEnable(isOn);
                if (isOn && !LuckyMoneyNotificationService.isRunning())
                    openNotificationServiceSettings();
            }
        });
        switchWechat.setOnSwitchStateChangeListener(new SwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                Config.getConfig(context).setEnableWechat(isOn);
            }
        });
        switchWechatScreen.setOnSwitchStateChangeListener(new SwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                Config.getConfig(context).setWechatScreen(isOn);
            }
        });
        switchQq.setOnSwitchStateChangeListener(new SwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (!changeByUser) {
                    changeByUser = true;
//                    return;
                }
                if (isOn) {
                    changeByUser = false;
                    switchQq.setEnabled(false);
                    ToastUtil.showToast(context, "暂未开通，敬请期待");
                }
            }
        });
        getUMConfig();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();
        if (LuckyMoneyService.isRunning()) {
            switchService.setOn(true);
        }else {
            switchService.setOn(false);
        }
        boolean enable = Config.getConfig(this).isEnableNotificationService();
        boolean running = LuckyMoneyService.isNotificationServiceRunning();
        if (enable && running && !switchNotification.isOn()) {
            changeByUser = false;
            switchNotification.setOn(true);
        } else if((!enable || !running) && switchNotification.isOn()) {
            changeByUser = false;
            switchNotification.setOn(false);
        }
        updateCount();
        updateEnableStatus();
    }

    private void updateCount() {
        int wechatSum = SharedPreferencesUtil.getInt(this, Config.KEY_WECHAT_SUM);
        int qqSum = SharedPreferencesUtil.getInt(this, Config.KEY_QQ_SUM);
        tvWechatCout.setText("微信红包："+wechatSum+"个");
        tvQqCout.setText("QQ红包："+qqSum+"个");

        float amount = SharedPreferencesUtil.getDouble(this, Config.KEY_WECHAT_AMOUNT);
        tvTotalCount.setText(amount+"元");
    }

    private void updateEnableStatus() {
        switchWechat.setOn(SharedPreferencesUtil.getBoolean(this, Config.KEY_WECHAT_ENABLE));
        switchQq.setOn(SharedPreferencesUtil.getBoolean(this, Config.KEY_QQ_ENABLE));
        setWechatModel(Config.getConfig(this).getWechatMode());
        setWechatDelay(Arrays.asList(delayTimes).indexOf(Config.getConfig(this).getWechatOpenDelayTime()));
        switchWechatScreen.setOn(SharedPreferencesUtil.getBoolean(this, Config.KEY_WECHAT_SCREEN));
    }

    @OnClick(R.id.ll_guide)
    public void onGuide() {
        Intent itGuide = new Intent(this, GuideActivity.class);
        startActivity(itGuide);
    }

    @OnClick(R.id.tv_wechat_mode)
    public void onWechatModel() {
        getModelDialog("请选择微信抢红包模式", WXModels, selectWXModel, new ISuccessCallBack<Integer>() {
            @Override
            public void onSuccess(Integer which) {
                setWechatModel(which);
            }
        });
    }

    @OnClick(R.id.tv_wechat_delay)
    public void onWechatDelay() {
        getModelDialog("请选择微信抢红包延迟时间", delays, selectWXDelay, new ISuccessCallBack<Integer>() {
            @Override
            public void onSuccess(Integer which) {
                setWechatDelay(which);
            }
        });
    }

    /** 打开辅助服务的设置*/
    public void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, R.string.notification_setting_tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 打开通知栏设置*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public void openNotificationServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, R.string.notification_setting_tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onEventMainThread(EventBusMsg msg) {
        switch (msg.getType()) {
            case EventBusMsg.ACCESSIBILITY_CONNECTED:
                Toast.makeText(getApplicationContext(), "已成功连接服务", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchService.setOn(true);
                break;
            case EventBusMsg.ACCESSIBILITY_DISCONNECTED:
                Toast.makeText(getApplicationContext(), "已断开连接", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchService.setOn(false);
                break;
            case EventBusMsg.NOTIFICATION_CONNECTED:
                Toast.makeText(getApplicationContext(), "正在监听通知栏", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchNotification.setOn(true);
                break;
            case EventBusMsg.NOTIFICATION_DISCONNECTED:
                Toast.makeText(getApplicationContext(), "已停止监听通知栏", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchNotification.setOn(false);
                break;
            default:
                break;
        }
    }

    private void getModelDialog(String title, String[] items, int select, final ISuccessCallBack callBack){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(title);
        builder.setSingleChoiceItems(items, select, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                callBack.onSuccess(which);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void setWechatModel(int which){
        selectWXModel = which;
        tvWechatMode.setText(WXModels[which]);
        Config.getConfig(this).setWechatMode(which);
    }

    private void setWechatDelay(int which){
        selectWXDelay = which;
        tvWechatDelay.setText(delays[which]);
        Config.getConfig(this).setWechatOpenDelayTime(delayTimes[which]);
    }

    private void getUMConfig(){
        UmUtil.toAd(this, new ICallBack<BannerAdConfig>() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(BannerAdConfig bannerAdConfig) {
                llBanner.addView(new NewsView(context).setConfig(bannerAdConfig));
                llBanner.setVisibility(View.VISIBLE);
            }
        });
        UmUtil.toUpdate(this);
    }

}
