package com.chwings.letgotips.activity.found;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.brianLin.adapter.MyBaseAdapter;
import com.brianLin.adapter.MyViewHolder;
import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 排行榜
 */
public class RankActivity extends BaseActivity {

    @BindView(R.id.gridView)
    GridView gridView;

    private GridViewAdapter mAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        mAdapter = new GridViewAdapter(this);
        mAdapter.setData(datas);
        gridView.setAdapter(mAdapter);
    }

    class GridViewAdapter extends MyBaseAdapter<Integer>{

        public GridViewAdapter(Context context) {
            super(context);
        }

        @Override
        public int getConvertViewId(int position) {
            return R.layout.item_found_rank;
        }

        @Override
        public MyViewHolder<Integer> getNewHolder(int position) {
            return new ViewHolder();
        }

        class ViewHolder extends MyViewHolder<Integer>{

            private ImageView iv_cover ;

            @Override
            public void initHolder(View view, int position) {
                iv_cover = (ImageView)view.findViewById(R.id.iv_cover);
            }

            @Override
            public void loadData(Integer data, int position) {
                Glide.with(iv_cover.getContext()).load(data).into(iv_cover);
            }
        }
    }
}
