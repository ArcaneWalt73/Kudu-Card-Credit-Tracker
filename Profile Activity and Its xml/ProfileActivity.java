package com.example.kuducredittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kuducard.MarketPlace.Marketplace;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);


    }

    public void shopOnClick(View view)
    {
      startActivity(new Intent(this, Marketplace.class));
    }

    public void historyOnClick(View view)
    {
        Toast.makeText(this, "You have not made any transactions yet!", Toast.LENGTH_LONG);
    }
}
