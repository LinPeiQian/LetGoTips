package com.chwings.letgotips.activity.found;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.dialog.SelectSearchTypePopupWindow;
import com.chwings.letgotips.helper.SearchHistoryHelper;
import com.chwings.letgotips.itemDecoration.DividerItemDecoration;
import com.chwings.letgotips.pull.IRecyclerView;
import com.chwings.letgotips.view.WithDelEditText;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener , SelectSearchTypePopupWindow.OnSelectSearchTypeListener{

    @BindView(R.id.recyclerView)
    IRecyclerView recyclerView;

    @BindView(R.id.et_search)
    WithDelEditText et_search;

    @BindView(R.id.rl_top)
    RelativeLayout rl_top;

    @BindView(R.id.tv_options)
    TextView tv_options;

    @BindView(R.id.textView)
    TextView tv_header;

    @BindView(R.id.rl_header)
    RelativeLayout rl_header ;

    @BindView(R.id.iv_clear_history)
    ImageView iv_clear_history;

    private SelectSearchTypePopupWindow mPopupWindow ;

    private SearchHistoryHelper mSearchHistoryHelper ;

    private View mHeaderView ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas = new ArrayList<>();
        datas.add("11111111");
        datas.add("2222222");
        datas.add("3333333");
        CommonAdapter<String> adapter = new CommonAdapter<String>(this , R.layout.item_search , datas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.textView , s);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this , LinearLayout.VERTICAL));
        et_search.addTextChangedListener(textWatcher);
    }

    private void initPopupWindow(){
        if(mPopupWindow == null){
            mPopupWindow = new SelectSearchTypePopupWindow(this , tv_options , rl_top , recyclerView , rl_header);
            mPopupWindow.setOnSelectSearchTypeListener(this);
        }
    }

    @OnClick({R.id.tv_options , R.id.tv_cancel})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_options:
                //弹出选项
                initPopupWindow();
                mPopupWindow.show();
                break;
            case R.id.tv_cancel:
                //取消
                finish();
                break;
        }
    }

    @Override
    public void onSelected(String str) {
        tv_options.setText(str);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
