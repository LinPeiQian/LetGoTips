package com.chwings.letgotips.testCase;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestPrt extends BaseActivity {

    @BindView(R.id.test_recycler_view)
    RecyclerView test_recycler_view;

    @BindView(R.id.test_recycler_view_frame)
    PtrClassicFrameLayout test_recycler_view_frame;

    private RecyclerAdapterWithHF mAdapter;

    private  List<String> datas ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_prt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList<>();
        for(int i = 0 ; i < 60 ; i ++){
            datas.add("Hello " + i);
        }
        CommonAdapter<String> adapter = new CommonAdapter<String>(this , R.layout.item_share_pop , datas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_platform , s);
            }
        };
        mAdapter = new RecyclerAdapterWithHF(adapter);
        test_recycler_view.setLayoutManager(new GridLayoutManager(this , 2));
        test_recycler_view.setAdapter(mAdapter);
        test_recycler_view_frame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.d(TAG , "出发刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        test_recycler_view_frame.refreshComplete();
                        test_recycler_view_frame.setLoadMoreEnable(true);
                    }
                }, 2000);
            }
        });

        test_recycler_view_frame.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        datas.add(new String("  RecyclerView item  - add " + 1));
                        mAdapter.notifyDataSetChanged();
                        test_recycler_view_frame.loadMoreComplete(true);
                    }
                }, 1000);
            }
        });
        test_recycler_view_frame.autoRefresh(true);
    }
}
