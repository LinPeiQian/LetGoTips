package com.chwings.letgotips.adapter.guide.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.fragment.guide.CommentFragment;
import com.chwings.letgotips.fragment.guide.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论 / 问答  适配器
 */
public class CommentQuestionAdapter extends FragmentPagerAdapter{

    private List<BaseFragment> mFragmentList ;

    private final String TAG = getClass().getSimpleName();

    public CommentQuestionAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new CommentFragment());
        mFragmentList.add(new QuestionFragment());
    }


    @Override
    public Fragment getItem(int position) {
        Log.d(TAG , "CommentQuestionAdapter getItem position = "+position);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "评论";
        }else if(position == 1){
            return "回答";
        }
        return super.getPageTitle(position);
    }
}
