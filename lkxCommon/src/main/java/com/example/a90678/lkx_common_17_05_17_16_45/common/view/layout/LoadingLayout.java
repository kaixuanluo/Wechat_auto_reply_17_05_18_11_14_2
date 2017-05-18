package com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/12 15:12 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/12 15:12 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class LoadingLayout<DATA> extends FrameLayout implements BaseLoadingStatus<DATA> {

    LoadingFixedFlipper<DATA> mFlp;
//    TextView mTvError;
//    LinearLayout mLlError;
//    int CONTENT = 0 ;
//    int ERROR = 1;
//    int EMPTY = 2;
//    int LOADING = 3;
    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mFlp = (LoadingFixedFlipper<DATA>) findViewById(R.id.flp);
//        mTvError = (TextView) findViewById(R.id.load_error_tv);
//        mLlError = (LinearLayout) findViewById(R.id.load_error_ll);
//        mLlError.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reLoad();
//            }
//        });
//        ImageView ivLoading = (ImageView) findViewById(R.id.loading_iv);
//        AnimationDrawable loadingDrawable = (AnimationDrawable) ivLoading.getDrawable();
//        loadingDrawable.start();
    }

//    void showError(String msg) {
//        mFlp.setDisplayedChild(ERROR);
//        mTvError.setText(msg);
//    }

//    void showFailure(int code, String msg) {
//        mFlp.setDisplayedChild(ERROR);
//        mTvError.setText("code : " + code + " msg: " + msg);
//    }

//    void showContent() {
//        mFlp.setDisplayedChild(CONTENT);
//    }
//
//    public void showEmpty() {
//        mFlp.setDisplayedChild(EMPTY);
//    }
//
//    void showLoading() {
//        mFlp.setDisplayedChild(LOADING);
//    }

    @Override
    public void success(DATA data) {
//        showContent();
        mFlp.success(data);
    }

    @Override
    public void empty() {
        mFlp.empty();
    }

    @Override
    public void failure(int code, String msg) {
//        showError(msg);
        mFlp.failure(code, msg);
    }

    @Override
    public void error(String msg) {
//        showFailure(0, msg);
        mFlp.error(msg);
    }

    @Override
    public void loading() {
//        showLoading();
        mFlp.loading();
    }

    @Override
    public void complete() {
        mFlp.complete();
    }

    @Override
    public void loadData() {
//        startLoad();
        mFlp.loadData();
    }

    @Override
    public void startLoad() {
//        showLoading();
        mFlp.startLoad();
    }

    @Override
    public void reLoad() {
//        startLoad();
        mFlp.reLoad();
    }

    @Override
    public void offLine() {
        mFlp.offLine();
    }

    @Override
    public void unLogin() {
        mFlp.unLogin();
    }
}
