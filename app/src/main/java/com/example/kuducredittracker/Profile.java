package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kuducredittracker.MarketPlace.Marketplace;

public class Profile extends AppCompatActivity {
    public  static String sessionUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent parent = getIntent();
        if (parent.hasExtra("username")) {
            String username = parent.getStringExtra("username");
            sessionUsername = username;
            TextView user = (TextView) findViewById(R.id.profile_username);
            user.setText(username);
        }

        if (parent.hasExtra("credit")) {
            String credit = parent.getStringExtra("credit");
            TextView user = (TextView) findViewById(R.id.profile_credit);
            user.setText(credit + "");
        }

        /////

        // Retrive other information from Server

        /////

    }

    public void doNext(View v)
    {
        Intent main = new Intent(this, Marketplace.class);
        startActivity(main);
    }
}
