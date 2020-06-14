package com.example.kuducredittracker.MarketPlace;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    int id = 1234567;
    String name = "testname";
    String itemCategory = "testCat";
    Double price = 0.0;
    String url = "testurl";
    String Description = "testDescrip";

    Item testItems = new Item(id, name, price, itemCategory, Description, url);

    @Test
    public void getPrice() {

        assertEquals(String.valueOf(testItems.getPrice()), String.valueOf(price));
    }

    @Test
    public void getId() {

        assertEquals(String.valueOf(testItems.getId()), String.valueOf(id));
    }

    @Test
    public void getUrl() {

        assertEquals(testItems.getUrl(), url);
    }

    @Test
    public void getDescription() {

        assertEquals(testItems.getDescription(), Description);
    }

    @Test
    public void getRating() {
        assertEquals(String.valueOf(testItems.getRating()), String.valueOf(0.0f));
    }

    @Test
    public void getName() {
        assertEquals(testItems.getName(), name);
    }
}