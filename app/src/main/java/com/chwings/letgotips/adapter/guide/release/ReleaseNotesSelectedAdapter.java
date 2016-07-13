package com.chwings.letgotips.adapter.guide.release;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.brianLin.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chwings.letgotips.R;
import com.chwings.letgotips.bean.PictureProcessFinishBean;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by Jensen on 2016/7/13.
 */
public class ReleaseNotesSelectedAdapter extends CommonAdapter<PictureProcessFinishBean>{

    private int itemWidth ;

    private final int mSpanCount = 4;

    private ViewGroup.LayoutParams mParams  ;

    private List<PictureProcessFinishBean> mDatas  ;

    public ReleaseNotesSelectedAdapter(RecyclerView recyclerView , List<PictureProcessFinishBean> datas) {
        super(recyclerView.getContext(), R.layout.item_release_notes_selected_img, datas);
        mDatas = datas;
        itemWidth = (ScreenUtils.getScreenWidth(recyclerView.getContext()) - recyclerView.getPaddingLeft() - recyclerView.getPaddingRight()) / mSpanCount ;
        mParams = new ViewGroup.LayoutParams(itemWidth , itemWidth);
        LinearLayout.LayoutParams llParmas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , itemWidth + recyclerView.getPaddingTop() + recyclerView.getPaddingBottom() );
        recyclerView.setLayoutParams(llParmas);
    }

    @Override
    public void convert(ViewHolder holder, PictureProcessFinishBean pictureProcessFinishBean, int position) {
        ImageView imageView = (ImageView)holder.getView(R.id.iv_selected);
        imageView.setLayoutParams(mParams);
        if(pictureProcessFinishBean != null){
            Glide.with(mContext).load(mDatas.get(position).path)
                    .centerCrop().placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
        }else if(position == mDatas.size() - 1){
            Glide.with(mContext).load(R.drawable.common_addimg).into(imageView);
        }
    }

}
