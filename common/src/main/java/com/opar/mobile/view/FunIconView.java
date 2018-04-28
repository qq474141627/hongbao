package com.opar.mobile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.opar.mobile.base.BaseActivity;
import com.opar.mobile.data.BannerAdConfig;
import com.opar.mobile.data.R;
import com.opar.mobile.data.UrlCommon;
import com.opar.mobile.inteface.IClickListener;
import com.opar.mobile.utils.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tangge on 18/3/22.
 */
public class FunIconView extends RelativeLayout implements View.OnClickListener{

    ImageView iv_icon;
    ImageView iv_new;
    TextView tv_label;


    private BannerAdConfig bannerAdBean;

    private IClickListener listener;

    public void setOnClickListener(IClickListener listener) {
        this.listener =listener;
    }


    public FunIconView(Context context , BannerAdConfig bannerAdBean) {
        super(context);

        this.bannerAdBean = bannerAdBean;
        View view = LayoutInflater.from(context).inflate(R.layout.item_funicon, this);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        iv_new = (ImageView) view.findViewById(R.id.iv_new);
        tv_label = (TextView) view.findViewById(R.id.tv_label);

        new GlideUtil().with(context).load(bannerAdBean.getImg()).into(iv_icon);
        iv_new.setVisibility(bannerAdBean.isNew() ? VISIBLE : GONE);
        tv_label.setText(bannerAdBean.getTitle());
        setOnClickListener(this);
    }

    public FunIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FunIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
