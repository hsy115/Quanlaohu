package com.feimeng.quanlaohu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.BaseLazyFragment;
import com.feimeng.quanlaohu.ui.adapter.OrderAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单列表
 * Created by hsy on 2018/4/18.
 */

public class OrderFragment extends BaseLazyFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private int postion;
    private OrderAdapter mOrderAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    public static OrderFragment getInstance(int postion) {
        OrderFragment of = new OrderFragment();
        of.postion = postion;
        return of;
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        mOrderAdapter = new OrderAdapter(R.layout.recycler_order,list);
        mRecycler.setAdapter(mOrderAdapter);
    }

    @Override
    protected void setListener() {
//        mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//
//            }
//        });
    }


}
