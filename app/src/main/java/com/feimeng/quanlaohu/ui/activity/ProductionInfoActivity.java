package com.feimeng.quanlaohu.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.BaseActivity;
import com.feimeng.quanlaohu.ui.adapter.TestRecyclerAdapter;
import com.feimeng.quanlaohu.util.HsyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 产品详情页
 * Created by hsy on 2018/4/17.
 */

public class ProductionInfoActivity extends BaseActivity {

    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.ll_money)
    LinearLayout mLlMoney;
    @BindView(R.id.ll_buy)
    LinearLayout mLlBuy;
    private BGABanner mBanner;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    private TestRecyclerAdapter mTestRecyclerAdapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_production;
    }

    @Override
    protected void initView() {
        initList();
    }

    /**
     * 初始化列表
     */
    private void initList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        // CustomDecoration customDecoration = new CustomDecoration(mActivity, CustomDecoration.VERTICAL_LIST, R.drawable.divider_left_inset, DensityUtil.dp2px(10), true);
        //recyclerView.addItemDecoration(customDecoration);
        mTestRecyclerAdapter = new TestRecyclerAdapter(R.layout.recycler_production, list);
        mRecycler.setAdapter(mTestRecyclerAdapter);
        initHeader();
    }

    /**
     * 初始化头布局
     * banner等
     */
    private void initHeader() {
        View view_header = View.inflate(this, R.layout.production_header, null);
        //获取banner
        mBanner = view_header.findViewById(R.id.banner);
        initBanner();
        //price 中划线
        TextView tv_price = view_header.findViewById(R.id.tv_mid_strike);
        tv_price.getPaint().setAntiAlias(true);//抗锯齿
        tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        //获取info
        mTestRecyclerAdapter.addHeaderView(view_header);
    }

    @Override
    protected void initData() {
        //bannar
        List<String> list_banner = new ArrayList<>();
        list_banner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524464835&di=942ea9767864b5e82681bbd879d62cb9&imgtype=jpg&er=1&src=http%3A%2F%2Fimgs.soufun.com%2Fviewimage%2Fhouse%2F2015_01%2F19%2Fxian%2F1421656074405_000%2F450x337.jpg");
        list_banner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524464863&di=ee8622af6987b614b3a32ad4b3e3c147&imgtype=jpg&er=1&src=http%3A%2F%2Fimgs.soufun.com%2Fviewimage%2Fhouse%2F2011_11%2F25%2Fxian%2F1322190806592_000%2F450x337.jpg");
        mBanner.setData(list_banner, null);
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        //计算图片左右间距之和
        final int imageWidth = HsyUtils.getScreenWidth(this);
        //计算宽高比，注意数字后面要加上f表示浮点型数字
        float scale = 1;
        //根据图片宽度和比例计算图片高度
        final int imageHeight = (int) (imageWidth / scale);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        mBanner.setLayoutParams(params);
        //banner
        mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, final ImageView itemView, String model, int position) {
                Glide.with(ProductionInfoActivity.this)
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_collection, R.id.ll_share, R.id.ll_money, R.id.ll_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_collection:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_money:
                break;
            case R.id.ll_buy:
                break;
        }
    }
}
