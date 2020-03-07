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
                    login(username, password);
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

        public void login(final String username, final String password)
        {
            ContentValues params = new ContentValues();
            params.put("userName", username);
            params.put("password", password);
            @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1965919/login.php", params)
            {
                @Override
                protected void onPostExecute(String output)
                {
                    try {
                        JSONArray output_array = new JSONArray(output);
                        System.out.println(output);

                        if (output_array.length()==0)
                        {
                            Toast.makeText(login.this, "username does not exist", Toast.LENGTH_SHORT).show();
                        }else {
                            JSONObject line = output_array.getJSONObject(0);
                            String output_password = line.getString("USERS_PASSWORD");
                            if (output_password.equals(password))   //if password and username exist and match
                            {
                                //Intent main =new Intent(login.this,/*new Marketplace class*/);
                                //main.putExtra("username", username); //stores for later display use of student number
                                //startActivity(main);
                                Toast.makeText(login.this, "Welcome", Toast.LENGTH_LONG).show();
                            } else  //if username exist but wrong password and other errors that might occur
                            {
                                Toast.makeText(login.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.err.println("Unable to access JSON Array from Login.php");
                    }
                }
            };
            asyncHTTPPost.execute();


        }
    }
