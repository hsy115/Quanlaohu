package com.feimeng.quanlaohu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.BaseLazyFragment;
import com.feimeng.quanlaohu.ui.activity.ProductionInfoActivity;
import com.feimeng.quanlaohu.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SimpleCardFragment extends BaseLazyFragment {
    @BindView(R.id.recycler_home_list)
    RecyclerView mRecyclerHomeList;

    //tab的title
    private String mTitle;
    private HomeAdapter mHomeAdapter;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fr_simple_card;
    }

    @Override
    protected void initData() {

        mRecyclerHomeList.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        mHomeAdapter = new HomeAdapter(R.layout.recycler_home, list);
        mRecyclerHomeList.setAdapter(mHomeAdapter);
        //mHomeAdapter.replaceData(list);
        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                intentActivity(ProductionInfoActivity.class,false);
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;//不使用
    }
}