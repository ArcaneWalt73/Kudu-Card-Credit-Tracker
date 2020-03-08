package com.example.kuducredittracker.MarketPlace;

import java.util.ArrayList;

public class Store {
	 private String name;
	 private ArrayList<Item> Items = new ArrayList<>();
	 //private String description;

	 public Store(String storeName ){
	        this.name = storeName;
	    }

	 public String getName(){
	        return this.name;
	    }

	 public void addItem(String Item_name, int Item_price){
	     Item StoreItem = new Item(Item_name , Item_price);
	     Items.add(StoreItem);
	 }

	 public Item getItem(int Index_of_item){
	        return Items.get(Index_of_item);
	    }

	 public int get_Number_of_Items(){
	        return Items.size();
	    }
}
