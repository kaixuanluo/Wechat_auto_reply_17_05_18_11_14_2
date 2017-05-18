package com.example.a90678.lkx_common_17_05_17_16_45.common.rx;

import android.util.Log;

import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseLoadingResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;

import retrofit2.adapter.rxjava.HttpException;


/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/17 11:31 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/17 11:31 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class BaseLoadingSubscriber<T extends BaseLoadingResult> extends BaseSubscriber<T> {

    BaseLoadingStatus<T> mBaseLoadingStatus;

    public BaseLoadingSubscriber(BaseLoadingStatus<T> baseLoadingStatus) {
        mBaseLoadingStatus = baseLoadingStatus;
    }

    @Override
    public void onCompleted() {
        mBaseLoadingStatus.complete();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.d("  ", "code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            if (code == 401) {
                //掉线了,需要重新登录
                mBaseLoadingStatus.offLine();
            }
            mBaseLoadingStatus.failure(code, msg);
        } else {
//            failure(0, e.getMessage());
            mBaseLoadingStatus.error(e.getMessage());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        switch (t.getFlag()) {
            case 0:
                mBaseLoadingStatus.failure(0, t.getMsg());
                break;
            default:
                mBaseLoadingStatus.success(t);
                break;
        }
    }
}
