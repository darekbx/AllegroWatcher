package com.allegrowatcher.model;

import android.text.TextUtils;

import com.allegrowatcher.FilterStorage;

/**
 * Created by daba on 2016-08-17.
 */

public class Filter {
    public String keyword;
    public Category category;
    public Integer priceMin;
    public Integer priceMax;
    public Long storageId;

    public Filter(Category category, int priceMin, int priceMax) {
        this.category = category;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public Filter(String keyword, Category category) {
        this.keyword = keyword;
        this.category = category;
    }

    public Filter(String keyword) {
        this.keyword = keyword;
    }

    public boolean hasKeyword() {
        return !TextUtils.isEmpty(keyword);
    }

    public boolean hasCategory() {
        return category != null;
    }

    public FilterStorage toFilterStorage() {
        return new FilterStorage(
                null,
                keyword,
                category != null ? category.id : null,
                category != null ? category.name : null,
                priceMin,
                priceMax);
    }

    public static Filter fromFilterStorage(FilterStorage filterStorage) {
        Filter filter = new Filter(filterStorage.getKeyword());
        filter.storageId = filterStorage.getId();
        filter.priceMin = filterStorage.getPrice_min();
        filter.priceMax = filterStorage.getPrice_max();
        if (filterStorage.getCategory_id() != null) {
            filter.category = new Category(filterStorage.getCategory_id(), filterStorage.getCategory_name());
        }
        return filter;
    }

    @Override
    public String toString() {
        if (hasKeyword()) {
            return keyword;
        }
        if (hasCategory()) {
            return category.name;
        }
        return super.toString();
    }
}