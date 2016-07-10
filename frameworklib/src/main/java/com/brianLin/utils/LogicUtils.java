package com.brianLin.utils;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 逻辑工具类
 */
public class LogicUtils {

    /**
     * 设置屏幕控件平均值
     * @param view    控件
     * @param count   每屏多少个
     * @param divider 间隔
     * @param margin 左右间隔
     * @return
     */
    public static void setScreenControlsAverageByFrameLayout(View view , int count , int divider , int margin , View otherView){
        if(view == null){
            return ;
        }
        int screenWidth = ScreenUtils.getScreenWidth(view.getContext()); //屏幕宽度
        if(screenWidth > 1920){
            return ;
        }else{
            divider = DensityUtils.dp2px(view.getContext() , divider);
            margin = DensityUtils.dp2px(view.getContext() , margin);
            int viewWidth = screenWidth - margin * 2 - (count - 1 ) * divider ;
            viewWidth = viewWidth / count ;
            if(viewWidth > 0){
                int otherHeight = 0;
                if(otherView != null){
                    int w = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    int h = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    otherView.measure(w, h);
                    int height = otherView.getMeasuredHeight();
                    int width = otherView.getMeasuredWidth();
                    Log.d("DEBUG" , "otherView = " + height);
                    otherHeight = height ;
                }
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewWidth , viewWidth + otherHeight);
                params.setMargins(0 , 0 ,margin , 0);
                view.setLayoutParams(params);
            }
        }
    }

}
