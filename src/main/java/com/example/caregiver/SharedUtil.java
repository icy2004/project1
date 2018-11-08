package com.example.caregiver;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedUtil {
    private SharedUtil(){}

    public static void put(Context context, String name, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, float value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void put(Context context, String name, String key, Set<String> value) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }

    public static String getString(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public static int getInt(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getInt(key, 0);
    }

    public static int getInt(Context context, String name, String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public static float getFloat(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getFloat(key, 0.0f);
    }

    public static long getLong(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getLong(key, 0);
    }

    public static Set<String> getStringSet(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getStringSet(key, null);
    }

    public static boolean getBoolean(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(name, MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }
}
