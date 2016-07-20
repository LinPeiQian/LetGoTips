package com.chwings.letgotips.fragment.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.dialog.MessageOperationPopupWindow;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.chwings.letgotips.view.FullyLinearLayoutManager;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageAtFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView rv_at;

    private MessageOperationPopupWindow mPop ;

    @Override
    public int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv_at.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i44444444);
        CommonAdapter<Integer> mAdapter = new CommonAdapter<Integer>(getActivity() , R.layout.item_message , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                ImageView iv_avater = (ImageView)holder.getView(R.id.iv_avater);
                Glide.with(getActivity()).load(integer).transform(new GlideCircleTransform(getActivity())).into(iv_avater);
            }
        };
        rv_at.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if(mPop == null){
                    mPop = new MessageOperationPopupWindow(getActivity());
                }
                mPop.show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

}
