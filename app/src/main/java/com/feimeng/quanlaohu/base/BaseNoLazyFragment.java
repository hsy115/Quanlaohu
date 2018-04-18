package com.feimeng.quanlaohu.base;

/**
 * fragment的基类
 * Created by hsy on 2017/8/2.
 */
public abstract class BaseNoLazyFragment extends BaseLazyFragment {

    @Override
    protected boolean isLazyLoad() {
        return false;
    }
}