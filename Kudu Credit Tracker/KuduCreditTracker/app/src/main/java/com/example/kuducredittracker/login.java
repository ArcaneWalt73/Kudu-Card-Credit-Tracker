package com.example.kuducredittracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity
{
 //   public String Output_From_PHP;//For unit Testing purpose
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            //User Login On Click Listener
            final EditText user = (EditText) findViewById(R.id.student_no);
            final EditText pass = (EditText) findViewById(R.id.password);

            Button login_btn = (Button) findViewById(R.id.login_btm);
            login_btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    final String username = user.getText().toString();
                    final String password = pass.getText().toString();

                    String [] info = {username, password};
                    UserAccount userAccount = new UserAccount(info, getApplicationContext());

                    if (userAccount.login(username, password)) {
                        //Intent main =new Intent(login.this,/*new Marketplace class*/);
                        //main.putExtra("username", username); //stores for later display use of student number
                        //startActivity(main);
                    }
                }
            });

            //User Register On Click Listener

            Button register_prompt = (Button) findViewById(R.id.register_btn);
            register_prompt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register = new Intent(login.this, register.class);
                    startActivity(register);
                }
            });



        }


    }
