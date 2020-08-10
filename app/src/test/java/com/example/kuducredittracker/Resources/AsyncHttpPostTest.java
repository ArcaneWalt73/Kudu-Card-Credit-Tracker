package com.example.kuducredittracker.Resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)

@PrepareForTest(AsyncHttpPost.class)

public class AsyncHttpPostTest {

    @Test
    public void doInBackgroundTest() {
        AsyncHttpPost asyncHttpPost = Mockito.mock(AsyncHttpPost.class);
        Mockito.when(asyncHttpPost.doInBackground(Mockito.anyString())).thenReturn("success");
    }

    @Test
    public void onPostExecuteTest() {
        AsyncHttpPost asyncHttpPost = Mockito.mock(AsyncHttpPost.class);
        String output = Mockito.anyString();
        assertNotNull(output);
        asyncHttpPost.onPostExecute(output);
    }
    @Test
    public void getLogged_inTest(){
        AsyncHttpPost asyncHttpPost = Mockito.mock(AsyncHttpPost.class);
        assertNotNull(asyncHttpPost.getLogged_in());
    }
    @Test
    public void getRegisteredTest(){
        AsyncHttpPost asyncHttpPost = Mockito.mock(AsyncHttpPost.class);
        assertNotNull(asyncHttpPost.getRegistered());
    }
}