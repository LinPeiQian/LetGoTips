package com.chwings.letgotips.testCase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.DBaseRecyclerViewAdapter;
import com.chwings.letgotips.adapter.guide.DBaseRecyclerViewHolder;
import com.chwings.letgotips.adapter.guide.DRecyclerViewAdapter;
import com.chwings.letgotips.itemDecoration.DSpanSizeLookup;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.chwings.letgotips.view.DStaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 测试  使用一个recyclerView 加header的方式实现笔记详情
 */
public class TestNotesDetaildActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private DStaggeredGridLayoutManager staggeLayoutManager;
    private MyAdpater myAdpater;
    private DRecyclerViewAdapter dRecyclerViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_notes_detaild;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TestGuideFunBean> mData = new ArrayList<>();
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111 , "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i22222222222 , "购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444 , "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444 , "购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111 , "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "购购购购购购购购购购"));

        myAdpater = new MyAdpater(mData, this);
        dRecyclerViewAdapter = new DRecyclerViewAdapter(myAdpater);

        staggeLayoutManager = new DStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeLayoutManager.setSpanSizeLookup(new DSpanSizeLookup(dRecyclerViewAdapter, staggeLayoutManager.getSpanCount()));

        TextView tv = new TextView(this);
        tv.setText("heade1r");
        tv.setGravity(Gravity.CENTER);
        TextView tv2 = new TextView(this);
        tv2.setText("header2");
        tv2.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 200);
        tv.setLayoutParams(params);
        tv2.setLayoutParams(params);
        dRecyclerViewAdapter.addHeadView(tv);
        dRecyclerViewAdapter.addHeadView(tv2);

        recyclerView.setLayoutManager(staggeLayoutManager);
        recyclerView.setAdapter(dRecyclerViewAdapter);
    }


    /**
     * 继承封装过的DBaseRecyclerViewAdapter
     */
    class MyAdpater extends DBaseRecyclerViewAdapter<TestGuideFunBean> {

        public MyAdpater(List<TestGuideFunBean> mDatas, Context mContext) {
            super(mDatas, mContext);
        }

        @Override
        protected DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType) {
            return new MyViewHoder(parent, R.layout.item_guide_note, this);
        }

    }


    /**
     * 继承封装过的 DBaseRecyclerViewHolder
     */
    class MyViewHoder extends DBaseRecyclerViewHolder<TestGuideFunBean> implements View.OnClickListener {

        private TextView tv_content;
        private ImageView iv_fun;


        public MyViewHoder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {
            super(parent, res, dBaseRecyclerViewAdapter);
            tv_content = $(R.id.tv_instructions);
            iv_fun = $(R.id.iv_fun);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setData(TestGuideFunBean data, int position) {
            tv_content.setText(data.instructions);
            Glide.with(TestNotesDetaildActivity.this).load(data.resId).into(iv_fun);
        }


        @Override
        public void onClick(View v) {
            if (getOnClickItemListsner() != null) {
                getOnClickItemListsner().onClick(getAdapterItemPosition());
            }
        }
    }

}
