package com.feimeng.quanlaohu.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsy on 2018/4/18.
 */

public class SettingNorRecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public SettingNorRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
