package com.chwings.letgotips.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.pull.RefreshTrigger;

/**
 * Created by Jensen on 2016/7/15.
 */
public class RefreshHeaderView extends RelativeLayout implements RefreshTrigger {

    private ImageView iv_anim ;

    private AnimationDrawable mAnimDrawable;

    private int mHeight;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_anim = (ImageView)findViewById(R.id.iv_anim);
        mAnimDrawable = (AnimationDrawable)iv_anim.getBackground();
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        this.mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean isComplete, boolean automatic, int moved) {
        if (!isComplete) {
            iv_anim.setVisibility(VISIBLE);
            if(!mAnimDrawable.isRunning()){
                mAnimDrawable.start();
            }
        }
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
    }
}
