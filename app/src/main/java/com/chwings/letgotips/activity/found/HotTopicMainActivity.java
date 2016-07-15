package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HotTopicMainActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ho_topic_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.i33333333333);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(this , R.layout.item_found_hot_topic_main , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView iv_avater = (ImageView)holder.getView(R.id.iv_avater);
                Glide.with(HotTopicMainActivity.this).load(integer)
                        .transform(new GlideCircleTransform(HotTopicMainActivity.this))
                        .into(iv_avater);
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
