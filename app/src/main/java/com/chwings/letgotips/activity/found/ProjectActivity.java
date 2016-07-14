package com.chwings.letgotips.activity.found;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.itemDecoration.SpaceItemDecoration;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 专题
 */
public class ProjectActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayout.VERTICAL , false));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0 , 0 , 10 , 0 , false));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i22222222222);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(this , R.layout.item_project , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                final ImageView imageView = (ImageView)holder.getView(R.id.iv_bg);
                Glide.with(ProjectActivity.this).load(integer).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(ProjectActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(8);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
