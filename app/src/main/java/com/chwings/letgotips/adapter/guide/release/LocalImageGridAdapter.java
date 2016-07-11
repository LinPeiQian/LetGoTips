package com.chwings.letgotips.adapter.guide.release;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.release.LocalImageGesturesActivity;
import com.chwings.letgotips.activity.release.LocalImageGridAvtivty;
import com.chwings.letgotips.bean.LocalPicBean;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jensen on 2016/7/11.
 */
public class LocalImageGridAdapter extends CommonAdapter<LocalPicBean> {

    private List<LocalPicBean> mDatas ;

    private List<Integer> checkPositionlist = new ArrayList<>();

    /** 选中的图片 */
    private List<LocalPicBean> mSelectList = new ArrayList<>();

    public LocalImageGridAdapter(Context context, List<LocalPicBean> datas ) {
        super(context, R.layout.item_local_image_grid, datas);
        mDatas = datas ;
    }

    public void setSelectedList(List<LocalPicBean>  list){
        this.mSelectList = list;
        notifyDataSetChanged();
    }

    public List<LocalPicBean> getSelectedList(){
        return mSelectList;
    }

    @Override
    public int getItemCount() {
        if(mDatas != null){
            if(mDatas.size() > 0 && mDatas.get(0) != null){
                mDatas.add(0 , null);
            }
            return mDatas.size();
        }
        return super.getItemCount();
    }

    @Override
    public void convert(ViewHolder holder,final LocalPicBean localPicBean,final int position) {
        ImageView imageView = (ImageView)holder.getView(R.id.iv_localImage);
        final CheckBox checkBox = (CheckBox)holder.getView(R.id.checkBox);
        TextView tv_camera = (TextView)holder.getView(R.id.tv_camera);
        RelativeLayout rl_select = (RelativeLayout)holder.getView(R.id.rl_select);
        checkBox.setChecked(mSelectList.contains(localPicBean));
        if(position == 0){
            imageView.setVisibility(View.GONE);
            checkBox.setVisibility(View.GONE);
            tv_camera.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            tv_camera.setVisibility(View.GONE);
            Glide.with(mContext).load(mDatas.get(position).path)
                    .centerCrop().placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
        }
        rl_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Log.d(TAG , "拍照");
                }else{
                    Intent intent = new Intent(mContext , LocalImageGesturesActivity.class);
                    intent.putExtra(LocalImageGesturesActivity.INTENT_LIST_TAG , (Serializable)mDatas);
                    intent.putExtra(LocalImageGesturesActivity.INTENT_SELECTED_TAG , (Serializable)mSelectList);
                    intent.putExtra(LocalImageGesturesActivity.INTENT_INDEX_TAG , position - 1);
                    ((Activity)mContext).startActivityForResult(intent , LocalImageGridAvtivty.REQWUEST_CODE);
                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = checkBox.isChecked();
                if(isChecked){
                    //选中
                    if(mSelectList.size() >= 9){
                        checkBox.setChecked(false);
                        Toast.makeText(mContext , "最多选9张" , Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    if(!mSelectList.contains(localPicBean)){
                        mSelectList.add(localPicBean);
                    }
                }else{
                    //取消选中
                    if(mSelectList.contains(localPicBean)){
                        mSelectList.remove(localPicBean);
                    }
                }
            }
        });
    }

}
