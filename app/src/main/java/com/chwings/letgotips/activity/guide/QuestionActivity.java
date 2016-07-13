package com.chwings.letgotips.activity.guide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.brianLin.view.TitleBarView;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.dialog.AskQuestionDialog;
import com.chwings.letgotips.view.FullyLinearLayoutManager;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QuestionActivity extends BaseActivity {

    @BindView(R.id.rv_question)
    RecyclerView rv_question;

    @BindView(R.id.title)
    TitleBarView title;

    private AskQuestionDialog mDialog ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager manager = new FullyLinearLayoutManager(this , LinearLayout.VERTICAL , true);
        manager.setStackFromEnd(true);
        rv_question.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        rv_question.setAdapter(new CommonAdapter<String>(this , R.layout.item_question , datas) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {

            }
        });
        title.setRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialog == null){
                    mDialog = new AskQuestionDialog();
                }
                mDialog.show(QuestionActivity.this.getSupportFragmentManager());
            }
        });
    }
}
