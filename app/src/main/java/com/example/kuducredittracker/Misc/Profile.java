package com.example.kuducredittracker.Misc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kuducredittracker.MarketPlace.Item;
import com.example.kuducredittracker.MarketPlace.Marketplace;
import com.example.kuducredittracker.Misc.Adding_Item;
import com.example.kuducredittracker.Misc.DisplayItems;
import com.example.kuducredittracker.PurchaseHistory;
import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.AsyncHttpPost;
import com.example.kuducredittracker.Resources.CustomVolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Profile extends AppCompatActivity {
    public static String sessionUsername;
    public static Double sessionCredit;
    private String PPserverAddress = "https://lamp.ms.wits.ac.za/~s1965919/DisplayPP.php";
    private CountDownTimer refreshCreditText;

    //for profile pic
/////////////////////////////////////////////////////
    private NetworkImageView imageView;
    private ImageLoader imageLoader;
    private String url;
///////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_profile);



        imageView = findViewById(R.id.profile_pic);


        Intent parent = getIntent();
        if (parent.hasExtra("username")) {
            String username = parent.getStringExtra("username");
            sessionUsername = username;
            TextView user = (TextView) findViewById(R.id.profile_username);
            user.setText(username);
        }

        if (parent.hasExtra("credit")) {
            String credit = parent.getStringExtra("credit");
            sessionCredit = Double.parseDouble(credit);

        }

        if (parent.hasExtra("PP_URL")) {
            String profile = parent.getStringExtra("PP_URL");
            url = profile;

            if(url.equals("emptyURL"))
            {
                url = "";
            }

        }

        /////

        // Retrive other information from Server
        loadImage();

        /////
        refreshCreditText = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCreditText();
            }

            @Override
            public void onFinish() {
                start();
            }
        };

        refreshCreditText.start();
    }

    public void doNext(View v)
    {
        Intent main = new Intent(this, Marketplace.class);
        startActivity(main);
    }

    public void add_item(View v)
    {
        Intent go = new Intent(this, Adding_Item.class);
        startActivity(go);
    }

    public void doHistory(View v) {
        Intent hist = new Intent(this, PurchaseHistory.class);
        startActivity(hist);
    }

    public void LogOut(View v)
    {
        finish();
    }

    private void updateCreditText() {
        TextView user = (TextView) findViewById(R.id.profile_credit);
        user.setText(sessionCredit + "");
    }

//////////////////////////////profile page///////////////////////////////////////////
    private void loadImage(){
        if(url.equals("")){
            Toast.makeText(this,"Can't Load Profile Pic at the moment",Toast.LENGTH_LONG).show();
            return;
        }
        //Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.pp, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }


////////////////////////////////Geting Url from DB///////////////////////////////////////

   /* public String getPPUrlFromDB() //gets all Items from database
    {
        final String[] url = new String[1];
        ContentValues params = new ContentValues();
        params.put("studentNumber", sessionUsername);
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(PPserverAddress, params) {
            @Override

            // Gets items from database
            protected void onPostExecute(String output) {
                try {

                    System.err.println("Check0");
                    JSONObject urlFromServer = new JSONObject(output);
                    url[0] =  urlFromServer.getString("PP_URL");
                    System.err.println("Check1");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Unable to access JSON Object from getPP.php");
                }
            }
        };
        asyncHttpPost.execute();
        return url[0];
    }*/
//////////////////////////////////////All to do with PP retieval Done above/////////////////////////////////////////////

    public void changeProfile(View v)
    {
        Intent change = new Intent(this, ChangePP.class);
        startActivity(change);

        /*Intent change = new Intent(this, ChangePP.class);
        change.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        change.putExtra("username", sessionUsername); // carry along the username
        this.startActivity(change); // Proceed to the user's profile*/
    }


}
