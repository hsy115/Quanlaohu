package com.feimeng.quanlaohu.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 测试适配器
 * Created by hsy on 2018/4/17.
 */

public class TestRecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public TestRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
