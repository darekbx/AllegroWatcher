package com.allegrowatcher.model;

import android.text.TextUtils;

/**
 * Created by daba on 2016-08-17.
 */

public class Filter {
    public String keyword;
    public Category category;
    public int priceMin;
    public int priceMax;

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
}