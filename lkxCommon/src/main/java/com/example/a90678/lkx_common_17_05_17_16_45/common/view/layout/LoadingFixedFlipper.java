package com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/12 9:56 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/12 9:56 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class LoadingFixedFlipper<T> extends FixedFlipper implements BaseLoadingStatus<T> {

    TextView mTvError;
    LinearLayout mLlError;
    int CONTENT = 0 ;
    int ERROR = 1;
    int EMPTY = 2;
    int LOADING = 3;
    int OFFLINE = 4;

    public LoadingFixedFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTvError = (TextView) findViewById(R.id.load_error_tv);
//        mLlError = (LinearLayout) findViewById(R.id.load_error_ll);
//        mLlError.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reLoad();
//            }
//        });
        ImageView ivLoading = (ImageView) findViewById(R.id.loading_iv);
        AnimationDrawable loadingDrawable = (AnimationDrawable) ivLoading.getDrawable();
        loadingDrawable.start();
    }

    void showError(String msg) {
        setDisplayedChild(ERROR);
        mTvError.setText(msg);
    }

    void showFailure(int code, String msg) {
        setDisplayedChild(ERROR);
        mTvError.setText("code : " + code + " msg: " + msg);
    }

    void showContent() {
        setDisplayedChild(CONTENT);
    }

    public void showEmpty() {
        setDisplayedChild(EMPTY);
    }

    void showLoading() {
        setDisplayedChild(LOADING);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void loading() {
        showLoading();
    }

    @Override
    public void success(T data) {
        showContent();
    }

    @Override
    public void empty() {
        showEmpty();
    }

    @Override
    public void failure(int code, String msg) {
        showFailure(code, msg);
    }

    @Override
    public void error(String msg) {
        showError(msg);
    }

    @Override
    public void complete() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void offLine() {
        setDisplayedChild(OFFLINE);
    }

    @Override
    public void unLogin() {

    }
}
