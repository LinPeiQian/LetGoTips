package com.chwings.letgotips.activity.release;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.release.EditorImageViewPagerAdapter;
import com.chwings.letgotips.bean.LocalPicBean;
import com.chwings.letgotips.bean.PictureProcessFinishBean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * 编辑图片
 */
public class EditorImageActivity extends BaseActivity implements View.OnClickListener , ViewPager.OnPageChangeListener{

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.title)
    TitleBarView title;

    public EditorImageViewPagerAdapter mAdapter ;

    private List<LocalPicBean> mSelectedList ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_editor_image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSelectedList = (List<LocalPicBean>) getIntent().getSerializableExtra(LocalImageGesturesActivity.INTENT_SELECTED_TAG);
        if(mSelectedList != null){
            mAdapter = new EditorImageViewPagerAdapter(mSelectedList);
            viewPager.setAdapter(mAdapter);
            viewPager.setOnPageChangeListener(this);
        }
        title.setRightOnclickListener(this);
    }

    @Override
    public void onClick(View v) {
        //继续
        List<PictureProcessFinishBean> list = mAdapter.getPictureProcessFinishBeanList();
        startActivity(ReleaseNotesActivity.class , ReleaseNotesActivity.INTENT_TAG , (Serializable) list);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mAdapter.setCurreentIndex(position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
