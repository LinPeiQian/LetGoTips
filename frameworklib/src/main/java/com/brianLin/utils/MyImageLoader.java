package com.brianLin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.brianLin.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class MyImageLoader  {
	private volatile static MyImageLoader instance;
	
	private static DisplayImageOptions options;
	
	private int currentDrawable ;
	
	private Context mContext ;

	/** Returns singleton class instance */
	public static MyImageLoader getInstance() {
		if (instance == null) {
			synchronized (MyImageLoader.class) {
				if (instance == null) {
					instance = new MyImageLoader();
				}
			}
		}
		return instance;
	}
	
	public void displayImage(String url , ImageView imageview , int id){
		if(imageview != null){
			mContext = imageview.getContext();
			getImageLoader().displayImage(url, imageview, getDefaultDisplayImageOptions(id));
		}
	}

	public void displayImagsFromSDCard(String url , ImageView imageview){
		displayImage("file://" + url, imageview, R.drawable.ic_launcher);
	}
	
	public void displayImage(String url , ImageView imageview){
		displayImage(url, imageview, R.drawable.ic_launcher);
	}
	
	private  DisplayImageOptions getDefaultDisplayImageOptions(int drawableId) {
		if (options == null || drawableId != currentDrawable)
			currentDrawable = drawableId;
			options = new DisplayImageOptions.Builder()
			 .showImageOnLoading(drawableId) // 设置图片下载期间显示的图片
					.showImageForEmptyUri(drawableId) // 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(drawableId) // 设置图片加载或解码过程中发生错误显示的图片
					.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
					.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
					.resetViewBeforeLoading(true) // 设置图片在下载前是否重置，复位
					.bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
					// .displayer(new FadeInBitmapDisplayer(300))//
					// 是否图片加载好后渐入的动画时间
					.build();
		return options;
	}
	
	private ImageLoader getImageLoader() {
		if (!ImageLoader.getInstance().isInited()) {
			ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
		}
		return ImageLoader.getInstance();
	}
}
