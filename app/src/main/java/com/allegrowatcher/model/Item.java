package com.allegrowatcher.model;

import org.parceler.Parcel;

/**
 * Created by daba on 2016-08-10.
 */
@Parcel
public class Item {
    public long id;
    public String title;
    public String timeToEnd;
    public String photo;
    public String biddingPrice;
    public String withDeliveryPrice;
    public String buyNowPrice;

    public Item() {
    }

    public Item(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Item(long id, String title, String timeToEnd, String photo, String biddingPrice,
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