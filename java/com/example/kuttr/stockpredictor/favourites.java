package com.example.kuttr.stockpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
