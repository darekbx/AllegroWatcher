package com.allegrowatcher;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-19.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FilterTest {

    @Test
    public void has_keyword() {
        Filter filter = new Filter("Keyword", new Category(1, "test"));

        assertTrue(filter.hasKeyword());
    }

    @Test
    public void has_category() {
        Filter filter = new Filter("Keyword", new Category(1, "test"));

        assertTrue(filter.hasCategory());
    }

    @Test
    public void to_filter_storage_1() {
        Filter filter = new Filter(new Category(1, "test"), 10, 50);

        FilterStorage filterStorage = filter.toFilterStorage();

        assertNull(filterStorage.getKeyword());
        assertEquals((int)filterStorage.getCategory_id(), 1);
        assertEquals(filterStorage.getCategory_name(), "test");
        assertEquals((int)filterStorage.getPrice_min(), 10);
        assertEquals((int)filterStorage.getPrice_max(), 50);
    }

    @Test
    public void to_filter_storage_2() {
        Filter filter = new Filter("keyword", new Category(1, "test"));

        FilterStorage filterStorage = filter.toFilterStorage();

        assertEquals(filterStorage.getKeyword(), "keyword");
        assertEquals((int)filterStorage.getCategory_id(), 1);
        assertEquals(filterStorage.getCategory_name(), "test");
        assertNull(filterStorage.getPrice_min());
        assertNull(filterStorage.getPrice_max());
    }

    @Test
    public void to_filter_storage_3() {
        Filter filter = new Filter("keyword");

        FilterStorage filterStorage = filter.toFilterStorage();

        assertEquals(filterStorage.getKeyword(), "keyword");
        assertNull(filterStorage.getCategory_id());
        assertNull(filterStorage.getCategory_name());
        assertNull(filterStorage.getPrice_min());
        assertNull(filterStorage.getPrice_max());
    }

    @Test
    public void from_filter_storage() {
        FilterStorage filterStorage = new FilterStorage(null, "keyword", 1, "category", 10, 20);

        Filter filter = Filter.fromFilterStorage(filterStorage);

        assertTrue(filter.hasCategory());
        assertTrue(filter.hasKeyword());
        assertEquals(filter.keyword, "keyword");
        assertEquals(filter.category.id, 1);
        assertEquals(filter.category.name, "category");
        assertEquals((int)filter.priceMin, 10);
        assertEquals((int)filter.priceMax, 20);
    }

    @Test
    public void to_string_1() {
        Filter filter = new Filter("keyword", new Category(1, "test"));

        assertEquals(filter.toString(), "keyword");
    }

    @Test
    public void to_string_2() {
        Filter filter = new Filter(new Category(1, "test"), 10, 20);

        assertEquals(filter.toString(), "test");
    }
}