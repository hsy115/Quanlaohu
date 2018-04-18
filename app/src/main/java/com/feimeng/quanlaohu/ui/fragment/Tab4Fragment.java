package com.feimeng.quanlaohu.ui.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.App;
import com.feimeng.quanlaohu.base.BaseNoLazyFragment;
import com.feimeng.quanlaohu.base.Urls;
import com.feimeng.quanlaohu.model.LzyResponse;
import com.feimeng.quanlaohu.model.bean.UserCenterBean;
import com.feimeng.quanlaohu.model.callback.JsonCallback;
import com.feimeng.quanlaohu.ui.activity.IncomeActivity;
import com.feimeng.quanlaohu.ui.activity.LoginActivity;
import com.feimeng.quanlaohu.ui.adapter.HomeFiveRecyclerAdapter;
import com.feimeng.quanlaohu.ui.adapter.SettingNorRecyclerAdapter;
import com.feimeng.quanlaohu.util.MyToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人中心
 * Created by hsy on 2018/4/16.
 */

public class Tab4Fragment extends BaseNoLazyFragment {
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_msg)
    ImageView mIvMsg;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.recycler_order)
    RecyclerView mRecyclerOrder;
    @BindView(R.id.recycler_other)
    RecyclerView mRecyclerOther;
    @BindView(R.id.recycler_other_vertical)
    RecyclerView mRecyclerOtherVertical;
    @BindView(R.id.tv_vip)
    TextView mTvVip;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.tv_money_today)
    TextView mTvMoneyToday;
    @BindView(R.id.tv_money_out)
    TextView mTvMoneyOut;
    @BindView(R.id.ll_get_money)
    LinearLayout mLlGetMoney;


    private HomeFiveRecyclerAdapter mHomeFiveRecyclerAdapter;
    private HomeFiveRecyclerAdapter mHomeFiveRecyclerAdapter2;
    private SettingNorRecyclerAdapter mSettingNorRecyclerAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_tab4;
    }

    @Override
    protected void initView() {
        //获取状态栏高度
        int heigh = mImmersionBar.getStatusBarHeight(getActivity());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mRlTop.getLayoutParams();
        lp.setMargins(0, heigh, 0, 0);
        mRlTop.setLayoutParams(lp);
    }

    @Override
    public void onResume() {
        super.onResume();

        //登陆
        if (App.isLogin) {
            mTvNickname.setText(App.userInfo.nickname);
            mLlCode.setVisibility(View.VISIBLE);
            mTvVip.setVisibility(View.VISIBLE);
            if (App.userInfo.is_member == 1) {//VIP
                mTvVip.setText("超级会员");
            } else {
                mTvVip.setText("普通会员");
            }
            //邀请码
            mTvCode.setText(App.userInfo.code);
            //获取个人中心
            getMoneyData();
        } else {
            mTvNickname.setText("未登录");
            mLlCode.setVisibility(View.GONE);
            mTvVip.setVisibility(View.GONE);
            mTvMoneyToday.setText("¥0.00");
            mTvMoneyOut.setText("¥0.00");
        }
    }


    /**
     * 获取money数据
     */
    private void getMoneyData() {
        OkGo.<LzyResponse<UserCenterBean>>post(Urls.USER_CENTER)
                .tag(this)
                .params("sign", App.userInfo.user_id)
                .execute(new JsonCallback<LzyResponse<UserCenterBean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UserCenterBean>> response) {
                        if (response.body() != null) {
                            if (response.body().status == 1) {
                                //成功
                                mTvMoneyToday.setText("¥" + response.body().data.todayIncome);
                                mTvMoneyOut.setText("¥" + response.body().data.forecastIncome);
                            } else {
                                mTvMoneyToday.setText("¥0.00");
                                mTvMoneyOut.setText("¥0.00");
                            }
                        }
                    }
                });

    }

    @Override
    protected void initData() {

        //4个分类
        mRecyclerOrder.setLayoutManager(new GridLayoutManager(mActivity, 4) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        mHomeFiveRecyclerAdapter = new HomeFiveRecyclerAdapter(R.layout.recycler_home_five, null);
        mRecyclerOrder.setAdapter(mHomeFiveRecyclerAdapter);
        List<String> list_five = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list_five.add("1");
        }
        mHomeFiveRecyclerAdapter.replaceData(list_five);
        //5个分类 其他
        mRecyclerOther.setLayoutManager(new GridLayoutManager(mActivity, 5) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        mHomeFiveRecyclerAdapter2 = new HomeFiveRecyclerAdapter(R.layout.recycler_home_five, null);
        mRecyclerOther.setAdapter(mHomeFiveRecyclerAdapter2);
        List<String> list_five2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list_five2.add("1");
        }
        mHomeFiveRecyclerAdapter2.replaceData(list_five2);
        //5个分类 其他
        mRecyclerOtherVertical.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mSettingNorRecyclerAdapter = new SettingNorRecyclerAdapter(R.layout.recycler_setting, null);
        mRecyclerOtherVertical.setAdapter(mSettingNorRecyclerAdapter);
        List<String> list_five3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list_five3.add("1");
        }
        mSettingNorRecyclerAdapter.replaceData(list_five3);
    }

    @Override
    protected void setListener() {
        //点击
        mTvNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (App.isLogin) {
                    MyToast.show(mActivity, "目前是登录状态");
                } else {
                    intentActivity(LoginActivity.class, false);
                }
            }
        });
        //copy
        mTvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(mTvCode.getText().toString().trim());
                MyToast.show(getActivity(), "复制成功");
            }
        });
        //去提现
        mLlGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(IncomeActivity.class,false);
            }
        });
    }


}
