package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.Misc.Profile;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.CustomVolleyRequest;

public class item_info extends AppCompatActivity {


    private String buy_url = "https://lamp.ms.wits.ac.za/~s1965919/buy.php";

    private int id;
    private String url;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Double rating;

    private TextView tv_price;
    private TextView tv_desc;

    private NetworkImageView imageView;
    private ImageLoader imageLoader;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        tv_price = findViewById(R.id.info_price);
        tv_desc = findViewById(R.id.info_desc);
        imageView = findViewById(R.id.info_item_img);
        ratingBar = findViewById(R.id.info_rating_bar);

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

    public void doBuy(View v) {

        if (Profile.sessionCredit < price) {
            Toast.makeText(this, "Insuffient Funds", Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues params = new ContentValues();
            params.put("studentID", Profile.sessionUsername);
            params.put("itemID", id);
            params.put("productAmount", price);
            @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(buy_url, params) {
                @Override
                protected void onPostExecute(String output) {
                    System.err.println(output);
                    Double d = Double.parseDouble(output);
                    if (Profile.sessionCredit - price == d)
                    {
                        Profile.sessionCredit = d;
                        Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            asyncHttpPost.execute();
        }
    }
}
