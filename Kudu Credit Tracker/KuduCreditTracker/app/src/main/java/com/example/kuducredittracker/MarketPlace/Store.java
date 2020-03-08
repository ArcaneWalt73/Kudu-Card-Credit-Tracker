package com.example.kuducredittracker.MarketPlace;

import java.util.ArrayList;

public class Store {
	 private int image;
	 private String name;
	 private String description;

	 public Store(String storeName, String description, int image){
	 	this.description = description;
	 	this.image = image;
	 	this.name = storeName;
	    }

	 public String getName(){
	        return this.name;
	    }

	public int getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*	 public void addItem(String Item_name, int Item_price){
	     Item StoreItem = new Item(Item_name , Item_price);
	     Items.add(StoreItem);
	 }

	 public Item getItem(int Index_of_item){
	        return Items.get(Index_of_item);
	    }

	 public int get_Number_of_Items(){
	        return Items.size();
	    }*/
}
