package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgedBrieItemHolderTest {
    @Test
    public void aged_brie_quality_increases_over_time() {
        AgedBrieItemHolder agedBrieItemHolder = new AgedBrieItemHolder(new Item("Aged Brie", 5, 10));

        agedBrieItemHolder.updateQuality();

        assertEquals(11, agedBrieItemHolder.item.quality);
        assertEquals(4, agedBrieItemHolder.item.sellIn);
    }

    @Test
    public void aged_brie_quality_never_increases_over_50() {
        AgedBrieItemHolder agedBrieItemHolder = new AgedBrieItemHolder(new Item("Aged Brie", 5, 49));

        agedBrieItemHolder.updateQuality();
        agedBrieItemHolder.updateQuality();
        agedBrieItemHolder.updateQuality();

        assertEquals(50, agedBrieItemHolder.item.quality);
        assertEquals(2, agedBrieItemHolder.item.sellIn);
    }
}
