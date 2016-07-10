package com.chwings.letgotips.adapter.guide.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.fragment.guide.DrinkFragment;
import com.chwings.letgotips.fragment.guide.EatFragment;
import com.chwings.letgotips.fragment.guide.FunFragment;
import com.chwings.letgotips.fragment.guide.LetGoFragment;
import com.chwings.letgotips.fragment.guide.PlayFragment;
import com.chwings.letgotips.fragment.guide.ShopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jensen on 2016/7/7.
 */
public class GuiTabPageIndicatorAdapter extends FragmentPagerAdapter {

    private static final String[] TITLE = new String[] { "吃", "LET GO", "喝", "玩",
            "乐", "购"};

    private List<BaseFragment> mFragmentList ;

    public GuiTabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new EatFragment());
        mFragmentList.add(new LetGoFragment());
        mFragmentList.add(new DrinkFragment());
        mFragmentList.add(new PlayFragment());
        mFragmentList.add(new FunFragment());
        mFragmentList.add(new ShopFragment());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position % TITLE.length];
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

}
