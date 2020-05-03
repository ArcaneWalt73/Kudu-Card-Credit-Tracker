package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kuducredittracker.AppEntrance.login;
import com.example.kuducredittracker.MarketPlace.Marketplace;
import com.example.kuducredittracker.Profile;
import com.example.kuducredittracker.Resources.AsyncHttpPost;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserAccount {
    String[] userDetails;
    Context context;
    String serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/register.php";
    String Output_From_PHP = "";
    JSONArray output_array;

    //takes a list: {username,password}
    public UserAccount(String[] userDetails, Context context)
    {
        this.userDetails = userDetails;
        this.context = context;
    }

    /*
    public Boolean login()
    {
        ContentValues params = new ContentValues();
        params.put( "userName", userDetails[0]);
        params.put("password", userDetails[1]);

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost(serverAddress, params)
        {
            @Override
            protected void onPostExecute(String output)
            {
                Output_From_PHP = output;//for unit Testing purpose
                if (output.contains(/*output from php that indicates that user does not exist*))
                {
                    Toast.makeText(context, "Username does not exist", Toast.LENGTH_SHORT).show();
                }
                else if (output.contains(userDetails[0]))//if password and username exist and match
                {
                    Intent main =new Intent(context,/*new Marketplace class*);
                    //main.putExtra("username", userDetails[0]); //stores for later display use of student number
                    //context.startActivity(main);
                }
                else//if username exist but wrong password and other errors that might occur
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHTTPPost.execute();
    }*/

    public JSONArray getOutput_array() {
        return output_array;
    }

    public String getOutput_From_PHP() {
        return Output_From_PHP;
    }

    public Boolean login(final String username, final String password)
    {
        ContentValues params = new ContentValues();
        params.put("studentNumber", username);
        params.put("password", password);
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1965919/login.php", params)
        {
            @Override
            protected void onPostExecute(String output)
            {


                    if (output.contains("false"))
                    {
                        Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
                       // Toast.makeText(context, "username does not exist Or incorrect password", Toast.LENGTH_SHORT).show();
                        this.logged_in = false;
                    }else if(output.contains("true")){
                       /* JSONObject line = output_array.getJSONObject(0);
                        String output_password = line.getString("USERS_PASSWORD");*/

                            Toast.makeText(context, "Welcome "+username, Toast.LENGTH_LONG).show();
                           // Output_From_PHP = output_password;
                            this.logged_in = true;

                            //JSONObject line = userAccount.getOutput_array().getJSONObject(0);

                         /*   String firstname = line.getString("USERS_FNAME");
                            String lastname = line.getString("USERS_LNAME");
                            String email = line.getString("USERS_EMAIL_ADDRESS");
                            String contact = line.getString("USERS_CONTACT_NO");
                            //String credit = line.getString("USERS_CREDIT"); // Credit from Database

                            Intent main = new Intent(context, Marketplace.class);
                            main.putExtra("username", username); //stores for later display use of student number
                            main.putExtra("firstname", firstname); //stores for later display use of firstname
                            main.putExtra("lastname", lastname); //stores for later display use of lastname
                            main.putExtra("email", email); //stores for later display use of email
                            main.putExtra("contact", contact);*/
                            //main.putExtra("credit", credit); //stores for later display use of credit
                            Intent main = new Intent(context, Profile.class);
                            context.startActivity(main);


                         /*else  //if username exist but wrong password and other errors that might occur
                        {
                            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                            this.logged_in = false;
                        }*/
                    }


            }
        };
        asyncHTTPPost.execute();
        return asyncHTTPPost.getLogged_in();
    }


    //array ={username, password, firstname, 2ndname, contact, email, studentNO, icamNumber}
    public Boolean register(String[] new_userDetails)
    {
        ContentValues params = new ContentValues();

        String[] labels = {"studentNumber","password", "firstName", "lastName", "contact", "emailAddress"};

        for(int i = 0; i < new_userDetails.length; ++i)
        {
            params.put(labels[i], new_userDetails[i]);
        }

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(serverAddress, params) {
            @Override

            // Sets register to true if the output string is 1

            protected void onPostExecute(String output) {
                System.out.println(output);

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
}
