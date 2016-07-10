package com.chwings.letgotips.activity.guide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.testCase.bean.TestHighQualityTravelBean;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HighQualityTravelActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    private CommonAdapter<TestHighQualityTravelBean> mAdapter ;

    private List<TestHighQualityTravelBean> mData = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_high_quality_travel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.addItemDecoration(new SpaceItemDecoration(0 , 0 , 5 , 0 , false));
        mData.add(new TestHighQualityTravelBean(R.drawable.i44444444 , "春霞秋冬"));
        mData.add(new TestHighQualityTravelBean(R.drawable.i22222222222 , "超人迪加"));
        mData.add(new TestHighQualityTravelBean(R.drawable.i11111111 , "四驱兄弟"));
        mData.add(new TestHighQualityTravelBean(R.drawable.i33333333333 , "数码暴龙"));
        mData.add(new TestHighQualityTravelBean(R.drawable.ic_launcher , "415215858"));
        mAdapter = new CommonAdapter<TestHighQualityTravelBean>(this , R.layout.item_high_quality_travel , mData) {
            @Override
            public void convert(ViewHolder holder, TestHighQualityTravelBean testHighQualityTravelBean, int position) {
                Glide.with(HighQualityTravelActivity.this).load(testHighQualityTravelBean.resId).into((ImageView)holder.getView(R.id.iv_cover));
                holder.setText(R.id.tv_cover , testHighQualityTravelBean.title);
            }
        };
        recycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                startActivity(TravelDetailedActivity.class);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

}
