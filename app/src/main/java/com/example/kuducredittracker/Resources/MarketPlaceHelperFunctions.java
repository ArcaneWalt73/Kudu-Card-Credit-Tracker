package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.UUID;

public class MarketPlaceHelperFunctions {
    //Add item to database
    public static Boolean addItem(Context context, String path, String name, String price, String Category, String Description)
    {
        //Uploading code
        String upload_Item = "https://lamp.ms.wits.ac.za/~s1965919/AddItem.php";
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(context, uploadId, upload_Item)
                    .addFileToUpload(path, "image") //Adding file
                    .setMethod("POST")
                    .addParameter("name", name) //Adding text parameter to the request
                    .addParameter("price", price)
                    .addParameter("Category", Category)
                    .addParameter("Description", Description)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload
            return true;

        } catch (Exception exc) {
            Toast.makeText(context, exc.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //Append your rating

    public static void appendRating(final Context context, Integer item_id, float rating)//rating system from 0-5(o worst 5 best)
    {
        ContentValues params = new ContentValues();
        String rate_url = "https://lamp.ms.wits.ac.za/~s1965919/Rating.php";

        String labelRating = "numberStars";
        String labelId = "itemId";

        params.put(labelRating, rating);
        params.put(labelId, item_id);

        final Float[] resultRating = {-1f};

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(rate_url, params) {
            @Override
            protected void onPostExecute(String output) {
                System.err.println("Here's the output to AddRating: "+output);
                try {
                    JSONObject jo = new JSONObject(output);
                    Toast.makeText(context, "Rating Added" + jo, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(context, "Check connection and rate again", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHttpPost.execute();
    }
}
