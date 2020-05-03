package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.kuducredittracker.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;

public class UserAccount {
    private String[] userDetails; //used to store login details
    private String[] user_account_info; //stores user information(names, contact number etc)

    private Context context;
    private String register_serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/register.php";
    private String login_serverAddress = "http://lamp.ms.wits.ac.za/~s1965919/login.php";
    private String Output_From_PHP = "";
    private JSONArray output_array;

    public UserAccount(String[] userDetails, Context context)
    {
        this.userDetails = userDetails;
        this.context = context;
    }

    public Boolean login(final String username, final String password)
    {
        final String encrypted_password = encryptor(password); //uses the md5 encryptor to pass the encrypted password
        ContentValues params = new ContentValues();
        params.put("studentNumber", username);
        params.put("password", encrypted_password);
        @SuppressLint("StaticFieldLeak") final AsyncHttpPost asyncHTTPPost = new AsyncHttpPost(login_serverAddress, params)
        {
            @Override
            protected void onPostExecute(String output)
            {
                System.err.println(output); // given output and app comparison are not the same
                                            // JSON within the try block gives a JSONException
                try {
                    output_array = new JSONArray(output);

                    if (output_array.length()==0)
                    {
                        Toast.makeText(context, "username does not exist", Toast.LENGTH_SHORT).show();
                        this.logged_in = false;
                    }else {
                        JSONObject line = output_array.getJSONObject(0);
                        String output_password = line.getString("USERS_PASSWORD");
                        if (output_password.equals(encrypted_password))   //if password and username exist and match
                        {
                            Toast.makeText(context, "Welcome "+username, Toast.LENGTH_LONG).show();
                            Output_From_PHP = output_password;
                            this.logged_in = true;

                            //Collect the data//
                            JSONArray names_array = line.names(); //stores the column names of USERS_USER_ACCOUNT
                            user_account_info = new String[names_array.length()];

                            for(int i = 0; i < user_account_info.length; ++i) //stores the values of each column in the user's row
                            {
                                user_account_info[i] = line.getString(names_array.get(i).toString()); //now to access it use the getUserInfor method!
                            }

                            Intent main = new Intent(context, Profile.class);
                            main.putExtra("username", username); //stores for later display use of student number
                            context.startActivity(main);

                        } else  //if username exist but wrong password and other errors that might occur
                        {
                            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
                            this.logged_in = false;
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(context, "Unable to connect to the server", Toast.LENGTH_LONG).show();
                    this.logged_in = false;
                }
            }
        };
        asyncHTTPPost.execute();
        /*try {  // wait for the asyncHHTPPost to finish executing
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (asyncHTTPPost.getStatus() == AsyncTask.Status.RUNNING){

                    }
                }
            });
            t.start();
        }
        catch (Exception e) {
            System.err.println("A Thread in UserAccount.login crashed");
        }*/
        return asyncHTTPPost.getLogged_in();
    }


    //array ={username, password, firstname, 2ndname, contact, email, studentNO, icamNumber}
    public Boolean register(String[] new_userDetails)
    {
        ContentValues params = new ContentValues();

        String[] labels = {"studentNumber","password", "firstName", "lastName", "contact", "emailAddress"};

        //hashing the password and passing that to the server insted of plaintext
        String hashed_pass = encryptor(new_userDetails[1]);
        new_userDetails[1] = hashed_pass;


        for(int i = 0; i < new_userDetails.length; ++i)
        {
            params.put(labels[i], new_userDetails[i]);
        }

        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(register_serverAddress, params) {
            @Override

            // Sets register to true if the output string is 1

            protected void onPostExecute(String output) {
                System.err.println(output); // output returned is false and register function expects 1

                if (output.contains("1"))
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

    //returns the user's account info string array
    public String[] getUserAccountInfo()
    {
        return user_account_info;
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



        }catch(Exception e)
        {
            e.printStackTrace();
        }



        return  encString;
    }

}
