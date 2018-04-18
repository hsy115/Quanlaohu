package com.feimeng.quanlaohu.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.App;
import com.feimeng.quanlaohu.base.BaseLazyFragment;
import com.feimeng.quanlaohu.ui.adapter.HomeFiveRecyclerAdapter;
import com.feimeng.quanlaohu.ui.adapter.HomeSixRecyclerAdapter;
import com.feimeng.quanlaohu.util.HsyUtils;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * 首页tab0
 * Created by hsy on 2018/4/16.
 */

public class Tab0Fragment extends BaseLazyFragment {


    @BindView(R.id.home_banner)
    BGABanner mBanner;
    @BindView(R.id.recycler_home_five)
    RecyclerView mRecycler_home_five;
    @BindView(R.id.recycler_home_imgs)
    RecyclerView mRecycler_home_six;
    @BindView(R.id.ll_search_all)
    LinearLayout mLlSearchAll;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.tab)
    SlidingTabLayout mtab;
    @BindView(R.id.vp_list)
    ViewPager mVpList;
    private float imageHeight;
    private int page;
    private HomeFiveRecyclerAdapter mHomeFiveRecyclerAdapter;
    private HomeSixRecyclerAdapter mHomeSixRecyclerAdapter;
    private String[] mTitles_3 = {"推荐", "女装", "男装", "母婴","家居","美妆","家电"};
    private ArrayList<SimpleCardFragment> mFragments = new ArrayList<>();
    private ClipboardManager mManager;
    private ClipboardManager.OnPrimaryClipChangedListener mOnPrimaryClipChangedListener;
    private boolean hasChanged;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_tab0;
    }

    @Override
    protected void initView() {
        //获取状态栏高度
        int heigh = mImmersionBar.getStatusBarHeight(getActivity());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLlSearchAll.getLayoutParams();
        lp.setMargins(0, heigh, 0, 0);
        mLlSearchAll.setLayoutParams(lp);
    }

    @Override
    protected void initData() {
        initList();
    }

    /**
     * 主要列表
     */
    private void initList() {
        for (String title : mTitles_3) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
        }
        //header
        initHeader();
    }

    /**
     * 初始化头布局
     */
    private void initHeader() {
        //banner
        initBanner();
        //5个分类
        mRecycler_home_five.setLayoutManager(new GridLayoutManager(mActivity, 5) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        initFive();
        //6个活动
        // mRecycler_home_six = mView_header.findViewById(R.id.recycler_home_imgs);
        mRecycler_home_six.setLayoutManager(new GridLayoutManager(mActivity, 3) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        initSix();
        //tab
        initTab();
        //自动刷新
        getAllData();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        mVpList.setAdapter(new MyPagerAdapter(getFragmentManager()));

        mtab.setViewPager(mVpList,mTitles_3);
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
            return mTitles_3.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
    /**
     * 初始化6个活动
     */
    private void initSix() {
        mHomeSixRecyclerAdapter = new HomeSixRecyclerAdapter(R.layout.recycler_home_six, null);
        mRecycler_home_six.setAdapter(mHomeSixRecyclerAdapter);
    }

    /**
     * 初始化5个分类
     */
    private void initFive() {
        mHomeFiveRecyclerAdapter = new HomeFiveRecyclerAdapter(R.layout.recycler_home_five, null);
        mRecycler_home_five.setAdapter(mHomeFiveRecyclerAdapter);
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        //计算图片左右间距之和
        final int imageWidth = HsyUtils.getScreenWidth(mActivity);
        //计算宽高比，注意数字后面要加上f表示浮点型数字
        float scale = 3f / 2f;
        //根据图片宽度和比例计算图片高度
        final int imageHeight = (int) (imageWidth / scale);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        mBanner.setLayoutParams(params);
        //banner
        mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, final ImageView itemView, String model, int position) {
                Glide.with(mActivity)
                        .load(model)
                        .centerCrop()
                        .dontAnimate()
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                itemView.setImageDrawable(resource);
                            }
                        });
            }
        });
    }

    @Override
    protected void setListener() {
        //监听变色
        initTopBarColor();
        //刷新
        initRefresh();
        //点击事件
    }

    /**
     * 初始化刷新
     */
    private void initRefresh() {

    }

    /**
     * 获取所有数据
     */
    private void getAllData() {
        //bannar
        List<String> list_banner = new ArrayList<>();
        list_banner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524464835&di=942ea9767864b5e82681bbd879d62cb9&imgtype=jpg&er=1&src=http%3A%2F%2Fimgs.soufun.com%2Fviewimage%2Fhouse%2F2015_01%2F19%2Fxian%2F1421656074405_000%2F450x337.jpg");
        list_banner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524464863&di=ee8622af6987b614b3a32ad4b3e3c147&imgtype=jpg&er=1&src=http%3A%2F%2Fimgs.soufun.com%2Fviewimage%2Fhouse%2F2011_11%2F25%2Fxian%2F1322190806592_000%2F450x337.jpg");
        mBanner.setData(list_banner, null);
        //5ge
        List<String> list_five = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list_five.add("1");
        }
        mHomeFiveRecyclerAdapter.replaceData(list_five);
        //6ge
        List<String> list6 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list6.add("133");
        }
        mHomeSixRecyclerAdapter.replaceData(list6);
    }

    /**
     * 动态变色
     */
    private void initTopBarColor() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = mBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBanner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = mBanner.getHeight() - mLlTop.getHeight();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                            float totalDy = i1 - i;
                           // Log.e("xx", "onScrollChange: " + totalDy);
                            // Log.e("xx", "onScrolled: " + totalDy);
                            if (totalDy <= 0) {
                                mLlTop.setBackgroundColor(Color.argb((int) 0, 250, 205, 52));//AGB由相关工具获得，或者美工提供
                            } else if (totalDy > 0 && totalDy <= imageHeight) {
                                float scale = (float) totalDy / imageHeight;
                                float alpha = (255 * scale);
                                // 只是layout背景透明(仿知乎滑动效果)
                                // Log.e("xx", "totalDy: ");
                                mLlTop.setBackgroundColor(Color.argb((int) alpha, 250, 205, 52));
                            } else {
                                mLlTop.setBackgroundColor(Color.argb((int) 255, 250, 205, 52));
                            }
                        }
                    });
                }

            }
        });
    }

    /**
     * 获取剪贴板
     */
    private void registerClipEvents() {
        // 获取 剪切板数据
        ClipboardManager cm = (ClipboardManager)getActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData cd2 = cm.getPrimaryClip();
        if (cm.hasPrimaryClip()) {
            String copy_string = cd2.getItemAt(0).getText().toString();
            if (!copy_string.equals(copy_string)) {
                showDialog(copy_string);
                App.copy_string = copy_string;
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        registerClipEvents();
    }

    /**
     * 弹窗
     * @param copy_string
     */
    private void showDialog(String copy_string) {
        final NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content(String.valueOf(copy_string))//
                .title("提示")
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {

                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {

                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
