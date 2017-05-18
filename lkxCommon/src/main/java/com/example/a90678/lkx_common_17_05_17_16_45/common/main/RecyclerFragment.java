package com.example.a90678.lkx_common_17_05_17_16_45.common.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a90678.lkx_common_17_05_17_16_45.R;
import com.example.a90678.lkx_common_17_05_17_16_45.common.presenter.BaseLoadingPresenter;
import com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity.BaseLoadingFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * --------------------------------------------
 * auther :  Lvfq
 * 2016/12/27 0:32
 * description ：
 * -------------------------------------------
 **/
public class RecyclerFragment extends BaseLoadingFragment {

    private View view;
    private RecyclerView recyclerView;
    private List<String> mList = new ArrayList<>();
    private RecyclAdapter mAdapter;

    public static RecyclerFragment newInstance() {

        Bundle args = new Bundle();
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BaseLoadingPresenter getPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler, container, false);

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecyclAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("item" + i);
        }
    }


    private class RecyclAdapter extends RecyclerView.Adapter<RecyclAdapter.MyHolder> {

        private List<String> list;

        public RecyclAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            String item = list.get(position);
            holder.tv_title.setText(item);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            TextView tv_title;

            public MyHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }


}
