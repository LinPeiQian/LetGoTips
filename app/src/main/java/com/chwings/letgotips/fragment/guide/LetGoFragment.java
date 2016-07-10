package com.chwings.letgotips.fragment.guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.guide.HighQualityTravelActivity;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.chwings.letgotips.testCase.bean.TestTravelBean;
import com.chwings.letgotips.utils.GlideRoundTransform;
import com.chwings.letgotips.view.FullyGridLayoutManager;
import com.chwings.letgotips.view.FullyLinearLayoutManager;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 指南中viewpager的 let go模块
 */
public class LetGoFragment extends BaseFragment {

    @BindView(R.id.rv_travel)
    RecyclerView rv_travel;

    @BindView(R.id.rv_information)
    RecyclerView rv_information;

    private List<TestTravelBean> travelList;
    private List<Integer> infoList ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_let_go;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initTravelData();
        initInfoData();
        rv_travel.setAdapter(new CommonAdapter<TestTravelBean>(getActivity() , R.layout.item_travel_guide , travelList) {
            @Override
            public void convert(ViewHolder holder, TestTravelBean testTravelBean, int position) {
                Glide.with(LetGoFragment.this.getActivity()).load(testTravelBean.resId)
                        .transform(new GlideRoundTransform(LetGoFragment.this.getActivity() , 8))
                        .into((ImageView) holder.getView(R.id.iv_travel));
                ((TextView)holder.getView(R.id.tv_title)).setText(testTravelBean.title);
            }
        });
        rv_information.setAdapter(new CommonAdapter<Integer>(getActivity() , R.layout.item_information_guide , infoList) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                Glide.with(LetGoFragment.this.getActivity()).load(integer)
                        .transform(new GlideRoundTransform(LetGoFragment.this.getActivity() , 8))
                        .into((ImageView) holder.getView(R.id.iv_information));
            }
        });
    }

    private void initView(){
        rv_travel.setLayoutManager(new FullyLinearLayoutManager(getActivity() , LinearLayout.HORIZONTAL , false));
        rv_information.setLayoutManager(new FullyGridLayoutManager(getActivity() , 4));
        rv_travel.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.padding) , 0 , 0 , 0, false));
    }

    private void initTravelData(){
        travelList = new ArrayList<>();
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "亲子"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "境外旗"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "国外"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "海外"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "拉到"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "点击"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "次奥"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "卧槽"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "尼玛"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "还是"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "求带飞"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "压缩"));
        travelList.add(new TestTravelBean(R.drawable.i33333333333 , "使用"));
    }

    private void initInfoData(){
        infoList = new ArrayList<>();
        infoList.add(R.drawable.i33333333333);
        infoList.add(R.drawable.i33333333333);
        infoList.add(R.drawable.i33333333333);
        infoList.add(R.drawable.i33333333333);
    }

    @OnClick(R.id.tv_all)
    public void onTravelAll(View view){
        //全部精品游记
        Intent intent = new Intent(getActivity() , HighQualityTravelActivity.class);
        getActivity().startActivity(intent);
    }

}
