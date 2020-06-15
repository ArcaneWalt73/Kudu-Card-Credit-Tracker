package com.example.kuducredittracker.Resources.test;

import com.example.kuducredittracker.MarketPlace.Item;
import com.example.kuducredittracker.MarketPlace.Store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class storeTest {
    Item item = new Item(1,"he",1.2,"shop","heh","jka");
    ArrayList<Item> items = new ArrayList<>();
    Store store = new Store("shop","hell",1,items);

    @Test
    public void getItemsTest(){

        assertNotNull(store.getItems());
    }
    @Test
    public void getImageTest(){
        assertNotNull(store.getImage());
    }
    @Test
    public void getDescriptionTest(){
        assertNotNull(store.getDescription());
    }
    @Test
    public void getNameTest(){
        assertNotNull(store.getName());
    }

    @Test
    public void setItemsTest(){
        items.add(item);
        store.setItems(items);
        assertEquals(items,store.getItems());
    }
    @Test
    public void setImageTest(){
        int image = 1;
        store.setImage(image);
        assertEquals(image,store.getImage());
    }
    @Test
    public void setDescriptionTest(){
        String desc = "hehe";
        store.setDescription(desc);
        assertEquals(desc,store.getDescription());
    }
    @Test
    public void setNameTest(){
        String name = "hello";
        store.setName(name);
        assertEquals(name,store.getName());
    }

}
