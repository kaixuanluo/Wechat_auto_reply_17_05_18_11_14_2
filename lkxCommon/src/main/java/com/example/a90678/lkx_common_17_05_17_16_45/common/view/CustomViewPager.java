package com.example.a90678.lkx_common_17_05_17_16_45.common.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/12/22 19:11 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/12/22 19:11 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class CustomViewPager extends ViewPager {
    private int current;
    private int height = 0;
    /**
     * 保存position与对于的View
     */
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();

    private boolean scrollble = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > current) {
            View child = mChildrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void resetHeight(int current) {
        this.current = current;
        if (mChildrenViews.size() > current) {

            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }
    /**
     * 保存position与对于的View
     */
    public void setObjectForPosition(View view, int position)
    {
        mChildrenViews.put(position, view);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }


    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }


    public void measureListViewHeight(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) { return; }
        int totalHeight = 0;
//        int listViewWidth = screedWidth - dip2px(this, 10);
        int listViewWidth = 60;
        //listView在布局时的宽度
        int widthSpec = MeasureSpec.makeMeasureSpec(listViewWidth, MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) { View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(widthSpec, 0);
            int itemHeight = listItem.getMeasuredHeight(); totalHeight += itemHeight; }
        // 减掉底部分割线的高度
        int historyHeight = totalHeight + (listView.getDividerHeight() * listAdapter.getCount() - 1);
        Log.d("Javine","listViewHeight = "+historyHeight);
    }
}