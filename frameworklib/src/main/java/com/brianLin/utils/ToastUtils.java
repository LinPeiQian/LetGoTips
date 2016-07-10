package com.brianLin.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.brianLin.LibApplication;


/**
 * Toast工具类
 * @author lpq
 *
 */
public class ToastUtils {
	
	public static Context context = null;
	
	static{
		context = LibApplication.getContext();
	}

	/**
	 * 私有 不能实例化
	 */
	private ToastUtils(){
		
	}
	
	/**
	 * 短时间显示
	 * @param msg
	 */
	public static void showShort(final CharSequence msg){
		if(context == null){
			return ;
		}
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	/**
	 * 短时间显示
	 */
	public static void showShort(final int msgId){
		if(context == null){
			return ;
		}
		final CharSequence msg = context.getResources().getString(msgId);
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	/**
	 * 长时间显示
	 * @param msg
	 */
	public static void showLong(final CharSequence msg){
		if(context == null){
			return ;
		}
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				}
			});
		}
	}
	
	/**
	 * 长时间显示
	 */
	public static void showLong(final int msgId){
		if(context == null){
			return ;
		}
		final CharSequence msg = context.getResources().getString(msgId);
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
				}
			});
		}
	}
	
	/**
	 * 显示自定义时间的toast
	 */
	public static void show(final CharSequence msg , final int duration){
		if(context == null){
			return ;
		}
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, duration).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, duration).show();
				}
			});
		}
	}
	
	/**
	 * 显示自定义时间的toast
	 */
	public static void show(final int msgId ,final int duration){
		if(context == null){
			return ;
		}
		final CharSequence msg = context.getResources().getString(msgId);
		if("main".equals(Thread.currentThread().getName())){
			Toast.makeText(context, msg, duration).show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, msg, duration).show();
				}
			});
		}
	}
	
	/**
	 * 在屏幕中间显示自定义时间的toast
	 * @param msg
	 */
	public static void showInCenter(final CharSequence msg , final int duration){
		if(context == null){
			return ;
		}
		final Toast toast = Toast.makeText(context, msg, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		if("main".equals(Thread.currentThread().getName())){
			toast.show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					toast.show();
				}
			});
		}
	}
	
	/**
	 * 在屏幕中间显示自定义时间的toast
	 */
	public static void showInCenter(final int msgId ,final int duration){
		if(context == null){
			return ;
		}
		final CharSequence msg = context.getResources().getString(msgId);
		final Toast toast = Toast.makeText(context, msg, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		if("main".equals(Thread.currentThread().getName())){
			toast.show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					toast.show();
				}
			});
		}
	}
	
	/**
	 * 显示自定义布局的toast 
	 * @param view
	 * @param msg
	 * @param duration
	 * @param gravity
	 */
	public static void showView(View view , CharSequence msg , int duration , int gravity){
		if(context == null){
			return ;
		}
		final Toast toast = Toast.makeText(context, msg, duration);
		toast.setView(view);
		toast.setGravity(gravity, 0, 0);
		if("main".equals(Thread.currentThread().getName())){
			toast.show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					toast.show();
				}
			});
		}
	}
	
	/**
	 * 显示自定义布局的toast 
	 * @param viewId
	 * @param msg
	 * @param duration
	 * @param gravity
	 */
	public static void showView(int viewId , CharSequence msg , int duration , int gravity){
		if(context == null){
			return ;
		}
		final Toast toast = Toast.makeText(context, msg, duration);
		toast.setView(LayoutInflater.from(context).inflate(viewId, null));
		toast.setGravity(gravity, 0, 0);
		if("main".equals(Thread.currentThread().getName())){
			toast.show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					toast.show();
				}
			});
		}
	}
	
	/**
	 * 显示自定义布局的toast 
	 * @param viewId
	 * @param msgId
	 * @param duration
	 * @param gravity
	 */
	public static void showView(int viewId , int msgId , int duration , int gravity){
		if(context == null){
			return ;
		}
		final Toast toast = Toast.makeText(context, context.getResources().getString(msgId), duration);
		toast.setView(LayoutInflater.from(context).inflate(viewId, null));
		toast.setGravity(gravity, 0, 0);
		if("main".equals(Thread.currentThread().getName())){
			toast.show();
		}else{
			new Handler(context.getMainLooper()).post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					toast.show();
				}
			});
		}
	}
}
