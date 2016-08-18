package com.allegrowatcher.utils;

import com.allegrowatcher.model.Item;

/**
 * Created by daba on 2016-08-18.
 */

public class PriceFormatter {

    public static String formatPrice(Item item) {
        boolean hasBidding = item.biddingPrice != null;
        boolean hasBuyNow = item.buyNowPrice != null;
        String price = "";

        if (hasBidding) {
            price += item.biddingPrice;
            price += "zł";
        }

        if (hasBuyNow) {
            if (hasBidding) {
                price += "\n";
            }
            price += item.buyNowPrice;
            price += "zł";
            price += " buy now";
        }

        return price;
    }
}
