package com.allegrowatcher.controllers;

import android.content.Context;

import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Category;
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

                    new DataManager(context).difference(items);

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

    public Observable<Integer> initializeCategories(final Context context) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                DataManager dataManager = new DataManager(context);
                if (!dataManager.hasCategoriesLoaded()) {
                    List<Category> categories = loadCategories();
                    dataManager.saveCategories(categories);
                    subscriber.onNext(categories.size());
                } else {
                    subscriber.onNext(0);
                }
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<com.allegrowatcher.Category>> loadCategoriesAsync(final Context context) {
        return Observable.create(new Observable.OnSubscribe<List<com.allegrowatcher.Category>>() {
            @Override
            public void call(Subscriber<? super List<com.allegrowatcher.Category>> subscriber) {
                DataManager dataManager = new DataManager(context);
                subscriber.onNext(dataManager.getCategories());
                subscriber.onCompleted();
            }
        });
    }

    public List<Item> loadItems(Filter filter) {
        return SoapMethods.doGetItemsListRequest(filter);
    }

    public List<Category> loadCategories() {
        return SoapMethods.doGetCatsDataRequest();
    }
}