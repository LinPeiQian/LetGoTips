package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FoundQuestionListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_found_question_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        List<List<Integer>> datas = new ArrayList<>();
        List<Integer> imgDatas = new ArrayList<>();
        imgDatas.add(R.drawable.i44444444);
        imgDatas.add(R.drawable.i22222222222);
        imgDatas.add(R.drawable.i33333333333);
        datas.add(imgDatas);
        datas.add(imgDatas);
        CommonAdapter<List<Integer>> adapter = new CommonAdapter<List<Integer>>(this , R.layout.item_found_question_list , datas) {
            @Override
            public void convert(ViewHolder holder, List<Integer> integers, int position) {

            }
        };
        recyclerView.setAdapter(adapter);
    }
}
