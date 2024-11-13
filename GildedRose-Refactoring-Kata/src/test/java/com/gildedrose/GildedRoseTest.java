package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testQualityDegrades(){
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void testQualityDegradesNegativeSellIn(){
        Item[] items = new Item[] { new Item("foo", -1, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testQualityDegradesConjuredNegativeSellIn(){
        Item[] items = new Item[] { new Item("Conjured", 0, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testQualityNeverNegative(){
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testAgedBrieIncreasesInQuality(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void testQualityNeverMoreThan50(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testSulfurasNeverDecreasesInQuality(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void testSulfurasNeverDecreasesInSellIn(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1000, 80) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(1000, app.items[0].sellIn);
    }

    @Test
    void testBackstagePassesIncreaseInQuality(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseInQualityLessThan10Days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }

    @Test
    void testBackstagePassesIncreaseInQualityLessThan5Days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    @Test
    void testBackstagePassesDropToZeroAfterConcert(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testConjuredItemsDegradeTwiceAsFast(){
        Item[] items = new Item[] { new Item("Conjured", 10, 10) };
        GildedRose app = new GildedRose(items);
        //a day passes
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }
}
