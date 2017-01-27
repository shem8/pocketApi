package com.shem.pocketapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PocketLoginResultActivity extends AppCompatActivity {

    public static final String TAG = PocketLoginResultActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == Intent.ACTION_VIEW) {
            getToken();
        } else {
            finish();
        }
    }

    private void getToken() {
        PocketService service = PocketServiceImpl.getInstance();
        Call<PocketService.TokenResponse> token = service.getToken(PocketKeys.getCostumerKey(this), PocketPrefs.getCode(this));
        token.enqueue(new Callback<PocketService.TokenResponse>() {
            @Override
            public void onResponse(Call<PocketService.TokenResponse> call, Response<PocketService.TokenResponse> response) {
                PocketPrefs.setAccessToken(PocketLoginResultActivity.this, response.body().access_token);
                PocketPrefs.setUserName(PocketLoginResultActivity.this, response.body().username);
                finish();
            }

            @Override
            public void onFailure(Call<PocketService.TokenResponse> call, Throwable t) {
                log("Failure: " + t.getMessage());
                finish();
            }
        });
    }

    private void log(String str) {
        Log.d(TAG, str);
    }
}
