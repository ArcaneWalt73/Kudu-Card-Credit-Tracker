package com.example.kuducredittracker.MarketPlace;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class MarketplaceTest {

    @Test
    public void getItemsFromDB() {
        Marketplace testMP = new Marketplace();

        ArrayList<Item> testArray = testMP.allItems;
        System.out.println(testMP.getItemsFromDB());
        assertNotNull(testMP.getItemsFromDB());

    }
    @Test
    public void setItems(){

    }
}