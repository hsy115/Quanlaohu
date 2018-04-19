package com.feimeng.quanlaohu.model.bean;

import java.io.Serializable;

/**
 * Created by hsy on 2018/4/18.
 */

public class IncomeCenterBean implements Serializable{

    /**
     * lastSettlement : 3.22上月结算
     * lastForecastIncome : 6.55上月预估
     * status : 0上月是否结算
     * todayPayMentPen : 3今日付款笔数
     * todayRatio : 5.33
     * yesterdayPaymentPen : 6.35昨日付款笔数
     * yesterdayRatio : 5.36
     */

    public double lastSettlement;
    public double lastForecastIncome;
    public int status;
    public double forecastIncome;
    public int todayPayMentPen;
    public double todayRatio;
    public double yesterdayPaymentPen;
    public double yesterdayRatio;
}
