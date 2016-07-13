package com.chwings.letgotips.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 图片处理完成的信息
 */
public class PictureProcessFinishBean implements Serializable{

    public String path ;
    public List<AddedLabelInfoBean> labelInfo;
    public int screenWidth ;
    public int screenHeight ;
    public int viewWidth ;
    public int viewHeight ;

    public PictureProcessFinishBean(String path , List<AddedLabelInfoBean> labelInfo , int screenWidth , int screenHeight , int viewWidth , int viewHeight){
        this.path = path ;
        this.labelInfo = labelInfo ;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
    }



}
