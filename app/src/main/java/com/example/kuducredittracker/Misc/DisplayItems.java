package com.example.kuducredittracker.Misc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kuducredittracker.R;
import com.example.kuducredittracker.Resources.ConnectivityHelper;

public class DisplayItems extends AppCompatActivity {

    private TextView editTextUrl;
    private TextView editTextUrl2;
    private TextView editTextUrl3;
    private Button buttonLoad;
    ConnectivityHelper connectivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);
        connectivityHelper = new ConnectivityHelper(this);

        editTextUrl = findViewById(R.id.editTextUrl);
        editTextUrl2 = findViewById(R.id.editTextUrl2);
        editTextUrl3 = findViewById(R.id.editTextUrl3);
        buttonLoad = (Button) findViewById(R.id.buttonLoad);
    }


    public void Load(View v)
    {
        Boolean is_conn1 = connectivityHelper.isOnline();

        editTextUrl.setText(Boolean.toString(is_conn1));
    }
    public void Load2(View v)
    {
        Boolean is_conn2 = connectivityHelper.isOnline();

        editTextUrl2.setText(Boolean.toString(is_conn2));
    }
    public void Load3(View v)
    {
        Boolean is_conn3 = ConnectivityHelper.checkInternetConnection(this);

        editTextUrl3.setText(Boolean.toString(is_conn3));
    }

}
