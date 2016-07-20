package com.chwings.letgotips.adapter.guide.message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chwings.letgotips.fragment.message.MessageAtFragment;
import com.chwings.letgotips.fragment.message.MessageCommentFragment;
import com.chwings.letgotips.fragment.message.MessageNoticeFragment;
import com.chwings.letgotips.fragment.message.MessageQuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息首页 viewPager适配器
 */
public class MessageViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments ;

    private String[] titles = new String[]{"@我" , "评论" , "问答" , "通知"};

    public MessageViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MessageAtFragment());
        fragments.add(new MessageCommentFragment());
        fragments.add(new MessageQuestionFragment());
        fragments.add(new MessageNoticeFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
