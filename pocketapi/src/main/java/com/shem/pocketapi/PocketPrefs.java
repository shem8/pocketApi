package com.shem.pocketapi;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by smagnezi on 19/01/2017.
 */

public class PocketPrefs {

    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String CODE_KEY = "code";
    private static final String USER_NAME_KEY = "usernane";

    private static SharedPreferences prefs = null;

    private static SharedPreferences getPrefs(Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(PocketPrefs.class.getSimpleName(), Context.MODE_PRIVATE);
        }
        return prefs;
    }

    public static String getAccessToken(Context c) {
        return getPrefs(c).getString(ACCESS_TOKEN_KEY, null);
    }

    public static void setAccessToken(Context c, String token) {
        SharedPreferences.Editor editor = getPrefs(c).edit();
        editor.putString(ACCESS_TOKEN_KEY, token);
        editor.apply();
    }

    public static String getCode(Context c) {
        return getPrefs(c).getString(CODE_KEY, null);
    }

    public static void setCode(Context c, String code) {
        SharedPreferences.Editor editor = getPrefs(c).edit();
        editor.putString(CODE_KEY, code);
        editor.apply();
    }

    public static void setUserName(Context c, String username) {
        SharedPreferences.Editor editor = getPrefs(c).edit();
        editor.putString(USER_NAME_KEY, username);
        editor.apply();
    }

    public static String getUserName(Context c) {
        return getPrefs(c).getString(USER_NAME_KEY, null);
    }
}
