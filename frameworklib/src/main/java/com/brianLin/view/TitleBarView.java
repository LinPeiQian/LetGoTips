package com.brianLin.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brianLin.R;
import com.brianLin.utils.DensityUtils;


/**
 * 自定义title控件
 */
public class TitleBarView extends RelativeLayout implements View.OnClickListener {

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

    private final String TAG = getClass().getSimpleName();

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //系统默认的字体大小
        float defaultTextSize = new Paint().getTextSize();
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView, defStyle, 0);
        mTitleBackground = typeArray.getColor(R.styleable.TitleBarView_titleBackground, Color.TRANSPARENT);
        mLeftImage = typeArray.getResourceId(R.styleable.TitleBarView_leftImage, 0);
        mLeftBackPressed = typeArray.getBoolean(R.styleable.TitleBarView_leftBackPressed, false);
        mLeftViewId = typeArray.getResourceId(R.styleable.TitleBarView_leftView, 0);
        mLeftText = typeArray.getString(R.styleable.TitleBarView_leftText);
        mLeftTextColor = typeArray.getColor(R.styleable.TitleBarView_leftTextColor , Color.WHITE);
        mLeftTextSize = typeArray.getDimension(R.styleable.TitleBarView_leftTextSize , 0);
        mRightTextColor = typeArray.getColor(R.styleable.TitleBarView_rightTextColor , Color.WHITE);
        mRightTextSize = typeArray.getDimension(R.styleable.TitleBarView_rightTextSize , 0);
        mRightViewId = typeArray.getResourceId(R.styleable.TitleBarView_rightView, 0);
        mRightText = typeArray.getString(R.styleable.TitleBarView_rightText);
        mRightImage = typeArray.getResourceId(R.styleable.TitleBarView_rightImage , 0);
        mCenterText = typeArray.getString(R.styleable.TitleBarView_centerText);
        mCenterTextColor = typeArray.getColor(R.styleable.TitleBarView_centerTextColor, Color.BLACK);
        mCenterTextSize = typeArray.getDimension(R.styleable.TitleBarView_centerTestSize , 0);
        mCenterViewId = typeArray.getResourceId(R.styleable.TitleBarView_centerView, 0);
        typeArray.recycle();
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(mLeftTextSize == 0){
            tv_left = (TextView)mTitleLayout.findViewById(R.id.tv_text_left);
            mLeftTextSize = tv_left.getTextSize();
        }
        if(mCenterTextSize == 0){
            tv_center = (TextView)mTitleLayout.findViewById(R.id.tv_text_middle);
            mCenterTextSize = tv_center.getTextSize();
        }
        if(mRightTextSize == 0){
            tv_right = (TextView)mTitleLayout.findViewById(R.id.tv_text_right);
            mRightTextSize = tv_right.getTextSize();
        }
        mLeftTextSize = DensityUtils.px2sp(getContext() , mLeftTextSize);
        mCenterTextSize = DensityUtils.px2sp(getContext() , mCenterTextSize);
        mRightTextSize = DensityUtils.px2sp(getContext() , mRightTextSize);
        initCenter();
        initLeft();
        initRight();
    }

    private void init() {
        mInflater = LayoutInflater.from(getContext());
        mTitleLayout = (RelativeLayout) mInflater.inflate(R.layout.title_bar, null);
        addView(mTitleLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mTitleLayout.setBackgroundColor(mTitleBackground);
    }

    /** 初始化中间的内容
     *      优先自定义布局
     * */
    private void initCenter(){
        if(mCenterViewId != 0){
            mCenterFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.middle_layout);
            mCenterView = mInflater.inflate(mCenterViewId , null);
            mCenterFrameLayout.addView(mCenterView);
        }else if(!TextUtils.isEmpty(mCenterText)){
            tv_center = (TextView)mTitleLayout.findViewById(R.id.tv_text_middle);
            tv_center.setText(mCenterText);
            tv_center.setTextColor(mCenterTextColor);
            tv_center.setTextSize(mCenterTextSize);
            tv_center.setVisibility(View.VISIBLE);
        }
    }

    /** 初始化左边的内容*/
    private void initLeft(){
        if(mLeftBackPressed && mLeftViewId == 0 && mLeftImage == 0 && TextUtils.isEmpty(mLeftText)){
            ib_left = (ImageButton)mTitleLayout.findViewById(R.id.ib_image_left);
            ib_left.setImageResource(mLeftImage);
            ib_left.setVisibility(View.VISIBLE);
            return ;
        }
        View view = null ;
        if(mLeftViewId != 0){
            mLeftFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.left_layout);
            mLeftView = mInflater.inflate(mLeftViewId , null);
            mLeftFrameLayout.addView(mLeftView);
            view = mLeftFrameLayout;
        }else if(mLeftImage != 0){
            ib_left = (ImageButton)mTitleLayout.findViewById(R.id.ib_image_left);
            ib_left.setImageResource(mLeftImage);
            ib_left.setVisibility(View.VISIBLE);
            view = ib_left;
        }else if(!TextUtils.isEmpty(mLeftText)){
            tv_left = (TextView)mTitleLayout.findViewById(R.id.tv_text_left);
            tv_left.setText(mLeftText);
            tv_left.setTextColor(mLeftTextColor);
            tv_left.setTextSize(mLeftTextSize);
            tv_left.setVisibility(View.VISIBLE);
            view = tv_left;
        }
        if(view != null && mLeftBackPressed){
            view.setOnClickListener(this);
        }
    }

    /** 初始化右边的内容 */
    private void initRight(){
        if(mRightViewId != 0){
            mRightFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.right_layout);
            mRightView = mInflater.inflate(mRightViewId , null);
            mRightFrameLayout.addView(mRightView);
        }else if(mRightImage != 0){
            ib_right = (ImageButton)mTitleLayout.findViewById(R.id.ib_image_right);
            ib_right.setImageResource(mRightImage);
            ib_right.setVisibility(View.VISIBLE);
        }else if(!TextUtils.isEmpty(mRightText)){
            tv_right = (TextView)mTitleLayout.findViewById(R.id.tv_text_right);
            tv_right.setText(mRightText);
            tv_right.setTextColor(mRightTextColor);
            tv_right.setTextSize(mRightTextSize);
            tv_right.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Activity activity = (Activity) getContext();
        activity.onBackPressed();
    }

    /****** left start *******/

    /** 设置左边控件的监听  不包括自定义的View  */
    public TitleBarView setLeftOnclickListener(OnClickListener l){
        if(ib_left != null && ib_right.getVisibility() == View.VISIBLE){
            ib_left.setOnClickListener(l);
        }else if(tv_left != null && tv_left.getVisibility() == View.VISIBLE){
            tv_left.setOnClickListener(l);
        }
        return this;
    }

    /** 设置左边自定义View指定id的监听 */
    public TitleBarView setLeftCustomViewOnclickListener(int id , OnClickListener l){
        if(mLeftView != null){
            View view = mLeftView.findViewById(id);
            if(view != null){
                view.setOnClickListener(l);
            }
        }
        return this;
    }

    public TitleBarView setLeftCustomViewOnclickListener(OnClickListener l){
        if(mLeftView != null){
            mLeftView.setOnClickListener(l);
        }
        return this ;
    }

    public TitleBarView setLeftText(CharSequence text){
        setLeftText(text , mLeftTextColor , mLeftTextSize);
        return this;
    }

    public TitleBarView setLeftText(CharSequence text , int textColor){
        setLeftText(text , textColor , mLeftTextSize);
        return this ;
    }

    public TitleBarView setLeftText(CharSequence text , float textSize ){
        setLeftText(text , mLeftTextColor , textSize);
        return this ;
    }

    public TitleBarView setLeftText(CharSequence text , int textColor , float textSize){
        if(tv_left == null){
            tv_left = (TextView)mTitleLayout.findViewById(R.id.tv_text_middle);
        }
        tv_left.setText(text);
        tv_left.setTextColor(textColor);
        tv_left.setTextSize(textSize);
        tv_left.setVisibility(View.VISIBLE);
        if(mLeftView != null){
            mLeftView.setVisibility(View.GONE);
        }
        if(ib_left != null){
            ib_left.setVisibility(View.GONE);
        }
        return this ;
    }

    public TitleBarView setLeftImage(int imageId){
        if(imageId != 0){
            if(ib_left == null){
                ib_left = (ImageButton)mTitleLayout.findViewById(R.id.ib_image_left);
            }
            ib_left.setImageResource(imageId);
            if(mLeftView != null){
                mLeftView.setVisibility(View.GONE);
            }
            if(tv_left != null){
                tv_left.setVisibility(View.GONE);
            }
        }
        return this ;
    }

    public TitleBarView setLeftCustomView(int resId){
        setLeftCustomView(mInflater.inflate(resId , null));
        return this;
    }

    public TitleBarView setLeftCustomView(View view){
        if(view != null){
            if(mLeftFrameLayout == null){
                mLeftFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.left_layout);
            }
            if(mLeftView != null){
                mLeftFrameLayout.removeView(mLeftView);
            }
            mLeftFrameLayout.addView(view);
            mLeftView = view ;
            if(tv_left != null){
                tv_left.setVisibility(View.GONE);
            }
            if(ib_left != null){
                ib_left.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 显示左上角返回按钮
     */
    public void showLeftBackPressed() {
        showLeftBackPressed(R.drawable.ic_launcher);
    }

    public void showLeftBackPressed(int imageId){
        if (ib_left == null) {
            ib_left = (ImageButton) mTitleLayout.findViewById(R.id.ib_image_left);
        }
        ib_left.setVisibility(View.VISIBLE);
        ib_left.setImageResource(imageId);
        ib_left.setOnClickListener(this);
        if(mLeftView != null){
            mLeftView.setVisibility(View.GONE);
        }
        if(tv_left != null){
            tv_left.setVisibility(View.VISIBLE);
        }
    }

    public TextView getLeftTextView(){
        return tv_left;
    }

    public ImageButton getLeftImageButton(){
        return ib_left;
    }

    public View getLeftCustomView(){
        return mLeftView;
    }

    /****** left end *******/

    /****** center start *******/

    public TitleBarView setCenterText(CharSequence text){
        setCenterText(text , mCenterTextColor , mCenterTextSize);
        return this ;
    }

    public TitleBarView setCenterText(CharSequence text , int textColor){
        setCenterText(text , textColor , mCenterTextSize);
        return this ;
    }

    public TitleBarView setCenterText(CharSequence text , float textSize){
        setCenterText(text , mCenterTextColor , textSize);
        return this ;
    }

    public TitleBarView setCenterText(CharSequence text , int textColor , float textSize){
        if(tv_center == null){
            tv_center = (TextView)mTitleLayout.findViewById(R.id.tv_text_middle);
        }
        tv_center.setText(text);
        tv_center.setTextColor(textColor);
        tv_center.setTextSize(textSize);
        tv_center.setVisibility(View.VISIBLE);
        if(mCenterView != null){
            mCenterView.setVisibility(View.GONE);
        }
        return this ;
    }

    public TitleBarView setCenterCustomView(int resId){
        setCenterCustomView(mInflater.inflate(resId , null));
        return this ;
    }

    public TitleBarView setCenterCustomView(View view){
        if(view != null){
            if(mCenterFrameLayout == null){
                mCenterFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.middle_layout);
            }
            if(mCenterView != null){
                mCenterFrameLayout.removeView(mCenterView);
            }
            mCenterFrameLayout.addView(view);
            mCenterView = view ;
            if(tv_center != null){
                tv_center.setVisibility(View.GONE);
            }
        }
        return this ;
    }

    public TitleBarView setCenterOnclickListener(OnClickListener l){
        if(tv_center != null){
            tv_center.setOnClickListener(l);
        }
        return this ;
    }

    public TitleBarView setCenterCustomViewOnclickListener(int id , OnClickListener l){
        if(mCenterView != null){
            View view = mCenterView.findViewById(id);
            if(view != null){
                view.setOnClickListener(this);
            }
        }
        return this ;
    }

    public TitleBarView setCenterCustomViewOnclickListener(OnClickListener l){
        if(mCenterView != null){
            mCenterView.setOnClickListener(l);
        }
        return this ;
    }

    public TextView getCenterTextView(){
        return tv_center ;
    }

    public View getCenterCustomView(){
        return mCenterView;
    }

    /****** center end *******/

    /****** right start *******/

    public TitleBarView setRightText(CharSequence text){
        setRightText(text , mRightTextColor , mRightTextSize);
        return this;
    }
    public TitleBarView setRightText(CharSequence text , int textColor){
        setRightText(text , textColor , mRightTextSize);
        return this ;
    }

    public TitleBarView setRightText(CharSequence text , float textSize){
        setRightText(text , mRightTextColor , textSize);
        return this ;
    }

    public TitleBarView setRightText(CharSequence text , int textColor , float textSize){
        if(tv_right == null){
            tv_right = (TextView)mTitleLayout.findViewById(R.id.tv_text_right);
        }
        tv_right.setText(text);
        tv_right.setTextColor(textColor);
        tv_right.setTextSize(textSize);
        tv_right.setVisibility(View.VISIBLE);
        if(mRightView != null){
            mRightView.setVisibility(View.GONE);
        }
        if(ib_right != null){
            ib_right.setVisibility(View.GONE);
        }
        return this ;
    }

    public TitleBarView setRightImage(int image){
        if(image != 0){
            if(ib_right == null){
                ib_right = (ImageButton)mTitleLayout.findViewById(R.id.ib_image_right);
            }
            ib_right.setImageResource(image);
            if(tv_right != null){
                tv_right.setVisibility(View.GONE);
            }
            if(mRightView != null){
                mRightView.setVisibility(View.GONE);
            }
        }
        return this ;
    }

    public TitleBarView setRightCustomView(int resId){
        setRightCustomView(mInflater.inflate(resId , null));
        return this ;
    }

    public TitleBarView setRightCustomView(View view){
        if(view != null){
            if(mRightFrameLayout == null){
                mRightFrameLayout = (FrameLayout)mTitleLayout.findViewById(R.id.right_layout);
            }
            if(mRightView != null){
                mRightFrameLayout.removeView(mRightView);
            }
            mRightFrameLayout.addView(view);
            mRightView = view ;
            if(tv_right != null){
                tv_right.setVisibility(View.GONE);
            }
            if(ib_right != null){
                ib_right.setVisibility(View.GONE);
            }
        }
        return this ;
    }

    public TitleBarView setRightOnclickListener(OnClickListener l){
        if(ib_right != null && ib_right.getVisibility() == View.VISIBLE){
            ib_right.setOnClickListener(l);
        }else if(tv_right != null && tv_right.getVisibility() == View.VISIBLE){
            tv_right.setOnClickListener(l);
        }
        return this ;
    }

    public TitleBarView setRightCustomViewOnclickListener(int id , OnClickListener l){
        if(mRightView != null){
            View view = mRightView.findViewById(id);
            if(view != null){
                view.setOnClickListener(l);
            }
        }
        return this ;
    }

    public TitleBarView setRightCustomViewOnclickListener(OnClickListener l){
        if(mRightView != null){
            mRightView.setOnClickListener(l);
        }
        return this ;
    }

    public TextView getRightTextView(){
        return tv_right;
    }

    public ImageButton getRightImageButton(){
        return ib_right;
    }

    public View getRightCustomView(){
        return mRightView ;
    }

    /****** right end *******/

}
