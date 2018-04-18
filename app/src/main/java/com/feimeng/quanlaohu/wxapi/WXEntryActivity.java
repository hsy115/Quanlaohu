package com.feimeng.quanlaohu.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.feimeng.quanlaohu.base.App;
import com.feimeng.quanlaohu.util.MyToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果没回调onResp，八成是这句没有写
        App.mWxApi.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        App.mWxApi.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(final BaseResp resp) {

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) {
                    MyToast.show(this, "分享失败");
                } else {
                    MyToast.show(this, "登录失败");

                }
                break;
            case BaseResp.ErrCode.ERR_OK:
                Log.e("xx", "onResp: " + resp.getType());
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) {
                    MyToast.show(this, "分享成功");
                } else {
                    MyToast.show(this, "授权成功");
                }
                break;
        }
        finish();
    }

}