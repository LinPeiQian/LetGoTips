package com.chwings.letgotips.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chwings.letgotips.R;

import butterknife.ButterKnife;

/**
 * 消息模块中 底部弹出可操作的popupwindow
 */
public class MessageOperationPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener{

    private View mView ;

    private Context mContext ;

    private View[] mAlphaViews ;

    public MessageOperationPopupWindow(Context context){
        mContext = context ;
        mView = LayoutInflater.from(context).inflate(R.layout.pop_message_operation , null);
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
        if(mAlphaViews != null && !isShowing()){
            showAtLocation(mAlphaViews[0], Gravity.BOTTOM, 0, 0);
            backgroundAlpha(0.5f);
        }
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
              lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)mContext).getWindow().setAttributes(lp);
    }
}
