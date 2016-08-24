package com.allegrowatcher.service.parsers;

import com.allegrowatcher.model.Item;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-10.
 */

public class ItemsListParser extends BaseParser {

    public enum PhotoSize {
        SMALL("small"),
        MEDIUM("medium"),
        LARGE("large");

        private String value;

        PhotoSize(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum PriceType {
        BIDDING("bidding"),
        BUY_NOW("buyNow"),
        WITH_DELIVERY("withDelivery");

        private String value;

        PriceType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public List<Item> parse(SoapObject object) {
        List<Item> itemList = new ArrayList<>();

        if (object.hasProperty("itemsList")) {
            Object itemsListObject = object.getProperty("itemsList");
            if (itemsListObject != null && itemsListObject instanceof SoapObject) {
                SoapObject itemsList = (SoapObject) itemsListObject;
                int itemsCount = itemsList.getPropertyCount();
                for (int i = 0; i < itemsCount; i++) {
                    Object itemObject = itemsList.getProperty(i);
                    if (itemObject != null && itemObject instanceof SoapObject) {
                        SoapObject soapItem = (SoapObject) itemObject;
                        Item item = new Item(
                                Long.parseLong(getValue(soapItem, "itemId")),
                                getValue(soapItem, "itemTitle"),
                                getValue(soapItem, "timeToEnd"),
                                getPhoto(soapItem, PhotoSize.MEDIUM),
                                getPrice(soapItem, PriceType.BIDDING),
                                getPrice(soapItem, PriceType.WITH_DELIVERY),
                                getPrice(soapItem, PriceType.BUY_NOW)
                        );
                        itemList.add(item);
                    }
                }
            }
        }

        return itemList;
    }
}