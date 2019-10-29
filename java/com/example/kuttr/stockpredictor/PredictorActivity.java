package com.example.kuttr.stockpredictor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class PredictorActivity extends AppCompatActivity {
    GraphView g;
    Spinner s;
     TextView Stock,Company,predict;
    ImageView img,img1,img2;
    ArrayList<Integer> l=new ArrayList<Integer>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor2);

        img2=findViewById(R.id.fav);
        g=findViewById(R.id.graph);
        Intent intent=getIntent();
        Bundle args=intent.getBundleExtra("BUNDLE");
        l=(ArrayList<Integer>)args.getSerializable("Arraylist");
        s=findViewById(R.id.spinner1);
        final String st=intent.getStringExtra("STOCK");
        String st1=intent.getStringExtra("COMPANY");
        int imm=intent.getIntExtra("IMAGE",0);
        img=(ImageView)findViewById(R.id.logoholder);
        img.setImageResource(imm);
        Stock=findViewById(R.id.StockName);
        Company=findViewById(R.id.CompanyName);
        Stock.setText(st);
        Company.setText(st1);
        List<String> spin =new ArrayList<String>();
        for(int i=0;i<l.size();i++)
        {
            spin.add("Predicted values after "+i+" days");
        }
        predict=findViewById(R.id.predict);
        ArrayAdapter<String> data=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spin);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(data);
        img1=findViewById(R.id.upordown);
        DataPoint dp[] =new DataPoint[l.size()];
        for(int j=0;j<l.size();j++)
        {
            dp[j]=new DataPoint(j,l.get(j));
        }
        LineGraphSeries<DataPoint>series=new LineGraphSeries<>(dp);
        g.getViewport().setXAxisBoundsManual(true);
        g.getViewport().setMaxX((double) l.size());
        g.addSeries(series);
        final SharedPreferences sp1=getSharedPreferences("fav",MODE_PRIVATE);
        final SharedPreferences.Editor edit1=sp1.edit();
        if(sp1.getString(st,"").equals("1"))
        {
            img2.setImageResource(R.drawable.filled_fav);
        }

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(sp1.getString(st,"").equals(""))
               {
                   img2.setImageResource(R.drawable.filled_fav);
                   edit1.putString(st,"1").commit();
               }
               else
               {
                   img2.setImageResource(R.drawable.favourite);
                   edit1.putString(st,"");
                   edit1.commit();
               }
            }
        });




      s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long ll) {
              if(i==0)
              {
                  //g.removeAllSeries();

                  predict.setText(l.get(i).toString());
                  img1.setImageResource(R.drawable.greenup);
                  predict.setTextColor(getResources().getColor(R.color.green));


              }
              else
              {
                  Toast.makeText(getApplicationContext(), Integer.toString(i),Toast.LENGTH_LONG).show();
                  if(l.get(i)>l.get(i-1))
                  {
                      predict.setText(l.get(i).toString());
                      img1.setImageResource(R.drawable.greenup);
                      predict.setTextColor(getResources().getColor(R.color.green));
                  }
                  else
                  {
                      predict.setText(l.get(i).toString());
                      img1.setImageResource(R.drawable.reddown);
                      predict.setTextColor(getResources().getColor(R.color.red));
                  }
                  g.removeAllSeries();
                  DataPoint dp[] =new DataPoint[i+1];
                  for(int j=0;j<=i;j++)
                  {
                      dp[j]=new DataPoint(j,l.get(j));
                  }
                  LineGraphSeries<DataPoint>series=new LineGraphSeries<>(dp);
                  g.getViewport().setXAxisBoundsManual(true);
                  g.getViewport().setMaxX((double) i);
                  g.addSeries(series);
              }


          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });







    }
}
