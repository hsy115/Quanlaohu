package com.feimeng.quanlaohu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.feimeng.quanlaohu.R;
import com.feimeng.quanlaohu.base.App;
import com.feimeng.quanlaohu.base.BaseActivity;
import com.feimeng.quanlaohu.base.Urls;
import com.feimeng.quanlaohu.model.LzyResponse;
import com.feimeng.quanlaohu.model.SimpleResponse;
import com.feimeng.quanlaohu.model.bean.UserBean;
import com.feimeng.quanlaohu.model.callback.JsonCallback;
import com.feimeng.quanlaohu.util.MyToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * Created by hsy on 2018/4/17.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.btn_login_nor)
    Button mBtnLoginNor;
    @BindView(R.id.btn_login_wx)
    Button mBtnLoginWx;
    private String mobile;
    private String code;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_send, R.id.btn_login_nor, R.id.btn_login_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                //获取phone
                mobile = mEtPhone.getText().toString().trim();
                if (mobile.isEmpty()) {
                    MyToast.show(this, "手机号不能为空!");
                } else {
                    sendCode();//发送验证码
                }
                break;
            case R.id.btn_login_nor:
                mobile = mEtPhone.getText().toString().trim();
                code = mEtCode.getText().toString().trim();
                if (mobile.isEmpty()) {
                    MyToast.show(this, "手机号不能为空!");
                } else if (code.isEmpty()){
                    MyToast.show(this, "验证码不能为空!");
                }else {
                    login_nor();
                }
                break;
            case R.id.btn_login_wx:
                break;
        }
    }

    /**
     * 普通登录
     */
    private void login_nor() {
        showLoading(false);
        OkGo.<LzyResponse<UserBean>>post(Urls.LOGIN_NOR)
                .tag(this)
                .params("mobile",mobile)
                .params("code",code)
                .execute(new JsonCallback<LzyResponse<UserBean>>() {
                    @Override
                    public void onError(Response<LzyResponse<UserBean>> response) {
                        super.onError(response);

                    }

                    @Override
                    public void onSuccess(Response<LzyResponse<UserBean>> response) {
                        if (response.body()!=null ) {

                            MyToast.show(LoginActivity.this,response.body().msg);
                            if (response.body().status == 1) {
                                App.userInfo = response.body().data;
                                //跳转
                                exit();
                                App.isLogin = true;
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        closeLoading();
                    }


                });
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
        showLoading(false);
        OkGo.<SimpleResponse>post(Urls.SEND_CODE)
                .tag(this)
                .params("mobile",mobile)
                .execute(new JsonCallback<SimpleResponse>() {
                    @Override
                    public void onSuccess(Response<SimpleResponse> response) {
                        if (response.body()!=null ) {
                            MyToast.show(LoginActivity.this,response.body().msg);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        closeLoading();
                    }


                });
    }
}
