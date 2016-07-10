package com.chwings.letgotips.fragment.guide;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkFragment extends BaseFragment {

    @BindView(R.id.rv_fun)
    RecyclerView rv_fun;

    private List<TestGuideFunBean> mData ;

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
        rv_fun.setAdapter(new CommonAdapter<TestGuideFunBean>(getActivity() , R.layout.item_guide_note, mData) {
            @Override
            public void convert(ViewHolder holder, TestGuideFunBean testGuideFunBean, int position) {
                Glide.with(getActivity()).load(testGuideFunBean.resId).into((ImageView)holder.getView(R.id.iv_fun));
                ((TextView)holder.getView(R.id.tv_instructions)).setText(testGuideFunBean.instructions);
            }
        });
    }

    private void initData(){
        mData = new ArrayList<>();
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "喝喝喝喝喝喝喝喝喝喝喝喝"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111 , "喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝"));
        mData.add(new TestGuideFunBean(R.drawable.i22222222222 , "喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444 , "喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝"));
    }


}
