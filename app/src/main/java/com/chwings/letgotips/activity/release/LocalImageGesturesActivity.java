package com.chwings.letgotips.activity.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.release.LocalImageGesturesViewPagerAdapter;
import com.chwings.letgotips.bean.LocalPicBean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/** 本地图片 手势操作 */
public class LocalImageGesturesActivity extends BaseActivity implements ViewPager.OnPageChangeListener , View.OnClickListener{

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tv_selectNum)
    TextView tv_selectNum;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.title)
    TitleBarView title ;

    public static final String INTENT_LIST_TAG = "allList";

    public static final String INTENT_SELECTED_TAG = "selectedList";

    public static final String INTENT_INDEX_TAG = "index";

    private List<LocalPicBean>  mAllList ;
    private List<LocalPicBean> mSelectedList ;

    private LocalPicBean mCurrentBean ;

    private LocalImageGesturesViewPagerAdapter mViewPagerAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_image_gestures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAllList = (List<LocalPicBean>) getIntent().getSerializableExtra(INTENT_LIST_TAG);
        mSelectedList = (List<LocalPicBean>) getIntent().getSerializableExtra(INTENT_SELECTED_TAG);
        if(mAllList != null){
            if(mAllList.get(0) == null){
                mAllList.remove(0);
            }
            mViewPagerAdapter = new LocalImageGesturesViewPagerAdapter(mAllList);
            viewPager.setAdapter(mViewPagerAdapter);
            viewPager.setCurrentItem(getIntent().getIntExtra(INTENT_INDEX_TAG , 0));
            viewPager.addOnPageChangeListener(this);
        }
        if(mSelectedList != null){
            tv_selectNum.setText(mSelectedList.size() + "");
        }
        checkBox.setOnClickListener(this);
        title.setLeftOnclickListener(this);
    }

    @OnClick(R.id.ll_finish)
    public void onFinish(View view){
        startActivity(EditorImageActivity.class , LocalImageGesturesActivity.INTENT_SELECTED_TAG , (Serializable) mSelectedList);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(mAllList != null){
            mCurrentBean = mAllList.get(position);
            checkBox.setChecked(mSelectedList != null && mSelectedList.contains(mCurrentBean));
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ib_image_left:
                Intent intent = new Intent();
                intent.putExtra(INTENT_SELECTED_TAG , (Serializable) mSelectedList);
                setResult(LocalImageGridAvtivty.REQWUEST_CODE , intent);
                finish();
                break;
            case R.id.checkBox:
                boolean isChecked = checkBox.isChecked() ;
                if(mSelectedList.size() >= 9){
                    checkBox.setChecked(false);
                    Toast.makeText(this , "最多选9张" , Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(isChecked ){
                    if(!mSelectedList.contains(mCurrentBean)){
                        mSelectedList.add(mCurrentBean);
                    }
                }else{
                    if(mSelectedList.contains(mCurrentBean)){
                        mSelectedList.remove(mCurrentBean);
                    }
                }
                tv_selectNum.setText(mSelectedList.size() + "");
                break;
        }
    }
}
