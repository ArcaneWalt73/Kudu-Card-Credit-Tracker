package com.example.kuducredittracker;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kuducredittracker.MarketPlace.Marketplace;

=======
import android.os.Bundle;
import android.view.View;

>>>>>>> ba5ebbb05825d65d275b422190a5476dfba060e5
public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

<<<<<<< HEAD
    public void doNext(View v)
    {
        Intent main = new Intent(this, Marketplace.class);
        startActivity(main);
=======
    public void Shop(View v){
        //Intent register = new Intent(MainActivity.this, //marketPlace//);
        //startActivity(register);
>>>>>>> ba5ebbb05825d65d275b422190a5476dfba060e5
    }
}
