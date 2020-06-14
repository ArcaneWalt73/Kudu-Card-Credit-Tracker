package com.example.kuducredittracker.Misc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.kuducredittracker.MarketPlace.Marketplace;
import com.example.kuducredittracker.Misc.Adding_Item;
import com.example.kuducredittracker.Misc.DisplayItems;
import com.example.kuducredittracker.PurchaseHistory;
import com.example.kuducredittracker.R;

public class Profile extends AppCompatActivity {
    public static String sessionUsername;
    public static Double sessionCredit;
    private CountDownTimer refreshCreditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_profile);
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

        /////

        // Retrive other information from Server

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

    public void LogOut(View v)
    {
        finish();
    }


    // Timer to update curent kudu amount
    private void startTimer() {
        refreshCreditText = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCreditText();
            }

            @Override
            public void onFinish() {
                start();
            }
        }.start();
    }

    private void updateCreditText() {
        TextView user = (TextView) findViewById(R.id.profile_credit);
        user.setText(sessionCredit + "");
    }
}
