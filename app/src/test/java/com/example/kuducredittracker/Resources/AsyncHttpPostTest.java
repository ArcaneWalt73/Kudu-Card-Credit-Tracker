package com.example.kuducredittracker.Resources;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import com.example.kuducredittracker.Misc.Profile;

import org.junit.Test;

import static org.junit.Assert.*;

public class AsyncHttpPostTest {

    @Test
    public void doInBackground() {
        String test = "https://lamp.ms.wits.ac.za/~s1965919/test.php";

        ContentValues params = new ContentValues();
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(test, params) {
            @Override
            protected void onPostExecute(String output) {
                AsyncHttpPostTest object = new AsyncHttpPostTest();
                assertNotNull(output);
                assertEquals("success", output);
            }
        };
        asyncHttpPost.execute();
    }

    @Test
    public void onPostExecute() {
        String test = "https://lamp.ms.wits.ac.za/~s1965919/test.php";

        ContentValues params = new ContentValues();
        @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHttpPost = new AsyncHttpPost(test, params) {
            @Override
            protected void onPostExecute(String output) {
                AsyncHttpPostTest object = new AsyncHttpPostTest();
                assertNotNull(output);
                assertEquals("success", output);
            }
        };
        asyncHttpPost.execute();
    }
}