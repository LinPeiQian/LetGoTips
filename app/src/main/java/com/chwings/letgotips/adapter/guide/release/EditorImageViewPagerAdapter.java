package com.chwings.letgotips.adapter.guide.release;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.brianLin.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.release.EditorImageActivity;
import com.chwings.letgotips.bean.AddedLabelInfoBean;
import com.chwings.letgotips.bean.LabelBean;
import com.chwings.letgotips.bean.LabelEnum;
import com.chwings.letgotips.bean.LocalPicBean;
import com.chwings.letgotips.bean.PictureProcessFinishBean;
import com.chwings.letgotips.dialog.EditorNotesInfoDialog;
import com.chwings.letgotips.view.LabelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编辑图片 viewpager 适配器  点击添加标签
 */
public class EditorImageViewPagerAdapter extends PagerAdapter implements View.OnTouchListener , View.OnClickListener , LabelView.OnLabelViewClickListener{

    private List<LocalPicBean> mData ;

    private Context mContext ;

    private EditorNotesInfoDialog mDialog ;

    private final String TAG = getClass().getSimpleName();

    public EditorImageViewPagerAdapter(List<LocalPicBean> data){
        mData = data ;
    }

    /** 当前展示的RelativeLayout */
    private RelativeLayout mCurrentRelativeLayout ;

    private float x , y ;

    private Map<RelativeLayout , List<LabelView>> mLabelViewMap = new HashMap<>();

    private Map<Integer , RelativeLayout> mParentMap = new HashMap<>();

    private Map<Integer , String> mPathMap = new HashMap<>();

    public void setLabelData(List<LabelBean> labelList){
        if(labelList != null && labelList.size() > 0 && mCurrentRelativeLayout != null){
            LabelView labelView = new LabelView(mCurrentRelativeLayout.getContext());
            labelView.setOnLabelViewLongClick(this);
            mCurrentRelativeLayout.addView(labelView);
            for(LabelBean bean : labelList){
                labelView.addLabelBean(bean);
            }
            labelView.setPercentXY(x / mCurrentRelativeLayout.getWidth() , y / mCurrentRelativeLayout.getHeight());
            List<LabelView> labelViews = mLabelViewMap.get(mCurrentRelativeLayout);
            if(labelViews == null ){
                labelViews = new ArrayList<>();
            }
            labelViews.add(labelView);
            mLabelViewMap.put(mCurrentRelativeLayout , labelViews);
        }
    }

    /** 获取统筹数据 */
    public List<PictureProcessFinishBean> getPictureProcessFinishBeanList(){
        List<PictureProcessFinishBean> resule = new ArrayList<>();
        for (Integer position : mParentMap.keySet()) {
            RelativeLayout relativeLayout = mParentMap.get(position);
            List<LabelView> labelViews = mLabelViewMap.get(mParentMap.get(position));
            PictureProcessFinishBean bean = new PictureProcessFinishBean(mPathMap.get(position) , getShowLabelBean(labelViews)
                    ,ScreenUtils.getScreenWidth(mContext) , ScreenUtils.getScreenHeight(mContext) ,
                    relativeLayout.getWidth() , relativeLayout.getHeight());
            resule.add(bean);
        }
        return resule;
    }

    private List<AddedLabelInfoBean> getShowLabelBean(List<LabelView> mLabelViewList){
        if(mLabelViewList != null && mLabelViewList.size() > 0){
            List<AddedLabelInfoBean> showLabelBeenList = new ArrayList<>();
            for(LabelView view : mLabelViewList){
                showLabelBeenList.add(new AddedLabelInfoBean(view.getCurrentStyle() , view.getPointAt().x , view.getPointAt().y , view.getDataForArr()));
            }
            return showLabelBeenList;
        }
        return null;
    }


    public void setCurreentIndex(int index){
        mCurrentRelativeLayout = mParentMap.get(index);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mContext = container.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_editor_image_viewpager , null);
        RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.setOnClickListener(this);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        Glide.with(mContext).load(mData.get(position).path).into(imageView);
        container.addView(view);
        mParentMap.put(position ,relativeLayout );
        mPathMap.put(position , mData.get(position).path);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX() ;
        y = event.getY();
        return false;
    }

    @Override
    public void onClick(View v) {
        mCurrentRelativeLayout = (RelativeLayout) v;
        showDialog();
    }

    private void showDialog(){
        if(mDialog == null){
            mDialog = new EditorNotesInfoDialog();
        }
        if(mContext != null){
            EditorImageActivity act = (EditorImageActivity)mContext;
            mDialog.show(act.getSupportFragmentManager());
        }
    }

    @Override
    public void onLabelViewLongClick(RelativeLayout parent , LabelView view, List<LabelBean> labelBeenList) {
        //长按删除
        if(parent != null && view != null){
            parent.removeView(view);
        }
        List<LabelView> labelViews = mLabelViewMap.get(parent);
        if(labelViews != null){
            labelViews.remove(view);
        }
    }

    @Override
    public void onLabelViewTagClick(String content, LabelEnum labelEnum, List<LabelBean> labelBeenList) {
        //点击标签修改
        showDialog();
        mDialog.setLabelBeanList(labelBeenList);
    }



}
