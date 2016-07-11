package com.chwings.letgotips.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chwings.letgotips.R;

/**
 * 自定义ToolBar
 */
public class CustomToolBar extends Toolbar implements View.OnClickListener{

    /** 布局加载器 */
    private LayoutInflater mInflater;

    /** title背景颜色  默认透明 */
    private int mTitleBackground;

    /** 左边ImageButton图片 */
    private int mLeftImage;

    /** 左边的文字 */
    private String mLeftText;

    /** 左边的文字颜色 */
    private int mLeftTextColor ;

    /** 左边文字大小 */
    private float mLeftTextSize ;

    /** 左边自定义布局id */
    private int mLeftViewId;

    /** 左边按钮是否按下就回退 */
    private boolean mLeftBackPressed;

    /** 左边的ImageButton */
    private ImageButton ib_left;

    /** 左边textview */
    private TextView tv_left;

    /** 左边自定义的View */
    private View mLeftView;

    /** title中间文字 */
    private String mCenterText;

    /** title文字颜色 */
    private int mCenterTextColor;

    /** title文字大小 */
    private float mCenterTextSize ;

    /** 中间自定义布局id */
    private int mCenterViewId;

    /** 中间自定义的View */
    private View mCenterView;

    /** 中间显示的textview */
    private TextView tv_center;


    /** 右边自定义布局id */
    private int mRightViewId;

    /** 右边自定义的View */
    private View mRightView;

    /** 右边文字 */
    private String mRightText ;

    /** 右边文字颜色 */
    private int mRightTextColor ;

    /** 右边文字大小 */
    private float mRightTextSize ;

    /** 右边的ImageButton */
    private ImageButton ib_right;

    /** 右边图片 */
    private int mRightImage ;

    /** 右边的TextView */
    private TextView tv_right;

    /** 整个title */
    private RelativeLayout mTitleLayout;

    /** 左边FrameLayout */
    private FrameLayout mLeftFrameLayout;

    /** 中间FrameLayout */
    private FrameLayout mCenterFrameLayout;

    /** 右边 */
    private FrameLayout mRightFrameLayout;

    public CustomToolBar(Context context) {
        super(context );
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs );


    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("DEBUG" , "333");
        float defaultTextSize = new Paint().getTextSize();
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar, defStyleAttr, 0);
        mLeftBackPressed = typeArray.getBoolean(R.styleable.CustomToolBar_leftBackPressed2, false);
        mLeftText = typeArray.getString(R.styleable.CustomToolBar_leftText2);
        mLeftTextColor = typeArray.getColor(R.styleable.CustomToolBar_leftTextColor2 , Color.WHITE);
        mLeftTextSize = typeArray.getDimension(R.styleable.CustomToolBar_leftTextSize2 , 0);
        mRightTextColor = typeArray.getColor(R.styleable.CustomToolBar_rightTextColor2 , Color.BLACK);
        mRightTextSize = typeArray.getDimension(R.styleable.CustomToolBar_rightTextSize2 , 0);
        mRightText = typeArray.getString(R.styleable.CustomToolBar_rightText2);
        mRightImage = typeArray.getResourceId(R.styleable.CustomToolBar_rightImage2 , 0);
        mCenterText = typeArray.getString(R.styleable.CustomToolBar_centerText2);
        mCenterTextColor = typeArray.getColor(R.styleable.CustomToolBar_centerTestSize2, Color.BLACK);
        mCenterTextSize = typeArray.getDimension(R.styleable.CustomToolBar_centerTestSize2 , 0);
        typeArray.recycle();
        init();
    }

    private void init(){
        initLeft();
    }

    private void initLeft(){
        if(mLeftBackPressed){
            if(getNavigationIcon() == null){
                setNavigationIcon(R.drawable.ic_divider_list_checkbox_checked);
            }
            setNavigationOnClickListener(this);
        }

        if(!TextUtils.isEmpty(mLeftText)){
            TextView tv_left = new TextView(getContext());
            tv_left.setText(mLeftText);
            tv_left.setPadding(getPaddingLeft() , 0 , 0 , 0);
            addView(tv_left);
        }
    }


    @Override
    public void onClick(View v) {
        Activity activity = (Activity) getContext();
        activity.onBackPressed();
    }
}
