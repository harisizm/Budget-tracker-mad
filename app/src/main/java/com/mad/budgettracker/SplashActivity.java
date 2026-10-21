package com.mad.budgettracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * First screen the user sees. Shows the logo for 2 seconds, then opens Home.
 */
public class SplashActivity extends AppCompatActivity {

    // How long the splash stays on screen, in milliseconds.
    private static final long SPLASH_DELAY_MS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // A Handler runs code on the main thread after a delay without blocking the UI.
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // An Intent is a request to open another screen.
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                // finish() closes the splash so pressing Back from Home exits the app
                // instead of showing the splash again.
                finish();
            }
        }, SPLASH_DELAY_MS);
    }
}
