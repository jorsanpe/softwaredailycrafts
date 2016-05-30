package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SulfurasItemHolderTest {
    @Test
    public void should_never_decrease_sell_in_value() {
        SulfurasItemHolder sulfurasItemHolder = new SulfurasItemHolder(new Item("Sulfuras, Hand of Ragnaros", 5, 80));

        sulfurasItemHolder.updateQuality();

        assertEquals(80, sulfurasItemHolder.item.quality);
        assertEquals(5, sulfurasItemHolder.item.sellIn);
    }
}
