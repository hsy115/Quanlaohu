package com.feimeng.quanlaohu.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.util.HsyUtils;

import java.util.List;

/**
 * Created by hsy on 2018/4/16.
 */

public class HomeSixRecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HomeSixRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv = helper.getView(R.id.iv_wrap);
        //计算图片左右间距之和-8px
        final int imageWidth = (HsyUtils.getScreenWidth(mContext)-8)/3;
        //计算宽高比，注意数字后面要加上f表示浮点型数字
        float scale = 1;
        //根据图片宽度和比例计算图片高度
        final int imageHeight = (int) (imageWidth / scale);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        iv.setLayoutParams(params);
    }
}
