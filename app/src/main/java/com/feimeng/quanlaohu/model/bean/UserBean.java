package com.feimeng.quanlaohu.model.bean;

import java.io.Serializable;

/**
 * 用户
 * Created by hsy on 2018/4/18.
 */

public class UserBean implements Serializable{

    /**
     * id : 3
     * nickname : null
     * headimgurl : null
     * idfa : null
     * sex : null
     * province : null
     * city : null
     * country : null
     * remark : null
     * created_at : 2018-04-18 10:48:07
     * updated_at : 2018-04-18 10:48:07
     * mobile : 15961715231
     * is_doubt : 0
     * is_del : 0
     * code : null
     * settlement : 0
     * forecastIncome : 0
     * incomeAll : 0 累计收入
     * incomeTakeOut : 0
     * proportions : 0
     * incomeInvite : 0
     * todayIncome : 0
     * fansNum : 0
     * password : null
     */

    public int id;
    public String nickname;
    public String headimgurl;
    public Object idfa;
    public Object sex;
    public Object province;
    public Object city;
    public Object country;
    public Object remark;
    public String created_at;
    public String updated_at;
    public String mobile;
    public int is_doubt;
    public int is_del;
    public String code;
    public int settlement;
    public int forecastIncome;
    public int incomeAll;
    public int incomeTakeOut;
    public int proportions;
    public int incomeInvite;
    public int todayIncome;
    public int fansNum;
    public Object password;
    public int is_member;// 1代表超级会员  0代表普通
    public String user_id;
}
