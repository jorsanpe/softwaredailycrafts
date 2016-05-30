package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackstagePassesItemHolderTest {
    @Test
    public void quality_increases_over_time() {
        BackstagePassesItemHolder backstagePassesItemHolder = new BackstagePassesItemHolder(new Item("Backstage passes", 20, 10));

        backstagePassesItemHolder.updateQuality();

        assertEquals(11, backstagePassesItemHolder.item.quality);
        assertEquals(19, backstagePassesItemHolder.item.sellIn);
    }

    @Test
    public void quality_increases_by_2_at_10_days_or_less_over_time() {
        BackstagePassesItemHolder backstagePassesItemHolder = new BackstagePassesItemHolder(new Item("Backstage passes", 8, 10));

        backstagePassesItemHolder.updateQuality();

        assertEquals(12, backstagePassesItemHolder.item.quality);
        assertEquals(7, backstagePassesItemHolder.item.sellIn);
    }

    @Test
    public void quality_increases_by_3_at_5_days_or_less_over_time() {
        BackstagePassesItemHolder backstagePassesItemHolder = new BackstagePassesItemHolder(new Item("Backstage passes", 4, 10));

        backstagePassesItemHolder.updateQuality();

        assertEquals(13, backstagePassesItemHolder.item.quality);
        assertEquals(3, backstagePassesItemHolder.item.sellIn);
    }

    @Test
    public void quality_never_increases_over_50() {
        BackstagePassesItemHolder backstagePassesItemHolder = new BackstagePassesItemHolder(new Item("Backstage passes", 4, 49));

        backstagePassesItemHolder.updateQuality();

        assertEquals(50, backstagePassesItemHolder.item.quality);
        assertEquals(3, backstagePassesItemHolder.item.sellIn);
    }
}
