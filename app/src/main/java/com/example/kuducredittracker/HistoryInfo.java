package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.Resources.CustomVolleyRequest;

public class HistoryInfo extends AppCompatActivity {

    private String buy_url = "https://lamp.ms.wits.ac.za/~s1965919/buy.php";

    private int id;
    private String url;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Double rating;
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
        System.err.println("Check 1");
        if (parent.hasExtra("id")) {
            id = parent.getIntExtra("id", -1);
        }

        System.err.println("Check 2");
        if (parent.hasExtra("url")) {
            url = parent.getStringExtra("url");
        }

        System.err.println("Check 3");
        if (parent.hasExtra("name")) {
            name = parent.getStringExtra("name");
        }

        System.err.println("Check 4");
        if (parent.hasExtra("price")) {
            price = parent.getDoubleExtra("price", -1.0);
            tv_price.setText(price.toString());
        }

        System.err.println("Check 5");
        if (parent.hasExtra("category")) {
            category = parent.getStringExtra("category");
        }

        System.err.println("Check 6");
        if (parent.hasExtra("description")) {
            description = parent.getStringExtra("description");
            tv_desc.setText(description);
        }

        System.err.println("Check 7");
        if (parent.hasExtra("rating")) {
            rating = parent.getDoubleExtra("rating", -1.0);
            ratingBar.setRating(Float.parseFloat(rating.toString()));
        }

        System.err.println("Check 8");
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
}
