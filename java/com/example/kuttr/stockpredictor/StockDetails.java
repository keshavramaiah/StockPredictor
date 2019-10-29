package com.example.kuttr.stockpredictor;

public class StockDetails {
    private String stock;
    private String company;
    private int imageresId;
    private boolean fav;

    public StockDetails(String stock,String company,int imageresId)
    {
        this.stock=stock;
        this.company=company;
        this.imageresId=imageresId;
    }
    public String getStock()
    {
        return this.stock;
    }
    public String getCompany()
    {
        return  this.company;
    }

    public int getImageresId() {
        return imageresId;
    }
    public void setFAV(boolean inp)
    {
        fav=inp;
    }
    public boolean isFav()
    {
        return fav;
    }
}
