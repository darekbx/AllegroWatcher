package com.allegrowatcher.utils;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

/**
 * Created by daba on 2016-08-18.
 */

public class FilterFormatter {

    public static String formatFilter(Filter filter) {
        if (filter.hasCategory() && !filter.hasKeyword()) {
            return formatCategory(filter.category) + ',' + formatPrice(filter);
        } else if (filter.hasCategory()) {
            return filter.keyword + " " + formatCategory(filter.category);
        } else {
            return filter.keyword;
        }
    }

    public static String formatCategory(Category category) {
        return '[' + category.name + ']';
    }

    public static String formatPrice(Filter filter) {
        return " price from " + filter.priceMin + "zł to " + filter.priceMax + "zł";
    }
}