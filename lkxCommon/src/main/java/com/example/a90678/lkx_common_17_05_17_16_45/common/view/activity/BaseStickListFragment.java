package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseListResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.presenter.BaseListPresenter;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.adapter.BaseHFAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/14 18:22 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/14 18:22 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseStickListFragment<ITEM, DATA extends BaseListResult<ITEM>,
        P extends BaseListPresenter<ITEM, DATA>, HOLDER extends ViewHolder>
        extends BaseListFragment<ITEM, DATA, P, HOLDER> {

    StickAdapter mStickAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStickAdapter = new StickAdapter(new BaseListAdapter2());

        super.onCreate(savedInstanceState);
//        getRcv().initRecyclerView(this.getContext(), mStickAdapter);
        final StickyRecyclerHeadersDecoration decor = new StickyRecyclerHeadersDecoration(mStickAdapter);
        getRcv().addItemDecoration(decor);

        //添加头部刷新
        mStickAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                decor.invalidateHeaders();
            }
        });

//        //添加头部点击监听
//        StickyRecyclerHeadersTouchListener touchListener =
//                new StickyRecyclerHeadersTouchListener(getRcv(), decor);
//        touchListener.setOnHeaderClickListener(
//                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
//                    @Override
//                    public void onHeaderClick(View header, int position, long headerId) {
//                        Toast.makeText(BaseStickListFragment.this.getContext(), "Header position: " + position + ", id: " + headerId,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//        getRcv().addOnItemTouchListener(touchListener);

    }

    protected abstract long initStickHeaderId(int position, ITEM item);
    protected abstract void bindStickHeardViewHolder(TextView tvHeard, ITEM item, int position);

    public class StickAdapter
            extends BaseHFAdapter
            implements StickyRecyclerHeadersAdapter<HeardHolderItem> {

        public StickAdapter(Adapter adapter) {
            super(adapter);
        }

        @Override
        public long getHeaderId(int position) {
            ITEM item = null;
            if (getBaseList() != null && getBaseList().size() > 0) {
                item = getBaseList().get(position);
                Log.e(" getHeaderId111111 ", " getHeaderId111111 " + position);
            } else {
                Log.e(" getHeaderId ", " getHeaderId " + position);
            }
            return initStickHeaderId(position, item);
        }

        @Override
        public HeardHolderItem onCreateHeaderViewHolder(ViewGroup parent) {
            return new HeardHolderItem(
                    LayoutInflater.from(BaseStickListFragment.this.getContext())
                            .inflate(R.layout.item_flow_progress_heard, null));
        }

        @Override
        public void onBindHeaderViewHolder(HeardHolderItem holder, int position) {
            if (getBaseList() != null && getBaseList().size() > 0) {
                bindStickHeardViewHolder(((HeardHolderItem) holder).tvProgress, getBaseList().get(position), position);
                Log.e("  HeaderViewHolder111 ", " onBindHeaderViewHolder11 " + position);
            } else {
                Log.e("  HeaderViewHolder ", " onBindHeaderViewHolder " + position);
            }
        }

//        @Override
//        public int getItemViewType(int position) {
//            return TYPE_ITEM;
//        }
    }

    class HeardHolderItem extends ViewHolder {
        TextView tvProgress;
        public HeardHolderItem(View itemView) {
            super(itemView);

            tvProgress = (TextView) itemView.findViewById(R.id.flow_prg_heard);
        }
    }

//    @Override
//    protected void setRecyclerViewAdapter(Adapter adapter) {
//        super.setRecyclerViewAdapter(mStickAdapter);
//    }

}
