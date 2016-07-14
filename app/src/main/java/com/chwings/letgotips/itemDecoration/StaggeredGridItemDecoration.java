package com.chwings.letgotips.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Jensen on 2016/7/14.
 */
public class StaggeredGridItemDecoration extends RecyclerView.ItemDecoration{

    private int left;
    private int right ;
    private int top ;
    private int bottom ;

    private int spanCount ;

    public StaggeredGridItemDecoration(int spanCount , int left , int top , int right , int bottom ) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


        outRect.bottom = bottom;
        int index =  parent.getChildPosition(view) + 1;
        if(index > spanCount){
            outRect.top = top;
        }
        if(index % spanCount != 0){
            outRect.right = right;
        }else{
            outRect.left = left;
        }
    }

}
