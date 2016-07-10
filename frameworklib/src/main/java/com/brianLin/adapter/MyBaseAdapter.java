package com.brianLin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.brianLin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器基类
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter
{
    List<T> dataList = new ArrayList<T>();
    public LayoutInflater mInflater;
    public Context mContext;

    public final String TAG = getClass().getSimpleName();


    public MyBaseAdapter(Context context)
    {
        // TODO Auto-generated constructor stub
        mContext=context;
        mInflater= LayoutInflater.from(mContext);
    }
    
    /**
     * 设置数据，以前数据会清空
     */
    public void setData(List<T> data){
//        if(dataList==null)
            dataList=new ArrayList<T>();
//        dataList.clear();
        if(data!=null)
            dataList.addAll(data);
        notifyDataSetChanged();
    }
    
    /**
     * 在原始数据上添加新数据
     */
    public void addData(List<T> data){
        if(dataList==null)
            dataList=new ArrayList<T>();
        dataList.addAll(data);
        notifyDataSetChanged();
    }
    
    /**
     * 在原始数据上添加一条新数据
     * @param data
     */
    public void addData(T data){
    	if(dataList==null)
            dataList=new ArrayList<T>();
        dataList.add(data);
        notifyDataSetChanged();
    }
    
    /**
     * 在原始数据上添加新数据
     */
    public void addData(List<T> data, int position){
        if(dataList==null)
            dataList=new ArrayList<T>();
        dataList.addAll(position, data);
        notifyDataSetChanged();
    }
    
    /**
     * 移除全部数据
     */
    public void removeAllData(){
        dataList.clear();
        notifyDataSetChanged();
    }

    public void changeData(int position , T data){
        if(dataList != null && dataList.size() > position){
            dataList.remove(position);
            dataList.add(position , data);
        }
    }
    
    
    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        if(dataList!=null)
            return dataList.size();
        return 0;
    }
    
    @Override
    public T getItem(int position)
    {
        // TODO Auto-generated method stub
        if(dataList!=null)
            try{
                return dataList.get(position);
            }catch(Exception g){
                return null;
            }
        return null;
    }
    
    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }
    
    public List<T> getAllData(){
    	return dataList;
    }
    
   public abstract int getConvertViewId(int position);
    
   public  abstract MyViewHolder<T> getNewHolder(int position);
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        MyViewHolder<T> holder;
        if(convertView==null){
            holder=getNewHolder(position);
            convertView=mInflater.inflate(getConvertViewId(position), null);
            holder.initHolder(convertView,position);
            convertView.setTag(R.string.app_name , holder);
        }else{
            holder=(MyViewHolder<T>)convertView.getTag(R.string.app_name);
        }
        holder.loadData(getItem(position),position);
        return convertView;
    }
    
    
    
    
    
    
    
    
}
