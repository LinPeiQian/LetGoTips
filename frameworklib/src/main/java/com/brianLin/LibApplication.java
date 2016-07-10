package com.brianLin;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;


import com.brianLin.crash.CrashHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2016/3/21.
 */
public class LibApplication extends Application {

    private static Context mContext ;

    private List<Activity> mActivityList ;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        /** 注册全局错误捕捉 */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());


    }


    /**
     * 获取全局上下文对象
     */
    public static Context getContext(){
        return  mContext;
    }

    /**
     * 获取全部activity
     */
    public List<Activity> getAllActivity(){
        return mActivityList;
    }

    /**
     * 获取栈顶的activity
     */
    public Activity getTopActivity(){
        if(mActivityList != null && mActivityList.size() > 0){
            return mActivityList.get(mActivityList.size() - 1);
        }
        return null;
    }

    /**
     * 加入activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if(mActivityList == null){
            mActivityList = new ArrayList<Activity>();
        }
        if(!mActivityList.contains(activity)){
            mActivityList.add(activity);
        }
    }

    /**
     * 从集合中删除指定activity
     * @param activity
     */
    public void delectActivity(Activity activity){
        if(mActivityList == null){
            mActivityList = new ArrayList<Activity>();
        }
        if(mActivityList.contains(activity)){
            mActivityList.remove(activity);
        }
    }

    public void delectActivity(String actName){
        if(!TextUtils.isEmpty(actName) && mActivityList != null){
            for(int i = 0 ; i < mActivityList.size() ; i ++){
                Activity act = mActivityList.get(i);
                if(act != null && actName.equals(act.getClass().getSimpleName())){
                    act.finish();
                }
            }
        }
    }

    /**
     * finish所有activity 除了ActivityMain
     */
    public void finishActivityAdditionMian(){
        if(mActivityList != null && mActivityList.size() > 0){
            for(int i = 0 ; i < mActivityList.size() ; i ++){
                Activity activity = mActivityList.get(i);
                if(activity != null ){
                    if(("ActivityMain").equalsIgnoreCase(activity.getClass().getSimpleName())){
                        //主界面
                        continue;
                    }
                    try {
                        activity.finish();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
            }
        }
    }

    /**
     * finish所有activity 除了ActivitySplash
     */
    public void finishActivityAdditionSplash(){
        if(mActivityList != null && mActivityList.size() > 0){
            for(int i = 0 ; i < mActivityList.size() ; i ++){
                Activity activity = mActivityList.get(i);
                if(activity != null ){
                    if(("ActivitySplash").equalsIgnoreCase(activity.getClass().getSimpleName())){
                        continue;
                    }
                    try {
                        activity.finish();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
            }
        }
    }

    /**
     * finish所有activity 除了ActivityLogin
     */
    public void finishActivityAdditionLogin(){
        if(mActivityList != null && mActivityList.size() > 0){
            for(int i = 0 ; i < mActivityList.size() ; i ++){
                Activity activity = mActivityList.get(i);
                if(activity != null ){
                    if(("ActivityLogin").equalsIgnoreCase(activity.getClass().getSimpleName())){
                        continue;
                    }
                    try {
                        activity.finish();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
            }
        }
    }

    /**
     * finis所有activity
     * @param act   例外
     */
    public void finishAllActivity(Activity act){
        if(mActivityList != null && mActivityList.size() > 0){
            for(int i = 0 ; i < mActivityList.size() ; i ++){
                Activity activity = mActivityList.get(i);
                if(activity != null){
                    if(activity == act){
                        continue;
                    }
                    try {
                        activity.finish();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
    }

    /**
     * finsih指定activity
     */
    public void finishActivity(Activity activity){
        if(activity != null && mActivityList != null && mActivityList.contains(activity)){
            activity.finish();
        }
    }

    /**
     * finsih指定activity
     */
    public void finishActivity(String simpleName){
        if(!TextUtils.isEmpty(simpleName) && mActivityList != null && mActivityList.size() > 0){
            for (Activity activity : mActivityList) {
                if(simpleName.equalsIgnoreCase(activity.getClass().getSimpleName())){
                    activity.finish();
                    break;
                }
            }
        }
    }
}
