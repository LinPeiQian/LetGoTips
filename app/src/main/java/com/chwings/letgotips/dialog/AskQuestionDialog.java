package com.chwings.letgotips.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brianLin.dialog.BaseDialogFragment;
import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.utils.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提问问题 弹出框
 */
public class AskQuestionDialog extends BaseDialogFragment{

    @BindView(R.id.iv_avater)
    ImageView iv_avater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(true);
        setFullScreen(true);
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_question , null);
        ButterKnife.bind(this , view );
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Glide.with(getActivity()).load(R.drawable.i33333333333).transform(new GlideCircleTransform(getActivity())).into(iv_avater);
    }

    @Override
    public void show(FragmentManager fragment) {
        show(fragment , getClass().getSimpleName());
    }

}
