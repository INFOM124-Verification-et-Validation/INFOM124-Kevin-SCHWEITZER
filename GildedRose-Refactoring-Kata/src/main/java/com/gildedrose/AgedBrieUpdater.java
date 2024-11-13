package com.gildedrose;

class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);
    }

    private void increaseQuality(Item item) {
        item.quality = Math.min(item.quality + 1, 50);
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }
}
