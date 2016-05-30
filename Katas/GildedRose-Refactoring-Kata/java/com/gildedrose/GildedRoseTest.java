package com.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Mockito.class)
public class GildedRoseTest {
    @Mock


    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("Doomhammer", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Doomhammer", app.item(0).name);
    }
}
