package com.example.a90678.lkx_common_17_05_17_16_45.common.view;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/10/20 14:02 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/10/20 14:02 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class OnRecyclerItemClickListener implements OnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public OnRecyclerItemClickListener(final RecyclerView recyclerView)
    {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null)
                {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(childView);
                    onItemClick(vh);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e)
            {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null)
                {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(childView);
                    onItemLongClick(vh);
                }
            }
        });
    }
    //点击事件交给mGestureDetector处理
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        mGestureDetector.onTouchEvent(e);
        return false;
    }
    //点击回掉
    public abstract void onItemClick(RecyclerView.ViewHolder vh);
    //长按监听
    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);
}