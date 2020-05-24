package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kuducredittracker.MarketPlace.Marketplace;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent parent = getIntent();
        if (parent.hasExtra("username")) {
            String username = parent.getStringExtra("username");
            TextView user = (TextView) findViewById(R.id.profile_username);
            user.setText(username);
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
