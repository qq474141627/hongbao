package com.opar.hongbao.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.opar.hongbao.R;

import butterknife.BindView;

//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class GuideActivity extends BaseActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private String[] imgs = new String[]{"open_service_tips"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        setSupportActionBar(toolbar);
        viewPager.setOffscreenPageLimit(imgs.length);
        viewPager.setAdapter(new ImageViewPagerAdapter(this));
    }

    public class ImageViewPagerAdapter extends PagerAdapter {

        private Context context;

        public ImageViewPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public Object instantiateItem(final ViewGroup container, int position) { // 这个方法用来实例化页卡
            final ImageView imageView;
            if (container.getChildAt(position) == null) {
                imageView = new ImageView(this.context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                try {
//                    BitmapDrawable d = new BitmapDrawable(this.context.getAssets()
//                            .open(imgs[position]));
//                    imageView.setImageDrawable(d);
                    imageView.setImageResource(R.drawable.open_service_tips);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                container.addView(imageView);
            } else
                imageView = (ImageView) container.getChildAt(position);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
