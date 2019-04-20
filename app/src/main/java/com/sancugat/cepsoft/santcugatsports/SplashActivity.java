package com.sancugat.cepsoft.santcugatsports;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler= new Handler();

        Thread t = new Thread(new Runnable() {
            public void run() {

                try {
                    sleep(1000);
                    startActivity(new Intent(SplashActivity.this,Login.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

    }
}
