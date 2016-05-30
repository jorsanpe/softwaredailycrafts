package com.gildedrose;

public class AgedBrieItemHolder implements ItemHolder {
    public final Item item;

    public AgedBrieItemHolder(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        item.sellIn -= 1;
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    public Item get() {
        return item;
    }
}
