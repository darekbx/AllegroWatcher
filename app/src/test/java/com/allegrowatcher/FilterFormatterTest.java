package com.allegrowatcher;

import com.allegrowatcher.model.Filter;
import com.allegrowatcher.utils.FilterFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-18.
 */
public class FilterFormatterTest {

    @Test
    public void no_category() {
        Filter filter = new Filter("Needle");

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "Needle, no category");
    }

    @Test
    public void with_category() {
        Filter filter = new Filter("Needle", 16544);

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "Needle, 16544");
    }

    @Test
    public void with_price() {
        Filter filter = new Filter("Needle", 16544, 50, 250);

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "Needle, 16544, price from 50zł to 250zł");
    }
}
