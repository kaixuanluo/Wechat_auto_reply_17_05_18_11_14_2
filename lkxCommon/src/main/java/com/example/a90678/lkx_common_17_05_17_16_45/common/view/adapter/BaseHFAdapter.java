package com.example.a90678.lkx_common_17_05_17_16_45.common.view.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/20 14:46 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/20 14:46 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class BaseHFAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public BaseHFAdapter(RecyclerView.Adapter adapter)
    {
        mInnerAdapter = adapter;
    }

    public boolean isHeaderViewPos(int position)
    {
        return position < getHeadersCount();
    }

    public boolean isFooterViewPos(int position)
    {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view)
    {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view)
    {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount()
    {
        return mHeaderViews.size();
    }

    public int getFootersCount()
    {
        return mFootViews.size();
    }

    GridLayoutManager.SpanSizeLookup oldLookup;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (mHeaderViews.get(viewType) != null)
        {

//            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            ViewHolder viewHolder = new HolderHeader(mHeaderViews.get(viewType));
            return viewHolder;

        } else if (mFootViews.get(viewType) != null)
        {
//            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            ViewHolder viewHolder = new HolderFoot(mFootViews.get(viewType));
            return viewHolder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (isHeaderViewPos(position))
        {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position))
        {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount()
    {
        return mInnerAdapter.getItemCount();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (isHeaderViewPos(position))
        {
            return;
        }
        if (isFooterViewPos(position))
        {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount()
    {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    public class HolderHeader extends ViewHolder {

        public HolderHeader(View itemView) {
            super(itemView);
        }
    }

    public class HolderFoot extends ViewHolder {

        public HolderFoot(View itemView) {
            super(itemView);
        }
    }

    public abstract class BetterViewHolder extends RecyclerView.ViewHolder {

        public BetterViewHolder(View itemView) {
            super(itemView);
        }

        public abstract BetterViewHolder onCreateViewHolder(ViewGroup parent);

        public abstract void onBindViewHolder(BetterViewHolder holder);
    }

    //针对Grid的 尾部铺满
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {
                @Override
                public int getSpanSize(int position)
                {
                    int viewType = getItemViewType(position);
                    if (mHeaderViews.get(viewType) != null)
                    {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else if (mFootViews.get(viewType) != null)
                    {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (oldLookup != null)
                        return oldLookup.getSpanSize(position);
                    return 1;
                }
            });
            oldLookup = spanSizeLookup;
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

//    针对瀑布流的 //尾部铺满
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder)
    {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position))
        {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams)
            {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }
}