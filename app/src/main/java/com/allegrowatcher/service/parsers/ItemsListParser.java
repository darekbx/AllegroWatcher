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

    public static List<Item> parse(SoapObject object) {
        List<Item> itemList = new ArrayList<>();

        Object itemsListObject = object.getProperty("itemsList");
        if (itemsListObject != null && itemsListObject instanceof SoapObject) {
            SoapObject itemsList = (SoapObject) itemsListObject;
            int itemsCount = itemsList.getPropertyCount();

            for (int i = 0; i < itemsCount; i++) {
                Object itemObject = itemsList.getProperty(i);
                if (itemObject != null && itemObject instanceof SoapObject) {
                    SoapObject soapItem = (SoapObject) itemObject;

                    SoapPrimitive titlePrimitive = (SoapPrimitive) soapItem.getProperty("itemTitle");
                    String title = titlePrimitive.getValue().toString();
                    Object timeToEnd = soapItem.getProperty("timeToEnd");

                    Item item = new Item();
                    item.title = title;

                    itemList.add(item);
                }
            }
        }

        return itemList;
    }
}