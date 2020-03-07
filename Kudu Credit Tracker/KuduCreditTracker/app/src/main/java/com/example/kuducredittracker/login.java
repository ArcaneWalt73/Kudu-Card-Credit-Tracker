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
                    ContentValues params = new ContentValues();
                    params.put("userName", username);
                    params.put("password", password);

                    @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1965919/login.php", params)
                    {
                        @Override
                        protected void onPostExecute(String output)
                        {
                            //[{"USERS_USERNAME":"1965919","USERS_FNAME":"Chester","USERS_LNAME":"Dlamini","USERS_PASSWORD":"Cats","USERS_STUDENT_NO":"1965919","USERS_ICAM_No":"1234567890","USERS_CONTACT_NO":"1234567890","USERS_EMAIL_ADDRESS":"chester@email.com"}]

                          //  Output_From_PHP = output;//for unit Testing purpose
                            //System.out.println(output);
                            try {
                                JSONArray output_array = new JSONArray(output);
                                System.out.println(output);

                                if (output_array.length()==0)
                                {
                                    Toast.makeText(login.this, "username does not exist", Toast.LENGTH_SHORT).show();
                                }else {
                                    JSONObject line = output_array.getJSONObject(0);
                                    String output_password = line.getString("USERS_PASSWORD");
                                    System.out.println(output_password);
                                    System.out.println(password);


                                    if (output_password.equals(password))//if password and username exist and match
                                    {
                                        //Intent main =new Intent(login.this,/*new Marketplace class*/);
                                        //main.putExtra("username", username); //stores for later display use of student number
                                        //startActivity(main);
                                        Toast.makeText(login.this, "The password worked", Toast.LENGTH_LONG).show();
                                    } else//if username exist but wrong password and other errors that might occur
                                    {
                                        Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
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
