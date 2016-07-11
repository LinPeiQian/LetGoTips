package com.chwings.letgotips.bean;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/5/11.
 */
public class LocalPicBean implements Serializable{

    public String path;
    public String name;
    public long time;


    public LocalPicBean(String path, String name, long time){
        this.path = path;
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        try {
            LocalPicBean other = (LocalPicBean) o;
            return this.path.equalsIgnoreCase(other.path);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return super.equals(o);
    }

}
