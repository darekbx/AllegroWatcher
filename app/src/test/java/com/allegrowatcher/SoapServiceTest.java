package com.allegrowatcher;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.SoapMethods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SoapServiceTest {

    @Test
    public void do_get_items_list_request_category() throws Exception {
        List<Item> items = SoapMethods.doGetItemsListRequest(new Filter(new Category(16693, "test"), 0, 200));
        assertNotNull(items);
        assertNotEquals(items.size(), 0);
    }

    @Test
    public void do_get_items_list_request_keyword() throws Exception {
        List<Item> items = SoapMethods.doGetItemsListRequest(new Filter("trialowy", new Category(16414, "test")));
        assertNotNull(items);
        assertNotEquals(items.size(), 0);
    }

    @Test
    public void do_get_cats_data_request() {
        List<Category> categories = SoapMethods.doGetCatsDataRequest();

        assertNotNull(categories);
        assertNotEquals(categories.size(), 0);
    }
}