package com.meteorsoftech.dailyattendance;

import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;

import com.meteorsoftech.dailyattendance.utils.Util;

public class Splash_ScreenActivity extends AppCompatActivity {
    public int Splash_TimeOut=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Util.isConnectedToInternet(Splash_ScreenActivity.this)) {
                    Intent i = new Intent(Splash_ScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    Splash_ScreenActivity.this.finish();
                }
                else
                {
                    Intent intent = new Intent(Splash_ScreenActivity.this,ActivityConnectionFailed.class);
                    startActivity(intent);
                    finish();
                }
            }
        },Splash_TimeOut);
    }
}
