package com.chwings.letgotips.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.brianLin.dialog.BaseDialogFragment;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.release.EditorImageActivity;
import com.chwings.letgotips.adapter.guide.release.EditorImageViewPagerAdapter;
import com.chwings.letgotips.bean.LabelBean;
import com.chwings.letgotips.bean.LabelEnum;
import com.chwings.letgotips.view.LineEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑笔记信息dialog
 */
public class EditorNotesInfoDialog extends BaseDialogFragment implements View.OnClickListener {

    @BindView(R.id.tabLayout)
    TableLayout tabLayout;

    @BindView(R.id.et_brand)
    LineEditText et_brand;

    @BindView(R.id.et_brandName)
    LineEditText et_brandName;

    @BindView(R.id.et_currency)
    LineEditText et_currency;

    @BindView(R.id.et_howMuch)
    LineEditText et_howMuch;

    @BindView(R.id.et_city)
    LineEditText et_city;

    @BindView(R.id.et_address)
    LineEditText et_address;

    //品牌
    private LabelBean mBrandLabelBean;

    //币种
    private LabelBean mMoneyLabelBean;

    //地址
    private LabelBean mAddLabelBean;

    private List<LabelBean> mLabelBeanList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(true);
        setFullScreen(true);
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_editor_notes_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mLabelBeanList != null){
            for(LabelBean bean : mLabelBeanList){
                if(bean != null){
                    if(bean.tag == LabelEnum.ADDRESS){
                        et_city.setText(bean.type);
                        et_address.setText(bean.obj.toString());
                    }else{
                        et_city.setText("");
                        et_address.setText("");
                    }
                    if(bean.tag == LabelEnum.PRICE){
                        et_currency.setText(bean.type);
                        et_howMuch.setText(bean.obj.toString());
                    }else{
                        et_currency.setText("");
                        et_howMuch.setText("");
                    }
                    if(bean.tag == LabelEnum.NAME){
                        et_brand.setText(bean.type);
                        et_brandName.setText(bean.obj.toString());
                    }else{
                        et_brand.setText("");
                        et_brandName.setText("");
                    }
                }
            }
            mLabelBeanList = null ;
        }else{
            setEmpty();
        }
//        if(mLabelBeanList == null){
//            setEmpty();
//        }
//        mLabelBeanList = null ;
    }

    private void setEmpty(){
        et_brand.setText("");
        et_brandName.setText("");
        et_currency.setText("");
        et_howMuch.setText("");
        et_city.setText("");
        et_address.setText("");
    }

    public void setLabelBeanList(List<LabelBean> list){
        this.mLabelBeanList = null;
        if(list != null){
            this.mLabelBeanList = list ;
        }
    }

    @Override
    public void show(FragmentManager fragment) {
        show(fragment, getClass().getSimpleName());
    }

    @OnClick({R.id.btn_finish, R.id.btn_cancel, R.id.tabLayout})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_finish:
                //完成
                onFinish();
                break;
            case R.id.tabLayout:
            case R.id.btn_cancel:
                //取消
                dismiss();
                break;
        }
    }

    private void onFinish() {
        mBrandLabelBean = null ;
        mMoneyLabelBean = null ;
        mAddLabelBean = null;
        String brand = et_brand.getText().toString().trim();
        String brandName = et_brandName.getText().toString().trim();
        String currency = et_currency.getText().toString().trim();
        String howMuch = et_howMuch.getText().toString().trim();
        String city = et_city.getText().toString().trim();
        String address = et_address.getText().toString().trim();
        if (!TextUtils.isEmpty(brand) || !TextUtils.isEmpty(brandName)) {
            mBrandLabelBean = new LabelBean(brandName, brand, LabelEnum.NAME);
        }
        if (!TextUtils.isEmpty(city) || !TextUtils.isEmpty(address)) {
            mAddLabelBean = new LabelBean(address, city, LabelEnum.ADDRESS);
        }
        if (!TextUtils.isEmpty(currency) || !TextUtils.isEmpty(howMuch)) {
            try {
                float money = Float.parseFloat(howMuch);
                mMoneyLabelBean = new LabelBean(money, currency, LabelEnum.PRICE);
            } catch (Exception e) {
                Log.d(TAG, "输入的价钱非法");
            }
        }
        List<LabelBean> labelBeanList = new ArrayList<>();
        if (mBrandLabelBean != null) {
            labelBeanList.add(mBrandLabelBean);
        }
        if (mAddLabelBean != null) {
            labelBeanList.add(mAddLabelBean);
        }
        if (mMoneyLabelBean != null) {
            labelBeanList.add(mMoneyLabelBean);
        }
        if (labelBeanList.size() > 0) {
            EditorImageActivity activity = (EditorImageActivity) getActivity();
            if (activity != null) {
                EditorImageViewPagerAdapter adapter = activity.mAdapter;
                if (adapter != null) {
                    adapter.setLabelData(labelBeanList);
                }
            }
        }
        dismiss();
    }
}
