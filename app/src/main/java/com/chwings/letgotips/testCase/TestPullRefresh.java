package com.chwings.letgotips.testCase;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestPullRefresh extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipe_target)
    RecyclerView recyclerView;

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private CommonAdapter<String> mAdapter ;

    private List<String> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_pull_refresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        for(int i = 0 ; i < 40 ; i ++){
            mDatas.add("Hello" + i);
        }
        mAdapter = new CommonAdapter<String>(this , R.layout.item_share_pop , mDatas) {
            @Override
            public void convert(ViewHolder holder, String string, int position) {
                holder.setText(R.id.tv_platform , string);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
