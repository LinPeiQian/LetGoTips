package com.chwings.letgotips.adapter.guide.guide;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.brianLin.adapter.MyBaseAdapter;
import com.brianLin.adapter.MyViewHolder;
import com.chwings.letgotips.R;

/**
 * Created by Jensen on 2016/7/8.
 */
public class TravelDetailedLvAdapter  extends MyBaseAdapter<String> {

    private RelativeLayout.LayoutParams params ;

    public TravelDetailedLvAdapter(Context context) {
        super(context);

    }

    @Override
    public int getConvertViewId(int position) {
        return R.layout.item_travel_detailed;
    }

    @Override
    public MyViewHolder<String> getNewHolder(int position) {
        return new ViewHolder();
    }

    class ViewHolder extends MyViewHolder<String> {

        View v_line;
//        RecyclerView recyclerView;
//        private RecyclerAdapter mAdapter ;

        @Override
        public void initHolder(View view, int position) {
            v_line = view.findViewById(R.id.v_line);
//            recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
//            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext() , 3));
            params = (RelativeLayout.LayoutParams) v_line.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_TOP , R.id.ll_top);
            params.addRule(RelativeLayout.ALIGN_BOTTOM , R.id.bottom);
            v_line.setLayoutParams(params);


        }

        @Override
        public void loadData(String data, int position) {
//            mAdapter = new RecyclerAdapter(null);
//            recyclerView.setAdapter(mAdapter);
        }
    }
}