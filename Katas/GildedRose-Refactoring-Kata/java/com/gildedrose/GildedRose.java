package com.gildedrose;

import java.util.ArrayList;

class GildedRose {
    private ArrayList<ItemHolder> itemHolders = null;

    public GildedRose(Item[] items) {
        itemHolders = new ArrayList<ItemHolder>();
        for (Item i: items) {
            if (i.name == "Aged Brie") {
                itemHolders.add(new AgedBrieItemHolder(i));
            } else if (i.name == "Sulfuras, Hand of Ragnaros") {
                itemHolders.add(new SulfurasItemHolder(i));
            } else if (i.name == "Backstage passes to a TAFKAL80ETC concert") {
                itemHolders.add(new BackstagePassesItemHolder(i));
            } else {
                itemHolders.add(new CommonItemHolder(i));
            }
        }
    }

    public void updateQuality() {
        for (int i = 0; i < itemHolders.size(); i++) {
            itemHolders.get(i).updateQuality();
        }
    }

    public Item item(int i) {
        return itemHolders.get(i).get();
    }
}
