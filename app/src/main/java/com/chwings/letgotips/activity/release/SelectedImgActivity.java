package com.chwings.letgotips.activity.release;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.bean.AddedLabelInfoBean;
import com.chwings.letgotips.bean.PictureProcessFinishBean;
import com.chwings.letgotips.view.LabelView;
import com.chwings.letgotips.view.ZoomableImageView;

import butterknife.BindView;

/**
 * 已选择的图片
 */
public class SelectedImgActivity extends BaseActivity {

    @BindView(R.id.iv_selected)
    ZoomableImageView iv_selected;

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    private PictureProcessFinishBean mInfoBean;

    private int rlWidth, rlHeight;

    private LabelView mLabelView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_selected_img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfoBean = (PictureProcessFinishBean) getIntent().getSerializableExtra(this.getClass().getSimpleName());
        if (mInfoBean != null) {
            Glide.with(this).load(mInfoBean.path).into(iv_selected);

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (mInfoBean.labelInfo != null) {
                for (AddedLabelInfoBean bean : mInfoBean.labelInfo) {
                    mLabelView = new LabelView(this);
                    relativeLayout.addView(mLabelView);
                    mLabelView.setLabelBean(bean.x * relativeLayout.getMeasuredWidth() / mInfoBean.viewWidth, bean.y * relativeLayout.getMeasuredHeight() / mInfoBean.viewHeight, bean.style, bean.labelData);
                }
            }
        }

    }

}
