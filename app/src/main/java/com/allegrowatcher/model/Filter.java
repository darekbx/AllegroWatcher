package com.allegrowatcher.model;

/**
 * Created by daba on 2016-08-17.
 */

public class Filter {
    public String name;
    public String keyword;
    public int categoryId;
    public int priceMin;
    public int priceMax;

    public Filter(String name, int categoryId, int priceMin, int priceMax) {
        this.name = name;
        this.categoryId = categoryId;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public Filter(String keyword, int categoryId) {
        this.name = keyword;
        this.keyword = keyword;
        this.categoryId = categoryId;
    }
}