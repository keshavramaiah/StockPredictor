package com.example.kuttr.stockpredictor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StockListAdapter extends BaseAdapter {

    private Context mContext;
    private List<StockDetails> stockDetails;

    /**
     * Constructor method
     * @param stockDetails The list of images to display
     */
    public StockListAdapter(Context context, List<StockDetails> stockDetails) {
        mContext = context;
        this.stockDetails = stockDetails;
    }
    @Override
    public int getCount() {
        return this.stockDetails.size();

    }

    @Override
    public Object getItem(int position) {
        return stockDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null)
        {
            convertView=LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        StockDetails stockDetail=(StockDetails) getItem(position);
        /*TextView stockname=(TextView)convertView.findViewById(R.id.StockName);
        stockname.setText(stockDetail.getStock());
        TextView companyname=(TextView)convertView.findViewById(R.id.CompanyName);
        companyname.setText(stockDetail.getCompany());
        ImageView imageView=(ImageView)convertView.findViewById(R.id.logo);
        imageView.setImageResource(stockDetail.getImageresId());*/
        holder.stockname.setText(stockDetail.getStock());
        holder.companyname.setText(stockDetail.getCompany());
        holder.companylogo.setImageResource(stockDetail.getImageresId());
        holder.companylogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.companylogo.setAdjustViewBounds(true);
        return convertView;
    }
    private class ViewHolder {
        private TextView stockname;
        private TextView companyname;
        private ImageView companylogo;
        public ViewHolder(View convertview)
        {
            stockname = (TextView) convertview.findViewById(R.id.StockName);
            companyname = (TextView) convertview.findViewById(R.id.CompanyName);
            companylogo = (ImageView) convertview.findViewById(R.id.logo);
        }
    }
}
