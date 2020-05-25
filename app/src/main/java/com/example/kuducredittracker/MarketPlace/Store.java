package com.example.kuducredittracker.MarketPlace;

import com.example.kuducredittracker.Items;
import com.example.kuducredittracker.Resources.MarketPlaceHelperFunctions;

import java.util.ArrayList;

public class Store {
	 private int image;
	 private String name;
	 private String description;
	 private ArrayList<Item> items;
	 private MarketPlaceHelperFunctions helperFunctions; //the helper functions that connect to the database

	 //last parameter is an arrayList of items that belong to that store
	 public Store(String storeName, String description, int image, ArrayList<Item> items){
	 	this.description = description;
	 	this.image = image;
	 	this.name = storeName;
	 	this.items = items;

	 	helperFunctions = new MarketPlaceHelperFunctions();
	    }

	//add items to store instance
    public void addItem(String name, double price, String category)
    {
        String[] details = {name, price + "", category};
        items.add(new Item(name,price,category)); //add to array
        helperFunctions.addItem(details); //add to the database

    }

    public Item getShopBestItem() //returns the highest rated item in the shop
    {
        Item best = items.get(0);
        for(int i = 0; i < items.size(); ++i)
        {
            if(items.get(i).getRating() > best.getRating())
            {
                best = items.get(i);
            }
        }

        return best;
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
