package com.opar.hongbao.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opar.hongbao.R;
import com.opar.hongbao.entity.BannerAdConfig;
import com.opar.hongbao.entity.IClickListener;
import com.opar.hongbao.entity.UrlCommon;
import com.opar.hongbao.utils.GlideUtil;

/**
 * Created by tangge on 18/3/21.
 */
public class NewsView extends LinearLayout implements View.OnClickListener{

    ImageView iv_news_icon;
    TextView tv_news_title;
    TextView tv_news_content;

    private BannerAdConfig bannerAdBean;
    private Context context;

    private IClickListener listener;

    public void setOnClickListener(IClickListener listener) {
        this.listener =listener;
    }

    public NewsView(Context context) {
        super(context);
        this.context = context;
        initView ();
    }

    public NewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initView (){
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, this);
        //解析数据
        iv_news_icon = (ImageView) view.findViewById(R.id.iv_news_icon);
        tv_news_title = (TextView) view.findViewById(R.id.tv_news_title);
        tv_news_content = (TextView) view.findViewById(R.id.tv_news_content);
        setOnClickListener(this);
    }


    public NewsView setConfig(BannerAdConfig bannerAdBean){
        this.bannerAdBean = bannerAdBean;
        new GlideUtil().with(context).load(bannerAdBean.getImg()).into(iv_news_icon);
        if(TextUtils.isEmpty(bannerAdBean.getTitle())){
            tv_news_title.setVisibility(GONE);
        }else{
            tv_news_title.setText(bannerAdBean.getTitle());
        }
        if(TextUtils.isEmpty(bannerAdBean.getDesc())){
            tv_news_content.setVisibility(GONE);
        }else{
            tv_news_content.setText(bannerAdBean.getDesc());
        }
        return this;
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
