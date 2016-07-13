package com.chwings.letgotips.fragment.guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.guide.CommentActivity;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.chwings.letgotips.view.FullyLinearLayoutManager;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends BaseFragment {

    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv_comment.setLayoutManager(new FullyLinearLayoutManager(getActivity() , LinearLayout.VERTICAL , true));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.ic_launcher);
        datas.add(R.drawable.i33333333333);
        rv_comment.setAdapter(new CommonAdapter<Integer>(this.getActivity() , R.layout.item_comment , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                Glide.with(CommentFragment.this.getActivity()).load(integer)
                        .transform(new GlideCircleTransform(CommentFragment.this.getActivity()))
                        .into((ImageView)holder.getView(R.id.iv_avater));
            }

        });
    }

    @OnClick(R.id.tv_allComment)
    public void onAllComment(View view){
        Intent intent = new Intent(getActivity() , CommentActivity.class);
        startActivity(intent);
    }
}
