package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.example.kuducredittracker.Resources.AsyncHttpPost;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserAccount {
    String[] userDetails;
    Context context;
    String serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/register.php";
    String Output_From_PHP = "";

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

    public Boolean login(final String username, final String password)
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
                        Toast.makeText(context, "username does not exist", Toast.LENGTH_SHORT).show();
                        this.logged_in = false;
                    }else {
                        JSONObject line = output_array.getJSONObject(0);
                        String output_password = line.getString("USERS_PASSWORD");
                        if (output_password.equals(password))   //if password and username exist and match
                        {
                            Toast.makeText(context, "Welcome", Toast.LENGTH_LONG).show();
                            this.logged_in = true;
                        } else  //if username exist but wrong password and other errors that might occur
                        {
                            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                            this.logged_in = false;
                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "Unable to connect to the server", Toast.LENGTH_LONG).show();
                    this.logged_in = false;
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
                Toast.makeText(context, "Account created "+ output, Toast.LENGTH_SHORT).show();

                if (output.equals("1"))
                {
                    this.registered = true;
                }
            }
        };
        asyncHttpPost.execute();
        return asyncHttpPost.getRegistered();
    }
}
