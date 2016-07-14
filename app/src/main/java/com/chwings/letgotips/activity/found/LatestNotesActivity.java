package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.itemDecoration.StaggeredGridItemDecoration;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 最新笔记
 */
public class LatestNotesActivity extends BaseActivity {

    @BindView(R.id.rv_latest_notes)
    RecyclerView rv_latest_notes;

    @Override
    public int getLayoutId() {
        return R.layout.activity_latest_notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv_latest_notes.setLayoutManager(new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL));
        rv_latest_notes.addItemDecoration(new StaggeredGridItemDecoration(2 , 5 , 0 , 5 , 5));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i11111111);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(this , R.layout.item_guide_note , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                ImageView iv_fun = (ImageView)holder.getView(R.id.iv_fun);
                Glide.with(LatestNotesActivity.this).load(integer).into(iv_fun);
            }
        };
        rv_latest_notes.setAdapter(adapter);
    }
}
