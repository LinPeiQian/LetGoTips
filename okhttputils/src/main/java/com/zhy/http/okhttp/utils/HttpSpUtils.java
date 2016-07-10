package com.zhy.http.okhttp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jensen on 2016/7/5.
 */
public class HttpSpUtils {

    private static final String NAME = "LibSp";

    private static HttpSpUtils mSPUtil = null;

    private Context mContext ;

    private SharedPreferences mSharedPreferences;

    private HttpSpUtils(Context context){
        mContext = context ;
    }

    public synchronized static HttpSpUtils getInstance(Context context){
        if(mSPUtil == null){
            mSPUtil = new HttpSpUtils(context);
        }
        return mSPUtil;
    }

    public SharedPreferences getSp() {
        if (mSharedPreferences == null)
            mSharedPreferences = mContext.getSharedPreferences(getSPFileName(),
                    Context.MODE_PRIVATE);
        return mSharedPreferences;
    }

    public SharedPreferences.Editor getEdit(){
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


}
