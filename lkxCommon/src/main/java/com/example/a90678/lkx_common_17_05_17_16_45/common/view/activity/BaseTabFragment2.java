package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/11 11:56 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/11 11:56 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseTabFragment2 extends BaseFragment {

//    @Bind(R.id.base_tab_fl)
    FrameLayout mBaseTabFl;
//    @Bind(R.id.base_tab_title_ll)
    LinearLayout mBaseTabTitleLl;

    public abstract List<ItemTab> getTabList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_tab2, container, false);
        ButterKnife.bind(this, view);
        mBaseTabFl = (FrameLayout) view.findViewById(R.id.base_tab_fl);
        mBaseTabTitleLl = (LinearLayout) view.findViewById(R.id.base_tab_title_ll);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<ItemTab> tabList = getTabList();
        if (tabList != null) {
            initTab(tabList);
        }
    }

    private void initTab(List<ItemTab> tabList) {
        if (tabList != null && tabList.size() > 0) {
            for (int i = 0; i < tabList.size(); i++) {
                TextView tvTitle = new TextView(this.getContext());
                tvTitle.setText(tabList.get(i).getTitle());
                mBaseTabTitleLl.addView(tvTitle);
                tvTitle.setOnClickListener(new TitleClickListener(tvTitle, i, tabList));
            }
        }
    }

    private class TitleClickListener implements OnClickListener {

        TextView mTvTitle;
        int mPosition;
        List<ItemTab> mItemTabList;

        public TitleClickListener(TextView tvTitle, int position, List<ItemTab> itemTabList) {
            mTvTitle = tvTitle;
            this.mPosition = position;
            mItemTabList = itemTabList;
        }

        @Override
        public void onClick(View view) {
            if (getTabList().size() > mPosition) {
                Fragment fragment = getTabList().get(mPosition).getFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_tab_fl, fragment)
//                    .add(fragment, fragment.getClass().getName())
                        .show(fragment)
                        .commit();
            }
        }
    }

    private class TLScrollListener implements OnTabSelectedListener {

        List<ItemTab> mItemTabList;

        public TLScrollListener(List<ItemTab> itemTabList) {
            mItemTabList = itemTabList;
        }

        @Override
        public void onTabSelected(Tab tab) {
            if (getTabList().size() > tab.getPosition()) {
                Fragment fragment = getTabList().get(tab.getPosition()).getFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.base_tab_fl, fragment)
//                    .add(fragment, fragment.getClass().getName())
                        .show(fragment)
                        .commit();
            }
        }

        @Override
        public void onTabUnselected(Tab tab) {

        }

        @Override
        public void onTabReselected(Tab tab) {

        }
    }

    private class TabAdapter extends FragmentStatePagerAdapter {

        List<ItemTab> mTabList;

        public TabAdapter(FragmentManager fm, List<ItemTab> tabList) {
            super(fm);
            this.mTabList = tabList;
        }

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabList.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return mTabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabList.get(position).getTitle();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class ItemTab {
        String mTitle;
        Fragment mFragment;

        public ItemTab(String title, Fragment fragment) {
            mTitle = title;
            mFragment = fragment;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public Fragment getFragment() {
            return mFragment;
        }

        public void setFragment(Fragment fragment) {
            mFragment = fragment;
        }
    }
}
