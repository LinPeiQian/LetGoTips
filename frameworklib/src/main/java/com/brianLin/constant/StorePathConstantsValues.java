package com.brianLin.constant;

import android.os.Environment;

/**
 * @description
 * 		数据存储常量封装，存放存储类别相关路径信息
 * @author Brian
 *
 */
public interface StorePathConstantsValues {
	
	public final static String SD_PREFIX = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

	/** 母目录 */
	public final static String STORE_PREFIX = SD_PREFIX + "MyApp" + "/" ;

	/** 错误报告 */
	public final static String CRASH_INFO_PATH = STORE_PREFIX + "crash";

	/** 下载的apk */
	public final static String DOWNLOAD_APK_PATH = STORE_PREFIX + "apk";

	/** 发表的图片 */
	public final static String RELEASE_PATH = STORE_PREFIX + "release";


}
