package com.allegrowatcher;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.utils.FilterFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-18.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FilterFormatterTest {

    @Test
    public void no_category() {
        Filter filter = new Filter("Needle");

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "Needle");
    }

    @Test
    public void with_category() {
        Filter filter = new Filter("Needle", new Category(16544, "Rowery"));

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "Needle [Rowery]");
    }

    @Test
    public void with_price() {
        Filter filter = new Filter(new Category(16544, "Rowery"), 50, 250);

        String text = FilterFormatter.formatFilter(filter);

        assertEquals(text, "[Rowery] price from 50zł to 250zł");
    }
}
