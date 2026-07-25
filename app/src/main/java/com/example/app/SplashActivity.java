package com.example.app;

import android.app.Activity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new android.content.Intent(
                    SplashActivity.this,
                    MainActivity.class
                ));
                finish();
            }
        }, 2000);
    }
}
