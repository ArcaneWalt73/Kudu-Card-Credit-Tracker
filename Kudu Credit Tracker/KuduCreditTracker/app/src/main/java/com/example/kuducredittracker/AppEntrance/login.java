package com.example.kuducredittracker.AppEntrance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kuducredittracker.MarketPlace.Marketplace;
import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.UserAccount;

import org.json.JSONObject;

public class login extends AppCompatActivity
{
 //   public String Output_From_PHP;//For unit Testing purpose
    private EditText user;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            //User Login On Click Listener
            user = (EditText) findViewById(R.id.student_no);
            pass = (EditText) findViewById(R.id.password);

            /*Button login_btn = (Button) findViewById(R.id.login_btm);
            login_btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    final String username = user.getText().toString();
                    final String password = pass.getText().toString();

                    String [] info = {username, password};
                    UserAccount userAccount = new UserAccount(info, getApplicationContext());

                    if (userAccount.login(username, password)) {
                        //Intent main =new Intent(login.this,/*new Marketplace class*);
                        //main.putExtra("username", username); //stores for later display use of student number
                        //startActivity(main);
                    }
                }
            });*/

            //User Register On Click Listener

            /*Button register_prompt = (Button) findViewById(R.id.register_btn);
            register_prompt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register = new Intent(login.this, com.example.kuducredittracker.AppEntrance.register.class);
                    startActivity(register);
                }
            });*/



        }


        public void doRegister(View v)
        {
            Intent register = new Intent(login.this, com.example.kuducredittracker.AppEntrance.register.class);
            startActivity(register);
        }

        public void doLogin(View v)
        {
            final String username = user.getText().toString();
            final String password = pass.getText().toString();

            String [] info = {username, password};
            UserAccount userAccount = new UserAccount(info, getApplicationContext());

            if (userAccount.login(username, password)) {

                try {
                    JSONObject line = userAccount.getOutput_array().getJSONObject(0);
                    String firstname = line.getString("USERS_FIRSTNAME");
                    String lastname = line.getString("USERS_LASTNAME");
                    String email = line.getString("USERS_EMAIL");
                    //String credit = line.getString("USERS_CREDIT"); // Credit from Database

                    Intent main = new Intent(login.this, Marketplace.class);
                    main.putExtra("username", username); //stores for later display use of student number
                    main.putExtra("firstname", firstname); //stores for later display use of firstname
                    main.putExtra("lastname", lastname); //stores for later display use of lastname
                    main.putExtra("email", email); //stores for later display use of email
                    //main.putExtra("credit", credit); //stores for later display use of credit
                    startActivity(main);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Unable to go to Profile", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
