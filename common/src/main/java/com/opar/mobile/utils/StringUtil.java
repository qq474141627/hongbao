package com.opar.mobile.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 返回保留两位的字符串
 * Created by tangge on 2018/3/22.
 */
public class StringUtil {
    /**
     * 返回保留两位的字符串
     */
    public static String getString(String aa) {
        int i = aa.indexOf(".");
        if (i > 0) {
            if (aa.length() - i > 2) {
                return aa.substring(0, i + 3);
            }
        } else {
            return aa;
        }
        return aa;
    }

    /**
     * 保留整数
     *
     * @param num
     * @return
     */
    public static String parseNum(String num) {
        DecimalFormat format = new DecimalFormat("#0");
        try {
            return format.format(StringUtil.parseDouble(num));
        } catch (Exception ex) {
//            LogUtils.e("StringUtil", "**" + num);
            return "0";
        }
    }

    /**
     * 保留整数
     *
     * @param num
     * @return
     */
    public static double parseDouble(String num) {
        try {
            return Double.parseDouble(num);
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 保留2位小数
     *
     * @param num
     * @return
     */
    public static float parse2Double(String num) {
        try {
            BigDecimal bg = new BigDecimal(num);
            float f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return f1;
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 保留整数
     *
     * @param num
     * @return
     */
    public static int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getNumKb(String s) {
        NumberFormat formatter = new DecimalFormat("#,##0.##");
        return formatter.format(Long.valueOf(s).longValue()) + "";
    }

    public static String getNumKbNot(String s) {
        NumberFormat formatter = new DecimalFormat("######");
        return formatter.format(Long.valueOf(s).longValue()) + "";
    }

    public static String getNumKbPoint(String s) {
        NumberFormat formatter = new DecimalFormat("######.###");
        return formatter.format(Long.valueOf(s)) + "";
    }

}
