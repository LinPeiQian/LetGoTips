package com.brianLin.adapter;

import android.view.View;

/**
 * 抽象viewholder
 */
public abstract class MyViewHolder<T>
{
    /**
     * 初始化ViewHolder
     */
   public  abstract void initHolder(View view , int position);
   
    /**
     * 装载数据
     */
   public abstract void loadData(T data,int position);
}
