package com.opar.hongbao.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.opar.hongbao.Config;
import com.opar.hongbao.R;
import com.opar.hongbao.data.AdConfig;
import com.opar.hongbao.service.LuckyMoneyNotificationService;
import com.opar.hongbao.service.LuckyMoneyService;
import com.opar.hongbao.util.DownLoadUtil;
import com.opar.hongbao.util.EventBusMsg;
import com.opar.hongbao.util.ISuccessCallBack;
import com.opar.hongbao.util.SharedPreferencesUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.OnlineConfigLog;
import com.umeng.onlineconfig.UmengOnlineConfigureListener;

import org.json.JSONObject;

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

public class StartActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.tv_total_count)
    TextView tvTotalCount;
    @BindView(R.id.tv_wechat_cout)
    TextView tvWechatCout;
    @BindView(R.id.tv_qq_cout)
    TextView tvQqCout;
    @BindView(R.id.switch_service)
    SwitchCompat switchService;
    @BindView(R.id.switch_notification)
    SwitchCompat switchNotification;
    @BindView(R.id.tv_guide)
    TextView tvGuide;
    @BindView(R.id.switch_wechat)
    SwitchCompat switchWechat;
    @BindView(R.id.switch_qq)
    SwitchCompat switchQq;
    @BindView(R.id.ll_wechat_mode)
    LinearLayout llWechatMode;
    @BindView(R.id.tv_wechat_mode)
    TextView tvWechatMode;
    @BindView(R.id.ll_wechat_delay)
    LinearLayout llWechatDelay;
    @BindView(R.id.tv_wechat_delay)
    TextView tvWechatDelay;


    boolean changeByUser = true;
    private final String[] WXModels = new String[] { "自动抢抢全部红包", "只自动抢单聊红包" ,"只自动抢群聊红包","只通知手动抢"};
    private final String[] delays = new String[] { "不延迟", "延迟0.2秒" ,"延迟0.5秒","延迟1秒"};
    private final Integer[] delayTimes = new Integer[] { 0, 200 ,500,1000};

    private int selectWXModel = Config.WX_MODE_0;//当前选中的模式
    private int selectWXDelay = 0;//当前选中的延时模式
    private AdConfig adConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        EventBus.getDefault().register(this);
//        OnlineConfigAgent.getInstance().setDebugMode(true);
        OnlineConfigAgent.getInstance().updateOnlineConfig(this);
        switchService.setOnCheckedChangeListener(this);
        switchNotification.setOnCheckedChangeListener(this);
        switchWechat.setOnCheckedChangeListener(this);
        switchQq.setOnCheckedChangeListener(this);
        getUMConfig();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();
        DownLoadUtil.getInstance().showInstallApkOrNo(this,adConfig);
        if (LuckyMoneyService.isRunning()) {
            switchService.setChecked(true);
        }else {
            switchService.setChecked(false);
        }
        boolean enable = Config.getConfig(this).isEnableNotificationService();
        boolean running = LuckyMoneyService.isNotificationServiceRunning();
        if (enable && running && !switchNotification.isChecked()) {
            changeByUser = false;
            switchNotification.setChecked(true);
        } else if((!enable || !running) && switchNotification.isChecked()) {
            changeByUser = false;
            switchNotification.setChecked(false);
        }
        setWechatModel(Config.getConfig(this).getWechatMode());
        setWechatDelay(Arrays.asList(delayTimes).indexOf(Config.getConfig(this).getWechatOpenDelayTime()));
        updateCount();
        updateEnableStatus();
    }

    private void updateCount() {
        int wechatSum = SharedPreferencesUtil.getInt(this, Config.KEY_WECHAT_SUM);
        int qqSum = SharedPreferencesUtil.getInt(this, Config.KEY_QQ_SUM);
        tvWechatCout.setText("微信红包："+wechatSum+"个");
        tvQqCout.setText("QQ红包："+qqSum+"个");
        tvTotalCount.setText(wechatSum+qqSum+"个红包");
    }

    private void updateEnableStatus() {
        switchWechat.setChecked(SharedPreferencesUtil.getBoolean(this, Config.KEY_WECHAT_ENABLE));
        switchQq.setChecked(SharedPreferencesUtil.getBoolean(this, Config.KEY_QQ_ENABLE));
    }

    @OnClick(R.id.tv_guide)
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean enable) {
        switch (compoundButton.getId()) {
            case R.id.switch_service:
                if (!changeByUser) {
                    changeByUser = true;
                    return;
                }
                if (enable && !LuckyMoneyService.isRunning()) {
                    openAccessibilityServiceSettings();
                }
                break;
            case R.id.switch_notification:
                if (!changeByUser) {
                    changeByUser = true;
                    return;
                }
                if(enable && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Toast.makeText(StartActivity.this, "该功能只支持安卓4.3以上的系统", Toast.LENGTH_SHORT).show();
                    return;
                }
                Config.getConfig(this).setNotificationServiceEnable(enable);
                if (enable && !LuckyMoneyNotificationService.isRunning())
                    openNotificationServiceSettings();
                break;
            case R.id.switch_wechat:
                SharedPreferencesUtil.saveBoolean(this, Config.KEY_WECHAT_ENABLE, enable);
                break;
            case R.id.switch_qq:
                if (!changeByUser) {
                    changeByUser = true;
                    return;
                }
                if (enable) {
                    changeByUser = false;
                    switchQq.setChecked(false);
                    Toast.makeText(this, "暂未开通，敬请期待", Toast.LENGTH_SHORT).show();
                }
//                SharedPreferencesUtil.saveBoolean(this, Config.KEY_QQ_ENABLE, enable);
                break;
        }
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
                switchService.setChecked(true);
                break;
            case EventBusMsg.ACCESSIBILITY_DISCONNECTED:
                Toast.makeText(getApplicationContext(), "已断开连接", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchService.setChecked(false);
                break;
            case EventBusMsg.NOTIFICATION_CONNECTED:
                Toast.makeText(getApplicationContext(), "正在监听通知栏", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchNotification.setChecked(true);
                break;
            case EventBusMsg.NOTIFICATION_DISCONNECTED:
                Toast.makeText(getApplicationContext(), "已停止监听通知栏", Toast.LENGTH_SHORT).show();
                changeByUser = false;
                switchNotification.setChecked(false);
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
        String json = OnlineConfigAgent.getInstance().getConfigParams(this,"ad");
        this.adConfig = JSON.parseObject(json,AdConfig.class);

    }

}
