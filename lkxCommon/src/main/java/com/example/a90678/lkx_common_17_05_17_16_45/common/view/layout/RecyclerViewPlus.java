package com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseListResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseListStatus;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.OnRecyclerItemClickListener;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.adapter.BaseHFAdapter;

import java.util.List;


/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/20 13:48 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/20 13:48 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class RecyclerViewPlus<DATA extends BaseListResult<ITEM>, ITEM> extends RecyclerView  implements BaseLoadingStatus<DATA>
        , BaseListStatus<ITEM> {

    public RecyclerViewPlus(Context context) {
        super(context);
    }

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private boolean mIsLoading, mHasMore = true, mIsLoadMore;

//    private List<ITEM> mBaseList;
    private TextView mTvFooter;
    private View mItemFootView;
    private BaseHFAdapter mBaseHFAdapter;

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    private OnRecyclerViewItemLongClickListener mOnRecyclerViewItemLongClickListener;

    private OnRecyclerViewLoadMoreListener mOnRecyclerViewLoadMoreListener;

    public boolean isLoading() {
        return mIsLoading;
    }

    public void setLoading(boolean loading) {
        mIsLoading = loading;
    }

    public boolean isHasMore() {
        return mHasMore;
    }

    public void setHasMore(boolean hasMore) {
        mHasMore = hasMore;
    }

    public boolean isLoadMore() {
        return mIsLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        mIsLoadMore = loadMore;
    }

//    public List<ITEM> getBaseList() {
//        return mBaseList;
//    }

    public TextView getTvFooter() {
        return mTvFooter;
    }

    public void setTvFooter(TextView tvFooter) {
        mTvFooter = tvFooter;
    }

    public View getItemFootView() {
        return mItemFootView;
    }

    public void setItemFootView(View itemFootView) {
        mItemFootView = itemFootView;
    }

    public BaseHFAdapter getBaseHFAdapter() {
        return mBaseHFAdapter;
    }

    public void setBaseHFAdapter(BaseHFAdapter baseHFAdapter) {
        mBaseHFAdapter = baseHFAdapter;
    }

    public void initRecyclerView(Context context, BaseHFAdapter baseHFAdapter) {
        mBaseHFAdapter = baseHFAdapter;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        setRecyclerViewManager(linearLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
//        setRecyclerViewManager(gridLayoutManager);
//        StaggeredGridLayoutManager staggeredGridLayoutManager =
//                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        setRecyclerViewManager(staggeredGridLayoutManager);
        setHasFixedSize(true);
//        BaseHFAdapter baseHFAdapter = new BaseHFAdapter(new BaseListAdapter2());//能够添加头部尾部的适配器
        addFootView(context, baseHFAdapter);//添加尾部。
        setRecyclerViewAdapter(baseHFAdapter);//设置适配器
        setRcvScrollListener();//设置滚动监听。
        addRcvItemTouchListener(baseHFAdapter);//设置条目点击
    }

    private void addFootView(Context context, BaseHFAdapter baseHFAdapter) {
        mItemFootView = LayoutInflater.from(context).inflate(R.layout.item_loading, null);
        FrameLayout.LayoutParams lytp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lytp.gravity = Gravity.CENTER;
        mItemFootView.setLayoutParams(lytp);
        mTvFooter = (TextView) mItemFootView.findViewById(R.id.progressTv);
        ImageView ivLoading = (ImageView) mItemFootView.findViewById(R.id.progressIv);
        AnimationDrawable loadingDrawable = (AnimationDrawable) ivLoading.getDrawable();
        loadingDrawable.start();
        setFooterClick(mItemFootView);
        baseHFAdapter.addFootView(mItemFootView);
    }

    protected void setFooterClick(View mItemFootView) {
        mItemFootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHasMore()) {
                    return;
                }
//                if (getLastItem() == null) {
//                    loadData();
//                } else {
//                    loadMoreData(getLastItem());
//                }
//                if (isHasMore() && getLastItem() != null) {
//                    loadMore(getLastItem());
//                } else {
//                    loadData();
//                }
                if (isHasMore() && isLoadMore()) {
                    mOnRecyclerViewLoadMoreListener.onLoadMore();
                } else {
                    loadData();
                }
            }
        });
    }

