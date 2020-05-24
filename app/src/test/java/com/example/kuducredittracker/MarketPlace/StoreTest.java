package com.example.kuducredittracker.MarketPlace;

import com.example.kuducredittracker.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {
    @Test
    public void getName() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        assertEquals(testStore.getName(), "Clothing");
        assertNotEquals(testStore.getName(), "");
    }

    @Test
    public void getImage() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        assertEquals(testStore.getImage(), R.drawable.test_foreground);
        assertNotEquals(testStore.getImage(), R.drawable.clothing);
    }

    @Test
    public void getDescription() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        assertEquals(testStore.getDescription(), "Cloths and other wearable stuff");
        assertNotEquals(testStore.getDescription(), "");
    }

    @Test
    public void setDescription() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        testStore.setDescription("This is now a store with new wearable stuff");
        assertEquals(testStore.getDescription(), "This is now a store with new wearable stuff");
    }

    @Test
    public void setImage() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        testStore.setImage(R.drawable.test_background);
        assertEquals(testStore.getImage(), R.drawable.test_background);
    }

    @Test
    public void setName() {
        Store testStore = new Store("Clothing", "Cloths and other wearable stuff", R.drawable.test_foreground);
        testStore.setDescription("Cloths and stuff");
        assertEquals(testStore.getDescription(), "Cloths and stuff");
    }
}