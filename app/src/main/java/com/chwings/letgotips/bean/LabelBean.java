package com.chwings.letgotips.bean;

import java.io.Serializable;

/**
 * 标签bean
 */
public class LabelBean implements Serializable {

    public Object obj ;

    public String type ;

    public LabelEnum tag ;


    public LabelBean(Object obj , String type , LabelEnum tag){
        this.obj = obj;
        this.type = type;
        this.tag = tag ;
    }

}