//    public ITEM getLastItem() {
//        return mBaseList != null && mBaseList.size() > 0 ? mBaseList.get(mBaseList.size() - 1) : null;
//    }

    protected void setRecyclerViewManager(LayoutManager layoutManager) {
        setLayoutManager(layoutManager);
    }

    protected void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        setAdapter(adapter);
    }

    private void setRcvScrollListener() {
        setOnScrollListener(new OnScrollListener() {
            private int lastVisibleItem;
            private int[] lastPositions;
            private int totalItemCount;
            private int visibleThreshold = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LayoutManager layoutManager = getLayoutManager();
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

                totalItemCount = layoutManager.getItemCount()-
                        (mBaseHFAdapter.getHeadersCount()+mBaseHFAdapter.getFootersCount());//这个包括头部和尾部

//                if (getBaseList() != null) {//这个不包括头部和尾部
//                    totalItemCount = getBaseList().size();
//                }

                if (!isLoading() && totalItemCount > 0 && totalItemCount <= (lastVisibleItem + visibleThreshold) && isHasMore()) {
//                    if (getBaseList() != null && getBaseList().size() > 0) {
                        setLoading(true);
                        setLoadMore(true);
//                        loadMore(getLastItem());
                        mOnRecyclerViewLoadMoreListener.onLoadMore();
//                    }
                }
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

    private void addRcvItemTouchListener(final BaseHFAdapter baseHFAdapter) {
        addOnItemTouchListener(new OnRecyclerItemClickListener(this) {
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
                    if (mOnRecyclerViewItemClickListener != null) {
                        mOnRecyclerViewItemClickListener.onItemClick(vh.getPosition());
                    }
                }
            }

            @Override
            public void onItemLongClick(ViewHolder vh) {
                //屏蔽头部和尾部点击
                if (baseHFAdapter.isHeaderViewPos(vh.getPosition())) {

                } else if (baseHFAdapter.isFooterViewPos(vh.getPosition())) {

                } else {
                    if (mOnRecyclerViewItemLongClickListener != null) {
                        mOnRecyclerViewItemLongClickListener.onItemLongClick(vh.getPosition());
                    }
                }
            }
        });
    }

    public void setOnItemClickListener (OnRecyclerViewItemClickListener listener) {
        this.mOnRecyclerViewItemClickListener = listener;
    }

    public void setonItemLongClickListener (OnRecyclerViewItemLongClickListener listener){
        this.mOnRecyclerViewItemLongClickListener = listener;
    }

    public void setOnRecyclerViewLoadMoreListener (OnRecyclerViewLoadMoreListener listener) {
        mOnRecyclerViewLoadMoreListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface OnRecyclerViewLoadMoreListener {
        void onLoadMore();
    }

    protected void setFooterText(String text) {
        if (mTvFooter != null) {
            mTvFooter.setText(text);
        }
    }

//    protected void setFooterText(@StringRes int text) {
//        if (mTvFooter != null) {
//            mTvFooter.setText(getString(text));
//        }
//    }

//    protected void setFooterClick() {
//        mItemFootView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mHasMore) {
//                    return;
//                }
////                if (getLastItem() == null) {
////                    loadData();
////                } else {
////                    loadMoreData(getLastItem());
////                }
//                if (mIsLoadMore && getLastItem() != null) {
//                    loadMore(getLastItem());
//                } else {
//                    loadData();
//                }
//            }
//        });
//    }

    public void setFooterVisiable(int visibility) {
        if (mItemFootView != null) {
            mItemFootView.findViewById(R.id.item_loading).setVisibility(visibility);
        }
    }

    protected void setFooterEnable(boolean enable) {
        if (mItemFootView != null) {
            mItemFootView.setEnabled(enable);
        }
    }

    @Override
    public void loadData() {
        setLoadMore(false);
        hasMore();
        startLoad();
    }

    @Override
    public void startLoad() {
//        super.startLoad();
        setFooterVisiable(View.VISIBLE);
        setFooterEnable(false);
    }

    @Override
    public void noMore() {
        setHasMore(false);
        setFooterText("没有更多。。。");
        setFooterEnable(false);
    }

    @Override
    public void hasMore() {
        setHasMore(true);
        setFooterText("加载中。。。");
        setFooterEnable(false);
    }

    @Override
    public void error(String msg) {
        if (!isLoadMore()) {
            setFooterVisiable(View.GONE);
        }
        setFooterText("加载错误，点击重试。。。");
        setFooterEnable(true);
    }

    @Override
    public void failure(int code, String msg) {
        if (!isLoadMore()) {
            setFooterVisiable(View.GONE);
        }
        setFooterText("加载失败， 点击重试。。。");
        setFooterEnable(true);
    }

    @Override
    public void loadMore(ITEM lastItem) {
        setFooterText("加载更多，加载中。。。");
        setFooterEnable(false);
//        mOnRecyclerViewLoadMoreListener.onLoadMore();
    }

    @Override
    public void complete() {
        setLoading(false);
    }

    @Override
    public void success(DATA data) {
        if (data == null) {
            noMore();
        } else {
            List<ITEM> list = data.getList();
            if (isLoadMore()) {
                getListMoreData(list);
            } else {
                getListData(list);
            }

            getAdapter().notifyDataSetChanged();
        }
//        notifyData(mRcv.getAdapter(), oldList, mBaseList);

    }

    protected void notifyData(RecyclerView.Adapter mAdapter, List<ITEM> mDatas, List<ITEM> newDatas) {

//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, newDatas), true);
//
//        diffResult.dispatchUpdatesTo(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void getListData(List<ITEM> list) {
    }

    @Override
    public void getListMoreData(List<ITEM> list) {
    }

    @Override
    public void reLoad() {
//        super.reLoad();
    }

    @Override
    public void offLine() {

    }

    @Override
    public void unLogin() {

    }

    @Override
    public void empty() {
        setFooterText("");
        setFooterVisiable(View.GONE);
    }

    @Override
    public void loading() {
//        super.loading();
    }
}
