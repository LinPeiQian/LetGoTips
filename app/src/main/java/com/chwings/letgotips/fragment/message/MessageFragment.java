package com.chwings.letgotips.fragment.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.chwings.letgotips.R;
import com.chwings.letgotips.adapter.guide.message.MessageViewPagerAdapter;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.view.IndicationMarginTabLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    IndicationMarginTabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager ;

    private MessageViewPagerAdapter mViewPagerAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPagerAdapter = new MessageViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager , 20 , 0 , 20 , 0);
            }
        });



    }

}
