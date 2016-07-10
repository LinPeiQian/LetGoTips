package com.chwings.letgotips.fragment.guide;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chwings.letgotips.R;
import com.chwings.letgotips.fragment.BaseFragment;

/**
 * 指南模块中viewpager 的 吃喝玩乐 瀑布流
 */
public class GuideFunFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_guide_fun;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG , "GuideFunFragment");
    }
}
