package com.chwings.letgotips.activity.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brianLin.utils.ScreenUtils;
import com.brianLin.view.TitleBarView;
import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.DBaseRecyclerViewAdapter;
import com.chwings.letgotips.adapter.guide.DBaseRecyclerViewHolder;
import com.chwings.letgotips.adapter.guide.DRecyclerViewAdapter;
import com.chwings.letgotips.adapter.guide.guide.CommentQuestionAdapter;
import com.chwings.letgotips.adapter.guide.guide.NotesDetailedViewPagerAdapter;
import com.chwings.letgotips.bean.AddedLabelInfoBean;
import com.chwings.letgotips.bean.LabelBean;
import com.chwings.letgotips.bean.LabelEnum;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.fragment.guide.NotesLabelFragment;
import com.chwings.letgotips.itemDecoration.DSpanSizeLookup;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.chwings.letgotips.view.DStaggeredGridLayoutManager;
import com.chwings.letgotips.view.LabelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 笔记详情
 */
public class NotesDetailedActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_backup)
    RelativeLayout rl_backup;
    @BindView(R.id.title)
    TitleBarView title;
    RelativeLayout rl_label;

    private ViewPager imViewPager;
    private ViewPager fmViewPager;
    private RelativeLayout rl_native;
    private RelativeLayout rl_indicator;
    private TextView tv_total_num;
    private TextView tv_index;
    private TabLayout tabLayout;
    private LabelView labelView;

    private int mTabLayoutHeight;

    private NotesDetailedViewPagerAdapter mViewPagerAdapter;
    private CommentQuestionAdapter mCommentQuestionAdapter;
    private List<TestGuideFunBean> mData;

    private View mHeaderView;
    private DRecyclerViewAdapter dRecyclerViewAdapter;
    private RecyclerViewAdapter mAdapter;
    private DStaggeredGridLayoutManager staggeLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notes_detailed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initHeader();
        initCommentQuestionViewPagerData();
        initViewPagerData();
    }

    private void initHeader() {
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header_notes_detailed, null);
        imViewPager = (ViewPager) mHeaderView.findViewById(R.id.imViewPager);
        fmViewPager = (ViewPager) mHeaderView.findViewById(R.id.fmViewPager);
        rl_native = (RelativeLayout) mHeaderView.findViewById(R.id.rl_native);
        tabLayout = (TabLayout) mHeaderView.findViewById(R.id.tabLayout);
        tv_total_num = (TextView) mHeaderView.findViewById(R.id.tv_total_num);
        tv_index = (TextView) mHeaderView.findViewById(R.id.tv_index);
        rl_indicator = (RelativeLayout)mHeaderView.findViewById(R.id.rl_indicator);
        rl_label = (RelativeLayout)mHeaderView.findViewById(R.id.rl_label);
        mAdapter = new RecyclerViewAdapter(mData, this);
        dRecyclerViewAdapter = new DRecyclerViewAdapter(mAdapter);
        staggeLayoutManager = new DStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeLayoutManager.setSpanSizeLookup(new DSpanSizeLookup(dRecyclerViewAdapter, staggeLayoutManager.getSpanCount()));
        dRecyclerViewAdapter.addHeadView(mHeaderView);
        recyclerView.setLayoutManager(staggeLayoutManager);
        recyclerView.setAdapter(dRecyclerViewAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10 , 10 , 10 , 0 , false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] location = new int[2];
                rl_native.getLocationOnScreen(location);
                if (location[1] - ScreenUtils.getStatusHeight(NotesDetailedActivity.this) <= title.getHeight()) {
                    if (rl_indicator.getParent() != rl_backup) {
                        rl_native.removeView(rl_indicator);
                        rl_backup.setVisibility(View.VISIBLE);
                        rl_native.setVisibility(View.INVISIBLE);
                        rl_backup.addView(rl_indicator);
                    }
                }else{
                    if (rl_indicator.getParent() != rl_native) {
                        rl_backup.removeView(rl_indicator);
                        rl_native.addView(rl_indicator);
                        rl_backup.setVisibility(View.GONE);
                        rl_native.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        labelView = new LabelView(this);
        rl_label.addView(labelView);
        LabelBean bean1 = new LabelBean(2.3f ,"人民币", LabelEnum.PRICE);
        LabelBean bean2 = new LabelBean("台湾" ,"国家", LabelEnum.ADDRESS);
        LabelBean bean3 = new LabelBean("阿玛尼" ,"牌子", LabelEnum.NAME);
        AddedLabelInfoBean bean = new AddedLabelInfoBean(LabelView.THREE_LEFT_STYLE , 100 , 200  , bean1 , bean2 , bean3 );
        labelView.setLabelBean(50 , 50, bean.style , bean.labelData);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new TestGuideFunBean(R.drawable.i33333333333, "购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111, "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i22222222222, "购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444, "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i44444444, "购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i11111111, "购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i33333333333, "购购购购购购购购购购"));
        mData.add(new TestGuideFunBean(R.drawable.i33333333333, "购购购购购购购购购购"));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int[] location = new int[2];
            rl_native.getLocationOnScreen(location);
            mTabLayoutHeight = location[1] - rl_native.getHeight() - ScreenUtils.getStatusHeight(this);
        }
    }

    private void initCommentQuestionViewPagerData() {
        mCommentQuestionAdapter = new CommentQuestionAdapter(getSupportFragmentManager());
        fmViewPager.setAdapter(mCommentQuestionAdapter);
        fmViewPager.addOnPageChangeListener(onFmPageChangeListener);
        tabLayout.setupWithViewPager(fmViewPager);
    }

    private void initViewPagerData() {
        List<BaseFragment> data = new ArrayList<>();
        data.add(new NotesLabelFragment());
        data.add(new NotesLabelFragment());
        data.add(new NotesLabelFragment());
        mViewPagerAdapter = new NotesDetailedViewPagerAdapter(getSupportFragmentManager(), data);
        imViewPager.setAdapter(mViewPagerAdapter);
        tv_total_num.setText(data.size() + "");
        imViewPager.addOnPageChangeListener(onImPageChangeListener);
    }

    ViewPager.OnPageChangeListener onImPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            tv_index.setText((position + 1) + "");
        }

        @Override
        public void onPageSelected(int position) {
            tv_index.setText((position + 1) + "");
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ViewPager.OnPageChangeListener onFmPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //动态计算高度
            View view = fmViewPager.getChildAt(position);
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            int childViewHeight = view.getMeasuredHeight();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, childViewHeight);//这里设置params的高度。
            fmViewPager.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

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
            Glide.with(NotesDetailedActivity.this).load(data.resId).into(iv_fun);
        }


        @Override
        public void onClick(View v) {
            if (getOnClickItemListsner() != null) {
                getOnClickItemListsner().onClick(getAdapterItemPosition());
            }
        }
    }

}
