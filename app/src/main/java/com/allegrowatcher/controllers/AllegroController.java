package com.allegrowatcher.controllers;

import android.content.Context;

import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;
import com.allegrowatcher.service.SoapMethods;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by daba on 2016-08-17.
 */
public class AllegroController {

    public Observable<Summary> loadSummary(final Context context, final int categoryId,
                                           final int priceMin, final int priceMax) {
        return Observable.create(new Observable.OnSubscribe<Summary>() {
            @Override
            public void call(Subscriber<? super Summary> subscriber) {
                List<Item> items = loadItems(categoryId, priceMin,  priceMax);
                List<Item> allItems = new ArrayList<>(items);

                new DataManager().difference(context, items);
                markNewItems(allItems, items);

                Summary summary = new Summary();
                summary.itemsCount = allItems.size();
                summary.newItemsCount = items.size();
                summary.items = allItems;

                subscriber.onNext(summary);
                subscriber.onCompleted();
            }
        });
    }

    public List<Item> loadItems(int categoryId, int priceMin, int priceMax) {
        return SoapMethods.doGetItemsListRequest(categoryId, priceMin,  priceMax, null);
    }

    public void markNewItems(List<Item> items, List<Item> newItems) {
        for (Item item : items) {
            for (Item newItem : newItems) {
                if (item.id == newItem.id) {
                    item.isNew = true;
                    break;
                }
            }
        }
    }
}