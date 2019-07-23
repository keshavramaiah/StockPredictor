package com.example.admin.stockpredictor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PredictorActivity extends AppCompatActivity  {
    private TextView StockTView,CompanyTView, predictView;
    final static String fileName = "fav.txt";
    final static String path = "MLMarksman" ;
    String data="";
    ImageView imageView,imageView1;
    double prevValue=0;
    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor);
        final Intent intent = getIntent();
        final String st = intent.getStringExtra("STOCK");
        final String cp = intent.getStringExtra("COMPANY");
        final int img = intent.getIntExtra("IMAGE", 0);
        imageView = (ImageView) findViewById(R.id.logoholder);
        imageView.setImageResource(img);
        StockTView = (TextView) findViewById(R.id.StockName);
        CompanyTView = (TextView) findViewById(R.id.CompanyName);
        StockTView.setText(st);
        CompanyTView.setText(cp);

        spinner = (Spinner) findViewById(R.id.spinner1);
        List<String> spin = new ArrayList<String>();
        for (int i = 0; i < 375; i++) {
            spin.add("Predicted Value after " + i + " days");
        }
        predictView = (TextView) findViewById(R.id.predict);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spin);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        imageView1 = (ImageView) findViewById(R.id.upordown);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position <= 100) {

                    String s1 = "120";
                    predictView.setText(s1);
                    prevValue = 120;
                    Log.d("VALUE", Double.toString(prevValue));
                    if (prevValue > 5) {
                        imageView1.setImageResource(R.drawable.reddown);
                        predictView.setTextColor(getResources().getColor(R.color.Red));
                    } else {
                        imageView1.setImageResource(R.drawable.greenup);
                        predictView.setTextColor(getResources().getColor(R.color.Green));
                    }


                } else {
                    String s1 = "120";
                    predictView.setText(s1);
                    prevValue = 120;
                    Log.d("VALUE", Double.toString(prevValue));
                    if (prevValue > 150) {
                        imageView1.setImageResource(R.drawable.reddown);
                        predictView.setTextColor(getResources().getColor(R.color.Red));
                    } else {
                        imageView1.setImageResource(R.drawable.greenup);
                        predictView.setTextColor(getResources().getColor(R.color.Green));
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageView1.setImageResource(R.drawable.greenup);
                predictView.setTextColor(getResources().getColor(R.color.Green));
                int n = 120;
                String s1 = Integer.toString(n);
                predictView.setText(s1);
            }
        });

    }

}