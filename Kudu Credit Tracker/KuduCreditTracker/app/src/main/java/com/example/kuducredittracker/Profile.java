package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kuducredittracker.MarketPlace.Marketplace;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void doNext(View v)
    {
        Intent main = new Intent(this, Marketplace.class);
        startActivity(main);
    }
}
