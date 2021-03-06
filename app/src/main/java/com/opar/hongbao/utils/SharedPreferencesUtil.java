package com.opar.hongbao.utils;

import android.content.Context;
import android.content.SharedPreferences;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class SharedPreferencesUtil {
    private static final String FILENAME = "config";

    public SharedPreferencesUtil() {
    }

    public static void saveString(Context con, String key, String value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getString(key, (String)null);
    }

    public static void saveInt(Context con, String key, int value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getInt(key, 0);
    }

    public static void saveDouble(Context con, String key, float value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        sp.edit().putFloat(key, value).commit();
    }

    public static float getDouble(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getFloat(key, 0);
    }

    public static int getIntWithDefault(Context con, String key, int value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getInt(key, value);
    }

    public static void saveIntWithDefault(Context con, String key, int value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    public static void saveBoolean(Context con, String key, boolean value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context con, String key) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getBoolean(key, false);
    }

    public static boolean getBooleanWithDefault(Context con, String key, boolean value) {
        SharedPreferences sp = con.getSharedPreferences(FILENAME, 0);
        return sp.getBoolean(key, value);
    }
}
