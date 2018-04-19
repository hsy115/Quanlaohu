package com.feimeng.quanlaohu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.BaseActivity;
import com.feimeng.quanlaohu.ui.fragment.OrderFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 订单页面
 * Created by hsy on 2018/4/18.
 */

public class OrderInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tab)
    SlidingTabLayout mtab;
    @BindView(R.id.vp)
    ViewPager mVpList;
    private String[] mTitles = {"全部", "已付款", "已结算", "已失效"};
    private ArrayList<OrderFragment> mFragments = new ArrayList<>();

    @Override
    protected int initContentView() {
        return R.layout.activity_order;
    }

    @Override
    protected boolean delBar() {
        return true;//禁止沉浸式
    }

    @Override
    protected void initView() {
        mTvTitle.setText("订单");
    }

    @Override
    protected void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(OrderFragment.getInstance(i));
        }
        initTab();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        mVpList.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mtab.setViewPager(mVpList, mTitles);
        mtab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVpList.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mVpList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mtab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpList.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }

}
