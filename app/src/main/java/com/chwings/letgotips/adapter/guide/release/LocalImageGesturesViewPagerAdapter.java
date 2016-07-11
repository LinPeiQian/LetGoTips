package com.chwings.letgotips.adapter.guide.release;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.bean.LocalPicBean;
import com.chwings.letgotips.view.ZoomableImageView;

import java.util.List;

/**
 * 手势查看图片 viewpager 适配器
 */
public class LocalImageGesturesViewPagerAdapter extends PagerAdapter{

    private List<LocalPicBean> allList ;

    public LocalImageGesturesViewPagerAdapter(List<LocalPicBean> allLocalPicBean){
        allList = allLocalPicBean ;
    }

    @Override
    public int getCount() {
        return allList == null ? 0 : allList.size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ZoomableImageView photoView = new ZoomableImageView(container.getContext());
        Glide.with(container.getContext()).load(allList.get(position).path).into(photoView);
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
