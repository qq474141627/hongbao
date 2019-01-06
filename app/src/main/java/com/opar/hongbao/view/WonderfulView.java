package com.opar.hongbao.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.opar.hongbao.R;
import com.opar.hongbao.entity.BannerAdConfig;
import com.opar.hongbao.entity.IClickListener;
import com.opar.hongbao.entity.UrlCommon;
import com.opar.hongbao.utils.GlideUtil;


/**
 * Created by wangbo on 16/11/21.
 */
public class WonderfulView implements Holder<BannerAdConfig>,View.OnClickListener{

    private ImageView imageView;
    private BannerAdConfig bannerAdBean;
    private IClickListener listener;

    public void setOnClickListener(IClickListener listener) {
        this.listener =listener;
    }

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wonderful,new LinearLayout(context),false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        return view;
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