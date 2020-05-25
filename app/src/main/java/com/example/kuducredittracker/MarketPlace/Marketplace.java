package com.example.kuducredittracker.MarketPlace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kuducredittracker.Profile;
import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.StoreAdapter;
import com.example.kuducredittracker.Resources.UserAccount;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Marketplace extends AppCompatActivity {

    private String getItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getItems.php"; //address to get data  from database#NB UPDATE THIS ADRESS TO THE PHP DAVIS CREATES
    private String getStores_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getStoreNames.php";
    private String getItemRating_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getItemRating.php";
    private ArrayList<Store> Stores = new ArrayList<Store>();
    private StoreAdapter storeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);
        listView = (ListView)findViewById(R.id.m_listview);

        //the names of the stores in the database
        String store0 = "Clothing", store1 = "Stationery", store2 = "Music", store3 = "Setting";

        ArrayList<ArrayList<Item>> itemsForStores = new ArrayList<ArrayList<Item>>(); //get the store names from db
        ArrayList<Item> allItems = getItemsFromDB(); //get all items from db

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
        listView.setAdapter(storeAdapter);

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
                    JSONArray ja = new JSONArray(output);
                    for(int i = 0; i < ja.length(); i++)
                    {

                        JSONObject jo = (JSONObject)ja.get(i);
                        String name = jo.getString("ItemName");
                        float price = (float)jo.getDouble("ItemPrice");
                        String category = jo.getString("ItemCategory");
                        double rating = Double.parseDouble(getItemRating(jo.getString("itemId"), category));

                        items.add(new Item(name, price, category, rating));
                    }

                }catch(Exception e)
                {
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
