package com.chwings.letgotips.adapter.guide.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.chwings.letgotips.fragment.BaseFragment;

import java.util.List;

/**
 * 笔记详情 ViewPager 标签
 */
public class NotesDetailedViewPagerAdapter extends FragmentPagerAdapter{

    private List<BaseFragment> mFragmentList ;

    private final String TAG = getClass().getSimpleName();

    public NotesDetailedViewPagerAdapter(FragmentManager fm , List<BaseFragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList ;
    }

    @Override
    public int getCount() {
        return  mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG , "getItem position = "+position);
        return mFragmentList.get(position);
    }

}
