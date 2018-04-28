package com.opar.mobile.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.opar.mobile.data.BannerAdConfig;
import com.opar.mobile.data.UrlCommon;
import com.opar.mobile.inteface.IClickListener;
import com.opar.mobile.utils.GlideUtil;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by tangge on 18/3/21.
 */
public class BannerView implements Holder<BannerAdConfig> ,View.OnClickListener{

    private GifImageView imageView;
    private BannerAdConfig bannerAdBean;
    private IClickListener listener;

    public void setOnClickListener(IClickListener listener) {
        this.listener =listener;
    }


    @Override
    public View createView(Context context) {
        imageView = new GifImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(this);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, BannerAdConfig data) {
        this.bannerAdBean = data;
        new GlideUtil().with(context).load(data.getImg()).into(imageView);
    }

    @Override
    public void onClick(View view) {
        if(bannerAdBean != null){
            new UrlCommon(view.getContext())
                    .setTitle(bannerAdBean.getTitle())
                    .setUrl(bannerAdBean.getLink())
                    .setCanshare(true)
                    .start();
        }
        if(listener != null){
            listener.onClick();
        }
    }
}