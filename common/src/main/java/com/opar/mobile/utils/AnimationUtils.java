package com.opar.mobile.utils;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * 动画效果
 * Created by tangge on 18/3/29.
 */
public class AnimationUtils {

    public static void translateXShake(View targetView, int transalteDeta) {
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -transalteDeta),
                Keyframe.ofFloat(.26f, transalteDeta),
                Keyframe.ofFloat(.42f, -transalteDeta),
                Keyframe.ofFloat(.58f, transalteDeta),
                Keyframe.ofFloat(.74f, -transalteDeta),
                Keyframe.ofFloat(.90f, transalteDeta),
                Keyframe.ofFloat(1f, 0f)
        );

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(targetView, pvhTranslateX).
                setDuration(500);
        animator.setRepeatCount(0);
        animator.start();
    }


    /**
     * 下拉，缩回动画
     * @param v
     * @param start
     * @param end
     * @param time 
     * @return
     */
    public static ValueAnimator createDropAnimator(final View v, int start, int end,int time) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        
        return animator;
    }
}
