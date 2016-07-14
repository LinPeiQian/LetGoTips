package com.chwings.letgotips.adapter.guide.found;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.adapter.guide.RecyclingPagerAdapter;

import java.util.List;

/**
 * Created by Jensen on 2016/7/14.
 */
public class FoundHomeViewPagerAdapter extends RecyclingPagerAdapter {

    private Context mContext ;
    private List<Integer> mDatas ;
    private boolean isInfiniteLoop;

    public FoundHomeViewPagerAdapter(Context context , List<Integer> datas ){
        mContext = context ;
        mDatas = datas ;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.imageView = new ImageView(mContext);
            viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
            viewHolder.imageView.setLayoutParams(params);
            convertView = viewHolder.imageView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Integer id = mDatas.get(getPosition(position));
        viewHolder.imageView.setImageResource(id);
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("DEBUG", "this is position " + getPosition(position));
            }
        });
        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return isInfiniteLoop ? Integer.MAX_VALUE : mDatas.size();
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? position % mDatas.size() : position;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public FoundHomeViewPagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
