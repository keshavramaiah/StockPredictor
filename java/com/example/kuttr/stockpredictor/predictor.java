package com.example.kuttr.stockpredictor;


import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class predictor extends AppCompatActivity implements StockListFragment.ListFragmentItemClickListener {



    String data = "";
    Fragment fragment=new StockListFragment();
    int pos;
    Connection con;
    Statement st;
    private String url = "jdbc:mysql://database-2.cffmb2ctf5cx.us-east-1.rds.amazonaws.com:3306/newdb";
    private String usr = "root";
    private String pwd = "password";
    ArrayList<Integer> list=new ArrayList<Integer>();
    ArrayList<StockDetails> tempstd;
    DataClass dc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor);
        ComponentName comp=new ComponentName(this,Job.class);
        JobInfo info=new JobInfo.Builder(123,comp)
                .setPersisted(true)
                .setPeriodic(12*60*60*1000).build();
        JobScheduler scheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);

        loadFragment(fragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    public String savedata(String s1)
    {
        SharedPreferences sp=getSharedPreferences("mycred",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sp.edit();
        if(sp.getString(s1,"").equals(""))
        {
            return "0";
        }
        else
        {
            return sp.getString(s1,"");
        }

    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft=
                    getSupportFragmentManager().beginTransaction();
            //Toast.makeText(getApplicationContext(),fragment.toString(),Toast.LENGTH_SHORT).show();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ft.replace(R.id.activity_container, fragment).commit();
            return true;
        }
        return false;
    }

    public void onListFragmentItemClick(int position) {
        //Toast.makeText(getApplicationContext(),position+" ",Toast.LENGTH_SHORT).show();
        dc=new DataClass();
         tempstd=dc.getArrList();
        pos=position;

        String s0=savedata(tempstd.get(pos).getStock());
        if(s0.equals("0"))
        {new myTask().execute();}
        else
        {
            try {
                JSONObject json=new JSONObject(s0);
                JSONArray j= json.optJSONArray("ua");
                list.clear();
                for(int i=0;i<j.length();i++)
                {
                    list.add(j.getInt(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PredictorActivity p=new PredictorActivity();
            Bundle args=new Bundle();
            args.putSerializable("Arraylist",(Serializable)list);
            Intent intent=new Intent(getApplicationContext(),PredictorActivity.class);
            intent.putExtra("STOCK",tempstd.get(pos).getStock());
            intent.putExtra("COMPANY",tempstd.get(pos).getCompany());
            intent.putExtra("IMAGE",tempstd.get(pos).getImageresId());
            intent.putExtra("BUNDLE",args);
            startActivity(intent);
        }
    }

    public class myTask extends AsyncTask<Void,Void,Void>
    {
        String s="";

        ProgressDialog prgDialog;
        @Override
        protected void onPreExecute() {

            prgDialog = new ProgressDialog(
                    predictor.this);
            prgDialog.setMessage
                    ("Gathering Details");
            prgDialog.setIndeterminate(false);
            prgDialog.setProgressStyle
                    (ProgressDialog.STYLE_SPINNER);
            prgDialog.setCancelable(false);
            prgDialog.show();

        }


        @Override
        protected Void doInBackground(Void... voids) {
            try{
                list.clear();
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, usr, pwd);
                s="1";
                st = con.createStatement();
                ResultSet rs;
                s="2";
                if(tempstd.get(pos).getStock().equals("AAPL"))
                {
                    rs=st.executeQuery("select * from apple;");
                    s="3";
                    while(rs.next())
                    {

                        list.add(Math.round(Float.valueOf(rs.getString(2))));

                    }
                    s="4";

                }
                else if(tempstd.get(pos).getStock().equals("CSCO"))
                {
                    rs=st.executeQuery("select * from cisco;");
                    while(rs.next())
                    {
                        list.add(Math.round(Float.valueOf(rs.getString(2))));
                    }
                }
                else if(tempstd.get(pos).getStock().equals("IBM"))
                {
                    rs=st.executeQuery("select * from ibm");
                    while(rs.next())
                    {
                        list.add(Math.round(Float.valueOf(rs.getString(2))));
                    }
                }
                else if(tempstd.get(pos).getStock().equals("NKE"))
                {
                    rs=st.executeQuery("select * from nike;");
                    while(rs.next())
                    {
                        list.add(Math.round(Float.valueOf(rs.getString(2))));
                    }
                }
                else if(tempstd.get(pos).getStock().equals("WMT"))
                {
                    rs=st.executeQuery("select * from walmart;");
                    while(rs.next())
                    {
                        list.add(Math.round(Float.valueOf(rs.getString(2))));
                    }
                }
                else
                {
                    rs=st.executeQuery("select * from walmart;");
                    while(rs.next())
                    {
                        list.add(Math.round(Float.valueOf(rs.getString(2))));
                    }
                }





        }
            catch(Exception E)
            {
                E.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            prgDialog.dismiss();
            JSONObject json=new JSONObject();
            try {

                json.put("ua",new JSONArray(list));
                String arrayList=json.toString();

                SharedPreferences sp=getSharedPreferences("mycred",MODE_PRIVATE);
                SharedPreferences.Editor edit=sp.edit();

                edit.putString(tempstd.get(pos).getStock(),arrayList);
                edit.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            PredictorActivity p=new PredictorActivity();
            Bundle args=new Bundle();
            args.putSerializable("Arraylist",(Serializable)list);
            Intent intent=new Intent(getApplicationContext(),PredictorActivity.class);
            intent.putExtra("STOCK",tempstd.get(pos).getStock());
            intent.putExtra("COMPANY",tempstd.get(pos).getCompany());
            intent.putExtra("IMAGE",tempstd.get(pos).getImageresId());
            intent.putExtra("BUNDLE",args);
            startActivity(intent);
        }
    }







}