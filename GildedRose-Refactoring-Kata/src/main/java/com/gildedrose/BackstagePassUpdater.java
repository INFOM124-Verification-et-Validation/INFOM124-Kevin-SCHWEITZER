package com.gildedrose;

class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        if (item.sellIn > 0) {
            if (item.sellIn < 6) {
                increaseQuality(item, 3);
            } else if (item.sellIn < 11) {
                increaseQuality(item, 2);
            } else {
                increaseQuality(item, 1);
            }
        } else {
            item.quality = 0;
        }
        decreaseSellIn(item);
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, 50);
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }
}
