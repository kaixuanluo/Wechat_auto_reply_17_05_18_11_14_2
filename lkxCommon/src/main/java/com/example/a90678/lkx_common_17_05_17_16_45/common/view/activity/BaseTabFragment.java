package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.a90678.lkx_common_17_05_17_16_45.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 17:40 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 17:40 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseTabFragment extends BaseFragment {

//    @Bind(R.id.main_tl)
    TabLayout mMainTl;
//    @Bind(R.id.main_vp)
    ViewPager mMainVp;
//    @Bind(R.id.base_tab_fl)
    FrameLayout mBaseTabFl;

    public TabLayout getMainTl() {
        return mMainTl;
    }

    public abstract List<ItemTab> getTabList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_tab, container, false);
        ButterKnife.bind(this, view);
        mMainTl = (TabLayout) view.findViewById(R.id.main_tl);
        mMainVp = (ViewPager) view.findViewById(R.id.main_vp);
        mBaseTabFl = (FrameLayout) view.findViewById(R.id.base_tab_fl);
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
        mMainVp.setAdapter(new TabAdapter(getActivity().getSupportFragmentManager(), tabList));
        mMainTl.setupWithViewPager(mMainVp);
        mMainVp.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
