package com.chwings.letgotips.testCase;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.pull.IRecyclerView;
import com.chwings.letgotips.pull.OnLoadMoreListener;
import com.chwings.letgotips.pull.OnRefreshListener;
import com.chwings.letgotips.view.LoadMoreFooterView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestPullRefresh extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.iRecyclerView)
    IRecyclerView iRecyclerView;

    private CommonAdapter<String> mAdapter ;

    private List<String> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_pull_refresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i = 0 ; i < 40 ; i ++){
            mDatas.add("Hello " + i);
        }
        mAdapter = new CommonAdapter<String>(this , R.layout.item_share_pop , mDatas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_platform , s);
            }
        };
        iRecyclerView.setIAdapter(mAdapter);
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });
    }

    @Override
    public void onLoadMore(final View loadMoreView) {
        iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
            }
        } , 3000);
    }

    @Override
    public void onRefresh() {
        iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(false);
            }
        } , 2000);
    }
}
