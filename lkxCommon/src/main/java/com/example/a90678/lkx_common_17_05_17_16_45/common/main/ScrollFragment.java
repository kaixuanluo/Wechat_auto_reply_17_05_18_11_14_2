package com.example.a90678.lkx_common_17_05_17_16_45.common.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.presenter.BaseLoadingPresenter;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity.BaseLoadingFragment;

/**
 * --------------------------------------------
 * auther :  Lvfq
 * 2016/12/27 0:28
 * description ：
 * -------------------------------------------
 **/
public class ScrollFragment extends BaseLoadingFragment {

    public static ScrollFragment newInstance() {
        Bundle args = new Bundle();
        ScrollFragment fragment = new ScrollFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public BaseLoadingPresenter getPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);
        return view;
    }
}
