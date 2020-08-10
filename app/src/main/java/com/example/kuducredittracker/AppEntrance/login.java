package com.example.kuducredittracker.AppEntrance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.UserAccount;

public class login extends AppCompatActivity
{
 //   public String Output_From_PHP;//For unit Testing purpose
    private EditText user;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //User Login On Click Listener
        user = (EditText) findViewById(R.id.student_no);
        pass = (EditText) findViewById(R.id.password);
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

            if (username.equals("") || password.equals(""))
            {
                Toast.makeText(this, "Incorrect Input", Toast.LENGTH_SHORT).show();
                return;
            }

            String [] info = {username, password};
            UserAccount userAccount = new UserAccount(info, getApplicationContext());
            userAccount.login(username,password);
            user.setText("");
            pass.setText("");
        }
    }
