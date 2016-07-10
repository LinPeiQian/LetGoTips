package com.chwings.letgotips.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 横向recyclerView的间隔设置
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int left;
    private int right ;
    private int top ;
    private int bottom ;

    private boolean hasZero;

    public SpaceItemDecoration(int left , int right , int top , int bottom , boolean hasZero) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.hasZero = hasZero;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) == 0){
            if(!hasZero){
                return ;
            }
        }
        outRect.left = left;
        outRect.right = right;
        outRect.top = top;
        outRect.bottom = bottom;

    }

}
