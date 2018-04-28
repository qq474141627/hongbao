package com.opar.mobile.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    /**
     * 判断内容是否完整
     *
     * @param str
     * @return
     */
    public static String isEmptyStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 获取带星的银行卡号
     *
     * @param bankcard
     * @return
     */
    public static String getBankStart(String bankcard) {
        if (TextUtils.isEmpty(bankcard)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int a = 1; a < bankcard.length() - 3; a++) {
            sb.append("*");
            if (a % 4 == 0) {
                sb.append(" ");
            }
        }
        sb.append(" " + bankcard.substring(bankcard.length() - 4));
        return sb.toString();
    }

    /**
     * 获取带星的用户姓名
     *
     * @param username
     * @return
     */
    public static String getUserNameForStart(String username) {
        if(TextUtils.isEmpty(username))
            return "";
        return username.substring(0, 1) + "*" + (username.length() > 2 ? username.substring(2) : "");
    }

    /**
     * 获取带星的身份证号
     *
     * @param identity
     * @return
     */
    public static String getUserIdentity(String identity) {
        if(TextUtils.isEmpty(identity))
            return "";
        return identity.length() == 15 ?
                identity.substring(0, 6) + "******" + identity.substring(12)
                :
                identity.substring(0, 6) + "*********" + identity.substring(15);
    }

    public static boolean isHaveChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                return bitmapToBase64(bitmapBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * byte[]转base64
     *
     * @param bitmapBytes
     * @return
     */
    public static String bitmapToBase64(byte[] bitmapBytes) {

        String result = null;
        try {
            result = "data:image/jpeg;base64," + Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
