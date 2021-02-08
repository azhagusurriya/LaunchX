package com.example.launchx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import adapter.BrewerlistAdapter;
import model.Brewery;
import viewmodel.BreweryViewModel;

public class MainActivity extends AppCompatActivity implements OnBrewerylistClickListener{

    private BreweryViewModel breweryViewModel;
    private final String TAG = this.getClass().getCanonicalName();

    private RecyclerView rvBrevery;
    private LinearLayoutManager viewManager;
    private BrewerlistAdapter brewerlistAdapter;
    private ArrayList<Brewery> breweryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvBrevery = findViewById(R.id.rvBreverylists);
        this.breweryArrayList = new ArrayList<>();
        this.brewerlistAdapter = new BrewerlistAdapter(this.getApplicationContext(), breweryArrayList,this);
        this.viewManager = new LinearLayoutManager(this.getApplicationContext());


        this.rvBrevery.setAdapter(this.brewerlistAdapter);
        this.rvBrevery.setLayoutManager(this.viewManager);
        this.rvBrevery.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));


        breweryViewModel = BreweryViewModel.getInstance();
        breweryViewModel.fetchBreweryData();



        this.breweryViewModel.breweryLiveList.observe(this, new Observer<List<Brewery>>() {
            @Override
            public void onChanged(List<Brewery> breweries) {

                if (breweries != null){
                    Log.e(TAG, "Data Changed : " + breweries.toString());
                    breweryArrayList.addAll(breweries);
                    brewerlistAdapter.notifyDataSetChanged();
                }

                for (int i=0; i < breweries.size(); i++){
                    Log.e(TAG, "Brewery " + (i+1) + " Name : " + breweries.get(i).getName() +
                            " Country : " + breweries.get(i).getCountry() +
                            " Postal Code : " + breweries.get(i).getPostalCode() +
                            " Website : " + breweries.get(i).getWebsite());


//                    TODO display brewery data in RecyclerView

                }
            }
        });


    }

    @Override
    public void onBrewerylistClickListener(Brewery brewery) {
        Log.d(TAG, "onBrewerylistClickListener: Item is clickeddd");
    }
}