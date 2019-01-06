package com.opar.hongbao.utils;

/**
 * Created by wangbo on 18/4/22.
 */

public interface ICallBack<T> extends ISuccessCallBack {
    void onFail();
}
