package com.example.kuducredittracker.MarketPlace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kuducredittracker.Profile;
import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.ItemAdapter;
import com.example.kuducredittracker.Resources.StoreAdapter;
import com.example.kuducredittracker.Resources.UserAccount;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Marketplace extends AppCompatActivity {

    private Button refresh; // Refresh Button
    private String getItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getItem.php"; //address to get data  from database#NB UPDATE THIS ADRESS TO THE PHP DAVIS CREATES
    private String getStores_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getStoreNames.php";
    private String getItemRating_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getItemRating.php";
    private ArrayList<Store> Stores = new ArrayList<Store>();
    private StoreAdapter storeAdapter;
    private ItemAdapter itemAdapter;
    ListView listView;
    GridView gridview;


    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (getSupportActionBar() != null)
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        else
            System.err.println("ToolBar was null");

        //actionBar = (ActionBar) findViewById(R.id.custom_toolbar);

        //listView = (ListView)findViewById(R.id.m_listview);
        gridview = (GridView) findViewById(R.id.marketplace_gridview);

        //refresh = getSupportActionBar().getCustomView().findViewById(R.id.custom_refresh_btn);
        /*refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refresh the gridview
            }
        });
        refresh.setVisibility(View.INVISIBLE);*/

        //the names of the stores in the database
        //String store0 = "Clothing", store1 = "Stationery", store2 = "Music", store3 = "Setting";

        //ArrayList<ArrayList<Item>> itemsForStores = new ArrayList<ArrayList<Item>>(); //get the store names from db
        ArrayList<Item> allItems = getItemsFromDB(); //get all items from db
/*
        for(int j = 0; j < allItems.size(); ++j)
        {
            //if the item's store name matches current store name then add it to current store's items
            if(allItems.get(j).getItemCategory().equals(store0))
            {
                itemsForStores.get(0).add(allItems.get(j));
            }else if(allItems.get(j).getItemCategory().equals(store1))
            {
                itemsForStores.get(1).add(allItems.get(j));
            }else if(allItems.get(j).getItemCategory().equals(store2))
            {
                itemsForStores.get(2).add(allItems.get(j));
            }else
            {
                itemsForStores.get(3).add(allItems.get(j));
            }
        }

        Stores.add(new Store(store0, "Clothes and other wearable stuff", R.drawable.clothing, itemsForStores.get(0)));
        Stores.add(new Store(store1, "This is a Music store", R.drawable.ipod, itemsForStores.get(1)));
        Stores.add(new Store(store2, "Stores music from Artists", R.drawable.music, itemsForStores.get(2)));
        Stores.add(new Store(store3, "Configure Data", R.drawable.settings, itemsForStores.get(3)));


        storeAdapter = new StoreAdapter(this, Stores);
        //listView.setAdapter(storeAdapter);
        //listView.setVisibility(View.INVISIBLE);
        gridview.setAdapter(storeAdapter);*/


    }

    public void setGridviewItems(ArrayList<Item> items) {
        itemAdapter = new ItemAdapter(this, items);
        gridview.setAdapter(itemAdapter);
    }

    public void printArray(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.err.println(items.get(i).getName()+" "+items.get(i).getItemCategory());
        }
    }

    public ArrayList<Item> getItemsFromDB() //gets all Items from database
    {
        final ArrayList<Item> items = new ArrayList<Item>();
        ContentValues params = new ContentValues();
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(getItem_serverAddress,params) {
            @Override

            // Gets items from database
            protected void onPostExecute(String output) {
                try
                {
                    System.err.println("hsahskahkshakjhskahkshakhsadjlajdkjagdkagdkgakdhgakgdkjahdlahldha");
                    System.err.println(output);
                    //String [] jsonArrayArray = output.split(",");

                    JSONArray fullArray = new JSONArray(output);
                    for (int ai = 0; ai < fullArray.length(); ai++) {
                        String array = fullArray.getString(ai);
                        System.out.println("This is the special array "+ array);
                        JSONArray ja = new JSONArray(array);
                        for(int i = 0; i < ja.length(); i++)
                        {
                            JSONObject jo = (JSONObject)ja.get(i);
                            String name = jo.getString("NAME");
                            float price = (float)jo.getDouble("PRICE");
                            String category = (String)jo.getString("MARKET_ID");
                            if (category.equals("1"))
                                category = "FOOD";
                            if (category.equals("2"))
                                category = "Clothing";
                            if (category.equals("3"))
                                category = "EVENTS";
                            if (category.equals("4"))
                                category = "Stationery";
                            //double rating = Double.parseDouble(getItemRating(jo.getString("MARKET_ID"), category));
                            double rating = 0.0;
                            items.add(new Item(name, price, category, rating));
                        }
                    }
                    setGridviewItems(items);
                    printArray(items);
                }catch(Exception e)
                {
                    e.printStackTrace();
                    System.err.println("Unable to access JSON Array from getItems.php");
                }
            }
        };
        asyncHttpPost.execute();

        return items;
    }

//    public ArrayList<String[]> getStoresFromDB() //gets all Items from database
//    {
//        final ArrayList<String[]> stores = new ArrayList<String[]>();
//        ContentValues params = new ContentValues();
//        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(getStores_serverAddress,params) {
//            @Override
//
//            // Gets items from database
//            protected void onPostExecute(String output) {
//                try
//                {
//                    JSONArray ja = new JSONArray(output);
//                    for(int i = 0; i < ja.length(); i++)
//                    {
//
//                        JSONObject jo = (JSONObject)ja.get(i);
//                        String name = jo.getString("Store_Name");
//                        String description = jo.getString("Store_Description");
//                        int image = jo.getInt("Store_Image");
//
//                        String[] thisStore = {name, description, image+""};
//                        stores.add(thisStore);
//
//                    }
//
//                }catch(Exception e)
//                {
//                    System.err.println("Unable to access JSON Array from getStores.php");
//                }
//            }
//        };
//        asyncHttpPost.execute();
//
//        return stores;
//    }

    public String getItemRating(String itemId, String category) //gets all Items from database
    {
        final String[] rating = new String[1];
        ContentValues params = new ContentValues();
        params.put("itemId", itemId);
        params.put("itemCategory", category);
        String studentNr = Profile.sessionUsername;
        params.put("studentNumber", studentNr);

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(getItemRating_serverAddress,params) {
            @Override

            // Gets items from database
            protected void onPostExecute(String output) {
                try
                {
                    JSONArray ja = new JSONArray(output);
                    for(int i = 0; i < ja.length(); i++)
                    {

                        JSONObject jo = (JSONObject)ja.get(i);
                        rating[0] = jo.getString("RATING");

                    }

                }catch(Exception e)
                {
                    System.err.println("Unable to access JSON Array from getStores.php");
                }
            }
        };
        asyncHttpPost.execute();

        return rating[0];
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
