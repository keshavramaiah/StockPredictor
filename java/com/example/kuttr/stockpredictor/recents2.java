package com.example.kuttr.stockpredictor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class recents2 extends AppCompatActivity {
TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv=findViewById(R.id.textView);

        final SharedPreferences sp=getSharedPreferences("mycred",MODE_PRIVATE);
        final SharedPreferences.Editor edit1=sp.edit();

        DataClass dc;
        dc=new DataClass();
        ArrayList<StockDetails> tempstd=dc.getArrList();
        String ss="";
        for(int i=0;i<tempstd.size();i++)
        {
            if(sp.getString(tempstd.get(i).getStock(),"").equals(""))
            {}
            else
            {
                ss+=sp.getString(tempstd.get(i).getCompany(),"")+"\n";

            }
        }
        tv.setText(ss);

    }
}
