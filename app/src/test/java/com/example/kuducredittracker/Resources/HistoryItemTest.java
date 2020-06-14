package com.example.kuducredittracker.Resources;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoryItemTest {

    @Test
    public void getDate() {

        HistoryItem historyItem =
                new HistoryItem(0, "Test", 0.0, "Test Category", "Test Description", "Test_URL", "00-00-0000");

        assertNotNull(historyItem.getDate());
        assertEquals("00-00-0000", historyItem.getDate());
    }

    @Test
    public void getHistoryItem() {
        try {
            JSONObject object = new JSONObject();
            object.put("MARKET_ID", 0);
            object.put("NAME", "TEST");
            object.put("PRICE", 0.0);
            object.put("DESCRIPTION", "Test Description");
            object.put("IMAGE_URL","Test_URL");
            object.put("CATEGORY", "Test Category");

            HistoryItem historyItem = HistoryItem.getHistoryItem(0, "00-00-0000", object);

            assertEquals(historyItem.getId(), object.getInt("MARKET_ID"));
            assertEquals(historyItem.getName(), object.getString("NAME"));
            assertEquals(historyItem.getPrice(), object.getDouble("PRICE"));
            assertEquals(historyItem.getDescription(), object.getString("DESCRIPTION"));
            assertEquals(historyItem.getUrl(), object.getString("IMAGE_URL"));
            assertEquals(historyItem.getItemCategory(), object.getString("CATEGORY"));

            historyItem.setRating(0.0f);
            assertNotNull(historyItem.getRating());
            assertEquals(0.0f, historyItem.getRating());

            assertNotNull(historyItem);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}