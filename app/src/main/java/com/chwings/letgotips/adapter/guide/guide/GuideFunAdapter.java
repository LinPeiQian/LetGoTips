package com.chwings.letgotips.adapter.guide.guide;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.testCase.bean.TestGuideFunBean;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

/**
 * Created by Jensen on 2016/7/13.
 */
public class GuideFunAdapter extends CommonAdapter<TestGuideFunBean>{

    public GuideFunAdapter(Context context , List<TestGuideFunBean> datas) {
        super(context, R.layout.item_guide_note, datas);
    }

    @Override
    public void convert(ViewHolder holder, TestGuideFunBean testGuideFunBean, int position) {
        Glide.with(mContext).load(testGuideFunBean.resId).into((ImageView)holder.getView(R.id.iv_fun));
        ((TextView)holder.getView(R.id.tv_instructions)).setText(testGuideFunBean.instructions);
    }

}
