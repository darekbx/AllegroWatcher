package com.allegrowatcher.controllers;

import android.content.Context;
import android.text.TextUtils;

import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;
import com.allegrowatcher.service.SoapMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by daba on 2016-08-17.
 */
public class AllegroController {

    private static AllegroController instance;
    private HashMap<Filter, Summary> cache = new HashMap<>();

    public static AllegroController getInstance() {
        if (instance == null) {
            instance = new AllegroController();
        }
        return instance;
    }

    public Observable<Summary> loadSummary(final Context context, final Filter filter) {
        if (cache.containsKey(filter)) {
            return Observable.just(cache.get(filter));
        } else {
            return Observable.create(new Observable.OnSubscribe<Summary>() {
                @Override
                public void call(Subscriber<? super Summary> subscriber) {
                    List<Item> items = loadItems(filter);
                    List<Item> allItems = new ArrayList<>(items);

                    new DataManager().difference(context, items);

                    Summary summary = new Summary();
                    summary.itemsCount = allItems.size();
                    summary.newItemsCount = items.size();
                    summary.newIitems = items;

                    cache.put(filter, summary);

                    subscriber.onNext(summary);
                    subscriber.onCompleted();
                }
            });
        }
    }

    public List<Item> loadItems(Filter filter) {
        if (TextUtils.isEmpty(filter.keyword)) {
            return SoapMethods.doGetItemsListRequest(filter.categoryId, filter.priceMin, filter.priceMax, null);
        } else if (filter.categoryId > 0) {
            return SoapMethods.doGetItemsListRequest(filter.categoryId, filter.keyword);
        } else {
            return SoapMethods.doGetItemsListRequest(filter.keyword);
        }
    }
}