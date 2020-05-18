package com.example.kuducredittracker.MarketPlace;

import com.example.kuducredittracker.Resources.MarketPlaceHelperFunctions;

public class Item {

	    private String name , description, shopName; //shopName = shop selling item
	    private double price, rating; //price and rating(0 - 5)
		MarketPlaceHelperFunctions helperFunctions;

		//This constructor is for when item doesn't have a rating yet
	    public Item(String ItemName, float ItemPrice, String description, String shopName)
		{
	        this.name = ItemName;
	        this.price = ItemPrice;
	        this.description = description;
	        this.shopName = shopName;

	        rating = 2.5; //intialise it to 2.5 if non is supplied

			helperFunctions = new MarketPlaceHelperFunctions();
	    }

		//This constructor is for when item already has a rating
		public Item(String ItemName, float ItemPrice, String description, String shopName, double rating)
		{
			this.name = ItemName;
			this.price = ItemPrice;
			this.description = description;
			this.shopName = shopName;
			this.rating = rating;
		}

	    public void addRating(double newRating) //adds a new rating to the existing one
		{
			rating = (rating + newRating)/2; //average the new rating
			helperFunctions.appendRating(rating); //update the database.
		}


		public double getRating()
		{
			return rating;
		}

		public String getName()
		{
			return name;
		}

	}

