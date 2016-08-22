package com.allegrowatcher;

import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataManagerTest {

    private DataManager dataManager;

    @Before
    public void prepare() {
        dataManager = new DataManager(RuntimeEnvironment.application);
    }

    @Test
    public void add_allegro_id() {
        dataManager.addAllegroId(1001);
        dataManager.addAllegroId(Long.MAX_VALUE);
        dataManager.addAllegroId(Long.MIN_VALUE);

        List<Long> allegroIds = dataManager.getStoredIds();

        assertEquals(allegroIds.size(), 3);
        assertEquals((long)allegroIds.get(0), 1001);
        assertEquals((long)allegroIds.get(1), Long.MAX_VALUE);
        assertEquals((long)allegroIds.get(2), Long.MIN_VALUE);
    }

    @Test
    public void difference() {
        dataManager.addAllegroId(1001);
        dataManager.addAllegroId(1002);
        dataManager.addAllegroId(1003);
        dataManager.addAllegroId(1004);

        List<Item> items = new ArrayList<>(6);
        items.add(new Item(1001, "Piasta"));
        items.add(new Item(1002, "Kaseta"));
        items.add(new Item(1003, "Kierownica"));
        items.add(new Item(1004, "Widelec"));
        items.add(new Item(1005, "Rama"));
        items.add(new Item(1006, "Korby"));

        dataManager.difference(items);

        assertEquals(items.size(), 2);
        assertEquals(items.get(0).id, 1005);
        assertEquals(items.get(1).id, 1006);
    }

    @Test
    public void add_filter_get_filters() {
        Filter filter1 = new Filter("keyword");
        Filter filter2 = new Filter("keyword", new Category(1, "test"));
        Filter filter3 = new Filter(new Category(1, "test"), 10, 50);

        dataManager.addFilter(filter1);
        dataManager.addFilter(filter2);
        dataManager.addFilter(filter3);

        List<Filter> filters = dataManager.getFilters();

        assertNotNull(filters);
        assertEquals(filters.size(), 3);

        assertEquals(filters.get(0).keyword, "keyword");
        assertNull(filters.get(0).category);

        assertEquals(filters.get(1).keyword, "keyword");
        assertNotNull(filters.get(1).category);
        assertEquals(filters.get(1).category.id, 1);
        assertEquals(filters.get(1).category.name, "test");

        assertNull(filters.get(2).keyword);
        assertNotNull(filters.get(2).category);
        assertEquals(filters.get(2).category.id, 1);
        assertEquals(filters.get(2).category.name, "test");
        assertEquals((int)filters.get(2).priceMin, 10);
        assertEquals((int)filters.get(2).priceMax, 50);
    }

    @Test
    public void delete_filter() {
        Filter filter1 = new Filter("keyword");
        Filter filter2 = new Filter("keyword", new Category(1, "test"));
        Filter filter3 = new Filter(new Category(1, "test"), 10, 50);

        dataManager.addFilter(filter1);
        dataManager.addFilter(filter2);
        dataManager.addFilter(filter3);

        List<Filter> filters = dataManager.getFilters();
        dataManager.deleteFilter(filters.get(1).storageId);

        filters = dataManager.getFilters();
        assertEquals(filters.size(), 2);
    }

    @Test
    public void load_initial_filters() {

        assertEquals(dataManager.getFilters().size(), 0);

        dataManager.loadInitialFilters();

        assertEquals(dataManager.getFilters().size(), 20);

        dataManager.loadInitialFilters();

        assertEquals(dataManager.getFilters().size(), 20);
    }

    @Test
    public void has_categories_loaded() {

        assertFalse(dataManager.hasCategoriesLoaded());
    }

    @Test
    public void save_categories() {
        List<Category> categories = getMockCategories();

        dataManager.saveCategories(categories);

        assertTrue(dataManager.hasCategoriesLoaded());
    }

    @Test
    public void get_categories() {
        List<Category> categoriesIn = getMockCategories();
        dataManager.saveCategories(categoriesIn);

        List<com.allegrowatcher.Category> categoriesOut = dataManager.getCategories();

        assertEquals(categoriesOut.size(), 4);
    }

    private List<Category> getMockCategories() {
        List<Category> categories = new ArrayList<>(4);
        categories.add(new Category(1, "One"));
        categories.add(new Category(2, "Two"));
        categories.add(new Category(3, "Three"));
        categories.add(new Category(4, "Four"));
        return categories;
    }
}