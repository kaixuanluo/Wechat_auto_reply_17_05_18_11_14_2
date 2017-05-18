package com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.OnRecyclerItemClickListener;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity.BaseListFragment;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.adapter.BaseHFAdapter;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/20 10:47 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/20 10:47 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class RecyclerViewUtil {

    public static void bindBaseListFragment(BaseListFragment fragment) {
        RecyclerView rcv = fragment.getRcv();
        BaseHFAdapter baseHFAdapter = fragment.getBaseHFAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        setRecyclerViewManager(rcv, linearLayoutManager);
        rcv.setHasFixedSize(true);
//        BaseHFAdapter baseHFAdapter = new BaseHFAdapter(new BaseListAdapter2());//能够添加头部尾部的适配器
        addFootView(fragment, baseHFAdapter);//添加尾部。
        setRecyclerViewAdapter(rcv, baseHFAdapter);//设置适配器
        setRcvScrollListener(rcv, fragment);//设置滚动监听。
        addRcvItemTouchListener(fragment, baseHFAdapter, rcv);//设置条目点击
    }

    protected static void setRecyclerViewManager(RecyclerView rcv, LayoutManager layoutManager) {
        rcv.setLayoutManager(layoutManager);
    }

    protected static void setRecyclerViewAdapter(RecyclerView rcv, RecyclerView.Adapter adapter) {
        rcv.setAdapter(adapter);
    }

    private static void addFootView(BaseListFragment fragment, BaseHFAdapter baseHFAdapter) {
        View mItemFootView = LayoutInflater.from(fragment.getContext()).inflate(R.layout.item_loading, null);
        FrameLayout.LayoutParams lytp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lytp.gravity = Gravity.CENTER;
        mItemFootView.setLayoutParams(lytp);
        TextView mTvFooter = (TextView) mItemFootView.findViewById(R.id.progressTv);
        ImageView ivLoading = (ImageView) mItemFootView.findViewById(R.id.progressIv);
        AnimationDrawable loadingDrawable = (AnimationDrawable) ivLoading.getDrawable();
        loadingDrawable.start();
        setFooterClick(fragment, mItemFootView);
        baseHFAdapter.addFootView(mItemFootView);
    }

    protected static void setFooterClick(final BaseListFragment fragment, View mItemFootView) {
        mItemFootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!fragment.isHasMore()) {
//                    return;
//                }
////                if (getLastItem() == null) {
////                    loadData();
////                } else {
////                    loadMoreData(getLastItem());
////                }
//                if (fragment.isHasMore() && fragment.getLastItem() != null) {
//                    fragment.loadMore(fragment.getLastItem());
//                } else {
//                    fragment.loadData();
//                }
            }
        });
    }

    private static void setRcvScrollListener(final RecyclerView rcv, final BaseListFragment fragment) {
        rcv.setOnScrollListener(new OnScrollListener() {
            private int lastVisibleItem;
            private int[] lastPositions;
            private int totalItemCount;
            private int visibleThreshold = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LayoutManager layoutManager = rcv.getLayoutManager();
                if (layoutManager != null) {
                    if (layoutManager instanceof LinearLayoutManager) {
                        lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof GridLayoutManager) {
                        lastVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        if (lastPositions == null) {
                            lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                        }
                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                        lastVisibleItem = findMax(lastPositions);
                    } else {

                    }
                }

//                totalItemCount = layoutManager.getItemCount();//这个包括头部和尾部

                if (fragment.getBaseList() != null) {//这个不包括头部和尾部
                    totalItemCount = fragment.getBaseList().size();
                }

//                if (!fragment.isLoading() && totalItemCount <= (lastVisibleItem + visibleThreshold) && fragment.isHasMore()) {
//                    if (fragment.getBaseList() != null && fragment.getBaseList().size() > 0) {
//                        fragment.setLoading(true);
//                        fragment.setLoadMore(true);
//                        fragment.loadMore(fragment.getBaseList().get(fragment.getBaseList().size() - 1));
//                    }
//                }
            }
        });
    }

    private static int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static void addRcvItemTouchListener(final BaseListFragment fragment,
                                                final BaseHFAdapter baseHFAdapter, final RecyclerView rcv) {
        rcv.addOnItemTouchListener(new OnRecyclerItemClickListener(rcv) {
            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(ViewHolder vh) {
                //屏蔽头部和尾部点击
                if (baseHFAdapter.isHeaderViewPos(vh.getPosition())) {

                } else if (baseHFAdapter.isFooterViewPos(vh.getPosition())) {

                } else {
                    fragment.onItemClickListener(fragment.getBaseList().get(vh.getPosition()));
                }
            }

            @Override
            public void onItemLongClick(ViewHolder vh) {
                //屏蔽头部和尾部点击
                if (baseHFAdapter.isHeaderViewPos(vh.getPosition())) {

                } else if (baseHFAdapter.isFooterViewPos(vh.getPosition())) {

                } else {
                    fragment.onItemLongClickListener(fragment.getBaseList().get(vh.getPosition()));
                }
            }
        });
    }

}
