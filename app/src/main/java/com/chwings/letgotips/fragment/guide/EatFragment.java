package com.chwings.letgotips.fragment.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.guide.NotesDetailedActivity;
import com.chwings.letgotips.adapter.guide.guide.GuideFunAdapter;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 指南中的 吃
 */
public class EatFragment extends BaseFragment implements OnItemClickListener<TestGuideFunBean>{

    @BindView(R.id.rv_fun)
    RecyclerView rv_fun;

    private List<TestGuideFunBean> mData ;

    private GuideFunAdapter mAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guide_fun;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        rv_fun.setLayoutManager(new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL));
        int decoration = getResources().getDimensionPixelSize(R.dimen.padding);
        rv_fun.addItemDecoration(new SpaceItemDecoration(decoration , 0 , decoration , 0 , true));
        mAdapter = new GuideFunAdapter(getActivity() , mData);
        mAdapter.setOnItemClickListener(this);
        rv_fun.setAdapter(mAdapter);
    }

    private void initData(){
        mData = new ArrayList<>();
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i22222222222 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
    }


    @Override
    public void onItemClick(ViewGroup parent, View view, TestGuideFunBean testGuideFunBean, int position) {
        Intent intent = new Intent(getActivity() , NotesDetailedActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, TestGuideFunBean testGuideFunBean, int position) {
        return false;
    }
}
