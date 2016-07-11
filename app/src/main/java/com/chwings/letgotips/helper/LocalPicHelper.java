package com.chwings.letgotips.helper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;

import com.brianLin.utils.SDCardUtils;
import com.chwings.letgotips.bean.LocalPicBean;
import com.chwings.letgotips.bean.LocalPicFolderBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 本地图片帮助类
 */
public class LocalPicHelper {

    private OnScanLocalPicListener mOnScanLocalPicListener;

    private final String TAG = getClass().getSimpleName();

    private Context mContext;

    private int totalCount = 0;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;

    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<LocalPicBean> mLocalPicBeanList = new ArrayList<LocalPicBean>();

    private Map<String, LocalPicBean> mLocalPicBeanMap = new LinkedHashMap<>();

    private LocalPicBean mAllPicBean;

    // 文件夹数据
    private List<LocalPicFolderBean> mResultFolder = new ArrayList<>();

    /** 判断图片是否有效 */
    private BitmapFactory.Options options = null;


    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

//    private LocalPicBean mAllLocalPicBean = new LocalPicBean();

    public LocalPicHelper(Context context) {
        mContext = context;
    }

    /**
     * 扫描本地图片
     */
    public void scanLocalPic() {
        if (!checkEnvironment()) {
            return;
        }
        if (mContext instanceof Activity && mOnScanLocalPicListener != null) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mOnScanLocalPicListener.onScanProgressing();
                }
            });
        }
        if (mContext instanceof FragmentActivity) {
            ((FragmentActivity)mContext).getSupportLoaderManager().initLoader(0 , null , mLoaderCallback);
        }
    }

    /**
     * 检查环境
     */
    private boolean checkEnvironment() {
        if (!SDCardUtils.isSDCardEnable()) {
            if (mOnScanLocalPicListener != null) {
                mOnScanLocalPicListener.onScanFaile(new RuntimeException("local not SDCard"));
            }
            return false;
        } else if (mContext == null) {
            if (mContext instanceof Activity && mOnScanLocalPicListener != null) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mOnScanLocalPicListener.onScanFaile(new RuntimeException("context is empty"));
                    }
                });
            }
            return false;
        }
        return true;
    }

    public void setOnScanLocalPicListener(OnScanLocalPicListener l) {
        this.mOnScanLocalPicListener = l;
    }

    public interface OnScanLocalPicListener {
        void onScanProgressing();

        void onScanFinish(List<LocalPicBean> all, List<LocalPicFolderBean> list);

        void onScanFaile(Exception e);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media._ID};

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = new CursorLoader(mContext,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                    IMAGE_PROJECTION[4] + ">0 AND " + IMAGE_PROJECTION[3] + "=? OR " + IMAGE_PROJECTION[3] + "=? ",
                    new String[]{"image/jpeg", "image/png"}, IMAGE_PROJECTION[2] + " DESC");
            return cursorLoader;
        }

        private boolean fileExist(String path) {
            if (!TextUtils.isEmpty(path)) {
                return new File(path).exists();
            }
            return false;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                if (data.getCount() > 0) {
                    List<LocalPicBean> images = new ArrayList<>();
                    data.moveToFirst();
                    LocalPicFolderBean allFolder = new LocalPicFolderBean();
                    do {
                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        getEffective(path);
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                        LocalPicBean image = null;
                        File folderFile = new File(path).getParentFile();
                        if (fileExist(path) && getEffective(path)) {
                            image = new LocalPicBean(path, name, dateTime);
                            images.add(image);
                        }
                        // 获取文件夹名称
                        if (folderFile != null && folderFile.exists() && getEffective(path)) {
                            String fp = folderFile.getAbsolutePath();
                            LocalPicFolderBean f = getFolderByPath(fp);
                            String absoluteFileName = folderFile.getName();
                            if (f == null) {
                                LocalPicFolderBean folder = new LocalPicFolderBean();
                                folder.name = absoluteFileName;
                                folder.path = fp;
                                folder.cover = image;
                                List<LocalPicBean> imageList = new ArrayList<>();
                                imageList.add(image);
                                folder.images = imageList;
                                mResultFolder.add(folder);
                            } else {
                                f.images.add(image);
                            }
                            if(TextUtils.isEmpty(allFolder.name)){
                                allFolder.name = absoluteFileName ;
                            }
                            if(TextUtils.isEmpty(allFolder.path)){
                                allFolder.path = fp ;
                            }
                            if(allFolder.cover == null ){
                                allFolder.cover = image ;
                            }
                            if(allFolder.images == null){
                                allFolder.images = new ArrayList<>();
                            }
                            allFolder.images.add(image);
                        }

                    } while (data.moveToNext());
                    mResultFolder.add(allFolder);
                    if(mOnScanLocalPicListener != null){
                        mOnScanLocalPicListener.onScanFinish(images , mResultFolder);
                    }
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    /** 判断图片是否已经损坏 */
    private boolean getEffective(String path){
        if (options == null){
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
        }
        BitmapFactory.decodeFile(path, options); //filePath代表图片路径
        if (options.mCancel || options.outWidth == -1
                || options.outHeight == -1) {
            //表示图片已损毁
            Log.d(TAG , "path = "+path + " 损坏");
            return false;
        }
        return true;
    }

    private LocalPicFolderBean getFolderByPath(String path) {
        if (mResultFolder != null) {
            for (LocalPicFolderBean folder : mResultFolder) {
                if (TextUtils.equals(folder.path, path)) {
                    return folder;
                }
            }
        }
        return null;
    }

}
