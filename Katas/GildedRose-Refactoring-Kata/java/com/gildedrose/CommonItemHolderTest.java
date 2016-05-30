package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonItemHolderTest {
    @Test
    public void should_lower_quality_on_item() {
        CommonItemHolder commonItemHolder = new CommonItemHolder(new Item("Deadly Poison", 3, 3));
        commonItemHolder.updateQuality();
        assertEquals("Deadly Poison", commonItemHolder.item.name);
        assertEquals(2, commonItemHolder.item.quality);
        assertEquals(2, commonItemHolder.item.sellIn);
    }

    @Test
    public void quality_should_not_get_below_zero() {
        CommonItemHolder commonItemHolder = new CommonItemHolder(new Item("Deadly Poison", 3, 3));
        for (int i = 0; i < 5; i++) {
            commonItemHolder.updateQuality();
        }
        assertEquals("Deadly Poison", commonItemHolder.item.name);
        assertEquals(0, commonItemHolder.item.quality);
        assertEquals(-2, commonItemHolder.item.sellIn);
    }

    @Test
    public void quality_should_decrease_twice_as_fast_after_sell_date() {
        CommonItemHolder commonItemHolder = new CommonItemHolder(new Item("Deadly Poison", 1, 10));
        commonItemHolder.updateQuality();
        commonItemHolder.updateQuality();

        assertEquals(7, commonItemHolder.item.quality);
        assertEquals(-1, commonItemHolder.item.sellIn);
    }
}
