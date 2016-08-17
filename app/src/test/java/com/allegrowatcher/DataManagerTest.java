package com.allegrowatcher;

import com.allegrowatcher.db.DataManager;
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
        dataManager = new DataManager();
    }

    @Test
    public void add_allegro_id() {
        dataManager.addAllegroId(RuntimeEnvironment.application, 1001);
        dataManager.addAllegroId(RuntimeEnvironment.application, Long.MAX_VALUE);
        dataManager.addAllegroId(RuntimeEnvironment.application, Long.MIN_VALUE);

        List<Long> allegroIds = dataManager.getStoredIds(RuntimeEnvironment.application);

        assertEquals(allegroIds.size(), 3);
        assertEquals((long)allegroIds.get(0), 1001);
        assertEquals((long)allegroIds.get(1), Long.MAX_VALUE);
        assertEquals((long)allegroIds.get(2), Long.MIN_VALUE);
    }

    @Test
    public void difference() {
        dataManager.addAllegroId(RuntimeEnvironment.application, 1001);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1002);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1003);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1004);

        List<Item> items = new ArrayList<>(6);
        items.add(new Item(1001, "Piasta"));
        items.add(new Item(1002, "Kaseta"));
        items.add(new Item(1003, "Kierownica"));
        items.add(new Item(1004, "Widelec"));
        items.add(new Item(1005, "Rama"));
        items.add(new Item(1006, "Korby"));

        dataManager.difference(RuntimeEnvironment.application, items);

        assertEquals(items.size(), 2);
        assertEquals(items.get(0).id, 1005);
        assertEquals(items.get(1).id, 1006);
    }
}