package com.example.kuducredittracker.MarketPlace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kuducredittracker.R;

import java.util.ArrayList;

public class Marketplace extends AppCompatActivity {

    private ArrayList<Store> Stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);
    }


    /*
    package com.example.kuducredittracker.MarketPlace;

import java.util.ArrayList;

public class MarketPlace {
	private ArrayList<Store> Stores = new ArrayList<>();

	    public void addStore(String Store_name){
	        Store MarketPlace_Store = new Store(Store_name);
	        Stores.add(MarketPlace_Store);
	    }

	    public Store getStore(int Index_of_store){
	        return Stores.get(Index_of_store);
	    }

	    public int get_Number_of_Stores(){
	        return Stores.size();
	    }
}

     */

}
