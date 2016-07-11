package com.chwings.letgotips.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Jensen on 2016/5/13.
 */
public class LocalPicFolderBean {
    public String name;
    public String path;
    public LocalPicBean cover;
    public List<LocalPicBean> images;

    @Override
    public boolean equals(Object o) {
        try {
            LocalPicBean other = (LocalPicBean) o;
            return TextUtils.equals(other.path, path);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return super.equals(o);
    }
}
