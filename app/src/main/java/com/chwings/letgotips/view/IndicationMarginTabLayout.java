package com.chwings.letgotips.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.brianLin.utils.DensityUtils;

import java.lang.reflect.Field;

/**
 * Created by Jensen on 2016/7/20.
 */
public class IndicationMarginTabLayout extends TabLayout{

    public IndicationMarginTabLayout(Context context) {
        this(context, null);
    }

    public IndicationMarginTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicationMarginTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setupWithViewPager(ViewPager viewPager , int left , int top , int right , int bottom){
        setupWithViewPager(viewPager);
        Class<?> tablayout = getClass().getSuperclass();
        Field tabStrip = null;
        try {
            tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab= (LinearLayout) tabStrip.get(this);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0,0,0,0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMargins(DensityUtils.dp2px(getContext() , left)
                        , DensityUtils.dp2px(getContext() , top)
                        , DensityUtils.dp2px(getContext() , right)
                        , DensityUtils.dp2px(getContext() , bottom));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
