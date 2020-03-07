package com.example.kuducredittracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;

public class UserAccount {
    Context context;
    String serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/register.php";
    String Output_From_PHP = "";

    //takes a list: {username,password}
    UserAccount(Context context)
    {
        this.context = context;
    }


    public void login(String[] userDetails)
    {
        String password = encryptor(userDetails[1]);
        ContentValues params = new ContentValues();
        params.put( "userName", userDetails[0]);
        params.put("password", password);

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost(serverAddress, params)
        {
            @Override
            protected void onPostExecute(String output)
            {
                try {
                    JSONArray output_array = new JSONArray(output);
                    System.out.println(output);

                    if (output_array.length()==0)
                    {
                        Toast.makeText(context, "username does not exist", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(context, "The password worked", Toast.LENGTH_LONG).show();
                        } else//if username exist but wrong password and other errors that might occur
                        {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
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

    public static String encryptor(String string)
    {
        String encString = "";

        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Add password bytes to digest
            md.update(string.getBytes());

            //Get the hash's bytes
            byte[] bytes = md.digest(); //This bytes[] has bytes in decimal format;

            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            //Get complete hashed password in hex format
            encString = sb.toString();



        }catch(Exception e)
        {
            e.printStackTrace();
        }



        return  encString;
    }


    //array ={username, password, firstname, 2ndname, contact, email, studentNO, icamNumber}
    public void register(String[] new_userDetails)
    {
        ContentValues params = new ContentValues();

        String[] labels = {"userName","password", "firstName", "lastName", "contact", "emailAddress", "studentNumber", "icamNumber"};

        //hasing the password
        //String hashed_pass = encryptor(new_userDetails[1]);
        //new_userDetails[1] = hashed_pass;

        for(int i = 0; i < new_userDetails.length; ++i)
        {
            params.put(labels[i], new_userDetails[i]);
        }

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(serverAddress, params) {
            @Override
            protected void onPostExecute(String output) {
                Toast.makeText(context, output, Toast.LENGTH_SHORT);
            }
        };
        asyncHttpPost.execute();
    }
}
