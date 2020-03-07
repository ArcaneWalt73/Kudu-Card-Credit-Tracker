package com.example.kuducredittracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class UserAccount {
    String[] userDetails;
    Context context;
    String serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/register.php";
    String Output_From_PHP = "";

    //takes a list: {username,password}
    UserAccount(String[] userDetails, Context context)
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

    //array ={username, password, firstname, 2ndname, contact, email, studentNO, icamNumber}
    public void register(String[] new_userDetails)
    {
        ContentValues params = new ContentValues();

        String[] labels = {"userName","password", "firstName", "lastName", "contact", "emailAddress", "studentNumber", "icamNumber"};

        for(int i = 0; i < new_userDetails.length; ++i)
        {
            params.put(labels[i], new_userDetails[i]);
        }

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(serverAddress, params) {
            @Override
            protected void onPostExecute(String output) {
                //System.err.println(output);
                Toast.makeText(context, output, Toast.LENGTH_SHORT);
            }
        };
        asyncHttpPost.execute();
    }
}
