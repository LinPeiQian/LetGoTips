package com.chwings.letgotips.fragment.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 消息中的问答
 */
public class MessageQuestionFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView rv_question ;

    @Override
    public int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv_question.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i44444444);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_question_message , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                Glide.with(getActivity()).load(integer).transform(new GlideCircleTransform(getActivity())).into((ImageView)holder.getView(R.id.iv_avater));
            }
        };
        rv_question.setAdapter(adapter);
    }
}
