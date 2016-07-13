package com.chwings.letgotips.activity.release;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.release.ReleaseNotesSelectedAdapter;
import com.chwings.letgotips.bean.PictureProcessFinishBean;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发布笔记
 */
public class ReleaseNotesActivity extends BaseActivity implements View.OnClickListener , OnItemClickListener<PictureProcessFinishBean>{

    @BindView(R.id.title)
    TitleBarView title;

    @BindView(R.id.et_notes)
    EditText et_notes;

    @BindView(R.id.rv_selected)
    RecyclerView rv_selected;

    @BindView(R.id.rl_addAddress)
    RelativeLayout rl_addAddress;

    public static final String INTENT_TAG = "ReleaseNotesActivity";

    private List<PictureProcessFinishBean> mBeanData ;

    private ReleaseNotesSelectedAdapter mAdapter ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_release_notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title.setRightOnclickListener(this);
        rv_selected.setLayoutManager(new LinearLayoutManager(this , LinearLayout.HORIZONTAL , false));
        mBeanData = (List<PictureProcessFinishBean>) getIntent().getSerializableExtra(INTENT_TAG);
        if(mBeanData != null){
            initAdapter();
        }
    }

    private void initAdapter(){
        if(mBeanData != null){
            if(mBeanData.size() < 9){
                mBeanData.add(null);
            }
            mAdapter = new ReleaseNotesSelectedAdapter(rv_selected , mBeanData);
            mAdapter.setOnItemClickListener(this);
            rv_selected.setAdapter(mAdapter);
        }
    }

    /** 补充数据 最后一位 */
    private List<PictureProcessFinishBean> getSupplementData(List<PictureProcessFinishBean> datas){
        if(datas != null && datas.size() < 9){
            datas.add(null);
        }
        return datas ;
    }

    @OnClick(R.id.rl_release)
    public void onRelease(View view){
        //发布
    }

    @OnClick(R.id.tv_save)
    public void onSave(View view){
        //保存草稿箱
    }

    @OnClick(R.id.rl_addAddress)
    public void onAddAddress(View view){
        //添加地点
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, PictureProcessFinishBean pictureProcessFinishBean, int position) {
        if(position == mBeanData.size() - 1 && pictureProcessFinishBean == null){
            //添加
        }else{
            startActivity(SelectedImgActivity.class, SelectedImgActivity.class.getSimpleName(), (Serializable) pictureProcessFinishBean);
        }
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, PictureProcessFinishBean pictureProcessFinishBean, int position) {
        return false;
    }
}
