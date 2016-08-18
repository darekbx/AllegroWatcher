package com.allegrowatcher;

import com.allegrowatcher.model.Item;
import com.allegrowatcher.utils.PriceFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-18.
 */
public class PriceFormatterTest {

    @Test
    public void only_bidding() {
        Item item = new Item(1, "title");
        item.biddingPrice = "15.00";

        String price = PriceFormatter.formatPrice(item);

        assertEquals(price, "15.00zł");
    }

    @Test
    public void only_buy_now() {
        Item item = new Item(1, "title");
        item.buyNowPrice = "20.90";

        String price = PriceFormatter.formatPrice(item);

        assertEquals(price, "20.90zł buy now");
    }

    @Test
    public void bidding_buy_now() {
        Item item = new Item(1, "title");
        item.biddingPrice = "15.00";
        item.buyNowPrice = "20.90";

        String price = PriceFormatter.formatPrice(item);

        assertEquals(price, "15.00zł\n20.90zł buy now");
    }
}
