package com.chwings.letgotips.bean;

import java.io.Serializable;

/**
 * 添加完成的标签信息
 */
public class AddedLabelInfoBean implements Serializable{

    /** 样式 */
    public int style ;
    /** 数据 */
    public LabelBean[] labelData;
    /** 圆点的X */
    public float x;
    /** 圆点的Y */
    public float y ;

    public AddedLabelInfoBean(int style , float x  , float y , LabelBean... data){
        this.style = style;
        this.labelData = data ;
        this.x = x ;
        this.y = y;
    }

}
