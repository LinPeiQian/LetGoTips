package com.brianLin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by Jensen on 2016/3/22.
 */
public class IntentUtils {

    /**
     * 从相机获取图片
     * @param context
     * @param picPath 图片保存路径
     * @param picName 图片保存名称
     * @param requestCode 返回的code
     */
    public static void getPictureForCamera(Context context , String picPath
            , String picName , int requestCode){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File dirFile = new File(picPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = new File(dirFile, picName + ".jpg");
        Uri imageUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        ((Activity)context).startActivityForResult(intent, requestCode);
    }

    /**
     * 从相册获取图片
     */
    public static void getPictureForPhotoAlbum(Context context , int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");// 相片类型
        ((Activity)context).startActivityForResult(intent, requestCode);
    }


}
