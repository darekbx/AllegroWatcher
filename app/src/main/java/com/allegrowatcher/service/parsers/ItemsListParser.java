package com.allegrowatcher.service.parsers;

import com.allegrowatcher.model.Item;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-10.
 */

public class ItemsListParser {

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

        Object itemsListObject = object.getProperty("itemsList");
        if (itemsListObject != null && itemsListObject instanceof SoapObject) {
            SoapObject itemsList = (SoapObject) itemsListObject;
            int itemsCount = itemsList.getPropertyCount();

            for (int i = 0; i < itemsCount; i++) {
                Object itemObject = itemsList.getProperty(i);
                if (itemObject != null && itemObject instanceof SoapObject) {
                    SoapObject soapItem = (SoapObject) itemObject;
                    Item item = new Item(
                            getValue(soapItem, "itemId"),
                            getValue(soapItem, "itemTitle"),
                            getValue(soapItem, "timeToEnd"),
                            getPhoto(soapItem, PhotoSize.MEDIUM),
                            getPrice(soapItem, PriceType.BIDDING),
                            getPrice(soapItem, PriceType.BUY_NOW),
                            getPrice(soapItem, PriceType.WITH_DELIVERY)
                    );
                    itemList.add(item);
                }
            }
        }

        return itemList;
    }

    public String getValue(SoapObject soapItem, String propertyName) {
        SoapPrimitive itemPrimitive = (SoapPrimitive) soapItem.getPrimitiveProperty(propertyName);
        return itemPrimitive.getValue().toString();
    }

    public String getPhoto(SoapObject soapItem, PhotoSize size) {
        SoapObject itemSoapObject = (SoapObject) soapItem.getProperty("photosInfo");
        for (int i = 0, count = itemSoapObject.getPropertyCount(); i < count; i++) {
            SoapObject subItemSoapObject = (SoapObject) itemSoapObject.getProperty(i);
            if (subItemSoapObject.getProperty("photoSize").toString().equals(size.toString())
                    && subItemSoapObject.getProperty("photoIsMain").toString().equals("true")) {
                return subItemSoapObject.getProperty("photoUrl").toString();
            }
        }
        return null;
    }

    public String getPrice(SoapObject soapItem, PriceType type) {
        SoapObject itemSoapObject = (SoapObject) soapItem.getProperty("priceInfo");
        for (int i = 0, count = itemSoapObject.getPropertyCount(); i < count; i++) {
            SoapObject subItemSoapObject = (SoapObject) itemSoapObject.getProperty(i);
            if (subItemSoapObject.getProperty("priceType").toString().equals(type.toString())) {
                return subItemSoapObject.getProperty("priceValue").toString();
            }
        }
        return null;
    }
}