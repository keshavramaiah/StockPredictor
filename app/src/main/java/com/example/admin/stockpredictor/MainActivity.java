package com.example.admin.stockpredictor;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StockListFragment.ListFragmentItemClickListener {


    final static String fileName = "fav.txt";
    final static String path = "MLMarksman";
    File myExternalFile;
    String data = "";
    Fragment fragment=new StockListFragment();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(fragment);


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
        DataClass dc=new DataClass();
        ArrayList<StockDetails> tempstd=dc.getArrList();
        Intent intent=new Intent(this,PredictorActivity.class);
        intent.putExtra("STOCK",tempstd.get(position).getStock());
        intent.putExtra("COMPANY",tempstd.get(position).getCompany());
        intent.putExtra("IMAGE",tempstd.get(position).getImageresId());
        startActivity(intent);
    }





    public void onListFragmentItemClick2(int position) {
        //Toast.makeText(getApplicationContext(),position+" ",Toast.LENGTH_SHORT).show();

        ArrayList<StockDetails> tempstd=getListfromFile();
        Intent intent=new Intent(this,PredictorActivity.class);
        intent.putExtra("STOCK",tempstd.get(position).getStock());
        intent.putExtra("COMPANY",tempstd.get(position).getCompany());
        intent.putExtra("IMAGE",tempstd.get(position).getImageresId());

        startActivity(intent);

    }
    public ArrayList<StockDetails> getListfromFile() {
        ArrayList<StockDetails>temp=new ArrayList<StockDetails>();
        try {
            myExternalFile = new File(getExternalFilesDir(path), fileName);
        } catch (Exception e) {

        }
        int flag=0;
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {

                String arr[]=strLine.split(" ");
                flag=0;
                Log.d("CREATION",Integer.toString(temp.size()));
                if(flag==0)
                {
                    temp.add(new StockDetails(arr[0],arr[1],Integer.parseInt(arr[2])));
                }


            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }
}