package com.gildedrose;

public class BackstagePassesItemHolder implements ItemHolder {
    public final Item item;

    public BackstagePassesItemHolder(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        item.sellIn -= 1;
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
        if (item.sellIn < 11) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    public Item get() {
        return item;
    }
}
