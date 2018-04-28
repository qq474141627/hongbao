package com.opar.mobile.utils;

/**
 * Created by wangbo on 16/9/2.
 */
public class MoneyUtil {

    //一次性还本付息
    public static double toCalculator(String money,String data,String rate){
        //年化收益率
        double rateValue = StringUtil.parseDouble(rate) / 100;
        //收益天数
        int dataValue = StringUtil.parseInt(data);
        //投资金额
        double moneyValue = StringUtil.parseDouble(money);
        //最终收益
        double avgInterestValue = (dataValue * rateValue * moneyValue) / (365);
        //最终收入
        return avgInterestValue + moneyValue;
    }

    //等额本息
    public static double toCalculator1(String money,String data,String rate){
        //月化收益率
        double rateValue = StringUtil.parseDouble(rate) / 1200;
        //收益期数
        int dataValue = StringUtil.parseInt(data);
        //投资金额
        double moneyValue = StringUtil.parseDouble(money);
        //利率计算
        double avgTmp = Math.pow(1 + rateValue, dataValue);
        //每期收益
        double avgInterestValue = (rateValue * avgTmp * moneyValue) / (avgTmp - 1);
        //总收益
        return avgInterestValue * dataValue;
    }

    //一次性还本 收益
    public static double toCalculator3(double money,int data,String rate){
        //年化收益率
        double rateValue = StringUtil.parseDouble(rate) / 100;
        //最终收益
        double avgInterestValue = (data * rateValue * money) / (365);
        //最终收入
        return avgInterestValue;
    }

    //一次性还本 收益
    public static double toCalculator4(double money,int data,String rate){
        //年化收益率
        double rateValue = StringUtil.parseDouble(rate);
        //最终收益
        double avgInterestValue = (data * rateValue * money) / (365);
        //最终收入
        return avgInterestValue;
    }

    //等额本息 收益
    public static double toCalculator5(double money,int data,String rate){
        //年化收益率
        double rateValue = StringUtil.parseDouble(rate);
        //最终收益
        double avgInterestValue = (data * rateValue * money) / (365);
        //最终收入
        return avgInterestValue;
    }

}
