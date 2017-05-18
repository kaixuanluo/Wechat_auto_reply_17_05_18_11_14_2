package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a90678.lkx_common_17_05_17_16_45.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/11 14:25 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/11 14:25 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseTabFragment3 extends BaseFragment {

//    @Bind(R.id.main_tl)
    TabLayout mMainTl;

    Fragment mLastFragment;

    public TabLayout getMainTl() {
        return mMainTl;
    }

    public abstract List<ItemTab> getTabList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_base_tab, container, false);
        mMainTl = (TabLayout) view.findViewById(R.id.main_tl);
        ButterKnife.bind(this, view);
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
            TabLayout mainTl = getMainTl();

            for (int i = 0; i < tabList.size(); i++) {
                ItemTab itemTab = tabList.get(i);
                mainTl.addTab(mainTl.newTab().setText(itemTab.getTitle()));
            }
            mainTl.addOnTabSelectedListener(new TLSelectedListener(tabList));
            tabSelected(0, tabList);
        }
    }

    private class TLSelectedListener implements OnTabSelectedListener {

        private List<ItemTab> mItemTabList;

        public TLSelectedListener(List<ItemTab> itemTabList) {
            mItemTabList = itemTabList;
        }

        @Override
        public void onTabSelected(Tab tab) {
           tabSelected(tab.getPosition(), mItemTabList);
        }

        @Override
        public void onTabUnselected(Tab tab) {

        }

        @Override
        public void onTabReselected(Tab tab) {

        }
    }

    private void tabSelected(int position, List<ItemTab> mItemTabList) {

        if (mItemTabList.size() > position) {

            FragmentTransaction transaction = getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            Fragment fragment = mItemTabList.get(position).getFragment();

            if (!fragment.isAdded()) {
                transaction.add(R.id.base_tab_fl, fragment);
            }

            if (mLastFragment != null && !mLastFragment.isHidden()) {
                transaction.hide(mLastFragment);
            }
            if (fragment != null && fragment.isHidden()) {
                transaction.show(fragment);
            }
            transaction.commit();

            mLastFragment = fragment;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class ItemTab {
        private String mTitle;
        private Fragment mFragment;

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
