package com.chwings.letgotips.activity.guide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.chwings.letgotips.utils.GlideCircleTransform;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 评论列表
 */
public class CommentActivity extends BaseActivity {

    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;

    @BindView(R.id.et_comment)
    EditText et_comment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv_comment.setLayoutManager(new LinearLayoutManager(this , LinearLayout.VERTICAL , true));
        List<Integer> datas = new ArrayList<>();
        datas.add(R.drawable.i11111111);
        datas.add(R.drawable.i22222222222);
        datas.add(R.drawable.i33333333333);
        datas.add(R.drawable.i44444444);
        datas.add(R.drawable.ic_launcher);
        datas.add(R.drawable.i33333333333);
        rv_comment.setAdapter(new CommonAdapter<Integer>(this , R.layout.item_comment , datas) {
            @Override
            public void convert(ViewHolder holder, Integer integer, int position) {
                Glide.with(CommentActivity.this).load(integer)
                        .transform(new GlideCircleTransform(CommentActivity.this))
                        .into((ImageView)holder.getView(R.id.iv_avater));
            }
        });
    }
}
