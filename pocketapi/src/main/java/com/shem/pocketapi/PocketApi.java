package com.shem.pocketapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shem.pocketapi.errors.NoKeyError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by smagnezi on 19/01/2017.
 */

public class PocketApi {

    public static final int LOGIN_REQUEST_CODE = 3000;

    private static final String TAG = PocketApi.class.getSimpleName();

    public static void login(Activity c) {
        if (!isLoggedIn(c)) {
            makeLoginCall(c);
        }
    }

    public static void logout(Activity c) {
        PocketPrefs.setAccessToken(c, null);
        PocketPrefs.setCode(c, null);
        PocketPrefs.setUserName(c, null);
    }

    public static boolean isLoggedIn(Context c) {
        String accessToken = PocketPrefs.getAccessToken(c);
        return (accessToken != null && !accessToken.isEmpty());
    }

    public static String getUsername(Context c) {
        return PocketPrefs.getUserName(c);
    }

    public static void getArticles(Context c, Callback<PocketService.ArticlesMap> callback) {
        PocketService service = PocketServiceImpl.getInstance();
        String customerKey = PocketKeys.getCostumerKey(c);
        Call<PocketService.ArticlesMap> listCall = service.get(customerKey,
                PocketPrefs.getAccessToken(c),
                "unread",
                "10",
                "newest",
                "complete");
        listCall.enqueue(callback);
    }

    private static void makeLoginCall(final Activity c) {
        final PocketService service = PocketServiceImpl.getInstance();
        Call<PocketService.LoginResponse> login = service.login(PocketKeys.getCostumerKey(c), PocketKeys.getRedirectUri(c));
        login.enqueue(new Callback<PocketService.LoginResponse>() {
            @Override
            public void onResponse(Call<PocketService.LoginResponse> call, Response<PocketService.LoginResponse> response) {
                String code = response.body().code;
                PocketPrefs.setCode(c, code);
                Uri uri = Uri.parse(getUri(code));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                c.startActivityForResult(browserIntent, LOGIN_REQUEST_CODE);
            }

            @NonNull
            private String getUri(String code) {
                return "https://getpocket.com/auth/authorize?request_token=" + code + "&redirect_uri=" + PocketKeys.getRedirectUri(c);
            }

            @Override
            public void onFailure(Call<PocketService.LoginResponse> call, Throwable t) {
                Log.d(TAG, "Error while login: " + t.getMessage());
            }
        });
    }
}
