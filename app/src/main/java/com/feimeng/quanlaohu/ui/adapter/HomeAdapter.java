package com.feimeng.quanlaohu.ui.adapter;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.feimeng.quanlaohu.R;

import java.util.List;

/**
 * Created by hsy on 2018/4/16.
 */

public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_mid = helper.getView(R.id.tv_mid_strike);
        tv_mid.getPaint().setAntiAlias(true);//抗锯齿
        tv_mid.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰

    }
}
