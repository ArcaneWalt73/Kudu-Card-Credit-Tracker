package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kuducredittracker.MarketPlace.Item;
import com.example.kuducredittracker.Misc.Profile;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.HistoryAdapter;
import com.example.kuducredittracker.Resources.HistoryItem;
import com.example.kuducredittracker.Resources.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PurchaseHistory extends AppCompatActivity {
    private String getItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/getHistory.php"; //address to get data  from database#NB UPDATE THIS ADRESS TO THE PHP DAVIS CREATES
    ListView listView;
    ArrayList<HistoryItem> allItems;
    HistoryAdapter historyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        listView = findViewById(R.id.history_listview);
        allItems = getItemsFromDB(); //get all items from db

    }

    public void setListviewItems(ArrayList<HistoryItem> historyItems) {
        historyAdapter = new HistoryAdapter(this, historyItems);
        listView.setAdapter(historyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryItem item = allItems.get(position);
                Intent main = new Intent(getApplicationContext(), HistoryInfo.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                main.putExtra("position", position);
                main.putExtra("id", item.getId());
                main.putExtra("url", item.getUrl());
                main.putExtra("name", item.getName());
                main.putExtra("price", item.getPrice());
                main.putExtra("category", item.getItemCategory());
                main.putExtra("description", item.getDescription());
                main.putExtra("rating", item.getRating());
                main.putExtra("date", item.getDate());
                getApplicationContext().startActivity(main);
            }
        });
    }

    public HistoryItem getHistoryItem(int market_id, String date, JSONObject jo) throws JSONException {
        if (jo.getInt("MARKET_ID") == market_id) {
            String name = jo.getString("NAME");
            Double price = (Double) jo.getDouble("PRICE");
            String description = jo.getString("DESCRIPTION");
            String url = jo.getString("IMAGE_URL");
            String category = jo.getString("CATEGORY");
            return (new HistoryItem(market_id, name, price, category, description, url, date));
        }
        return null;
    }

    public void printArray(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.err.println(items.get(i).getName()+" "+items.get(i).getItemCategory());
        }
    }

    public ArrayList<HistoryItem> getItemsFromDB() //gets all Items from database
    {
        final ArrayList<HistoryItem> items = new ArrayList<HistoryItem>();
        ContentValues params = new ContentValues();
        params.put("studentNo", Profile.sessionUsername);
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(getItem_serverAddress,params) {
            @Override

            // Gets items from database
            protected void onPostExecute(String output) {
                try
                {
                    //String [] jsonArrayArray = output.split(",");

                    JSONArray fullArray = new JSONArray(output);
                    JSONArray firstArray = new JSONArray(fullArray.getString(0));
                    JSONArray secondArray = new JSONArray(fullArray.getString(1));

                    for (int j = 0; j < firstArray.length(); j++) {
                        JSONObject jo1 = firstArray.getJSONObject(j);
                        int id = (Integer)jo1.getInt("MARKET_ID");
                        String date = jo1.getString("PURCHASE_DATE");
                        HistoryItem historyItem = null;
                        for (int i = 0; i < secondArray.length(); i++) {
                            historyItem = HistoryItem.getHistoryItem(id, date, secondArray.getJSONObject(i));
                            if (historyItem != null)
                                break;
                        }
                        items.add(historyItem);
                    }
                    setListviewItems(items);
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