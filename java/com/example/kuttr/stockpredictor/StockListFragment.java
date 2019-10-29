package com.example.kuttr.stockpredictor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class StockListFragment extends Fragment {
    private List<StockDetails> std;


    public StockListFragment() {
        // Required empty public constructor
    }

    ListFragmentItemClickListener ifaceItemClickListener;

    /** An interface for defining the callback method */
    public interface ListFragmentItemClickListener {
        /** This method will be invoked when an item in the ListFragment is clicked */
        void onListFragmentItemClick(int position);
    }

    /** A callback function, executed when this fragment is attached to an activity */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            /** This statement ensures that the hosting activity implements ListFragmentItemClickListener */
            ifaceItemClickListener = (ListFragmentItemClickListener) activity;
        }catch(Exception e){
            Toast.makeText(activity.getBaseContext(), "Exception",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stock_list, container, false);
        std=new ArrayList<>();
        DataClass dataClass=new DataClass();
        std=dataClass.getArrList();
        ListView lst=(ListView)view.findViewById(R.id.listview);

        StockListAdapter stockListAdapter=new StockListAdapter(getContext(),std);
        lst.setAdapter(stockListAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ifaceItemClickListener.onListFragmentItemClick(position);
            }
        });
        return view;
    }






}