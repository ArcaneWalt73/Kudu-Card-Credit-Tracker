package com.example.kuducredittracker.MarketPlace;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MarketplaceTest {

    @Test
    public void getItemsFromDB() {
        Marketplace testMP = new Marketplace();

        ArrayList<Item> testArray = testMP.allItems;

        //assertArrayEquals(testMP.getItemsFromDB(), testArray);
        assertNotNull(testMP.getItemsFromDB());

    }
}