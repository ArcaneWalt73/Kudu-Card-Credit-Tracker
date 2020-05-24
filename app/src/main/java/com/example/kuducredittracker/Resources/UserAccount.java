package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kuducredittracker.Profile;


import org.json.JSONArray;

import java.security.MessageDigest;

public class UserAccount {
    private String[] userDetails; //used to store login details

    private Context context;
    private String register_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/register.php";
    private String login_serverAddress = "https://lamp.ms.wits.ac.za/~s1965919/login.php";
    private String Output_From_PHP = "";
    private JSONArray output_array;
    private String session_username; //will be needed to get ratings

    public UserAccount(String[] userDetails, Context context)
    {
        this.userDetails = userDetails;
        this.context = context;
    }

    /* UserAccount helper Functions */
    public JSONArray getOutput_array() {
        return output_array;
    }
    public String getOutput_From_PHP() {
        return Output_From_PHP;
    }

    //returns the session username
    public String getUserName()
    {
        return session_username;
    }

    /* Login Function */
    public Boolean login(final String username, final String password)
    {
        ContentValues params = new ContentValues();
        params.put("studentNumber", username);
        params.put("password", password);
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost(login_serverAddress, params)
        {
            @Override
            protected void onPostExecute(String output)
            {
                System.err.println("This is the output from LOGIN: "+ output);
                if (output.contains("false"))
                {
                    Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
                    this.logged_in = false;
                }else if(output.contains("true")){
                    Toast.makeText(context, "Welcome "+username, Toast.LENGTH_LONG).show();
                    this.logged_in = true; // The user successfully logged in

                    session_username = username; //set the username for this login session
                    Intent main = new Intent(context, Profile.class);
                    main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    main.putExtra("username", username); // carry along the username
                    context.startActivity(main); // Proceed to the user's profile
                }


            }
        };
        asyncHTTPPost.execute();
        return asyncHTTPPost.getLogged_in();
    }

    /* Register Function */
    public Boolean register(String[] new_userDetails)
    {
        ContentValues params = new ContentValues();

        String[] labels = {"studentNumber","password", "firstName", "lastName", "contact", "emailAddress"};

        //hashing the password and passing that to the server insted of plaintext
        //String hashed_pass = encryptor(new_userDetails[1]);
        //new_userDetails[1] = hashed_pass;

        for(int i = 0; i < new_userDetails.length; ++i)
        {
            params.put(labels[i], new_userDetails[i]);
        }

       @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(register_serverAddress, params) {
           @Override

            // Sets register to true if the output string is 1

            protected void onPostExecute(String output) {
                System.err.println("Here's the output to REGISTER: "+output);

                if (output.contains("true"))
                {
                    Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show();
                    ((Activity)(context)).finish();
                    this.registered = true;
                }
                else {
                    Toast.makeText(context, "Unable to create account", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHttpPost.execute();
        return asyncHttpPost.getRegistered();
    }


    private static String encryptor(String string)
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
        }catch(Exception e) {
            e.printStackTrace();
        }
        return encString;
    }

    public static void printStringArray(String [] array) {
        for (int i = 0; i < array.length; i++)
            System.err.println("TEEHEE : "+ array[i]);
        System.err.println("Goooone");
    }

    public static boolean emailChecker(String s) {
        String [] remainder = s.split("@");

        printStringArray(remainder);
        if (remainder.length == 2) {
            if (remainder[0].equals(""))
                return false;
            if (remainder[1].equals(""))
                return false;
            String [] domain = remainder[1].split("\\.");
            printStringArray(domain);
            if (domain.length >= 2 && !(domain[0].equals("") || domain[1].equals(""))) {
                if (domain[domain.length - 1].equals("com") || domain[domain.length - 1].equals("za")) {
                    return true;
                }
            }
        }
        return false;
    }

}
