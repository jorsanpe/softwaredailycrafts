package com.gildedrose;

public class CommonItemHolder implements ItemHolder {
    private final Item item;

    public CommonItemHolder(Item item) {
        this.item = item;
    }

    public void updateQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.sellIn = item.sellIn - 1;
        }
    }
}
