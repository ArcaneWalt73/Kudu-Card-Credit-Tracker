package com.example.kuducredittracker.Resources;

import com.example.kuducredittracker.MarketPlace.Item;

import org.json.JSONException;
import org.json.JSONObject;

public class HistoryItem extends Item {
    private String date;
    public HistoryItem(int id, String ItemName, double ItemPrice, String itemCategory, String Description, String url, String date) {
        super(id, ItemName, ItemPrice, itemCategory, Description, url);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public static HistoryItem getHistoryItem(int market_id, String date, JSONObject jo) throws JSONException {
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

}
