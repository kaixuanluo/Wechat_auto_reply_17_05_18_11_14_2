package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a90678.lkx_common_17_05_17_16_45.R;


/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/11 19:01 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/11 19:01 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class BaseSwipeRefreshFragment
        extends RxFragment {

    SwipeRefreshLayout mSrl;

    public SwipeRefreshLayout getSrl() {
        return mSrl;
    }

    public void setSrl(SwipeRefreshLayout srl) {
        mSrl = srl;
    }

    protected void setSwipeRefreshEnable(SwipeRefreshLayout srl, boolean enable) {
        if (srl != null) {
            srl.setEnabled(enable);
        }
    }

    protected void setSwipeRefreshEnable(boolean enable) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setEnabled(enable);
        }
    }

    protected void setOnSwipeRefreshListener (OnRefreshListener listener) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setOnRefreshListener(listener);
        }
    }

    protected void setSwipeRefreshing(boolean refreshing) {
        SwipeRefreshLayout srl = getSrl();
        if (srl != null) {
            srl.setRefreshing(refreshing);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View swipeRefreshView = addSwipeRefreshView(inflater, container, savedInstanceState);
        return super.onCreateView(inflater, (ViewGroup) swipeRefreshView, savedInstanceState);
    }

    private View addSwipeRefreshView (LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View swipeView = inflater.inflate(R.layout.layout_swipe_refresh, null);
        mSrl = (SwipeRefreshLayout) swipeView.findViewById(R.id.srl);
        mSrl.addView(container);
//        mSrl.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadData();
//            }
//        });
        setSwipeRefreshEnable(getSrl(), false);
        return swipeView;
//        return container;
    }
}
