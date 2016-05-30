package com.gildedrose;

public class CommonItemHolder implements ItemHolder {
    public final Item item;

    public CommonItemHolder(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        item.sellIn = item.sellIn - 1;

        decreaseQuality();
        if (item.sellIn < 0) {
            decreaseQuality();
        }
    }

    public Item get() {
        return item;
    }

    private void decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
