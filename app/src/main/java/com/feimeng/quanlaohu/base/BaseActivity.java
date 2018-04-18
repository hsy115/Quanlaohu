package com.feimeng.quanlaohu.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.util.WeiboDialogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


/**
 * activity的基类
 * Created by hsy on 2017/1/9.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected ImmersionBar mImmersionBar;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        //界面中如果有EditText，默认隐藏输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);//竖屏
        initBar();
        //初始化布局
        setContentView(initContentView());
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    protected void initBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }


    /**
     * 初始化contentView
     *
     * @return 返回contentView的layout id
     */
    protected abstract int initContentView();

    /**
     * 初始化View，执行findViewById操作
     */
    protected void initView() {

    }


    /**
     * 初始化监听器
     */
    protected void initListener() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 全局坐左移右移
     *
     * @param a
     * @param isFinish 是否结束
     */
    public void intentActivity(Class a,boolean isFinish) {
        Intent i = new Intent(this, a);
        startActivity(i);
        if (isFinish) {
            finish();
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 此方法只是关闭软键盘
     */
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 返回键..销毁
     */
    @Override
    public void onBackPressed() {
        exit();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
    /**
     * 退出---动画
     */
    protected void exit() {
        finish();
        hintKbTwo();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    /**
     * 显示加载
     */
    protected void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = WeiboDialogUtils.createLoadingDialog(this, "加载中",true);
        } else {
            mLoadingDialog.show();
        }

    }
    /**
     * 显示加载
     */
    protected void showLoading(boolean canFinish) {
        if (mLoadingDialog == null) {
            mLoadingDialog = WeiboDialogUtils.createLoadingDialog(this, "加载中",canFinish);
        } else {
            mLoadingDialog.show();
        }
    }

    /**
     * 关闭加载
     */
    protected void closeLoading() {
        WeiboDialogUtils.closeDialog(mLoadingDialog);
    }
}
