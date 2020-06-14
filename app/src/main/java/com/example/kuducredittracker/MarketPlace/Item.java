package com.example.kuducredittracker.MarketPlace;

import com.example.kuducredittracker.Resources.MarketPlaceHelperFunctions;

public class Item {
	private int id;
	private String name, itemCategory;
	private Double price;
	private Float rating; //price and rating(0 - 5)
	String url, Description;
	MarketPlaceHelperFunctions helperFunctions;

	//This constructor is for when item already has a rating
	public Item(int id, String ItemName, double ItemPrice,String itemCategory, String Description, String url)
	{
		this.id = id;
		this.name = ItemName;
		this.price = ItemPrice;
		this.rating = 0.0f;
		this.itemCategory = itemCategory;
		this.url = url;
		this.Description = Description;
	}

	//This constructor is for when item already has a rating
	public Item(int id, String ItemName, double ItemPrice,String itemCategory, String Description, String url, Float rating)
	{
		this.id = id;
		this.name = ItemName;
		this.price = ItemPrice;
		this.rating = rating;
		this.itemCategory = itemCategory;
		this.url = url;
		this.Description = Description;
	}

	public double getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return Description;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRating()
	{
		return rating;
	}

	public String getName()
	{
		return name;
	}
	public String getItemCategory(){return itemCategory;}
}

