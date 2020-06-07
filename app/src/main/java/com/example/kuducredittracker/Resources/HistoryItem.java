package com.example.kuducredittracker.Resources;

import com.example.kuducredittracker.MarketPlace.Item;

public class HistoryItem extends Item {
    private String date;
    public HistoryItem(int id, String ItemName, double ItemPrice, String itemCategory, String Description, String url, String date) {
        super(id, ItemName, ItemPrice, itemCategory, Description, url);
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
