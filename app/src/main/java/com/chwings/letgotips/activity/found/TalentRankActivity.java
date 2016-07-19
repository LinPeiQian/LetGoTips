package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.chwings.letgotips.view.SelectableRoundedImageView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 达人榜
 */
public class TalentRankActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_talent_rank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Integer> mAdapter = new CommonAdapter<Integer>(this , R.layout.item_talent_rank , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                ImageView iv_cover = (ImageView)holder.getView(R.id.iv_cover);
                SelectableRoundedImageView iv_avater = (SelectableRoundedImageView)holder.getView(R.id.iv_avater);
                Glide.with(TalentRankActivity.this).load(integer).into(iv_cover);
                iv_avater.setOval(true);
                Glide.with(TalentRankActivity.this).load(integer).transform(new GlideCircleTransform(TalentRankActivity.this)).into(iv_avater);
            }
        };
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                startActivity(RankActivity.class);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
