package com.chwings.letgotips.listener;

import android.content.Context;
import android.view.View;

import com.chwings.letgotips.dialog.SharePopupWindow;

/**
 * Created by Jensen on 2016/7/8.
 */
public class SharePopupWindowOnclicklistener implements View.OnClickListener {

    private SharePopupWindow mSharePopupWindow ;

    public SharePopupWindowOnclicklistener(Context context , View... view){
        mSharePopupWindow = new SharePopupWindow(context , view);
    }

    @Override
    public void onClick(View v) {
        mSharePopupWindow.show();
    }

}
