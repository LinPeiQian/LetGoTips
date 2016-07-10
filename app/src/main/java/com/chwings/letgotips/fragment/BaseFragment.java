package com.chwings.letgotips.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {


    public final String TAG = this.getClass().getSimpleName();

    public abstract int getLayoutId();

    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (getLayoutId() > 0) {
            mView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this , mView);
            return mView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        try {
            setRetainInstance(true);
        } catch (Exception e) {

        }
    }



}
