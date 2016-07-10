package com.chwings.letgotips.itemDecoration;

import android.support.v7.widget.GridLayoutManager;

import com.chwings.letgotips.adapter.guide.DRecyclerViewAdapter;

/**
 * Created by User on 2016/7/10.
 */
public class DSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private DRecyclerViewAdapter adapter;
    private int mSpanSize = 1;

    public DSpanSizeLookup(DRecyclerViewAdapter adapter, int spanSize) {
        this.adapter = adapter;
        this.mSpanSize = spanSize;
    }

    @Override
    public int getSpanSize(int position) {
        boolean isHeaderOrFooterOrRandom = adapter.isHeader(position) || adapter.isFooter(position)
                || adapter.isRandom(position);
        return isHeaderOrFooterOrRandom ? mSpanSize : 1;
    }
}