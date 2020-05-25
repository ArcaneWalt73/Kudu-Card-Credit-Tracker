package com.example.kuducredittracker.MarketPlace;

import com.example.kuducredittracker.Resources.MarketPlaceHelperFunctions;

public class Item {

	    private String name, itemCategory;
	    private double price, rating; //price and rating(0 - 5)
		MarketPlaceHelperFunctions helperFunctions;
		
		//This constructor is for when item already has a rating
		public Item(String ItemName, double ItemPrice,String itemCategory)
		{
			this.name = ItemName;
			this.price = ItemPrice;
			this.rating = 2.5;
			this.itemCategory = itemCategory;
		}

		//This constructor is for when item already has a rating
		public Item(String ItemName, double ItemPrice,String itemCategory, double rating)
		{
			this.name = ItemName;
			this.price = ItemPrice;
			this.rating = rating;
			this.itemCategory = itemCategory;
		}

	    public void addRating(double newRating) //adds a new rating to the existing one
		{
			helperFunctions.appendRating(newRating); //update the database.
		}


		public double getRating()
		{
			return rating;
		}

		public String getName()
		{
			return name;
		}
		public String getItemCategory(){return itemCategory;}
	}

