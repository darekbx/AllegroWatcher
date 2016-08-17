package com.allegrowatcher;

import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.SoapMethods;
import com.allegrowatcher.service.enums.StartingTime;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SoapServiceTest {

    @Test
    public void do_get_items_list_request_category() throws Exception {
        List<Item> items = SoapMethods.doGetItemsListRequest(16693, 0, 200, StartingTime.DAYS_7);
        assertNotNull(items);
        assertNotEquals(items.size(), 0);
    }

    @Test
    public void do_get_items_list_request_keyword() throws Exception {
        List<Item> items = SoapMethods.doGetItemsListRequest(16414, "trialowy");
        assertNotNull(items);
        assertNotEquals(items.size(), 0);
    }
}