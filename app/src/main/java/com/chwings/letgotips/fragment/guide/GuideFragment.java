package com.chwings.letgotips.fragment.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.release.LocalImageGridAvtivty;
import com.chwings.letgotips.adapter.guide.guide.GuiTabPageIndicatorAdapter;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.view.ViewPagerIndicator;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * 指南 大模块
 */
public class GuideFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.indicator)
    ViewPagerIndicator indicator;

    @BindView(R.id.title)
    TitleBarView title;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private GuiTabPageIndicatorAdapter mTabAdapter ;

    private List<String> mTitles = Arrays.asList("吃", "LET GO", "喝", "玩",
            "乐", "购");

    private PopupWindow mPopupWindow ;

    private View mPopView ;

    private Animation inAnimation ;

    private Animation outAnimation ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guide;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTabAdapter = new GuiTabPageIndicatorAdapter(getChildFragmentManager());
        viewPager.setAdapter(mTabAdapter);
        indicator.setTabItemTitles(mTitles);
        indicator.setViewPager(viewPager , 1);
        title.setRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG , "title right");
                initPopupWindow();
                if(inAnimation == null){
                    inAnimation = AnimationUtils.loadAnimation(GuideFragment.this.getActivity() , R.anim.guide_alpha_in);
                }
                indicator.startAnimation(inAnimation);
                viewPager.startAnimation(inAnimation);
                mPopupWindow.showAsDropDown(title);
            }
        });
    }

    private void initPopupWindow(){
        if(mPopView == null){
            mPopView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_guide , null);
            mPopView.findViewById(R.id.rl_notes).setOnClickListener(this);
            mPopView.findViewById(R.id.rl_information).setOnClickListener(this);
        }
        if(mPopupWindow == null){
            mPopupWindow = new PopupWindow(mPopView
                    , LinearLayout.LayoutParams.MATCH_PARENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT
                    , true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    outAnimation = AnimationUtils.loadAnimation(GuideFragment.this.getActivity() , R.anim.guide_alpha_out);
                    indicator.startAnimation(outAnimation);
                    viewPager.startAnimation(outAnimation);
                }
            });
            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
                    R.mipmap.ic_launcher));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.rl_notes:
                Log.d(TAG , "笔记");
            case R.id.rl_information:
                Intent intent = new Intent(getActivity() , LocalImageGridAvtivty.class);
                startActivity(intent);
                break;
        }
        mPopupWindow.dismiss();
    }


}
