package com.chwings.letgotips.fragment.guide;


import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.chwings.letgotips.R;
import com.chwings.letgotips.fragment.BaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesLabelFragment extends BaseFragment {

    @BindView(R.id.rl_content)
    RelativeLayout rl_content;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notes_label;
    }

}
