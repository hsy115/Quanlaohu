package com.feimeng.quanlaohu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.App;
import com.feimeng.quanlaohu.base.BaseActivity;
import com.feimeng.quanlaohu.base.Urls;
import com.feimeng.quanlaohu.model.LzyResponse;
import com.feimeng.quanlaohu.model.bean.IncomeCenterBean;
import com.feimeng.quanlaohu.model.callback.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收益页面
 * Created by hsy on 2018/4/18.
 */

public class IncomeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.tv_money_out)
    TextView mTvMoneyOut;
    @BindView(R.id.tv_money_total)
    TextView mTvMoneyTotal;
    @BindView(R.id.tv_last_month_money_get)
    TextView mTvLastMonthMoneyGet;
    @BindView(R.id.tv_this_month_money)
    TextView mTvThisMonthMoney;
    @BindView(R.id.tv_last_month_money)
    TextView mTvLastMonthMoney;
    @BindView(R.id.tv_today_num)
    TextView mTvTodayNum;
    @BindView(R.id.tv_today_money)
    TextView mTvTodayMoney;
    @BindView(R.id.tv_yesterday_num)
    TextView mTvYesterdayNum;
    @BindView(R.id.tv_yesterday_money)
    TextView mTvYesterdayMoney;
    private String mMoney;

    @Override
    protected int initContentView() {
        return R.layout.activity_income;
    }

    @Override
    protected void initView() {
        //获取状态栏高度
        int heigh = mImmersionBar.getStatusBarHeight(this);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mRlTop.getLayoutParams();
        lp.setMargins(0, heigh, 0, 0);
        mRlTop.setLayoutParams(lp);
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

    @Override
    protected void initData() {
        mMoney = getIntent().getStringExtra("money");
        mTvMoneyOut.setText(mMoney);
        mTvMoneyTotal.setText("累计结算收益:¥" + App.userInfo.incomeAll);
        showLoading();
        OkGo.<LzyResponse<IncomeCenterBean>>post(Urls.INCOME_CENTER)
                .tag(this)
                .params("sign", App.userInfo.user_id)
                .execute(new JsonCallback<LzyResponse<IncomeCenterBean>>() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        closeLoading();
                    }

                    @Override
                    public void onSuccess(Response<LzyResponse<IncomeCenterBean>> response) {
                        if (response.body() != null) {
                            if (response.body().status == 1) {
                                //绑定ui
                                bindData(response.body().data);
                            }
                        }
                    }
                });
    }

    /**
     * 绑定数据
     *
     * @param data
     */
    private void bindData(IncomeCenterBean data) {
        //上月结算
        mTvLastMonthMoneyGet.setText("¥" + data.lastSettlement);
        //本月预估
        mTvThisMonthMoney.setText("¥" + data.forecastIncome);
        //上月预估
        mTvLastMonthMoney.setText("¥" + data.lastForecastIncome);
        //今日付款num
        mTvTodayNum.setText(data.todayPayMentPen+"");
        mTvTodayMoney.setText("¥" + data.todayRatio);
        //昨日付款
        mTvYesterdayNum.setText((int) data.yesterdayPaymentPen+"");
        mTvYesterdayMoney.setText("¥" + data.yesterdayRatio);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
