package com.chwings.letgotips.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.fragment.BaseFragment;
import com.chwings.letgotips.fragment.found.FoundFragment;
import com.chwings.letgotips.fragment.guide.GuideFragment;
import com.chwings.letgotips.fragment.message.MessageFragment;
import com.chwings.letgotips.fragment.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.menu_guide)
    RelativeLayout menu_guide;

    private List<BaseFragment> mFragmrntList ;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
        //默认点击菜单第一项
        menu_guide.performClick();
    }

    private void initFragment(){
        mFragmrntList = new ArrayList<>();
        mFragmrntList.add(new GuideFragment());
        mFragmrntList.add(new FoundFragment());
        mFragmrntList.add(new MessageFragment());
        mFragmrntList.add(new MyFragment());
    }

    @OnClick({R.id.menu_guide , R.id.menu_found , R.id.menu_message , R.id.menu_my})
    public void onSwitchFragment(View view){
        int id = view.getId();
        int cuttent = 0;
        BaseFragment fragment = null;
        switch (id){
            case R.id.menu_guide:
                //指南
                cuttent = 0;
                break;
            case R.id.menu_found:
                //发现
                cuttent = 1;
                break;
            case R.id.menu_message:
                //消息
                cuttent = 2;
                break;
            case R.id.menu_my:
                //我的
                cuttent = 3;
                break;
            default:
                cuttent = 0;
                break;
        }
        fragment = mFragmrntList.get(cuttent);
        if(fragment != null){
            if(fragment.isAdded()){
                getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fl_content , fragment , fragment.getClass().getSimpleName()).commitAllowingStateLoss();
            }
            for (BaseFragment hideFragment : mFragmrntList){
                if(hideFragment != fragment){
                    if(hideFragment.isAdded()){
                        getSupportFragmentManager().beginTransaction().hide(hideFragment).commitAllowingStateLoss();
                    }
                }
            }
        }
    }


}
