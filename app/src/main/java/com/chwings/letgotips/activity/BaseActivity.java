package com.chwings.letgotips.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.brianLin.LibApplication;
import com.brianLin.view.SlideFinishActivityLayout;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Activity基类\n
 * 主要通过getLayoutId抽象方法获取布局并实现注解
 * @author lpq
 */
public abstract class BaseActivity extends AppCompatActivity {
	
	/** 布局  */
	public abstract int getLayoutId();

	private Context mContext ;
	
	private Intent mIntent = null;

	private static LibApplication mYXApplication;

	public String TAG ;
	
	private SlideFinishActivityLayout mSlideFinishActivityLayout;

	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//去标题栏
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


		//锁定竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//透明状态栏  沉浸模式
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		//透明导航栏   这句会使虚拟键盘变透明
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		mContext = this ;

		TAG = this.getClass().getSimpleName();
		
		//去掉信息栏
		if(setHiddenNotificationBar()){
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		if(setSlidFinishActivity()){
			mSlideFinishActivityLayout = new SlideFinishActivityLayout(this);
			mSlideFinishActivityLayout.attachToActivity(BaseActivity.this);
		}
		
		//设置布局并注解
		if(getLayoutId() != 0){
			setContentView(getLayoutId());
//			ViewUtils.inject(this);
			ButterKnife.bind(this);
		}

		
		/**
		 * 将当前activity加入集合
		 */
		if(mYXApplication == null){
			mYXApplication = (LibApplication)mContext.getApplicationContext();
		}
		mYXApplication.addActivity((Activity)mContext);
	}


	/**
	 * 是否隐藏通知栏
		 * @return true隐藏
	 */
	public boolean setHiddenNotificationBar(){
		return false;
	}
	
	/**
	 * 是否滑动结束activity
	 * @return
     */
	public boolean setSlidFinishActivity(){
		return false;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mYXApplication.delectActivity((Activity)mContext);
	}
	
	/**
	 * 初始化intent
	 */
	public Intent initIntent(Class<? extends Activity> activity){
		if(activity == null){
			return null;
		}
		mIntent = new Intent(mContext , activity);
		return mIntent;
	}
	
	/**
	 * 启动一个activity
	 * @param activity
	 */
	public void startActivity(Class<? extends Activity> activity){
		initIntent(activity);
		if(mIntent != null){
			startActivity(mIntent);
		}
	}
	
	public void startActivity(Class<? extends Activity> activity , String key , Object value){
		initIntent(activity);
		putExtra(key, value);
		startActivity(mIntent);
	}
	
	public Object getIntentValue(Intent intent , String key){
		if(intent != null){
			return intent.getExtras().get(key);
		}
		return null;
	}
	
	public void putExtra(String key, Object value) {
		if (mIntent != null && key != null && value != null) {
			if (value instanceof Integer) {
				mIntent.putExtra(key, (Integer) value);
			} else if (value instanceof String) {
				mIntent.putExtra(key, (String) value);
			} else if (value instanceof Serializable) {
				mIntent.putExtra(key, (Serializable) value);
			} else if (value instanceof Boolean) {
				mIntent.putExtra(key, (Boolean) value);
			} else if (value instanceof Long) {
				mIntent.putExtra(key, (Long) value);
			} else if (value instanceof Double) {
				mIntent.putExtra(key, (Double) value);
			}
		}
	}
	
	/**
	 * 带参数的启动新activity
	 */
	public void startActivity(Class<? extends Activity> activity , Bundle bundle){
		initIntent(activity);
		if(mIntent != null && bundle != null){
			mIntent.putExtras(bundle);
			startActivity(mIntent);
		}
	}
	
	/**
	 * 带Serializable对象启动新activity
	 * @param activity
	 * @param serializable
	 */
	public void startActivity(Class<? extends Activity> activity , String key , Serializable serializable){
		initIntent(activity);
		if(mIntent != null){
			mIntent.putExtra(key, serializable);
			startActivity(mIntent);
		}
	}
	/**
	 * finish所有activity 除了ActivityMain
	 */
	public void finishAllActivityAdditionMain(){
		mYXApplication.finishActivityAdditionMian();
	}
	
	public void finishAllActivity(Activity activity){
		mYXApplication.finishAllActivity(activity);
	}

	/**
	 * finish指定的activity
	 * @param actName
	 */
	public void finishSpecifiedActivity(String actName){
		mYXApplication.delectActivity(actName);
	}

	
	
	
	/**
	 * 设置tv_error的显示 与 内容
	 * @param tv
	 * @param state
	 * @param strId
	 */
	public void setErrorVisibility(TextView tv , int state , int strId){
		tv.setVisibility(state);
		tv.setText(strId);
	}
	
	/**
	 * 从bundle中获取指定Key的string
	 */
	public String getStringByBundle(Intent intent , String key){
		if(intent == null){
			return "";
		}
		return intent.getExtras().getString(key);
	}




	private IntentFilter initIntentFilter(String... action){
		if(action != null && action.length > 0){
			IntentFilter mFilter = new IntentFilter();
			for (String string : action) {
				mFilter.addAction(string);
			}
			return mFilter;
		}
		return null;
	}

	/**
	 * 注册广播接收器
	 * @param receiver
	 * @param action
	 */
	public void registerReceiver(BroadcastReceiver receiver , String action){
		registerReceiver(receiver, initIntentFilter(action));
	}
	
	/**
	 * 注册广播接收器
	 * @param receiver
	 * @param actionList
	 */
	public void registerReceiver(BroadcastReceiver receiver , List<String> actionList){
		registerReceiver(receiver, initIntentFilter((String[]) actionList.toArray()));
	}
	
	/**
	 * 发送广播
	 */
	public void sendBroadcast(String action){
		sendBroadcast(new Intent(action));
	}
	
	public void sendBroadcast(String action , String key , CharSequence value){
		Intent intent = new Intent(action);
		intent.putExtra(key, value);
		sendBroadcast(intent);
	}
	
	public void sendBroadcast(String action , Map params){
		if(params != null && params.size() > 0){
			Intent intent = new Intent();
		}
	}

}
