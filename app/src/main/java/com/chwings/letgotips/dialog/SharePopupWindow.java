package com.chwings.letgotips.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chwings.letgotips.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jensen on 2016/7/8.
 */
public class SharePopupWindow extends PopupWindow implements PopupWindow.OnDismissListener{

    private View mView ;

    private Context mContext ;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    private View[] targetView ;

    private String[] strArr = new String[]{"朋友圈快照" , "微信快照" , "朋友圈" , "微信" , "微博" , "QQ" , "复制链接" , "举报"};

    public SharePopupWindow(Context context , View... view){
        this.targetView = view ;
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.pop_share , null);
        ButterKnife.bind(this , mView);
        setContentView(mView);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setOnDismissListener(this);
        setBackgroundDrawable(new BitmapDrawable());
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setAnimationStyle(R.style.share_pop_anim_style);
    }

    public void show(){
        if(targetView != null && !isShowing()){
            showAtLocation(targetView[0], Gravity.BOTTOM, 0, 0);
            for(View view : targetView){
                view.startAnimation(AnimationUtils.loadAnimation(mContext , R.anim.guide_alpha_in));
            }
        }
    }

    @Override
    public void onDismiss() {
        for(View view : targetView){
            view.startAnimation(AnimationUtils.loadAnimation(mContext , R.anim.guide_alpha_out));
        }
    }
}
