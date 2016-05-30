package com.gildedrose;

public class SulfurasItemHolder implements ItemHolder {
    public final Item item;

    public SulfurasItemHolder(Item item) {
        this.item = item;
    }

    public void updateQuality() {
    }

    public Item get() {
        return item;
    }
}
