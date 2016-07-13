package com.chwings.letgotips.fragment.guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.guide.QuestionActivity;
import com.chwings.letgotips.fragment.BaseFragment;
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
public class QuestionFragment extends BaseFragment {

    @BindView(R.id.rv_question)
    RecyclerView rv_question;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_question;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new FullyLinearLayoutManager(getActivity() , LinearLayout.VERTICAL , true);
        manager.setStackFromEnd(true);
        rv_question.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        rv_question.setAdapter(new CommonAdapter<String>(getActivity() , R.layout.item_question , datas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {

            }
        });
    }

    @OnClick(R.id.tv_allQuestion)
    public void onAllQuestion(View view){
        //全部问答
        Intent intent = new Intent(getActivity() , QuestionActivity.class);
        startActivity(intent);
    }

}
