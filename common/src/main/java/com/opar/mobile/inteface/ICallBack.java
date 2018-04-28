package com.opar.mobile.inteface;

/**
 * Created by tangge on 18/3/12.
 */
public interface ICallBack<T> extends ISuccessCallback<T>{
    void onError();
}
