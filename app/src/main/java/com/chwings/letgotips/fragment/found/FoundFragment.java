package com.chwings.letgotips.fragment.found;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.found.AddressDetailedActivity;
import com.chwings.letgotips.activity.found.FoundMoreQuestionActivity;
import com.chwings.letgotips.activity.found.HotTopicMainActivity;
import com.chwings.letgotips.activity.found.HotQuestionActivity;
import com.chwings.letgotips.activity.found.LatestNotesActivity;
import com.chwings.letgotips.activity.found.ProjectActivity;
import com.chwings.letgotips.activity.found.SearchActivity;
import com.chwings.letgotips.activity.found.TalentRankActivity;
import com.chwings.letgotips.adapter.guide.found.FoundHomeViewPagerAdapter;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.itemDecoration.GridItemDecoration;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.view.AutoScrollViewPagerWithIndicator;
import com.chwings.letgotips.view.FullyGridLayoutManager;
import com.chwings.letgotips.view.FullyLinearLayoutManager;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoundFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.banner)
    AutoScrollViewPagerWithIndicator banner;

    @BindView(R.id.rv_question)
    RecyclerView rv_question;

    @BindView(R.id.rv_project)
    RecyclerView rv_project;

    @BindView(R.id.rv_address)
    RecyclerView rv_address;

    @BindView(R.id.rv_talent)
    RecyclerView rv_talent;

    private FoundHomeViewPagerAdapter mBannerAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBanner();
        initQuestionRecyclerView(rv_question);
        initProjectRecyclerView();
        iniAddressRecyclerView(rv_address);
        initTalentRecyclerView();
    }

    private void initBanner(){
        List<Integer> bannerDatas = new ArrayList<>();
        bannerDatas.add(R.drawable.i33333333333);
        bannerDatas.add(R.drawable.i11111111);
        bannerDatas.add(R.drawable.i44444444);
        mBannerAdapter = new FoundHomeViewPagerAdapter(getActivity() , bannerDatas);
        mBannerAdapter.setInfiniteLoop(true);
        banner.setAdapter(mBannerAdapter);
        banner.getViewPager().startAutoScroll();
    }

    private void initQuestionRecyclerView(RecyclerView view){
        view.setLayoutManager(new FullyLinearLayoutManager(getActivity() , LinearLayout.HORIZONTAL , false));
        List<Integer> questionData = new ArrayList<>();
        questionData.add(R.drawable.i44444444);
        questionData.add(R.drawable.i33333333333);
        questionData.add(R.drawable.i22222222222);
        questionData.add(R.drawable.i11111111);
        questionData.add(R.drawable.i22222222222);
        questionData.add(R.drawable.i44444444);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_question_found_home , questionData) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView imageView = (ImageView)holder.getView(R.id.imageView);
                Glide.with(FoundFragment.this.getActivity()).load(integer).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(FoundFragment.this.getActivity().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.img_radius));
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        };
        view.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.padding) , 0 , 0 , 0 , false));
        view.setAdapter(adapter);
    }

    private void initProjectRecyclerView(){
        rv_project.setLayoutManager(new FullyGridLayoutManager(getActivity() , 3));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i44444444);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_project_found_home , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView imageView = (ImageView)holder.getView(R.id.imageView);
                Glide.with(FoundFragment.this.getActivity()).load(integer).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(FoundFragment.this.getActivity().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.img_radius));
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        };
        rv_project.addItemDecoration(new GridItemDecoration(3 , getResources().getDimensionPixelSize(R.dimen.padding ), 0 , getResources().getDimensionPixelSize(R.dimen.padding ) , 0));
        rv_project.setAdapter(adapter);
    }

    private void initTalentRecyclerView(){
        rv_talent.setLayoutManager(new FullyGridLayoutManager(getActivity() , 3));
        List<Integer> questionData = new ArrayList<>();
        questionData.add(R.drawable.i44444444);
        questionData.add(R.drawable.i33333333333);
        questionData.add(R.drawable.i22222222222);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_talent_found_home , questionData) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView imageView = (ImageView)holder.getView(R.id.imageView);
                Glide.with(FoundFragment.this.getActivity()).load(integer).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(FoundFragment.this.getActivity().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.img_radius));
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        };
        rv_talent.addItemDecoration(new GridItemDecoration(3 , getResources().getDimensionPixelSize(R.dimen.padding ) , 0 , 0 , 0));
        rv_talent.setAdapter(adapter);
    }
    private void iniAddressRecyclerView(RecyclerView view){
        view.setLayoutManager(new FullyLinearLayoutManager(getActivity() , LinearLayout.HORIZONTAL , false));
        List<Integer> questionData = new ArrayList<>();
        questionData.add(R.drawable.i44444444);
        questionData.add(R.drawable.i33333333333);
        questionData.add(R.drawable.i22222222222);
        questionData.add(R.drawable.i11111111);
        questionData.add(R.drawable.i22222222222);
        questionData.add(R.drawable.i44444444);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_question_found_home , questionData) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView imageView = (ImageView)holder.getView(R.id.imageView);
                Glide.with(FoundFragment.this.getActivity()).load(integer).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(FoundFragment.this.getActivity().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.img_radius));
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(getActivity() , AddressDetailedActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        view.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.padding) , 0 , 0 , 0 , false));
        view.setAdapter(adapter);
    }


    @OnClick({R.id.tv_more_project , R.id.tv_latest_notes , R.id.tv_more_question
                ,R.id.ll_hot_question , R.id.ll_hot_person , R.id.tv_more_talent_rank
                  , R.id.tv_search})
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.tv_more_project:
                intent = new Intent(getActivity() , ProjectActivity.class);
                break;
            case R.id.tv_latest_notes:
                intent = new Intent(getActivity() , LatestNotesActivity.class);
                break;
            case R.id.tv_more_question:
                intent = new Intent(getActivity() , FoundMoreQuestionActivity.class);
                break;
            case R.id.ll_hot_question:
                intent = new Intent(getActivity() , HotQuestionActivity.class);
                break;
            case R.id.ll_hot_person:
                intent = new Intent(getActivity() , HotTopicMainActivity.class);
                break;
            case R.id.tv_more_talent_rank:
                intent = new Intent(getActivity() , TalentRankActivity.class);
                break;
            case R.id.tv_search:
                intent = new Intent(getActivity() , SearchActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
