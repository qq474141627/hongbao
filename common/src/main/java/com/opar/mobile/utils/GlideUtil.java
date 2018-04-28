package com.opar.mobile.utils;

import android.content.Context;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;

/**
 * Created by tangge on 18/3/29.
 */

public class GlideUtil {

    private RequestManager requestManager;

    public GlideUtil with(Context context){
        requestManager =  Glide.with(context);
        return this;
    }

    public DrawableTypeRequest<String> load(String url){
        return requestManager.load(url);
    }

    public DrawableTypeRequest<File> load(File file){
        return requestManager.load(file);
    }

    public DrawableTypeRequest<Integer> load(Integer resId){
        return requestManager.load(resId);
    }

}
