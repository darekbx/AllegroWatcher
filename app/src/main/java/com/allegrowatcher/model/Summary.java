package com.allegrowatcher.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by daba on 2016-08-17.
 */
@Parcel
public class Summary {
    public int itemsCount;
    public int newItemsCount;
    public List<Item> allItems;
    public List<Item> newIitems;

    public Summary() {
    }

    public boolean hasNewItems() {
        return newIitems.size() > 0;
    }
}
