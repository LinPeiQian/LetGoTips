package com.brianLin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtil {
	
	private static final String NAME = "YiXunApp";
	
	private static SPUtil mSPUtil = null;
	
	private Context mContext ;
	
	private SharedPreferences mSharedPreferences;
	
	private SPUtil(Context context){
		mContext = context ;
	}
	
	public synchronized static SPUtil getInstance(Context context){
		if(mSPUtil == null){
			mSPUtil = new SPUtil(context);
		}
		return mSPUtil;
	}
	
	public SharedPreferences getSp() {
		if (mSharedPreferences == null)
			mSharedPreferences = mContext.getSharedPreferences(getSPFileName(),
					Context.MODE_PRIVATE);
		return mSharedPreferences;
	}
	
	public Editor getEdit(){
		return getSp().edit();
	}
	
	public String getSPFileName(){
		return NAME;
	}

	/** 保存缓存数据 */
	public void setCacheData(String tag , String data){
		getEdit().putString(tag, data).commit();
	}

	/** 取出tag相应的缓存数据 */
	public String getCacheData(String tag){
		return getSp().getString(tag, "");
	}
	
//	//每次请求带上的session id
//	public void setToken(String id){
//		getEdit().putString(ConstantsValues.SP_SESSION_ID, id).commit();
//	}
//
//	public String getToken(){
//		return getSp().getString(ConstantsValues.SP_SESSION_ID, "");
//	}

}
