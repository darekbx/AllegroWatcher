package com.allegrowatcher.utils;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

/**
 * Created by daba on 2016-08-18.
 */

public class FilterFormatter {

    public static SpannableStringBuilder formatFilterSpan(Filter filter) {
        String filterString = formatFilter(filter);
        SpannableStringBuilder builder = new SpannableStringBuilder(filterString);
        int start = 0;
        int end;

        if (filter.hasCategory() && !filter.hasKeyword()) {
            start = filterString.indexOf(']') + 1;
            end = filterString.length();
        } else if (filter.hasCategory()) {
            start = 0;
            end = filterString.indexOf('[');
        } else {
            end = filterString.length();
        }

        builder.setSpan(new ForegroundColorSpan(Color.BLACK), start, end,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        if (filter.hasKeyword() && filter.hasPrice()) {
            start = filterString.indexOf("price");
            end = filterString.length();
            builder.setSpan(new ForegroundColorSpan(Color.BLACK), start, end,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }

        return builder;
    }

    public static String formatFilter(Filter filter) {
        String filterString = "";
        if (filter.hasKeyword()) {
            filterString = filter.keyword + " ";
        }
        if (filter.hasCategory()) {
            filterString += formatCategory(filter.category) + " ";
        }
        if (filter.hasPrice()) {
            filterString += formatPrice(filter) + " ";
        }
        if (filter.hasCondition()) {
            filterString += "(" + filter.getConditionString() + ")";
        }
        return filterString;
    }

    public static String formatCategory(Category category) {
        return '[' + category.name + ']';
    }

    public static String formatPrice(Filter filter) {
        return "price from " + filter.priceMin + "zł to " + filter.priceMax + "zł";
    }
}