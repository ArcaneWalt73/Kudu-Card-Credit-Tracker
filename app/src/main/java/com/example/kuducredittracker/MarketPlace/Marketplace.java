package com.example.kuducredittracker.MarketPlace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kuducredittracker.Misc.Profile;
import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.ItemAdapter;
import com.example.kuducredittracker.Resources.StoreAdapter;
import com.example.kuducredittracker.item_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Marketplace extends AppCompatActivity {

    private String getItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getItem.php"; //address to get data  from database#NB UPDATE THIS ADRESS TO THE PHP DAVIS CREATES
    ArrayList<Item> allItems;
    private ItemAdapter itemAdapter;
    GridView gridview;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (getSupportActionBar() != null)
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        else {
            Toast.makeText(this, "Unable to load Toolbar, Exiting...", Toast.LENGTH_LONG).show();
            finish();
        }
        gridview = (GridView) findViewById(R.id.marketplace_gridview);
        allItems = getItemsFromDB(); //get all items from db
    }

    public void setGridviewItems(ArrayList<Item> items) {
        itemAdapter = new ItemAdapter(this, items);
        gridview.setAdapter(itemAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = allItems.get(position);
                Intent main = new Intent(getApplicationContext(), item_info.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                main.putExtra("id", item.getId());
                main.putExtra("url", item.getUrl());
                main.putExtra("name", item.getName());
                main.putExtra("price", item.getPrice());
                main.putExtra("category", item.getItemCategory());
                main.putExtra("description", item.getDescription());
                main.putExtra("rating", item.getRating());
                main.putExtra("reviews", item.getReviews());
                getApplicationContext().startActivity(main);
            }
        });
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
                    JSONArray jsonArray = new JSONArray(output);
                    /////////////////////////////// sorting jsonAray //////////////////////////////
                    JSONArray sortedJsonArray = new JSONArray();
                    List<JSONObject> list = new ArrayList<JSONObject>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        list.add(jsonArray.getJSONObject(i));
                    }

                    Collections.sort(list, new Comparator<JSONObject>() {
                        private static final String KEY_NAME = "RATING";
                        @Override
                        public int compare(JSONObject a, JSONObject b) {
                            String str1 = new String();
                            String str2 = new String();
                            System.err.println("Check 1");

                            try {
                                str1 = String.valueOf(a.get(KEY_NAME));
                                str2 = String.valueOf(b.get(KEY_NAME));
                                System.err.println("Check 2 = "+str1+str2);
                            } catch(JSONException e) {
                                e.printStackTrace();
                            }
                            System.err.println("Check 3");
                            return str1.compareTo(str2);
                        }
                    });
                    for(int i = jsonArray.length()-1; i > -1; i--) {
                        sortedJsonArray.put(list.get(i));
                    }
                    //////////////////////// sorted JsonArray ///////////////////////////
                    for (int j = 0; j < sortedJsonArray.length(); j++) {
                        JSONObject jo = (JSONObject) sortedJsonArray.get(j);
                        String name = jo.getString("NAME");
                        Double price = (Double) jo.getDouble("PRICE");
                        int id = (Integer)jo.getInt("MARKET_ID");
                        String description = jo.getString("DESCRIPTION");
                        String url = jo.getString("IMAGE_URL");
                        String category = jo.getString("CATEGORY");
                        float rating = Float.parseFloat(jo.getString("RATING"));
                        String reviews = jo.getString("REVIEWS");
                        items.add(new Item(id, name, price, category, description, url, rating, reviews));
                    }
                    setGridviewItems(items);
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
}
