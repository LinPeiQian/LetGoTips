package com.chwings.letgotips.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chwings.letgotips.R;

/**
 * Created by Jensen on 2016/7/15.
 */
public class LoadMoreFooterView extends FrameLayout {

    private ImageView iv_foot;

    private Status mStatus;

    private AnimationDrawable mAnimationDrawable;

//    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true);
        iv_foot = (ImageView)findViewById(R.id.iv_foot);
        mAnimationDrawable = (AnimationDrawable)iv_foot.getBackground();
        setStatus(Status.GONE);
    }


//    public void setOnRetryListener(OnRetryListener listener) {
//        this.mOnRetryListener = listener;
//    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
        change();
    }

//    public boolean canLoadMore() {
//        return mStatus == Status.GONE || mStatus == Status.ERROR;
//    }

    private void change() {
        switch (mStatus) {
            case LOADING:
                setVisibility(View.VISIBLE);
                if(mAnimationDrawable != null){
                    mAnimationDrawable.start();
                }
                break;
            case ERROR:
            case GONE:
            case THE_END:
                setVisibility(View.GONE);
                if(mAnimationDrawable != null){
                    mAnimationDrawable.stop();
                }
                break;
        }
    }

    public enum Status {
        GONE, LOADING, ERROR, THE_END
    }

//    public interface OnRetryListener {
//        void onRetry(LoadMoreFooterView view);
//    }

}
