package com.brianLin.utils;

import android.view.View;

/**
 * Created by Jensen on 2016/7/14.
 */
public class ViewUtils {

    public static int[] getViewSize(View view){
        if(view != null){
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth();
            int[] result = new int[]{width , height};
            return result;
        }
        return null;
    }

}
