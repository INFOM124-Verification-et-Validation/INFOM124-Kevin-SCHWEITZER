package com.gildedrose;

class StandardItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        int decreaseAmount = (item.sellIn >= 0) ? 1 : 2;
        decreaseQuality(item, decreaseAmount);
        decreaseSellIn(item);
    }

    private void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, 0);
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }
}
