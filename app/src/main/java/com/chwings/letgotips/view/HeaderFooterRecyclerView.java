package com.chwings.letgotips.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.chwings.letgotips.adapter.guide.recyclerView.HeaderFooterRecyclerWrapAdapter;

import java.util.ArrayList;

/**
 * Created by User on 2016/7/10.
 */
public class HeaderFooterRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>() ;

    private ArrayList<View> mFootViews = new ArrayList<>() ;

    private Adapter mAdapter ;

    public HeaderFooterRecyclerView(Context context) {
        super(context);
    }

    public HeaderFooterRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderFooterRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view){
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof HeaderFooterRecyclerWrapAdapter)){
                mAdapter = new HeaderFooterRecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFootView(View view){
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof HeaderFooterRecyclerWrapAdapter)){
                mAdapter = new HeaderFooterRecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderViews.isEmpty()&&mFootViews.isEmpty()){
            super.setAdapter(adapter);
        }else {
            adapter = new HeaderFooterRecyclerWrapAdapter(mHeaderViews,mFootViews,adapter) ;
            super.setAdapter(adapter);
        }
        mAdapter = adapter ;
    }

}
