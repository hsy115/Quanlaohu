package com.feimeng.quanlaohu.ui.test;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自动适应高度的ViewPager
 * @author 
 *
 */
public class Custom2ViewPager extends ViewPager {
 
    public Custom2ViewPager(Context context) {
        super(context);
    }
 
    public Custom2ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
 
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }
 
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
 
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}