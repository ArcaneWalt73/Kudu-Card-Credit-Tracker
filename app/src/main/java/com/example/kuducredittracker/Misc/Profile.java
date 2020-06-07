package com.example.kuducredittracker.Misc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kuducredittracker.MarketPlace.Marketplace;
import com.example.kuducredittracker.Misc.Adding_Item;
import com.example.kuducredittracker.Misc.DisplayItems;
import com.example.kuducredittracker.PurchaseHistory;
import com.example.kuducredittracker.R;

public class Profile extends AppCompatActivity {
    public static String sessionUsername;
    public static Double sessionCredit;
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
            sessionCredit = Double.parseDouble(credit);
            TextView user = (TextView) findViewById(R.id.profile_credit);
            user.setText(credit + "");
        }

        /////

        // Retrive other information from Server

        /////

    }

    public void TestImageLoader(View v)
    {
        Intent main = new Intent(this, DisplayItems.class);
        startActivity(main);
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
}
