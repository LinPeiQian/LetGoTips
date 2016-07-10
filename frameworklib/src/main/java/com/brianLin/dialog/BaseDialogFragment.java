package com.brianLin.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public abstract class BaseDialogFragment extends DialogFragment {

	protected final String TAG = getClass().getSimpleName();

	private boolean mIsCanDismiss ;
	
	private onDismissListener mListener ;
	
	private boolean mIsShowing = false; 
	
	private boolean mIsLoadingDialog = false;

	public abstract void show(FragmentManager fragment);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		if(mIsLoadingDialog){
			setCancelable(true);
			getDialog().setCanceledOnTouchOutside(false);
		}else{
			if(mIsCanDismiss){
				setCancelable(true);
				getDialog().setCanceledOnTouchOutside(true);
			}else{
				setCancelable(false);
				getDialog().setCanceledOnTouchOutside(false);
			}
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void setCancelable(boolean cancelable) {
		// TODO Auto-generated method stub
		super.setCancelable(cancelable);
		mIsCanDismiss = cancelable;
	}
	
	public void setLoadingDialog(){
		mIsLoadingDialog = true;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateDialog(savedInstanceState);
	}
	
	@Override
	public void show(FragmentManager manager, String tag) {
		// TODO Auto-generated method stub
		if(!isShowing()){
			mIsShowing = true;
			try {
				super.show(manager, tag);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public int show(FragmentTransaction transaction, String tag) {
		// TODO Auto-generated method stub
		if(!isShowing()){
			mIsShowing = true;
		}
		return -1;
	}
	
	public boolean isShowing(){
		return mIsShowing;
	}
	
	
	
	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onDismiss(dialog);
		mIsShowing = false;
		if(mListener != null)
			mListener.onDismiss();
	}
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		if(isShowing()){
			mIsShowing = false;
			try {
				super.dismiss();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public void dismissAllowingStateLoss() {
		// TODO Auto-generated method stub
		if(isShowing()){
			mIsShowing = false;
			super.dismissAllowingStateLoss();
		}
	}
	
	public void setOnDismissListener(onDismissListener listener){
		mListener = listener;
	}
	
	public interface onDismissListener{
		void onDismiss();
	}

}
