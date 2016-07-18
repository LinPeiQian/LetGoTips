package com.chwings.letgotips.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.chwings.letgotips.R;

/**
 * Created by Jensen on 2016/7/15.
 */
public class LoadMoreFooterView extends FrameLayout {

    private ProgressBar mProgressBar ;

    private Status mStatus;

//    private View mLoadingView;
//
//    private View mErrorView;
//
//    private View mTheEndView;

//    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context, null);
//        super(context);
//        setStatus(Status.GONE);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
//        super(context , attrs);
//        setStatus(Status.GONE);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        mLoadingView = findViewById(R.id.loadingView);
//        mErrorView = findViewById(R.id.errorView);
//        mTheEndView = findViewById(R.id.theEndView);
//
//        mErrorView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnRetryListener != null) {
//                    mOnRetryListener.onRetry(LoadMoreFooterView.this);
//                }
//            }
//        });

        setStatus(Status.GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
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
            case GONE:
                mProgressBar.setVisibility(GONE);
                break;

            case LOADING:
                mProgressBar.setVisibility(VISIBLE);
                break;

            case ERROR:
                mProgressBar.setVisibility(GONE);
                break;

            case THE_END:
                mProgressBar.setVisibility(GONE);
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
