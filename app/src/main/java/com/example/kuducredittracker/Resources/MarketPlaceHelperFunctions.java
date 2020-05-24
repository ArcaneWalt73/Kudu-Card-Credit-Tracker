package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

public class MarketPlaceHelperFunctions {

    private Context context;
    private String addItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/Item.php";//made up this address for now(Siya)
    private String removeItem_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/Item.php";//made up this address for now(Siya)
    private String updateCredit_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/Users.php";//made up this address for now(Siya)
    public Integer credit;
    public String studentNumber;


    //Add item to database

    public Boolean addItem(String[] new_itemDetails)//also need to parse in image
    {
        ContentValues params = new ContentValues();

        String[] labels = {"itemName","itemPrice", "itemDescription", "itemCategory"};

        for(int i = 0; i < new_itemDetails.length; ++i)
        {
            params.put(labels[i], new_itemDetails[i]);
        }

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(addItem_serverAddress, params) {
            @Override

            protected void onPostExecute(String output) {
                System.err.println("Here's the output to AddItem: "+output);

                if (output.contains("true"))
                {
                    Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
                    ((Activity)(context)).finish();
                    this.item_added = true;
                }
                else {
                    Toast.makeText(context, "Unable add Item, Please check item registration and Try again", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHttpPost.execute();
        return asyncHttpPost.getItem_added();
    }


    //Append your rating

    public void appendRating(Integer rating)//rating system from 0-5(o worst 5 best)
    {
        ContentValues params = new ContentValues();

        String label = "ranting";

        params.put(label, rating);


        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(addItem_serverAddress, params) {
            @Override


            protected void onPostExecute(String output) {
                System.err.println("Here's the output to AddRating: "+output);

                if (output.contains("true"))
                {
                    Toast.makeText(context, "Rating Added", Toast.LENGTH_SHORT).show();
                    ((Activity)(context)).finish();
                }
                else {
                    Toast.makeText(context, "Unable add rating, Please check your connection and try again", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHttpPost.execute();
    }

    //buy item function

    public void BuyItem(Integer IemPrice, Integer ItemInventory, String Item_Identification)
    {
        if (IemPrice > credit)
        {
            Toast.makeText(context, "Not enough funds", Toast.LENGTH_SHORT).show();
        }
        else if(ItemInventory == 0)
        {
            Toast.makeText(context, "Out of stock", Toast.LENGTH_SHORT).show();
        }
        else
        {
            credit = credit - IemPrice;

            ContentValues params = new ContentValues();

            String[] labels = {"studentNumber","creditAmount"};

            params.put(labels[0], studentNumber);
            params.put(labels[1], credit);

            @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(updateCredit_serverAddress, params) {
                @Override


                protected void onPostExecute(String output) {
                    System.err.println("Here's the output to UpdateCredit: "+output);

                    if (output.contains("true"))
                    {
                        Toast.makeText(context, "Credit updated", Toast.LENGTH_SHORT).show();
                        ((Activity)(context)).finish();
                    }
                    else {
                        Toast.makeText(context, "Please check connection", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            asyncHttpPost.execute();

            //Now remove item from server not sure if i can do the following but....


            ContentValues params2 = new ContentValues();

            String label2 = "Item";

            params.put(label2, Item_Identification);


            @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost2 = new AsyncHttpPost(removeItem_serverAddress, params) {
                @Override


                protected void onPostExecute(String output) {
                    System.err.println("Here's the output to removingItem: "+output);

                    if (output.contains("true"))
                    {
                        Toast.makeText(context, "Item removed successfully", Toast.LENGTH_SHORT).show();
                        ((Activity)(context)).finish();
                    }
                    else {
                        Toast.makeText(context, "Please check connection", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            asyncHttpPost.execute();

        }

    }


    //selectionSort to be modified for use of item ranking(Best raked item on top of marketplace).
    //idea is for it to take in ranks of items and sort
    public void selectionSort(Integer[] rankArray)
    {
        for(int i = 0; i < rankArray.length; i++)
        {
            int min = rankArray[i];
            int minId = i;
            for (int j = i+1; j < rankArray.length; j++)
            {
                if(rankArray[j] < min)
                {
                    min = rankArray[j];
                    minId = j;
                }
            }
            //swapping
            int temp = rankArray[i];
            rankArray[i] = min;
            rankArray[minId] = temp;
        }
    }
}
