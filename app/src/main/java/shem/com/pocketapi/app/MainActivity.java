package shem.com.pocketapi.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shem.pocketapi.PocketApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleLoginStatus(PocketApi.isLoggedIn(this));
    }

    public void login(View v) {
        PocketApi.login(this);
    }

    public void logout(View v) {
        PocketApi.logout(this);
        handleLoginStatus(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PocketApi.LOGIN_REQUEST_CODE) {
            handleLoginStatus(PocketApi.isLoggedIn(this));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleLoginStatus(boolean loggedIn) {
        findViewById(R.id.login).setEnabled(!loggedIn);
        findViewById(R.id.logout).setEnabled(loggedIn);
    }
}
