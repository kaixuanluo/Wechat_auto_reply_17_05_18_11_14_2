package com.example.a90678.wechat_auto_reply_17_05_18_11_14.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseViewStatus;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity.BaseActivity;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity.BaseListFragment;
import com.example.a90678.wechat_auto_reply_17_05_18_11_14.bean.MainMenuResult;
import com.example.a90678.wechat_auto_reply_17_05_18_11_14.presenter.MainMenuPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    public Fragment getFragment() {
        return MainFragment.newInstance();
    }

    public static class MainFragment extends BaseListFragment<MainMenuResult.MainMenu, MainMenuResult,
            MainMenuPresenter, MainFragment.MainViewHolde> implements BaseViewStatus<MainMenuResult> {
        public static MainFragment newInstance() {

            Bundle args = new Bundle();

            MainFragment fragment = new MainFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public MainMenuPresenter getPresenter() {
            return new MainMenuPresenter(this, this);
        }

        @Override
        protected MainViewHolde createItemViewHolder(ViewGroup parent) {
            return null;
        }

        @Override
        protected void bindItemViewHolder(MainViewHolde holder, MainMenuResult.MainMenu mainMenu, int position) {

        }

        @Override
        protected void loadStartData() {

        }

        @Override
        protected void loadMoreData(MainMenuResult.MainMenu mainMenu) {

        }

        @Override
        public void fillView(MainMenuResult data) {

        }

        class MainViewHolde extends RecyclerView.ViewHolder {

            public MainViewHolde(View itemView) {
                super(itemView);
            }
        }
    }
}
