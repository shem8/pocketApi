package com.shem.pocketapi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.shem.pocketapi.errors.NoKeyError;

/**
 * Created by shem on 27/01/2017.
 */

public class PocketKeys {

    private static final String CONSUMER_KEY = "shem.pocketapi.CustomerKey";
    private static final String REDIRECT_URI_KEY = "shem.pocketapi.RedirectUri";


    public static String getCostumerKey(Context c) {
        return getKey(c, CONSUMER_KEY);
    }

    public static String getRedirectUri(Context c) {
        return getKey(c, REDIRECT_URI_KEY);
    }

    private static String getKey(Context c, String key) {
        ApplicationInfo ai;
        try {
            ai = c.getPackageManager().getApplicationInfo(c.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            throw new NoKeyError(key);
        }
        String customerKey = ai.metaData.getString(key);
        if (customerKey == null) {
            throw new NoKeyError(key);
        }
        return customerKey;
    }
}
