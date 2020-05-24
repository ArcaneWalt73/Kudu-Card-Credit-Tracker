package com.example.kuducredittracker.MarketPlace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.StoreAdapter;

import java.util.ArrayList;

public class Marketplace extends AppCompatActivity {

    private ArrayList<Store> Stores = new ArrayList<>();
    private StoreAdapter storeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);
        listView = (ListView)findViewById(R.id.m_listview);

        Stores.add(new Store("Clothing", "Cloths and other wearable stuff", R.drawable.clothing));
        Stores.add(new Store("Ipod Text", "This is a text Editor", R.drawable.ipod));
        Stores.add(new Store("Music", "Stores music from Artists", R.drawable.music));
        Stores.add(new Store("Setting", "Configure Data", R.drawable.settings));

        storeAdapter = new StoreAdapter(this, Stores);
        listView.setAdapter(storeAdapter);

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
