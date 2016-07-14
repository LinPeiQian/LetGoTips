package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HotQuestionActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayout.VERTICAL , false));
        List<String> datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        CommonAdapter<String> adapter = new CommonAdapter<String>(this , R.layout.item_found_question , datas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {

            }
        };
        recyclerView.setAdapter(adapter);

    }
}
