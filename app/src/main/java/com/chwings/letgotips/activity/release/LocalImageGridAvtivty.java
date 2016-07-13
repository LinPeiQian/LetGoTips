package com.chwings.letgotips.activity.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.release.LocalImageGridAdapter;
import com.chwings.letgotips.bean.LocalPicBean;
import com.chwings.letgotips.bean.LocalPicFolderBean;
import com.chwings.letgotips.helper.LocalPicHelper;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * 本地图片九宫格
 */
public class LocalImageGridAvtivty extends BaseActivity implements LocalPicHelper.OnScanLocalPicListener , View.OnClickListener{

    @BindView(R.id.rv_photo)
    RecyclerView rv_photo;

    @BindView(R.id.title)
    TitleBarView title;

    private LocalPicHelper mLocalPicBeanList;

    private LocalImageGridAdapter mGridAdapter ;

    public static final int REQWUEST_CODE = 10001 ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_image_grid_avtivty;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSearchLocalPic();
        rv_photo.setLayoutManager(new GridLayoutManager(this , 3));
        title.setRightOnclickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQWUEST_CODE && mGridAdapter != null && data != null){
            List<LocalPicBean> list = (List<LocalPicBean>) data.getSerializableExtra(LocalImageGesturesActivity.INTENT_SELECTED_TAG);
            mGridAdapter.setSelectedList(list);
        }
    }

    private void initSearchLocalPic(){
        mLocalPicBeanList = new LocalPicHelper(this);
        mLocalPicBeanList.setOnScanLocalPicListener(this);
        mLocalPicBeanList.scanLocalPic();
    }

    @Override
    public void onScanProgressing() {

    }

    @Override
    public void onScanFinish(List<LocalPicBean> all, List<LocalPicFolderBean> list) {
        mGridAdapter = new LocalImageGridAdapter(this , all );
        rv_photo.setAdapter(mGridAdapter);
    }

    @Override
    public void onScanFaile(Exception e) {

    }

    @Override
    public void onClick(View v) {
        startActivity(EditorImageActivity.class , LocalImageGesturesActivity.INTENT_SELECTED_TAG , (Serializable) mGridAdapter.getSelectedList());
    }
}
