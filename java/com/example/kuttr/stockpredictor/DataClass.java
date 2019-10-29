package com.example.kuttr.stockpredictor;

import java.io.Serializable;
import java.util.ArrayList;

public class DataClass implements Serializable {
    ArrayList<StockDetails> arrmain=new ArrayList<StockDetails>();
    ArrayList<StockDetails> arrfav=new ArrayList<StockDetails>();

    public DataClass()
    {
        arrmain.add(new StockDetails("AAPL","AppleInc",R.drawable.aapl));
        arrmain.add(new StockDetails("AXP","AmericanExpress",R.drawable.axp));
        arrmain.add(new StockDetails("BA","Boeing",R.drawable.ba));
        arrmain.add(new StockDetails("CAT","Caterpillar",R.drawable.cat));
        arrmain.add(new StockDetails("CSCO","CiscoSystems",R.drawable.csco));
        arrmain.add(new StockDetails("CVX","Chevron",R.drawable.cvx));
        arrmain.add(new StockDetails("DIS","WaltDisney",R.drawable.dis));
        arrmain.add(new StockDetails("DCDP","DowDuPont",R.drawable.dcdp));
        arrmain.add(new StockDetails("GS","GoldmanSachs",R.drawable.gs));
        arrmain.add(new StockDetails("HD","TheHomeDepot",R.drawable.hd));
        arrmain.add(new StockDetails("IBM","IBM",R.drawable.ibm));
        arrmain.add(new StockDetails("INTC","Intel Corp",R.drawable.intc));
        arrmain.add(new StockDetails("JNJ","JohnsonandJohnsons",R.drawable.jnj));
        arrmain.add(new StockDetails("JPM","J.P.Morgan",R.drawable.jpm));
        arrmain.add(new StockDetails("KO","CocoCola",R.drawable.ko));
        arrmain.add(new StockDetails("MCD","McDonalds",R.drawable.mcd));
        arrmain.add(new StockDetails("MMM","MinnesotaMiningandManufacturingCompany",R.drawable.mmm));
        arrmain.add(new StockDetails("MRK","MerckSolutions",R.drawable.mrk));
        arrmain.add(new StockDetails("MSFT","MicrosoftInc",R.drawable.msft));
        arrmain.add(new StockDetails("NKE","Nike",R.drawable.nke));
        arrmain.add(new StockDetails("PFE","Pfizer",R.drawable.pfe));
        arrmain.add(new StockDetails("PG","P&G",R.drawable.pg));
        arrmain.add(new StockDetails("TRV","Travellers",R.drawable.trv));
        arrmain.add(new StockDetails("UNH","UnitedHealthCare",R.drawable.unh));
        arrmain.add(new StockDetails("UTX","UnitedTechnologies",R.drawable.utx));
        arrmain.add(new StockDetails("V","VISA",R.drawable.v));
        arrmain.add(new StockDetails("VZ","Verizon",R.drawable.vz));
        arrmain.add(new StockDetails("WBA","WallgreensBootAlliance",R.drawable.wba));
        arrmain.add(new StockDetails("WMT","Walmart",R.drawable.wmt));
        arrmain.add(new StockDetails("XOM","ExonMobil",R.drawable.xom));
        arrmain.add(new StockDetails("","",0));


    }

    public ArrayList<StockDetails> getArrList()
    {

        return arrmain;
    }
    public ArrayList<StockDetails> getFavList()
    {
        return arrfav;
    }
    public void setArrfav(String stockName,String companyName,int imageResId)
    {
        //if(!arrfav.contains(new StockDetails(stockName,companyName,imageResId)))
        arrfav.add(new StockDetails(stockName,companyName,imageResId));
    }
    /*public void removeArrfav(String stockName,String companyName,int imageResId)
    {
        if(arrfav.contains(new StockDetails(stockName,companyName,imageResId)))
            arrfav.remove(new StockDetails(stockName,companyName,imageResId));
    }*/
}