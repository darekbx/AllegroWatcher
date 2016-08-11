package com.allegrowatcher.model;

/**
 * Created by daba on 2016-08-10.
 */

public class Item {
    public String id;
    public String title;
    public String timeToEnd;
    public String photo;
    public String biddingPrice;
    public String withDeliveryPrice;
    public String buyNowPrice;

    public Item(String id, String title, String timeToEnd, String photo, String biddingPrice,
                String withDeliveryPrice, String buyNowPrice) {
        this.id = id;
        this.title = title;
        this.timeToEnd = timeToEnd;
        this.photo = photo;
        this.biddingPrice = biddingPrice;
        this.withDeliveryPrice = withDeliveryPrice;
        this.buyNowPrice = buyNowPrice;
    }
}