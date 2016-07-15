package com.chwings.letgotips.activity.found;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
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
import com.chwings.letgotips.adapter.guide.guide.GuideFunAdapter;
import com.chwings.letgotips.itemDecoration.DSpanSizeLookup;
import com.chwings.letgotips.itemDecoration.StaggeredGridItemDecoration;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.chwings.letgotips.view.DStaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 地标详情
 */
public class AddressDetailedActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private View mHeaderView;
    private DRecyclerViewAdapter dRecyclerViewAdapter;
    private RecyclerViewAdapter mAdapter;
    private DStaggeredGridLayoutManager staggeLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_detailed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHeader();

        List<TestGuideFunBean> mData = new ArrayList<>();
        mData.add(new TestGuideFunBean(R.drawable.i33333333333 , "吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i22222222222 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444 , "吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃吃"));
        mAdapter = new RecyclerViewAdapter(mData, this);
        dRecyclerViewAdapter = new DRecyclerViewAdapter(mAdapter);
        staggeLayoutManager = new DStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeLayoutManager.setSpanSizeLookup(new DSpanSizeLookup(dRecyclerViewAdapter, staggeLayoutManager.getSpanCount()));
        dRecyclerViewAdapter.addHeadView(mHeaderView);
        recyclerView.setLayoutManager(staggeLayoutManager);
        recyclerView.addItemDecoration(new StaggeredGridItemDecoration(2 , 5 , 0 , 5 , 5));
        GuideFunAdapter adapter = new GuideFunAdapter(this , mData);
        recyclerView.setAdapter(dRecyclerViewAdapter);
    }

    private void initHeader(){
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header_address_detailed , null);
    }

    /**
     * 继承封装过的DBaseRecyclerViewAdapter
     */
    class RecyclerViewAdapter extends DBaseRecyclerViewAdapter<TestGuideFunBean> {

        public RecyclerViewAdapter(List<TestGuideFunBean> mDatas, Context mContext) {
            super(mDatas, mContext);
        }

        @Override
        protected DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType) {
            return new RecyclerViewAdapterHoder(parent, R.layout.item_guide_note, this);
        }

    }

    /**
     * 继承封装过的 DBaseRecyclerViewHolder
     */
    class RecyclerViewAdapterHoder extends DBaseRecyclerViewHolder<TestGuideFunBean> implements View.OnClickListener {

        private TextView tv_content;
        private ImageView iv_fun;


        public RecyclerViewAdapterHoder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {
            super(parent, res, dBaseRecyclerViewAdapter);
            tv_content = $(R.id.tv_instructions);
            iv_fun = $(R.id.iv_fun);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setData(TestGuideFunBean data, int position) {
            tv_content.setText(data.instructions);
            Glide.with(AddressDetailedActivity.this).load(data.resId).into(iv_fun);
        }


        @Override
        public void onClick(View v) {
            if (getOnClickItemListsner() != null) {
                getOnClickItemListsner().onClick(getAdapterItemPosition());
            }
        }
    }
}
