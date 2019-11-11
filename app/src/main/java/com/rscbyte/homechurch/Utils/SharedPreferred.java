package com.rscbyte.homechurch.Utils;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferred {

    private static final String SESSION_NAME = "_account_rsc";
    private Context context;

    public SharedPreferred(Context context) {
        this.context = context;
    }

    //put to preferred local storage
    public boolean putString(String name, String value) {
        context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).edit().putString(name, value).apply();
        return true;
    }

    public boolean putBoolean(String name, boolean value) {
        context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).edit().putBoolean(name, value).apply();
        return true;
    }

    public boolean putInt(String name, int value) {
        context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).edit().putInt(name, value).apply();
        return true;
    }

    //get from preferred local storage
    public String getString(String name) {
        return context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).getString(name, null);
    }

    public boolean getBoolean(String name) {
        return context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).getBoolean(name, false);
    }

    public int getInt(String name) {
        return context.getSharedPreferences(SESSION_NAME, MODE_PRIVATE).getInt(name, 0);
    }
}
