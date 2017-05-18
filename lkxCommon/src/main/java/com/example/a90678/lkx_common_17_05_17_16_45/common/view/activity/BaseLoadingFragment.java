package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseLoadingResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.presenter.BaseLoadingPresenter;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout.FixedFlipper;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout.LoadingLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 16:39 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 16:39 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseLoadingFragment<T extends BaseLoadingResult,
        P extends BaseLoadingPresenter<T>> extends BaseSwipeRefreshFragment implements BaseLoadingStatus<T> {

//    @Bind(R.id.flp)
    FixedFlipper mFlp;
//    @Bind(R.id.load_transparent_ll)
    FrameLayout mLoadTransparentLl;
//    @Bind(R.id.load_error_tv)
    TextView mLoadErrorTv;
//    @Bind(R.id.load_error_ll)
    LinearLayout mLoadErrorLl;
//    @Bind(R.id.iv_empty)
    ImageView mIvEmpty;
//    @Bind(R.id.load_empty_ll)
    LinearLayout mLoadEmptyLl;
//    @Bind(R.id.loading_iv)
    ImageView mLoadingIv;
//    @Bind(R.id.loading_ll)
    LinearLayout mLoadingLl;
//    @Bind(R.id.loading_layout)
    LoadingLayout mLoadingLayout;

    public abstract P getPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = addLoadingView(inflater, container, savedInstanceState);
        View source = super.onCreateView(inflater, (ViewGroup) view, savedInstanceState);
//        ButterKnife.bind(this, source);
        setSwipeRefreshEnable(getSrl(), true);
        return source;
    }

    private View addLoadingView(LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_loading, null);
        mLoadingLayout = (LoadingLayout) view.findViewById(R.id.loading_layout);
        mLoadingLayout.addView(container, 0);
        LinearLayout mLlError = (LinearLayout) mLoadingLayout.findViewById(R.id.load_error_ll);
        mLlError.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reLoad();
            }
        });
        LinearLayout mLlEmpty = (LinearLayout) mLoadingLayout.findViewById(R.id.load_empty_ll);
        mLlEmpty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reLoad();
            }
        });

        mFlp = (FixedFlipper) view.findViewById(R.id.flp);
        mLoadTransparentLl = (FrameLayout) view.findViewById(R.id.load_transparent_ll);
        mLoadErrorTv = (TextView) view.findViewById(R.id.load_error_tv);
        mLoadErrorLl = (LinearLayout) view.findViewById(R.id.load_error_ll);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mLoadEmptyLl = (LinearLayout) view.findViewById(R.id.load_empty_ll);
        mLoadingIv = (ImageView) view.findViewById(R.id.loading_iv);
        mLoadingLl = (LinearLayout) view.findViewById(R.id.loading_ll);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnSwipeRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    @Override
    public void loadData() {
        startLoad();
        mLoadingLayout.loadData();
    }

    @Override
    public void startLoad() {
//        loading();
        mLoadingLayout.startLoad();
        //getPresenter().startLoad();
    }

    @Override
    public void loading() {
        mLoadingLayout.loading();
        //getPresenter().loading();
    }

    @Override
    public void success(T data) {
        mLoadingLayout.success(data);
        //getPresenter().success(data);
//        complete();
    }

    @Override
    public void empty() {
        mLoadingLayout.empty();
        //getPresenter().empty();
//        complete();
    }

    @Override
    public void failure(int code, String msg) {
        mLoadingLayout.failure(code, msg);
        //getPresenter().failure(code, msg);
//        complete();
    }

    @Override
    public void error(String msg) {
        mLoadingLayout.error(msg);
        //getPresenter().error(msg);
//        complete();
    }

    @Override
    public void complete() {
        mLoadingLayout.complete();
        //        //getPresenter().complete();
        setSwipeRefreshing(false);
    }

    @Override
    public void reLoad() {
        mLoadingLayout.reLoad();
        //getPresenter().reLoad();
//        startLoad();
        getPresenter().loadData();
    }

    @Override
    public void offLine() {
        mLoadingLayout.offLine();
        //getPresenter().offLine();
    }

    @Override
    public void unLogin() {
        mLoadingLayout.unLogin();
        //getPresenter().unLogin();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
