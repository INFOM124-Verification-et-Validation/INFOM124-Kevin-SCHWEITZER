package com.gildedrose;

class ConjuredUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseQuality(item);
        decreaseSellIn(item);
    }

    private void decreaseQuality(Item item) {
        int decreaseAmount = (item.sellIn >= 0) ? 2 : 4;
        item.quality = Math.max(item.quality - decreaseAmount, 0);
    }

    private void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }
}
