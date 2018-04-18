package com.feimeng.quanlaohu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.BaseActivity;
import com.feimeng.quanlaohu.ui.fragment.Tab0Fragment;
import com.feimeng.quanlaohu.ui.fragment.Tab1Fragment;
import com.feimeng.quanlaohu.ui.fragment.Tab2Fragment;
import com.feimeng.quanlaohu.ui.fragment.Tab3Fragment;
import com.feimeng.quanlaohu.ui.fragment.Tab4Fragment;
import com.feimeng.quanlaohu.ui.view.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.iv_bottom0)
    ImageView mIvBottom0;
    @BindView(R.id.tv_bottom0)
    TextView mTvBottom0;
    @BindView(R.id.iv_bottom1)
    ImageView mIvBottom1;
    @BindView(R.id.tv_bottom1)
    TextView mTvBottom1;
    @BindView(R.id.iv_bottom2)
    ImageView mIvBottom2;
    @BindView(R.id.tv_bottom2)
    TextView mTvBottom2;
    @BindView(R.id.iv_bottom3)
    ImageView mIvBottom3;
    @BindView(R.id.tv_bottom3)
    TextView mTvBottom3;
    @BindView(R.id.iv_bottom4)
    ImageView mIvBottom4;
    @BindView(R.id.tv_bottom4)
    TextView mTvBottom4;
    @BindView(R.id.ll_tab0)
    LinearLayout mLlTab0;
    @BindView(R.id.ll_tab1)
    LinearLayout mLlTab1;
    @BindView(R.id.ll_tab2)
    LinearLayout mLlTab2;
    @BindView(R.id.ll_tab3)
    LinearLayout mLlTab3;
    @BindView(R.id.ll_tab4)
    LinearLayout mLlTab4;
    private ArrayList<Fragment> mFragments;
    private Tab0Fragment tab0;
    private Tab1Fragment tab1;
    private Tab2Fragment tab2;
    private Tab3Fragment tab3;
    private Tab4Fragment tab4;

    @Override
    protected int initContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        tab0 = new Tab0Fragment();
        tab1 = new Tab1Fragment();
        tab2 = new Tab2Fragment();
        tab3 = new Tab3Fragment();
        tab4 = new Tab4Fragment();
        mFragments.add(tab0);
        mFragments.add(tab1);
        mFragments.add(tab2);
        mFragments.add(tab3);
        mFragments.add(tab4);
        //初始化vp
        initViewPager();
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(4);
        //tab0选中
        mLlTab0.setSelected(true);
        mViewPager.setAdapter(new MyHomeAdapter(getSupportFragmentManager()));
        //禁止滑动
        mViewPager.setScroll(false);
    }

    @OnClick({R.id.ll_tab0, R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3, R.id.ll_tab4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab0:
                tabSelected(0);
                break;
            case R.id.ll_tab1:
                tabSelected(1);
                break;
            case R.id.ll_tab2:
                tabSelected(2);
                break;
            case R.id.ll_tab3:
                tabSelected(3);
                break;
            case R.id.ll_tab4:
                tabSelected(4);
                break;
        }
    }

    /**
     * 私有适配器
     * fragment
     */
    private class MyHomeAdapter extends FragmentPagerAdapter {
        MyHomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    /**
     * tab选中
     *
     * @param i
     */
    public void tabSelected(int i) {
        mLlTab0.setSelected(false);
        mLlTab1.setSelected(false);
        mLlTab2.setSelected(false);
        mLlTab3.setSelected(false);
        mLlTab4.setSelected(false);
        switch (i) {
            case 0:
                mViewPager.setCurrentItem(0);
                mLlTab0.setSelected(true);
                break;
            case 1:
                mViewPager.setCurrentItem(1);
                mLlTab1.setSelected(true);
                break;
            case 2:
                mViewPager.setCurrentItem(2);
                mLlTab2.setSelected(true);
                break;
            case 3:
                mViewPager.setCurrentItem(3);
                mLlTab3.setSelected(true);
                break;
            case 4:
                mViewPager.setCurrentItem(4);
                mLlTab4.setSelected(true);
                tab4.onResume();
                break;
            default:
                break;
        }
    }
}
