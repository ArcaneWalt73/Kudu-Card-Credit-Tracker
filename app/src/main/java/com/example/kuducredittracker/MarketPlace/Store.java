package com.example.kuducredittracker.MarketPlace;

import java.util.ArrayList;

public class Store {
	 private int image;
	 private String name;
	 private String description;
	 private ArrayList<Item> items;

	 //last parameter is an arrayList of items that belong to that store
	 public Store(String storeName, String description, int image, ArrayList<Item> items){
	 	this.description = description;
	 	this.image = image;
	 	this.name = storeName;
	 	this.items = items;
	 }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage()
    {
        return image;
    }

    public String getDescription()
    {
        return  description;
    }

    public String getName()
    {
        return name;
    }
}
