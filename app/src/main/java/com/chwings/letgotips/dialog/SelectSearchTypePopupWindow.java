package com.chwings.letgotips.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chwings.letgotips.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择搜索类型的弹出框
 */
public class SelectSearchTypePopupWindow extends PopupWindow implements PopupWindow.OnDismissListener , View.OnClickListener{

    private View mView;

    private Context mContext ;

    private View mTargetView ;

    private View[] mAlphaViews ;

    private View mXView ;

    private int[] mLocaltion ;

    private final String TAG = getClass().getSimpleName();

    private OnSelectSearchTypeListener mOnSelectSearchTypeListener ;

    public SelectSearchTypePopupWindow(Context context , View xView , View targetView , View... views){
        mContext = context ;
        mTargetView = targetView ;
        mAlphaViews = views ;
        mXView = xView ;
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_select_search_type , null);
        ButterKnife.bind(this , mView);
        setContentView(mView);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setOnDismissListener(this);
        setBackgroundDrawable(new BitmapDrawable());
    }

    public void show(){
        if(mTargetView != null && !isShowing()){
            initEditTextLocaltion();
            if(mLocaltion != null){
                Log.d(TAG , "x = "+ mLocaltion[0]);
                showAsDropDown(mTargetView , mLocaltion[0] , 10);
            }else{
                showAsDropDown(mTargetView , 40 , 10);
            }
            for(View view : mAlphaViews){
                view.startAnimation(AnimationUtils.loadAnimation(mContext , R.anim.guide_alpha_in));
            }
        }
    }

    private void initEditTextLocaltion(){
        if(mLocaltion == null && mXView != null){
            mLocaltion = new int[2];
            mXView.getLocationOnScreen(mLocaltion);
        }
    }

    @Override
    public void onDismiss() {
        for(View view : mAlphaViews){
            view.startAnimation(AnimationUtils.loadAnimation(mContext , R.anim.guide_alpha_out));
        }
    }

    @OnClick({R.id.rl_notes , R.id.rl_user , R.id.rl_question , R.id.rl_project})
    @Override
    public void onClick(View v) {
        if(mOnSelectSearchTypeListener != null){
            RelativeLayout relativeLayout = (RelativeLayout)v ;
            TextView textView = (TextView)relativeLayout.getChildAt(0) ;
            mOnSelectSearchTypeListener.onSelected(textView.getText().toString().trim());
            dismiss();
        }
    }

    public interface  OnSelectSearchTypeListener{
        public void onSelected(String str);
    }

    public void setOnSelectSearchTypeListener(OnSelectSearchTypeListener l){
        mOnSelectSearchTypeListener = l ;
    }
}
