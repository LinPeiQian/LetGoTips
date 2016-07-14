package com.chwings.letgotips.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jensen on 2016/7/14.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration{

    private int left;
    private int right ;
    private int top ;
    private int bottom ;

    private int spanCount ;

    public GridItemDecoration(int spanCount , int left , int right , int top , int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) % spanCount  == 0){
            outRect.right = right;
            outRect.top = top;
            outRect.bottom = bottom;
        }else{
            outRect.left = left;
            outRect.right = right;
            outRect.top = top;
            outRect.bottom = bottom;
        }
    }

}
