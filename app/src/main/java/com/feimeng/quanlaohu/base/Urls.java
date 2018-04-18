package com.feimeng.quanlaohu.base;

/**
 * 存放urls
 * Created by hsy on 2018/4/17.
 */

public interface Urls {
    String BASE_URL = "http://192.168.0.108:8000/";
    //发送验证码 访问参数：mobile
    String SEND_CODE =BASE_URL+ "register/send-code";
    //普通登录接口
    String LOGIN_NOR =BASE_URL+ "register/login" ;
    //个人中心接口
    String USER_CENTER = BASE_URL + "tk/personal-info";
}
