package com.example.kuttr.stockpredictor;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME = 3000; //This is 4 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(splashscreen.this, MainActivity.class);
                startActivity(mySuperIntent);

                finish();
            }
        }, SPLASH_TIME);
    }
}
