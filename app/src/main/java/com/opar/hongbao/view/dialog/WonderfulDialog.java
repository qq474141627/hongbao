package com.opar.hongbao.view.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.opar.hongbao.R;
import com.opar.hongbao.base.BaseDialog;
import com.opar.hongbao.entity.BannerAdConfig;
import com.opar.hongbao.entity.IClickListener;
import com.opar.hongbao.utils.ZoomOutPageTransformer;
import com.opar.hongbao.view.WonderfulView;

import java.util.List;

/**
 * Created by tangge on 18/3/13.
 */
public class WonderfulDialog extends BaseDialog {

    ConvenientBanner ly_wonderful;
    ImageView iv_cancel;

    private int screenWidth;

    public WonderfulDialog(final Activity context) {
        super(context , R.style.dialog_untran);
        setBottom(false);
        super.initView(R.layout.dialog_wonderful);

        iv_cancel = (ImageView) view.findViewById(R.id.iv_cancel);
        ly_wonderful = (ConvenientBanner) view.findViewById(R.id.ly_wonderful);

        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        //设置横屏全屏
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if(params != null){
            params.width = screenWidth;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }

        ly_wonderful.setCanLoop(false);
        //添加仿iphone动画
        ly_wonderful.getViewPager().setPageTransformer(true ,new ZoomOutPageTransformer());
        //设置间距 露出来60像素
        ly_wonderful.getViewPager().setPageMargin(-(screenWidth - 400)/2 - 30);

        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    //
    public void showDialog(final List<BannerAdConfig> configs) {
        if(configs != null && configs.size() > 0)
        //数据有更新,使用新数据,数据为空则使用老数据
        ly_wonderful.setPages(
                new CBViewHolderCreator<WonderfulView>() {
                    @Override
                    public WonderfulView createHolder() {
                        WonderfulView wonderfulView = new WonderfulView();
                        wonderfulView.setOnClickListener(new IClickListener() {
                            @Override
                            public void onClick() {
                                cancelDialog();
                            }
                        });
                        return wonderfulView;
                    }
                }, configs);
        super.showDialog();
    }

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
