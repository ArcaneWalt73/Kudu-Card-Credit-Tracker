package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.CustomVolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HistoryInfo extends AppCompatActivity {

    private String rate_url = "https://lamp.ms.wits.ac.za/~s1965919/Rating.php";

    private int id;
    private String url;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Float rating;
    private String date;

    private TextView tv_price;
    private TextView tv_desc;
    private TextView tv_date;

    private NetworkImageView imageView;
    private ImageLoader imageLoader;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_info);

        tv_price = findViewById(R.id.hist_info_price);
        tv_desc = findViewById(R.id.hist_info_desc);
        tv_date = findViewById(R.id.hist_info_date);
        imageView = findViewById(R.id.hist_info_item_img);
        ratingBar = findViewById(R.id.hist_info_rating_bar);

        Intent parent = getIntent();

        if (parent.hasExtra("id")) {
            id = parent.getIntExtra("id", -1);
        }

        if (parent.hasExtra("url")) {
            url = parent.getStringExtra("url");
        }

        if (parent.hasExtra("name")) {
            name = parent.getStringExtra("name");
        }

        if (parent.hasExtra("price")) {
            price = parent.getDoubleExtra("price", -1.0);
            tv_price.setText(price.toString());
        }

        if (parent.hasExtra("category")) {
            category = parent.getStringExtra("category");
        }

        if (parent.hasExtra("description")) {
            description = parent.getStringExtra("description");
            tv_desc.setText(description);
        }

        if (parent.hasExtra("rating")) {
           // rating = parent.getFloatExtra("rating", -1.0f);
            ratingBar.setRating(-1.0f);
        }

        if (parent.hasExtra("date")) {
             date = parent.getStringExtra("date");
             tv_date.setText(date);
        }

        loadImage();
    }

    private void loadImage(){
        if(url.equals("")){
            Toast.makeText(this,"Please enter a URL",Toast.LENGTH_LONG).show();
            return;
        }

        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.default_store_icon, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }


    public void doRate(View v)
    {
        float rating = ratingBar.getRating();
        System.err.println("Check 10 == "+rating);

        appendRating(this, this.id,rating);

        System.err.println("Check 12 == done ");

    }

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
