package com.chwings.letgotips.activity.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.brianLin.view.ListViewForScrollView;
import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.adapter.guide.guide.TravelDetailedLvAdapter;
import com.chwings.letgotips.listener.SharePopupWindowOnclicklistener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 游记详情
 */
public class TravelDetailedActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleBarView title;

    @BindView(R.id.listview)
    ListViewForScrollView listview;

    @BindView(R.id.ll_all)
    LinearLayout ll_all;

    @BindView(R.id.scrollview)
    ScrollView scrollview;

    private List<String> mDatas = new ArrayList<>();

    private TravelDetailedLvAdapter mAdapter ;


    @Override
    public int getLayoutId() {
        return R.layout.activity_travel_detailed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mAdapter = new TravelDetailedLvAdapter(this);
        listview = (ListViewForScrollView)findViewById(R.id.listview);
        mAdapter.setData(mDatas);
        listview.setAdapter(mAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.item_travel_detailed_num , null);
        listview.addHeaderView(view);
        title.setRightOnclickListener(new SharePopupWindowOnclicklistener(this , title , scrollview));
    }

    private void initData(){
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
    }

}
